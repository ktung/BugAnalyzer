package com.rimel.requests;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

/**
 * Class generating all requests to external API
 * Created by lpotages on 24/01/17.
 */

public class RequestGenerator {

    /**
     * Getting all isssues for a given repository
     * @param url Format: /:owner/:repo
     */
    public HttpResponse<JsonNode> requestIsssues(String url){
        try {
            HttpResponse<JsonNode> response =
                    Unirest.get("https://api.github.com/repos"+ url +"/issues")
                            .queryString("labels","bug")
                            .queryString("state","closed")
                            .asJson();

            return response;
        }catch(Exception e){
            System.err.println(e);
        }

        return null;
    }

    /**
     * Retrieve all Events for a given Repository
     * @param url Url of the repository
     * @return JSONNode request
     */
    public HttpResponse<JsonNode> requestEvents(String url){
        try {
            return Unirest.get(url)
                    .asJson();
        }catch(Exception e){
            System.err.println(e);
        }

        return null;
    }
}
