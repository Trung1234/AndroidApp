package com.betterlife.pronunciationjourney;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Administrator on 26/02/2018.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("WordPopular.realm")
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
