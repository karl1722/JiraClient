package primary;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.json.Json;
import javax.json.JsonObject;

public class jiraRestClient {

    public final  String username = "admin";
    public final String password = "admin";
    public final String jiraBaseUrl = "http://localhost:8080/rest/api/2";

    private HttpURLConnection connect(String parameters) throws  IOException{

        URL url = new URL(jiraBaseUrl + "/" + parameters);
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

    private HttpURLConnection connect() throws  IOException {

        return connect("");
    }

    private String getProjectIdFromKey(String jiraProjectKey) throws IOException, JSONException{
        HttpURLConnection connection = connect("project");
        connection.setRequestMethod("GET");

        //to execute the request
        int status = connection.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JSONArray jsonArray = new JSONArray(content.toString());
        Map projectsMap = new HashMap();


        for (int i=0; i < jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String projectKey = jsonObject.get("key").toString();
            String projectId = jsonObject.get("id").toString();
            projectsMap.put(projectKey, projectId);
        }

        String projectId = projectsMap.get(jiraProjectKey).toString();

        return projectId;
    }

    public jiraRestClient()  throws IOException {
        //do nothing
    }


    public void createJiraTicket(String projectKey, String issueType) throws IOException, JSONException {
        String projectId = getProjectIdFromKey(projectKey);

        HttpURLConnection connection = connect("issue");

        connection.setRequestMethod("POST");

        JsonObject jsonBody = Json.createObjectBuilder()
                .add("fields",
                        Json.createObjectBuilder().add("project",
                                Json.createObjectBuilder().add("id", projectId))
                                .add("summary", "Test issue")
                                .add("issuetype",
                                        Json.createObjectBuilder().add("name", issueType))
                ).build();

        String encodedData = jsonBody.toString();
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
        HttpURLConnection connection = connect("issue/" + issueKey + "/transitions");
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

        HttpURLConnection connection = connect(issueKey + "/comment");
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
