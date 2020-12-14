package com.company;

public class Observer {
    private String url;

    public Observer(String url) {
        this.url = url;
    }
    public void update() {
        System.out.println("Observer: " + url + " was modified");
    }
    public String getUrl() {
        return url;
    }
    public String toString() {
        return " Observer: " + url;
    }
}