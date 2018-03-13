package primary;

abstract class jiraTicket {

    String assignee;
    String summary;


    public String getSummary() {
        return summary;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

}
