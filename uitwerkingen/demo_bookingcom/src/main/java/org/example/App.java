package org.example;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class App 
{
    public static void main( String[] args ) throws UnirestException {
        HttpResponse<String> response = Unirest.get("https://booking-com.p.rapidapi.com/v1/hotels/locations?name=Nijmegen&locale=en-gb")
                .header("X-RapidAPI-Key", "cbb15196e0mshb44c607aede1353p17d241jsnd8ce2f2aed4d")
                .header("X-RapidAPI-Host", "booking-com.p.rapidapi.com").asString();
        System.out.println(response.getBody());
    }
}
