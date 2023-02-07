package br.com.ada.georg.filehandler.orchestrators;

import java.io.IOException;
import java.net.URL;

public class URLHandler {
    private URL url;

    public boolean assignURL(String url) {
        try {
            this.url = new URL(url);
            return true;
        } catch (IOException e) {
            System.out.println("Invalid URL");
            return false;
        }
    }

    public URL getUrl() {
        return url;
    }
}
