package com.company;
import java.net.URLConnection;
import java.util.Date;

public class UrlAddressState {
    private Date timeModification;
    private String URL;
    private URLConnection urlConnection;

    public UrlAddressState(String URL,URLConnection urlConnection) {
        this.URL = URL;
        this.urlConnection = urlConnection;
    }
    public void setTime(long time) {
        timeModification = new Date(time);
    }
    public Date getTime() {
        return timeModification;
    }
    public URLConnection getConnection() {
        return urlConnection;
    }
    public String urlAsString() {
        return URL;
    }
    public static boolean checkUrlPresence(UrlAddressState[] urlAddressStates, String url) {
        for (UrlAddressState u: urlAddressStates) {
            if(u.urlAsString().equals(url)) return true;
        } return false;
    }
    public String toString() {
        return " Url: " + URL + ", last time modification: " + timeModification;
    }
}