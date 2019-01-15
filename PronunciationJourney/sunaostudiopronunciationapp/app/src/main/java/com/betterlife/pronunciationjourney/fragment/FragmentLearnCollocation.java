package com.betterlife.pronunciationjourney.fragment;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.betterlife.pronunciationjourney.R;
import com.betterlife.pronunciationjourney.model.PopularCollocationObject;
import com.betterlife.pronunciationjourney.utils.Constants;
import com.betterlife.pronunciationjourney.view.LearnCollocationAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by admin on 3/11/2018.
 */

public class FragmentLearnCollocation extends Fragment implements LearnCollocationAdapter.OnItemClick{
    private String url;
    private View view;
    private RecyclerView recyclerView;
    private ProgressDialog dialog;
    private LearnCollocationAdapter mAdapter;
    private List<PopularCollocationObject> objectList;

    private MediaPlayer mMediaPlayer;
    private TextToSpeech mTextToSpeech;

    public FragmentLearnCollocation() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_learn_collocation, container, false);

        recyclerView = view.findViewById(R.id.listCollo);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        objectList = new ArrayList<>();
        dialog = new ProgressDialog(getActivity());
        mAdapter = new LearnCollocationAdapter(objectList, getContext());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTextToSpeech=new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    mTextToSpeech.setLanguage(Locale.UK);
                }
            }
        });
        mMediaPlayer = new MediaPlayer();
        Bundle bundle=  getArguments();
        if(bundle!=null){
            String input = bundle.getString(Constants.BUNDLE_Collocation);
            Log.d("InputLink",input);
            url = Constants.WEB_HOCTIENGANH+input.substring(2,input.length());
            Log.d("CollocationWebURL",url+"");
        }
        LoadData loadData = new LoadData();
        loadData.execute(url);
    }

    @Override
    public void onClickListener(int position, View view) {
        Toast.makeText(getActivity(),"Click item "+position,Toast.LENGTH_SHORT).show();
        try {
            mMediaPlayer.setDataSource(objectList.get(position).getUrl());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("MediaPlayer Error",e.getMessage());
            mTextToSpeech.speak(objectList.get(position).getTitle(),TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mTextToSpeech!=null){
            mTextToSpeech.stop();
            mTextToSpeech.shutdown();
        }
    }

    private class LoadData extends AsyncTask<String, Void, List<PopularCollocationObject>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog.setMessage(Constants.DIALOG_MESSASE);
            dialog.show();
        }

        @Override
        protected List<PopularCollocationObject> doInBackground(String... strings) {
            Document doc = null;
            Log.e("logg", "load data source " + strings[0]);
            try {
                doc = Jsoup.connect(strings[0]).get();
                if (doc != null) {

                    Element home = doc.getElementsByClass("congso-post").first();
                    Elements tagA = home.getElementsByTag("p");
                    for (int i = 0; i < tagA.size(); i++) {

                        String link = tagA.get(i).getElementsByTag("a").attr("href");
                        String title = tagA.get(i).getElementsByTag("a").text();
                        //Add to list
                        if(link.length()<2 || title.length()<2 || !link.contains("mp3")){
                            continue;
                        }else {
                            objectList.add(new PopularCollocationObject(title, 0, link));
                        }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("logg", e.getMessage());
            }
            return objectList;

        }

        @Override
        protected void onPostExecute(List<PopularCollocationObject> popularCollocationObjects) {
            super.onPostExecute(popularCollocationObjects);
            dialog.dismiss();
            mAdapter = new LearnCollocationAdapter(objectList, getContext());
            mAdapter.setClickListener(FragmentLearnCollocation.this);
            mAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(mAdapter);
        }

    }
}