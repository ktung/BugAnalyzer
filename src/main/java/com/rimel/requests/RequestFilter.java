package com.rimel.requests;

import com.mashape.unirest.http.JsonNode;
import com.rimel.models.EventType;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class filtering all entering requests to retrieve essential informations
 * Created by lpotages on 24/01/17.
 */

public class RequestFilter {

    public Map<String,String> retrieveEventUrl(JsonNode node){
        JSONArray allIssues = node.getArray();

        // First = Title of the debug  // Second = CommitLink
        Map<String, String> commitEvents = new HashMap<String, String>();

        for(int i = 0; i < allIssues.length(); i++){
            String title = allIssues.getJSONObject(i).get("title").toString();
            String eventUrl = allIssues.getJSONObject(i).get("events_url").toString();

            commitEvents.put(title, eventUrl);
        }

        return commitEvents;
    }

    public List<EventType> filterCommitEvents(JsonNode node){
        JSONArray events = node.getArray();

        List<EventType> eventTypes = new ArrayList<EventType>();

        for(int i = 0; i < events.length(); i++){
            String commitId = events.getJSONObject(i).get("commit_id").toString();
            if(!"null".equals(commitId)){
                String commiterLogin = events.getJSONObject(i).getJSONObject("actor").get("login").toString();
                String createdAt = events.getJSONObject(i).get("created_at").toString();

                EventType event = new EventType();
                event.setCommiterLogin(commiterLogin);
                event.setCommitId(commitId);
                event.setCreatedAt(createdAt);
                eventTypes.add(event);
            }
        }

        return eventTypes;
    }
}
