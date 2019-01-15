package com.betterlife.pronunciationjourney.utils;

import android.app.Activity;
import android.media.MediaPlayer;

import com.betterlife.pronunciationjourney.R;

/**
 * Created by SON on 2/12/2016.
 */
public class SoundEffect implements MediaPlayer.OnPreparedListener,MediaPlayer.OnCompletionListener{
    public static SoundEffect instance;
    public static SoundEffect getInstance(){
        if(instance==null){
            instance=new SoundEffect();
        }
        return instance;
    }

    public void playRightAnswerSound(Activity activity, MediaPlayer mediaPlayer){
        if(mediaPlayer!=null){
            mediaPlayer.release();
        }
        mediaPlayer= MediaPlayer.create(activity, R.raw.right_answer_pronunciation);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
    }

    public void playWrongAnswerSound(Activity activity, MediaPlayer mediaPlayer){
        if(mediaPlayer!=null){
            mediaPlayer.release();
        }
        mediaPlayer= MediaPlayer.create(activity, R.raw.wrong_answer_pronuncation);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.release();
    }
}
