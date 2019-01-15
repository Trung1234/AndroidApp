package com.betterlife.pronunciationjourney.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by HauDo on 3/2/2018.
 */

public class WordObject extends RealmObject implements Serializable {

    @PrimaryKey
    @Required
    private String word;

    private String splelling;
    private String mean;
    private String example;
    private String urlAudio;

    public WordObject() {
    }

    public WordObject(String word, String splelling, String mean, String example, String url) {
        this.word = word;
        this.splelling = splelling;
        this.mean = mean;
        this.example = example;
        this.urlAudio = url;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSplelling() {
        return splelling;
    }

    public void setSplelling(String splelling) {
        this.splelling = splelling;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getUrlAudio() {
        return urlAudio;
    }

    public void setUrlAudio(String urlAudio) {
        this.urlAudio = urlAudio;
    }
}
