package com.nexgrid_fsp.myapplication.thumbil.Style;

import android.content.Context;

import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Thumbnail_sub extends LinearLayout
{


    public LinearLayout lineC_background_color;

    public TextView  title_num              ;
    public TextView  name                   ;
    public TextView  gender_age             ;
    public ImageView profile_img            ;
    public ImageView server_back_img        ;
    public TextView  start_date             ;
    public TextView  end_date               ;
    public TextView  survival_gps           ;
    public TextView  people_title           ;
    public ConstraintLayout view_off        ;

    public TextView in;

    public ConstraintLayout title_tab_box;
    public LinearLayout     date_tab_box;
    public TextView start_date_box;
    public TextView end_date_box;
    public TextView survival_gps_box;

    public WebView WebText_View;
    public WebSettings webSettings;
    public ImageView off_img;


    // 이미지형 리스트 부모

    public Thumbnail_sub(Context context)
    {
        super(context);


    }

    public Thumbnail_sub(Context context , AttributeSet attributeSet)
    {
        super(context,attributeSet);
    }


}
