package com.nexgrid_fsp.myapplication.sharinglive;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nexgrid_fsp.myapplication.R;
import com.nexgrid_fsp.myapplication.log.LogService;

import org.json.JSONException;
import org.json.JSONObject;


import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.File_URL;


public class ScreenImageDataCall
{

    public static String screenOffDateString;
    public static String screenOffImageDataReturn;
    public static String beforeScreenString;


    public static String screenDataCall(Context context, JSONObject resBody) {


        try {

            if ( resBody != null &&resBody.getString("screen_save_image_v").toString().equals(screenOffDateString) == false) {
                screenOffDateString = resBody.getString("screen_save_image_v").toString();

                Log.i("TAG","screenOffDateString:"+screenOffDateString);

                if (screenOffDateString.equals("")){
                    // 이미지가 없을경우 빈값 리턴하여 value 이미지 보냄
                    screenOffImageDataReturn = "";
                }else {
                    screenOffImageDataReturn = ScreenImageDataDownload.test(context, File_URL+screenOffDateString);
                }

                LogService.info("이미지 다운로드 완료");

                return screenOffImageDataReturn;

            } else {
//                Log.i("EAST","off 사진이 들어 있음");
                LogService.info("off 사진이 들어 있음");
                // 월레 있던 데이터 그대로 리턴
                return screenOffImageDataReturn;
            }
        } catch (JSONException ex) {
            LogService.error(ex.getMessage(),ex);
        }

        return screenOffImageDataReturn;
    }


    public static void Screen_Glide(Context context, String FileAddress, ImageView screen_img)
    {

        Activity activity = (Activity)context;
        if (activity.isFinishing())
        {
            return;
        }

        // 이전 서버 이미지 스트링과 현재 서버 이미지 스트링 값이 다를 경우
        LogService.info("==============================================");
        LogService.info(screenOffDateString);
        LogService.info(beforeScreenString);
        LogService.info(FileAddress);
        LogService.info("==============================================");

        if (FileAddress == null || FileAddress.equals(""))
        {
            // 기본 이미지
            if(screenOffDateString.equals(beforeScreenString) == false)
            {
                return;
            }
        }
        else
        {
            if(screenOffDateString.equals(beforeScreenString) == false)
            {
                Glide.with(context)
                        .load(FileAddress)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(screen_img);

                beforeScreenString = screenOffDateString;
            }

            else
            {
                Glide.with(context)
                        .load(FileAddress)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(false)
                        .into(screen_img);
            }
        }

    }

}
