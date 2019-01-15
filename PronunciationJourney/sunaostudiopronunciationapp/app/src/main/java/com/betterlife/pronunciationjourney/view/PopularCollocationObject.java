package com.betterlife.pronunciationjourney.view;

import com.betterlife.pronunciationjourney.model.PopularWordObject;

/**
 * Created by admin on 3/7/2018.
 */

public class PopularCollocationObject extends PopularWordObject {
//    public PopularCollocationObject() {
//    }

    public PopularCollocationObject(String title, int progress, String url) {
        super(title, progress, url);
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public int getProgress() {
        return super.getProgress();
    }

    @Override
    public void setProgress(int progress) {
        super.setProgress(progress);
    }

    @Override
    public String getUrl() {
        return super.getUrl();
    }

    @Override
    public void setUrl(String url) {
        super.setUrl(url);
    }
}
