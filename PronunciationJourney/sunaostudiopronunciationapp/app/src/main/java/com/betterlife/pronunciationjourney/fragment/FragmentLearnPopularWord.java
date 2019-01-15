package com.betterlife.pronunciationjourney.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betterlife.pronunciationjourney.R;
import com.betterlife.pronunciationjourney.model.WordObject;
import com.betterlife.pronunciationjourney.utils.Constants;
import com.betterlife.pronunciationjourney.view.ViewpagerAdapter;

import java.util.List;

import haudo.com.viewpagerindicator.IndicatorView;
import io.realm.Realm;

/**
 * Created by HauDo on 2/27/2018.
 */

public class FragmentLearnPopularWord extends Fragment {

    private View mView;
    private static List<WordObject> wordObjectReceive;
    private ViewPager viewPager;
    private IndicatorView indicatorView;
    private ViewpagerAdapter adapter;

    public static FragmentLearnPopularWord newInstance(List<WordObject> wordObject) {
        wordObjectReceive = wordObject;
        return new FragmentLearnPopularWord();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_learn_word, container, false);

        viewPager = mView.findViewById(R.id.viewPager);
        indicatorView = mView.findViewById(R.id.indicator);

        adapter = new ViewpagerAdapter(getFragmentManager());

        setupViewPager();

        return mView;
    }

    private void setupViewPager() {
        for (int i = 0; i < 12; i++) {
            FragmentItemWord fragmentItemWord = new FragmentItemWord();
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.BUNDLE_WORD_POPULAR, wordObjectReceive.get(i));
            fragmentItemWord.setArguments(bundle);
            adapter.addFragment(fragmentItemWord);
            Log.e("logg", wordObjectReceive.get(i).getWord());
        }

        viewPager.setAdapter(adapter);
        indicatorView.setViewPager(viewPager);
    }
}
