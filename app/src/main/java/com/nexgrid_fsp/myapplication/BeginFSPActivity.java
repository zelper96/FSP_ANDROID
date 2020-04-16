package com.nexgrid_fsp.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

import com.nexgrid_fsp.myapplication.thumbil.Style.Thumbnail_sub;
import com.nexgrid_fsp.myapplication.vo.SharingVO;
import com.nexgrid_fsp.myapplication.log.LogService;

import static com.nexgrid_fsp.myapplication.sharinglive.ModuleDataReturn.color_arr;

public class BeginFSPActivity extends AppCompatActivity {







    public void hideNavigationBar() {

        // 위,아래 바 전부 삭제

        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        boolean isImmersiveModeEnabled =
                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
//            Log.d(TAG, "Turning immersive mode mode off. ");
        } else {
//            Log.d(TAG, "Turning immersive mode mode on.");
        }
        newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }

    public void ScreenWidht(AppCompatActivity activity)
    {
        // 세로형 widht 맞추기 위해 : widht 560

        DisplayMetrics metrics = new DisplayMetrics();

    /*-------------------------------
    해상동를 640 * 360으로 맞추기
    ------------------------------*/
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //float magH  = metrics.heightPixels / (1* 640.0f);
        //float magH = metrics.heightPixels / (1 * 560.0f); //원래는 640이지만 가로 비율로 무조건 맞추기 위해서 바꾸었음
        // float magW = metrics.widthPixels / (1 * 360.0f);

        float mag = 1.0f;
        //if (magH < magW) {
        mag = metrics.widthPixels / (1 * 560.0f);
        //} else {
        //    mag = magW;
        //}

        metrics.density = mag;
        //metrics.densityDpi = 240;
        //metrics.heightPixels = 1104;
        // metrics.widthPixels = 1920;
        metrics.scaledDensity = mag;
        //metrics.xdpi = 254.0f;
        //metrics.ydpi = 254.0f;
        activity.getResources().getDisplayMetrics().setTo(metrics);
    }

    public void ScreenHeight(AppCompatActivity activity)
    {
        // 가로형 Height 맞추기 위해 : Height 560

        DisplayMetrics metrics = new DisplayMetrics();

    /*-------------------------------
    해상동를 640 * 360으로 맞추기
    ------------------------------*/
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //float magH  = metrics.heightPixels / (1* 640.0f);
        //float magH = metrics.heightPixels / (1 * 560.0f); //원래는 640이지만 가로 비율로 무조건 맞추기 위해서 바꾸었음
        // float magW = metrics.widthPixels / (1 * 360.0f);

        float mag = 1.0f;
        //if (magH < magW) {
        mag = metrics.heightPixels / (1 * 560.0f);
        //} else {
        //    mag = magW;
        //}

        metrics.density = mag;
        //metrics.densityDpi = 240;
        //metrics.heightPixels = 1104;
        // metrics.widthPixels = 1920;
        metrics.scaledDensity = mag;
        //metrics.xdpi = 254.0f;
        //metrics.ydpi = 254.0f;
        activity.getResources().getDisplayMetrics().setTo(metrics);
    }

    public String[] Color_AllChange(int colorDataindex)
    {

        //color_arr[0][0] = 색상결정
        //color_arr[0][1] = mainC
        //color_arr[0][2] = subC
        //color_arr[0][3] = lineC

        //main Color  = mainC
        //sub  Color  = subC
        //line Color  = lineC

        String color_name = color_arr[colorDataindex][0];
        String mainC = color_arr[colorDataindex][1];
        String subC  = color_arr[colorDataindex][2];
        String lineC = color_arr[colorDataindex][3];

        String[] setColorDate_Change ={color_name,mainC,subC,lineC};
        return setColorDate_Change;

    }

    public void setText_Color(final Thumbnail_sub lyt, final SharingVO data)
    {
        try
        {
            //Text Color 검은 색갈
            lyt.title_num.setTextColor(Color.parseColor(data.getBright_text_Color()));
            lyt.name.setTextColor(Color.parseColor(data.getBright_text_Color()));
            lyt.gender_age.setTextColor(Color.parseColor(data.getBright_text_Color()));
            lyt.in.setTextColor(Color.parseColor(data.getBright_text_Color()));

            lyt.people_title.setTextColor(Color.parseColor(data.getBright_text_Color()));
            lyt.start_date_box.setTextColor(Color.parseColor(data.getBright_text_Color()));
            lyt.end_date_box.setTextColor(Color.parseColor(data.getBright_text_Color()));
            lyt.survival_gps_box.setTextColor(Color.parseColor(data.getBright_text_Color()));


            //Text Color 화이트색갈
            lyt.start_date.setTextColor(Color.parseColor(data.getDark_text_Color()));
            lyt.end_date.setTextColor(Color.parseColor(data.getDark_text_Color()));
            lyt.survival_gps.setTextColor(Color.parseColor(data.getDark_text_Color()));
        }
        catch (Exception ex)
        {
            LogService.error("Color",ex);
        }
    }



}


