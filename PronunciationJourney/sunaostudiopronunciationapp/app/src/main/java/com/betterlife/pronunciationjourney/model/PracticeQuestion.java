package com.betterlife.pronunciationjourney.model;

import com.betterlife.pronunciationjourney.utils.Constants;

import java.util.Arrays;

/**
 * Created by Administrator on 04/03/2018.
 */

public class PracticeQuestion {
    private int mType;
    //1:phat am 1 cau gom 2-3 tu bat ki da hoc
    //2: doc 1 cau khi biet nghia cua cau
    //3: doc 1 cau khi da biet cau tra loi
    //4: doc 1 cau khi da biet cau hoi
    private String mQuestion;
    private String mDescription;
    private String mExplaination;
    private String mResult;
    private String mCombineImportantWord;//Cac tu quan trong cach nhau giau -
    private String mCombineWordNeedPronounce;//cac tu can phat am cho loai cau hoi 1

    public PracticeQuestion(int mType, String mQuestion, String mDescription, String mExplaination,
                            String mResult) {
        this.mType = mType;
        this.mQuestion = mQuestion;
        this.mDescription = mDescription;
        this.mExplaination = mExplaination;
        this.mResult = mResult;
        findImportantWord();
    }

    public PracticeQuestion() {
    }

    public int getType() {
        return mType;
    }

    public void setType(int mType) {
        this.mType = mType;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getExplaination() {
        return mExplaination;
    }

    public void setExplaination(String mExplaination) {
        this.mExplaination = mExplaination;
    }

    public String getResult() {
        return mResult;
    }

    public String getmCombineWordNeedPronounce() {
        return mCombineWordNeedPronounce;
    }

    public void setmCombineWordNeedPronounce(String mCombineWordNeedPronounce) {
        this.mCombineWordNeedPronounce = mCombineWordNeedPronounce;
        String[] allWord = mCombineWordNeedPronounce.split("-");
        StringBuffer question = new StringBuffer("");
        for(String word :allWord){
            question.append(word).append(" ");
        }
        setQuestion(question.toString());
    }

    public void setResult(String mResult) {
        this.mResult = mResult;
        findImportantWord();
    }

    public String getCombineImportantWord() {
        return mCombineImportantWord;
    }

    public void findImportantWord(){
        String[] allWord = mResult.split(" ");
        mCombineImportantWord = "";
        for(String word : allWord){
            if(Arrays.asList(Constants.SIMPLE_WORD).contains(word.toLowerCase()) ||
                    word.length()<=2){
                continue;
            }else {
                mCombineImportantWord+="-"+word;
            }
        }

    }

    public String getHint() {
        String hint=mResult;
        String[] allWord = mResult.split(" ");
        for(String word : allWord){
            if(Arrays.asList(Constants.SIMPLE_WORD).contains(word.toLowerCase()) ||
                    word.length()<=2){
                continue;
            }else {
                hint = hint.replace(word,"_____");
            }
        }
        return hint;
    }
}
