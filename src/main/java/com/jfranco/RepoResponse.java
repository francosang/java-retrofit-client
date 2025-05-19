package com.jfranco;

public class RepoResponse {

    private String id;
    private String htmlUrl;
    private String fullName;

    RepoResponse(String id, String htmlUrl, String fullName) {
        this.id = id;
        this.htmlUrl = htmlUrl;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "ResponseGithub{" +
                "id='" + id + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

}
