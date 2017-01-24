package com.rimel;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.rimel.models.EventType;
import com.rimel.requests.RequestFilter;
import com.rimel.requests.RequestGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {
        RequestGenerator generator = new RequestGenerator();
        RequestFilter filter = new RequestFilter();
        Map<String,String> issueList = new HashMap<String, String>();

        List<HttpResponse<JsonNode>> repoInfos = generator.requestIssues("/elastic/elasticsearch");
        for (HttpResponse<JsonNode> info : repoInfos) {
            issueList.putAll(filter.retrieveEventUrl(info.getBody()));
        }

        for(String bugName : issueList.keySet()){
            String eventUrl = issueList.get(bugName);

            JsonNode allEvents = generator.requestEvents(eventUrl).getBody();
            List<EventType> bugEvents = filter.filterCommitEvents(allEvents);
            if (0 != bugEvents.size()) {
                System.out.println(bugEvents);
            }
        }
    }
}
