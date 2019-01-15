package com.betterlife.pronunciationjourney.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.betterlife.pronunciationjourney.R;
import com.betterlife.pronunciationjourney.utils.ScreenManager;

/**
 * Created by Administrator on 26/02/2018.
 */

public class FragmentHome extends Fragment implements View.OnClickListener {

    public static final String TAG = "FragmentHome";
    private ImageView btnPopularWord;
    private ImageView btnPractice;
    private ImageView btnPopularCollocation;
    private ImageView btnChallengeGetGift;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        setupView(rootView);
        return rootView;
    }

    private void setupView(View rootView) {
        btnPopularWord = rootView.findViewById(R.id.home_img_popular_word);
        btnPopularWord.setOnClickListener(this);
        btnPractice = rootView.findViewById(R.id.home_img_practice_word);
        btnPractice.setOnClickListener(this);
        btnChallengeGetGift = rootView.findViewById(R.id.home_img_challenge);
        btnChallengeGetGift.setOnClickListener(this);
        btnPopularCollocation = rootView.findViewById(R.id.home_img_popular_collocation);
        btnPopularCollocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_img_popular_word:
                ScreenManager.getInst().openFragmentWithAnimation(getFragmentManager(),
                        R.id.fragment_container, FragmentListPopularWord.newInstance(), true,null);
                break;
            case R.id.home_img_practice_word:
                ScreenManager.getInst().openFragmentWithAnimation(getFragmentManager(),R.id.fragment_container,
                        new FragmentPractice(),true,FragmentPractice.TAG);
                break;
            case R.id.home_img_popular_collocation:
                ScreenManager.getInst().openFragment(getFragmentManager(),
                        R.id.fragment_container, FragmentListPopularCollocation
                                .newInstance(), true);
                break;
            case R.id.home_img_challenge:
                ScreenManager.getInst().openFragmentWithAnimation(getFragmentManager(),R.id.fragment_container,
                        new FragmentChallenge(),true,FragmentChallenge.TAG);
                break;
        }
    }
}
