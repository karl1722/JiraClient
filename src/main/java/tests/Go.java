package tests;

import org.json.JSONArray;
import org.json.JSONException;
import primary.jiraQuestionTicket;
import primary.jiraRestClient;

import java.io.IOException;


public class Go {

    public static void main(String args[]) throws IOException, JSONException {

        jiraRestClient jiraClient = new jiraRestClient();
        //JSONArray result = jiraClient.getProjects();
        jiraQuestionTicket jiraTicket = jiraClient.getJiraQuestionTicketDetails("HEL-1");
    //    jiraClient.createAnswerTicket("EXT-7","admin");
        //jiraClient.createJiraTicket("EXT", "Question");
        //jiraClient.transitionJiraTicket("HOW-3","11");
        //jiraClient.commentOnJiraTicket("HOW-3", "this is a comment");
        int x=1;



    }

}
