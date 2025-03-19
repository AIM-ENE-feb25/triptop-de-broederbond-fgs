package com.github.axaca;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Les3
{
    public static void main(String[] args) throws UnirestException {
        HttpResponse<String> response = Unirest.post("https://triptop-identity.wiremockapi.cloud/login")
                .body("{username: 'edevries', password: '3g2Rw9sT1x'}")
                .asString();

        String body = response.getBody();

        System.out.println(body);

        Pattern pattern = Pattern.compile("\"value\"\\s*:\\s*\"([a-f0-9-]+)\"");
        Matcher matcher = pattern.matcher(body);

        if (matcher.find()) {
            String tokenValue = matcher.group(1);

            HttpResponse<String> response2 = Unirest.post(String.format("https://triptop-identity.wiremockapi.cloud/checkAppAccess?token=%s", tokenValue))
                    .body("{username: 'edevries', application: 'triptop'}")
                    .asString();
            System.out.println(response2.getBody());

        } else {
            System.out.println("Token value not found.");
        }
    }



}
