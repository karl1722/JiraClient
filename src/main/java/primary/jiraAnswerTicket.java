package primary;

import java.util.HashMap;

public class jiraAnswerTicket extends jiraTicket{

    String question;
    String category;

    public jiraAnswerTicket(HashMap<String, String> fieldsMap) {

        this.summary = fieldsMap.get("summary");
        this.question = fieldsMap.get("question");
        this.category = fieldsMap.get("category");
    }
}
