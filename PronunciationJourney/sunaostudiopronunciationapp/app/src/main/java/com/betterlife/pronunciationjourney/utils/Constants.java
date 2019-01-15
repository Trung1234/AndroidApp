package com.betterlife.pronunciationjourney.utils;

import android.os.Environment;

/**
 * Created by HauDo on 3/2/2018.
 */

public class Constants {
    public static final String URL_POPULAR_WORD = "http://www.trumtuvung.com/bo-tu-vung-thong-dung-oxford/page-";
    public static final String BUNDLE_WORD_POPULAR = "WordPopular";
    public static final String FOLDER_APP = Environment.getExternalStorageState() + "/Sunao/";
    public static final String[] SIMPLE_WORD=new String[]{
      "are","was","were","off","can","must","able","should","could","does",
            "she","they","you","may","have","need","all","every","with","some","any",
            "much","many","most","none","the","couldn't","can't"
    };
    public static final String MY_PREF = "my_pref";
    public static final String KEY_CURRENT_SCORE ="KEY_CURRENT_SCORE";
    public static final String WEB_HOCTIENGANH = "http://webhoctienganh.com";
    public static final String URL_POPULAR_COLLOCATION = "http://webhoctienganh.com/35-tinh-huong-giao-tiep-tieng-anh-co-ban-682.html";
    public static final String BUNDLE_Collocation = "CollocationPopular";

    public static final String DIALOG_MESSASE = "Vui lòng chờ";
}
