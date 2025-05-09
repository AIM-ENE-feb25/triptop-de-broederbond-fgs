package persoonlijkeUitwerkingen.StrategyPatternUitwerking;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Paypal implements PaymentMethod {
    public String getToken(){
        try {
            // PayPal Sandbox API Endpoint
            String url = "https://api-3t.sandbox.paypal.com/nvp";

            // PayPal credentials and request parameters
            String params = "USER=" + URLEncoder.encode("sb-e49jx39386118_api1.business.example.com", StandardCharsets.UTF_8) +
                    "&PWD=" + URLEncoder.encode("WPL3HBU6QWFMXVSD", StandardCharsets.UTF_8) +
                    "&SIGNATURE=" + URLEncoder.encode("AjUvjLgeG5Y3dyd381Q2Plw3sIFrA7IXTPlbB-.rj31YePE7ucOm1pKd", StandardCharsets.UTF_8) +
                    "&METHOD=SetExpressCheckout" +
                    "&VERSION=98" +
                    "&PAYMENTREQUEST_0_AMT=10" +
                    "&PAYMENTREQUEST_0_CURRENCYCODE=USD" +
                    "&PAYMENTREQUEST_0_PAYMENTACTION=SALE" +
                    "&cancelUrl=https://example.com/cancel.html" +
                    "&returnUrl=https://example.com/success.html";

            // Create HTTP client
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(params))
                    .build();

            // Send request and get response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

            return response.body();
            // Print response
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void handlePayment() {
        // TODO
    }

    @Override
    public void canclePayment() {
        // TODO
    }
}
