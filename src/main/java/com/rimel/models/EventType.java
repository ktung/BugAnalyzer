package com.rimel.models;

/**
 * Event type
 * Created by lpotages on 24/01/17.
 */

public class EventType {

    private String commiterLogin;
    private String commitId;
    private String createdAt;

    public String getCommiterLogin() {
        return commiterLogin;
    }

    public void setCommiterLogin(String commiterLogin) {
        this.commiterLogin = commiterLogin;
    }

    public String getCommitId() {
        return commitId;
    }

    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "EventType{" +
                "commiterLogin='" + commiterLogin + '\'' +
                ", commitId='" + commitId + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
