package primary;

import java.util.Map;

public class jiraQuestionTicket extends jiraTicket {

    public String getQuestion() {
        return question;
    }

    public String getCategory() {
        return category;
    }

    String question;
    String category;

    public jiraQuestionTicket(Map<String, String> fieldsMap){

        summary = fieldsMap.get("summary");
        question = fieldsMap.get("customfield_10102");
        category = fieldsMap.get("customfield_10103");

    }
}
