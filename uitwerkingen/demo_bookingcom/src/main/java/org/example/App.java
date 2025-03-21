package org.example;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class App 
{
    public static void main( String[] args ) throws UnirestException {
//        HttpResponse<String> response = Unirest.get("https://booking-com.p.rapidapi.com/v1/hotels/locations?name=Nijmegen&locale=en-gb")
//                .header("X-RapidAPI-Key", "cbb15196e0mshb44c607aede1353p17d241jsnd8ce2f2aed4d")
//                .header("X-RapidAPI-Host", "booking-com.p.rapidapi.com").asString();
//        System.out.println(response.getBody());

        String url = "https://triptop-identity.wiremockapi.cloud/login";
        HttpResponse<JsonNode> loginResponse = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(new JSONObject().put("username", "edevries").put("password", "3g2Rw9sT1x"))
                .asJson();
        System.out.println(loginResponse.getBody());

        String url1 = "https://triptop-identity.wiremockapi.cloud/checkAppAccess?token="
        HttpResponse<JsonNode> roleResponse = Unirest.post()
    }
}
