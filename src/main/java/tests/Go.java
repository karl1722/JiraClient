package tests;

import primary.jiraRestClient;

import java.io.IOException;


public class Go {

    public static void main(String args[]) throws IOException {

        jiraRestClient jiraClient = new jiraRestClient();
        //jiraClient.createJiraTicket();
        //jiraClient.transitionJiraTicket("HOW-3","11");
        jiraClient.commentOnJiraTicket("HOW-3", "this is a comment");
        int x=1;



    }

}
