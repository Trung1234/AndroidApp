package com.betterlife.pronunciationjourney.utils;

import android.content.Context;
import android.speech.SpeechRecognizer;

/**
 * Created by Administrator on 04/03/2018.
 */

public class SpeechRecognizerSingleton {
    private static SpeechRecognizer mSpeechRecognizer;

    public static SpeechRecognizer getInstance(Context context){
        if(mSpeechRecognizer == null){
            mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        }
        return mSpeechRecognizer;
    }
}
