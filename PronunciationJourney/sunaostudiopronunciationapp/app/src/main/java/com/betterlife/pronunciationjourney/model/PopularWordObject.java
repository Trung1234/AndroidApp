package com.betterlife.pronunciationjourney.model;

/**
 * Created by HauDo on 2/27/2018.
 */

public class PopularWordObject {

    private String title;
    private int progress;
    private String url;

    public PopularWordObject() {
    }

    public PopularWordObject(String title, int progress, String url) {
        this.title = title;
        this.progress = progress;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
