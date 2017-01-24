package com.rimel;

import com.mashape.unirest.http.JsonNode;
import com.rimel.models.EventType;
import com.rimel.requests.RequestFilter;
import com.rimel.requests.RequestGenerator;
import org.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        RequestGenerator generator = new RequestGenerator();
        RequestFilter filter = new RequestFilter();

        JsonNode repoInfo = generator.requestIsssues("/elastic/elasticsearch").getBody();
        System.out.println(repoInfo);
        Map<String,String> issueList = filter.retrieveEventUrl(repoInfo);

        for(String bugName : issueList.keySet()){
            String eventUrl = issueList.get(bugName);

            JsonNode allEvents = generator.requestEvents(eventUrl).getBody();
            List<EventType> bugEvents = filter.filterCommitEvents(allEvents);
            System.out.println(bugEvents);
        }
    }
}
