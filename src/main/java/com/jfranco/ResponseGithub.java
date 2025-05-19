package com.jfranco;

public class ResponseGithub {

    private String id;
    private String htmlUrl;
    private String full_name;

    ResponseGithub(String id, String htmlUrl, String fullName) {
        this.id = id;
        this.htmlUrl = htmlUrl;
        this.full_name = fullName;
    }

    public String getId() {
        return id;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getFull_name() {
        return full_name;
    }

    @Override
    public String toString() {
        return "ResponseGithub{" +
                "id='" + id + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", fullName='" + full_name + '\'' +
                '}';
    }

}
