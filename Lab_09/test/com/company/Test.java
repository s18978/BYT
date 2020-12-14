package com.company;
import org.junit.Before;
import static org.mockito.Mockito.*;
import java.net.URLConnection;

public class Test {
    private Observer observer; private Observer observer2; private Observer observer3;
    private String url1; private String url2;

    @Before
    public void setUP() {

        url1 = "http://www.pja.edu.pl/";
        url2 = "https://www.apple.com";
        observer = new Observer(url1);
        observer2 = new Observer(url2);
        observer3 = new Observer(url2);
    }

    @org.junit.Test
    public void startTest() {
        URLConnection urlConnection1 = mock(URLConnection.class);
        URLConnection urlConnection2 = mock(URLConnection.class);

        UrlAddressState state1 = new UrlAddressState( url1, urlConnection1);
        state1.setTime(11111111111111L);
        UrlAddressState state2 = new UrlAddressState( url2, urlConnection2);

        when(urlConnection1.getLastModified()).thenReturn(System.nanoTime());
        when(urlConnection2.getLastModified()).thenReturn(System.nanoTime());

        CheckerOfModification modificationChecker = new CheckerOfModification(new UrlAddressState[]{ state1,state2 }
                ,observer, observer2, observer3);
        modificationChecker.startCheck();
    }
}