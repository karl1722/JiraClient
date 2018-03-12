package primary;

import java.util.Map;

public class jiraQuestionTicket extends jiraTicket {

    String question;
    String category;

    public jiraQuestionTicket(Map<String, String> fieldsMap){

        summary = fieldsMap.get("summary");
        question = fieldsMap.get("customfield_10102");
        category = fieldsMap.get("customfield_10103");

    }
}
