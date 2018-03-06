package primary;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

import org.apache.commons.codec.binary.Base64;

import javax.json.Json;
import javax.json.JsonObject;

public class jiraRestClient {

    public final  String username = "admin";
    public final String password = "admin";
    public String urlParameters = "";
    public final String jiraBaseUrl = "http://localhost:8080/rest/api/2/issue";

    private HttpURLConnection connectWithParameters(String parameters) throws  IOException{
        urlParameters = parameters;
        return connect();
    }

    private HttpURLConnection connect() throws IOException {

        URL url = new URL(jiraBaseUrl + "/" + urlParameters);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String userpass = username + ":" + password;
        String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
        connection.setRequestProperty ("Authorization", basicAuth);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        //InputStream in = connection.getInputStream();

        // connection.setRequestMethod("GET");
        return connection;
    }

    public jiraRestClient()  throws IOException {
        //do nothing
    }


    public void createJiraTicket() throws IOException {
        HttpURLConnection connection = connect();
        connection.setRequestMethod("POST");
        String encodedData = getJSON_Body();
        connection.getOutputStream().write(encodedData.getBytes());

        try {
            InputStream inputStream = connection.getInputStream();
            System.out.println(inputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void transitionJiraTicket(String issueKey, String transitionId) throws IOException{

        ///transitions
        HttpURLConnection connection = connectWithParameters(issueKey + "/transitions");
        connection.setRequestMethod("POST");

        JsonObject jsonBody = Json.createObjectBuilder()
                .add("transition",
                        Json.createObjectBuilder().add("id",transitionId)).build();

        String encodedData = jsonBody.toString();
        connection.getOutputStream().write(encodedData.getBytes());

        try {
            InputStream inputStream = connection.getInputStream();
            System.out.println(inputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void commentOnJiraTicket(String issueKey, String comment) throws IOException{

        HttpURLConnection connection = connectWithParameters(issueKey + "/comment");
        connection.setRequestMethod("POST");

        JsonObject jsonBody = Json.createObjectBuilder()
                .add("body",comment).build();

        String encodedData = jsonBody.toString();
        connection.getOutputStream().write(encodedData.getBytes());

        try {
            InputStream inputStream = connection.getInputStream();
            System.out.println(inputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getJSON_Body() {

        JsonObject createIssue = Json.createObjectBuilder()
                .add("fields",
                        Json.createObjectBuilder().add("project",
                                Json.createObjectBuilder().add("key", "HOW"))
                                .add("summary", "Test issue")
                                .add("description", "Test Issue")
                                .add("issuetype",
                                        Json.createObjectBuilder().add("name", "Task"))
                ).build();

        return createIssue.toString();
    }

}
