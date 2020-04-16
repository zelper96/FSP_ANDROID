package com.nexgrid_fsp.myapplication.thumbil.Style;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebSettings;

import com.nexgrid_fsp.myapplication.R;

public class Thumbnail_sub_Multi_V extends Thumbnail_sub
{




    public Thumbnail_sub_Multi_V(Context context)
    {
        super(context);
        init();

    }

    public Thumbnail_sub_Multi_V(Context context , AttributeSet attributeSet)
    {
        super(context,attributeSet);
        init();
    }

    // 이미지형 4개 있을때

    protected void init()
    {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_thumbnail_item_multi_v,this,true);

        lineC_background_color       = findViewById(R.id.lineC_background_color);
        title_num              = findViewById(R.id.title_num);
        name                   = findViewById(R.id.name);
        gender_age             = findViewById(R.id.gender_age);
        profile_img            = findViewById(R.id.profile_img);
        server_back_img        = findViewById(R.id.server_back_img);
        start_date             = findViewById(R.id.start_date);
        end_date               = findViewById(R.id.end_date);
        survival_gps           = findViewById(R.id.survival_gps);
        people_title           = findViewById(R.id.people_title);
        view_off               = findViewById(R.id.view_off);

        off_img = findViewById(R.id.off_img);

        title_tab_box          = findViewById(R.id.title_tab_box);
        date_tab_box           = findViewById(R.id.date_tab_box);
        start_date_box         = findViewById(R.id.start_date_box);
        end_date_box           = findViewById(R.id.end_date_box);
        survival_gps_box       = findViewById(R.id.survival_gps_box);

        WebText_View           = findViewById(R.id.WebText_View);
        WebText_View.setHorizontalScrollBarEnabled(false);
        WebText_View.setVerticalScrollBarEnabled(false);
        webSettings = WebText_View.getSettings();
        webSettings.setJavaScriptEnabled(true);

//        // Initial webview
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setUseWideViewPort(true);       // wide viewport를 사용하도록 설정
        webSettings.setLoadWithOverviewMode(true);

    }


}
