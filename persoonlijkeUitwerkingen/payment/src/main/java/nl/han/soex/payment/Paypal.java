package nl.han.soex.payment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Timer;
import java.util.TimerTask;

public class Paypal implements PaymentMethod {
    private final String CLIENT_ID = "AV6s_s3Y8_xMz8c-PSMCVUYCeIMyWKGegOMRSBd1HUZXiJ5bxT9F-59brHGfHqllEgRQo24-ePNBzIkR";
    private final String CLIENT_SECRET = "EJl6gMBKY7PRx1DHePDxgxjJETz9wgVEhVjiffZ4IvbvcvpTcdBjOrOpDWEQB7nxK4LK_-_9ShHC0lav";

    private final int MAX_TRIES = 20;

    private String getToken(){
        try {
            String url = "https://api-m.sandbox.paypal.com/v1/oauth2/token";

            String auth = CLIENT_ID + ":" + CLIENT_SECRET;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Authorization", "Basic " + encodedAuth)
                    .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject json = new JSONObject(response.body());

            return json.getString("access_token");
        } catch (IOException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void handlePayment() throws InterruptedException {
        String token = getToken();
        System.out.println(token);

        String paymentId = createPayment(token);
        System.out.println(paymentId);

        // User needs to have agreed to pay before this method is called
        int tries = 0;
        PaymentStatus paymentStatus = PaymentStatus.PENDING;
        while(paymentStatus == PaymentStatus.PENDING && tries < MAX_TRIES) {
            System.out.println("Checking payment status...");
            String status = capturePayment(token, paymentId);
            if(status == null){
                System.out.println("Payment not accepted yet!");
                // wait a few seconds before checking again
                Thread.sleep(5000);
                tries++;
                continue;
            }
            if(status.equals("COMPLETED")){
                paymentStatus = PaymentStatus.PAID;
                System.out.println("Payment completed!");
                continue;
            }
            if(tries == MAX_TRIES - 1){
                System.out.println("Payment failed!");
                paymentStatus = PaymentStatus.CANCELLED;
                break;
            }
        }
    }

    @Override
    public void canclePayment() {
        // TODO
    }

    private String createPayment(String token){
        String url = "https://api-m.sandbox.paypal.com/v2/checkout/orders/";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString("{\"intent\": \"CAPTURE\",\"purchase_units\": " +
                        "[{\"amount\": {\"currency_code\": \"EUR\",\"value\": \"100.00\"}}]}"))
                .build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            JSONObject json = new JSONObject(response.body());
            return json.getString("id");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // we need to capture the payment to complete the transaction
    private String capturePayment(String token, String paymentId){
        String url = "https://api-m.sandbox.paypal.com/v2/checkout/orders/" + paymentId +"/capture";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject json = new JSONObject(response.body());
            return json.getString("status");
        } catch (IOException | InterruptedException | JSONException e) {
            return null;
        }
    }
}
