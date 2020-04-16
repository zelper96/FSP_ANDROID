package com.nexgrid_fsp.myapplication.sharinglive;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nexgrid_fsp.myapplication.log.LogService;

import org.json.JSONException;
import org.json.JSONObject;




import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.File_URL;
import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.getScreen_img;


// Delete 예정
public class ScreenImageUiSet {
    //View off 되었을 경우 Glide 실행됨
    //server screen image 사진 다운 로드 후 내부 저장소에 저장 한다.
    // 저장후 Glide 로 보여준다.
    //server 데이터 동일한지 확인 string


    public  static  String before_screen_string = "";
        public static String screen_string;

//    public static String screenDataCall(Context context, JSONObject resBody)
//    {
//
//        try {
//            if ( resBody != null &&resBody.getString("screen_save_image_v").toString().equals(screen_string) == false)
//            {
//                screen_string = resBody.getString("screen_save_image_v").toString();
//
//
//                getScreen_img = ScreenImageDataDownload.test(context, File_URL+screen_string);
//
//                before_screen_string = screen_string;
//
//                return getScreen_img;
//                // 이미지 사진 다운로드 완료
//            }
//            else
//            {
//                // 이미지 사진 변경 되지않음
//            }
//        }
//        catch (JSONException ex) {
//            LogService.error(ex.getMessage(),ex);
//        }
//
//        return getScreen_img;
//    }

//    public static void Screen_Glide(Context context, String FileAddress, ImageView screen_img)
//    {
//
//        Activity activity = (Activity)context;
//        if (activity.isFinishing())
//        {
//            return;
//        }
//
//        // 이전 서버 이미지 스트링과 현재 서버 이미지 스트링 값이 다를 경우
//
//        LogService.info("==============================================");
//        LogService.info(screen_string);
//        LogService.info(before_screen_string);
//        LogService.info(FileAddress);
//        LogService.info("==============================================");
//
//        if (FileAddress == null || FileAddress.equals(""))
//        {
//            // 기본 이미지
//            if(screen_string.equals(before_screen_string) == false)
//            {
//                return;
//            }
//        }
//        else
//        {
//            if(screen_string.equals(before_screen_string) == false)
//            {
//                Glide.with(context)
//                        .load(FileAddress)
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .skipMemoryCache(true)
//                        .into(screen_img);
//
//                before_screen_string = screen_string;
//            }
//
//            else
//            {
//                Glide.with(context)
//                        .load(FileAddress)
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .skipMemoryCache(false)
//                        .into(screen_img);
//            }
//        }
//
//    }


}
