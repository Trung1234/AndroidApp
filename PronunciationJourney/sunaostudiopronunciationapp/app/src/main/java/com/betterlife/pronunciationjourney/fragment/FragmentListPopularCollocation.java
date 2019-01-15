package com.betterlife.pronunciationjourney.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.betterlife.pronunciationjourney.model.PopularCollocationObject;
import com.betterlife.pronunciationjourney.utils.Constants;
import com.betterlife.pronunciationjourney.utils.ScreenManager;
import com.betterlife.pronunciationjourney.view.PopularCollocationAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 3/7/2018.
 */

public class FragmentListPopularCollocation extends Fragment {
    public static int PERSONAL_ID = 1;
    private View view;
    private FragmentLearnCollocation fragmentLearn;
    private RecyclerView recyclerView;
    private ProgressDialog dialog;
    private PopularCollocationAdapter mAdapter;
    private List<PopularCollocationObject> objectList;
    public static FragmentListPopularCollocation newInstance() {
        return new FragmentListPopularCollocation();
    }

//    public FragmentListPopularCollocation() {
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_popular_collocation, container, false);

        recyclerView = view.findViewById(R.id.listColloTopic);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        objectList = new ArrayList<>();
        dialog = new ProgressDialog(getActivity());
        mAdapter = new PopularCollocationAdapter(objectList, getContext());

        LoadData loadData = new LoadData();
        loadData.execute(Constants.URL_POPULAR_COLLOCATION);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter.onItemClickListener(new PopularCollocationAdapter.OnItemClick() {
            @Override
            public void onClickListener(int position, View view) {
                Bundle bundle=new Bundle();
                bundle.putString(Constants.BUNDLE_Collocation, objectList.get(position).getUrl());
                fragmentLearn = new FragmentLearnCollocation();
                fragmentLearn.setArguments(bundle);

                ScreenManager.getInst().openFragment(getFragmentManager(),
                        R.id.fragment_container,
                        fragmentLearn,
                        true);
            }
        });
    }

    private class LoadData extends AsyncTask<String, Void, List<PopularCollocationObject>>{
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
                doc=  Jsoup.connect(strings[0]).get();
                if (doc != null) {

                    Element home = doc.getElementsByClass("main-post-body").first();
                    Elements tagA = home.getElementsByTag("h2");
                    for (int i = 0; i < tagA.size(); i++) {

                        String link = tagA.get(i).getElementsByTag("a").attr("href");
                        String title = tagA.get(i).getElementsByTag("a").text();

                        //Add to list
                        if(link.length()<2){
                            continue;
                        }else {
                            objectList.add(new PopularCollocationObject(title,0,link));
                        }

                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
                Log.e("logg", e.getMessage());
            }
            return objectList;

        }

        @Override
        protected void onPostExecute(List<PopularCollocationObject> popularCollocationObjects) {
            super.onPostExecute(popularCollocationObjects);
            dialog.dismiss();
            mAdapter = new PopularCollocationAdapter(objectList, getContext());
            mAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(mAdapter);
        }

    }
}
