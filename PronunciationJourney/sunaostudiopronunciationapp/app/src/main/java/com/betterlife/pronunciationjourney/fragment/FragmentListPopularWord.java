package com.betterlife.pronunciationjourney.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betterlife.pronunciationjourney.MainActivity;
import com.betterlife.pronunciationjourney.R;
import com.betterlife.pronunciationjourney.model.PopularWordObject;
import com.betterlife.pronunciationjourney.model.WordObject;
import com.betterlife.pronunciationjourney.utils.Constants;
import com.betterlife.pronunciationjourney.utils.ScreenManager;
import com.betterlife.pronunciationjourney.view.PopularWordAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

/**
 * Created by HauDo on 2/27/2018.
 */

public class FragmentListPopularWord extends Fragment {

    private View mView;
    private List<WordObject> listWord;
    private PopularWordAdapter mAdapter;


    public static FragmentListPopularWord newInstance() {
        return new FragmentListPopularWord();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_popular_word, container, false);

        RecyclerView recyclerView = mView.findViewById(R.id.listWordTopic);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        listWord = new ArrayList<>();

        final List<PopularWordObject> wordObjectList = new ArrayList<>();

        for (int i = 1; i < 30; i++) {
            wordObjectList.add(new PopularWordObject(String.valueOf(i), 0, Constants.URL_POPULAR_WORD + i));
        }

        mAdapter = new PopularWordAdapter(wordObjectList, getContext());
        recyclerView.setAdapter(mAdapter);

        mAdapter.onItemClickListener(new PopularWordAdapter.OnItemClick() {
            @Override
            public void onClickListener(int position, View view) {
                new LoadDataSource().execute(wordObjectList.get(position).getUrl());
            }
        });

        return mView;
    }

    private class LoadDataSource extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            Document docs;
            Log.e("logg", "load data source " + strings[0]);
            try {
                docs = Jsoup.connect(strings[0]).get();
                Elements row = docs.select("div.media-body");

                Log.e("logg", "size of data:" + row.size());
                for (Element element : row) {
                    String textBig = element.text();

                    String text = element.getElementsByTag("h4").first().text();
                    String word = text.substring(0, text.indexOf("/"));
                    String spelling = text.substring(text.indexOf("/"), text.length());

                    String mean = textBig.substring(textBig.indexOf("("), textBig.indexOf("Example"));
                    String example = textBig.substring(textBig.indexOf("Example") + 9, textBig.indexOf("\" type"));

                    String url = element.getElementsByClass("margin-top-5").first()
                            .getElementsByTag("audio").first().getElementsByTag("source").attr("src");

                    listWord.add(new WordObject(word, spelling, mean, example, url));

                    Log.e("logg", word + "\n" + spelling + "\n" + mean + "\n" + example + "\n" + url);
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("logg", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            ScreenManager.getInst().openFragment(getFragmentManager(),
                    R.id.fragment_container,
                    FragmentLearnPopularWord.newInstance(listWord),
                    true);

           /* mRealmWord.beginTransaction();

            for (WordObject word : listWord) {
                try {
                    mRealmWord.copyToRealm(word);
                } catch (RealmPrimaryKeyConstraintException exception) {
                    exception.printStackTrace();
                    break;
                }
            }

            mRealmWord.commitTransaction();

            RealmResults<WordObject> list = mRealmWord.where(WordObject.class).findAll();
            Log.e("logg", "size add database: " + list.size());*/

        }
    }
}
