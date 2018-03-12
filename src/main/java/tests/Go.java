package tests;

import org.json.JSONException;
import primary.jiraRestClient;

import java.io.IOException;


public class Go {

    public static void main(String args[]) throws IOException, JSONException {

        jiraRestClient jiraClient = new jiraRestClient();
//        jiraClient.getJiraQuestionTicketDetails("EXT-7");
        jiraClient.createAnswerTicket("EXT-7");
        //jiraClient.createJiraTicket("EXT", "Question");
        //jiraClient.transitionJiraTicket("HOW-3","11");
        //jiraClient.commentOnJiraTicket("HOW-3", "this is a comment");
        int x=1;



    }

}
