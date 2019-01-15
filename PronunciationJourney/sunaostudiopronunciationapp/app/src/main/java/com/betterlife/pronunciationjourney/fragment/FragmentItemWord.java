package com.betterlife.pronunciationjourney.fragment;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.betterlife.pronunciationjourney.R;
import com.betterlife.pronunciationjourney.model.WordObject;
import com.betterlife.pronunciationjourney.utils.Constants;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by HauDo on 3/2/2018.
 */

public class FragmentItemWord extends Fragment implements View.OnClickListener {

    private View mView;
    private TextView txtWord;
    private TextView txtSpelling;
    private TextView txtMean;
    private ImageView imgSpeaker;

    private WordObject wordObject;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.item_learn_word, container, false);

        txtWord = mView.findViewById(R.id.txtWord);
        txtSpelling = mView.findViewById(R.id.txtSpelling);
        txtMean = mView.findViewById(R.id.txtMean);
        imgSpeaker = mView.findViewById(R.id.imgSpeaker);

        imgSpeaker.setOnClickListener(this);

        Bundle bundle = getArguments();
        if (bundle != null) {
            wordObject = (WordObject) bundle.getSerializable(Constants.BUNDLE_WORD_POPULAR);
            if (wordObject != null) {
                txtWord.setText(wordObject.getWord());
                txtSpelling.setText(wordObject.getSplelling());
                txtMean.setText(wordObject.getMean());
                new DownloadFile().execute(wordObject.getUrlAudio(), wordObject.getWord());
            }
        }
        return mView;
    }

    @Override
    public void onClick(View view) {
        String filePath = Constants.FOLDER_APP + wordObject.getWord() + ".mp3";
        Log.e("logg", filePath);
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.e("logg", e.getMessage());
        }

    }

    public class DownloadFile extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            int count;
            try {
                URL url = new URL(params[0]);
                URLConnection conection = url.openConnection();
                conection.connect();

                int lenghtOfFile = conection.getContentLength();
                InputStream input = new BufferedInputStream(url.openStream(),
                        8192);

                OutputStream output = new FileOutputStream(Constants.FOLDER_APP + params[1] + ".mp3");

                byte data[] = new byte[1024];
                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            return null;
        }
    }
}
