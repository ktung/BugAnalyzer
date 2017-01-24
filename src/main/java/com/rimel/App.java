package com.rimel;

import com.jcabi.github.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Github github = new RtGithub("", "");
        Repo repo = github.repos().get(new Coordinates.Simple("elastic/elasticsearch"));

        try {
//            EnumMap<Issues.Qualifier, String> params = new EnumMap<Issues.Qualifier, String>(Issues.Qualifier.class);
//            params.put(Issues.Qualifier.STATE, "closed");
//            params.put(Issues.Qualifier.LABELS, "bug");
//            Iterable<Issue> issues = repo.issues().search(Issues.Sort.CREATED, Search.Order.DESC, params);
            Map<String, String> params = new HashMap<String, String>();
            params.put("state", "closed");
            params.put("labels", "bug");
            Iterable<Issue> issues = repo.issues().iterate(params);
            for (Issue i : issues) {
                Issue.Smart issue = new Issue.Smart(i);
                String issueStr = issue.title()+"|"+
                    issue.createdAt().toString()+"|"+
                    issue.updatedAt().toString();

                Iterable<Event> events = i.events();
                for (Event ev : events) {
                    Event.Smart event = new Event.Smart(ev);
                    if (Event.REFERENCED.equals(event.type())) {
                        String eventStr = "{"+
                            event.author().login() +", "+
                            event.hashCode() +", "+
                            event.createdAt().toString() +
                        "}";

                        issueStr += eventStr;
                    }
                }

                System.out.println(issueStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        RequestGenerator generator = new RequestGenerator();
//        RequestFilter filter = new RequestFilter();
//        Map<String,String> issueList = new HashMap<String, String>();
//
//        List<HttpResponse<JsonNode>> repoInfos = generator.requestIsssues("/elastic/elasticsearch");
//        for (HttpResponse<JsonNode> info : repoInfos) {
//            issueList.putAll(filter.retrieveEventUrl(info.getBody()));
//        }
//
//        for(String bugName : issueList.keySet()){
//            String eventUrl = issueList.get(bugName);
//
//            JsonNode allEvents = generator.requestEvents(eventUrl).getBody();
//            List<EventType> bugEvents = filter.filterCommitEvents(allEvents);
//            System.out.println(bugEvents);
//        }
    }
}
