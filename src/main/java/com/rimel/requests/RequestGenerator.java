package com.rimel.requests;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import java.util.ArrayList;
import java.util.List;

/**
 * Class generating all requests to external API
 * Created by lpotages on 24/01/17.
 */

public class RequestGenerator {

    private static final String TOKEN = "token 4eedafe420633d68e9bed08b213b58586cab64c6";

    /**
     * Getting all isssues for a given repository
     * @param url Format: /:owner/:repo
     */
    public List<HttpResponse<JsonNode>> requestIssues(String url) {
        List<HttpResponse<JsonNode>> list = new ArrayList<HttpResponse<JsonNode>>();
        try {
            HttpResponse<JsonNode> response =
                Unirest.get("https://api.github.com/repos"+ url +"/issues")
                    .header("Authorization", TOKEN)
                    .queryString("labels","bug")
                    .queryString("state","closed")
                    .asJson();
            String link = response.getHeaders().get("Link").toString();
            Integer i1 = link.lastIndexOf("page=");
            Integer i2 = link.lastIndexOf(">");
            Integer lastPage = Integer.parseInt(link.substring(i1+5, i2));
            list.add(response);

            for (int i = 2; i < lastPage; i++) {
                HttpResponse<JsonNode> res =
                    Unirest.get("https://api.github.com/repos"+ url +"/issues")
                        .header("Authorization", TOKEN)
                        .queryString("labels","bug")
                        .queryString("state","closed")
                        .queryString("page", i)
                        .asJson();
                list.add(res);
            }
        } catch(Exception e) {
            System.err.println(e);
        }

        return list;
    }

    /**
     * Retrieve all Events for a given Repository
     * @param url Url of the repository
     * @return JSONNode request
     */
    public HttpResponse<JsonNode> requestEvents(String url){
        try {
            return Unirest.get(url)
                    .header("Authorization", TOKEN)
                    .asJson();
        }catch(Exception e){
            System.err.println(e);
        }

        return null;
    }
}
