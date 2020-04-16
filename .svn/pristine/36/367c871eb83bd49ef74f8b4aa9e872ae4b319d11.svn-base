package com.nexgrid_fsp.myapplication.profile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nexgrid_fsp.myapplication.BeginFSPActivity;
import com.nexgrid_fsp.myapplication.impl.BasicsDataBase;
import com.nexgrid_fsp.myapplication.sharinglive.HttpUtil;
import com.nexgrid_fsp.myapplication.R;
import com.nexgrid_fsp.myapplication.sharinglive.ModuleDataReturn;
import com.nexgrid_fsp.myapplication.sharinglive.ScreenImageDataCall;
import com.nexgrid_fsp.myapplication.sharinglive.RoomsValidation;
import com.nexgrid_fsp.myapplication.log.LogService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;


import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.File_URL;
import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.cereal;
import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.room_url;
import static com.nexgrid_fsp.myapplication.sharinglive.ModuleDataReturn.beforeRoomsInfo;


public class ProfileViewPage extends BeginFSPActivity implements BasicsDataBase {



    private ImageView off_img;
    private ImageView profile_usb_img;
    private ImageView profile_server_img;
    private TextView error_textview;


    //screen url String
    private String screenOffImage;

    // 서버 실행 Main 쓰레드
    private Thread serverThread = null;

    // 서버 시작 하는 Timer
    private Timer serverDataReciveTimer = new Timer();
    // usb Data 불러오는 Timer
    private Timer usbRunTimer = new Timer();

    //mp3 class
    private MP3 mp3 = new MP3();
    // 현재 실행 혹은 마지막으로 실행한 mp3 파일 이름
    private String runMp3FileName = null;
    //mp3 Download Thred
    private MP3.DownloadFilesTask downloadFilesTask = null;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScreenHeight(this);
        hideNavigationBar();

        setContentView(R.layout.activity_profile_img_view);
        init();

        serverConnection();

        usbReaction();

    }

    // Usb 사용 메메소드
    private void usbReaction(){

        //usb 가 연결되었을때 실행
        registerReceiver(mUsbDeviceReceiver, new IntentFilter(UsbManager.ACTION_USB_DEVICE_ATTACHED));
        //usb 가 헤제되었을때 실행
        registerReceiver(mUsbDeviceReceiver, new IntentFilter(UsbManager.ACTION_USB_DEVICE_DETACHED));

    }

    @Override
    public void init()
    {
        //server 프로필 사진
        profile_server_img = findViewById(R.id.profile_server_img);
        //usb 프로필 사진
        profile_usb_img = findViewById(R.id.profile_usb_img);
        //리본 사진
//        ribbon_img = findViewById(R.id.ribbon_img);
        off_img = findViewById(R.id.off_img);
        //에러 발생했을경우
        error_textview = findViewById(R.id.error_textview);
    }



    @Override
    public void serverConnection() {



        TimerTask serverRecive = new TimerTask() {
            @Override
            public void run() {
                final JSONObject resBody = HttpUtil.requestBody(room_url, "POST", cereal);


                if (serverThread != null){
                    serverThread.setDaemon(true);
                    serverThread = null;
                }

                serverThread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        screenOffImage = ScreenImageDataCall.screenDataCall(ProfileViewPage.this, resBody);

                        boolean jsonCheck = RoomsValidation.roomsValidation(resBody);

                        if (jsonCheck) {
                            getDeviceRoom(resBody);
                        } else {
                            LogService.info("json value not found  = 에러");
                        }

                    }
                });
                serverThread.start();



            }
        };
        serverDataReciveTimer.scheduleAtFixedRate(serverRecive, 0, 10000);
    }


    public void getDeviceRoom(JSONObject resBody){

        if (ModuleDataReturn.roomDataSameCheck(resBody)) {

            try{
                JSONArray jsonarray_rooms = ((JSONArray) resBody.get("rooms"));
                JSONObject json_rooms_data = ((JSONObject) jsonarray_rooms.get(0));

                // 영정 사진 Url
                String death_face_image = File_URL +json_rooms_data.get("death_face_image").toString();

                // monitor 상태
                String monitor_on =  json_rooms_data.get("monitor_on").toString();

                // Mp3 On,Off
                String bg_music_on = json_rooms_data.get("bg_music_on").toString();

                // MP3 파일  Url
                String death_bg_music = json_rooms_data.getString("death_bg_music");

                // Mp3 노래 다운 로드 실행
                Mp3_DataSet(bg_music_on,death_bg_music);
                // 데이터 Ui 연결
                setUiRoomData(death_face_image,monitor_on);


            }catch(Exception ex){
                LogService.error(ex.getMessage(),ex);
            }

        }else{
            LogService.info("데이터 동일합니다. ");
        }
    }


    /**
     * @param death_face_image : 서버 받은 이미지 String
     * @param monitor_on : monitor 상태 확인
     */
    public void setUiRoomData(final String death_face_image,final String monitor_on){


//        profile_server_img : 서버 받은 이미지
//        profile_usb_img : Usb 에서 받은 이미지
//        off_img : Off 되었을때 서버 에서 받은 이미지

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (monitor_on.equals("on")) {


                    //profile_server_img : 서버 받은 이미지
                    Glide.with(ProfileViewPage.this).load(death_face_image).into(profile_server_img);

                    profile_server_img.setVisibility(View.VISIBLE);
                    profile_usb_img.setVisibility(View.GONE);
                    off_img.setVisibility(View.GONE);
                    error_textview.setVisibility(View.GONE);

                } else if (monitor_on.equals("off")) {

                    //off_img : Off 되었을때 서버 에서 받은 이미지
                    ScreenImageDataCall.Screen_Glide(ProfileViewPage.this, screenOffImage,off_img);

                    profile_server_img.setVisibility(View.GONE);
                    profile_usb_img.setVisibility(View.GONE);
                    off_img.setVisibility(View.VISIBLE);

                    error_textview.setVisibility(View.VISIBLE);
                    error_textview.setText("off");
                } else {
                    error_textview.setText("JsonDataerror");
                    LogService.error("jsonrooms_data_Result : 이미지 on,off 에러", null);
                }
            }
        });
    }


    /**
     * @param bg_music_on : Mp3 상태 확인
     * @param death_bg_music : Mp3 파일 Url
     */
    private void Mp3_DataSet(String bg_music_on,String death_bg_music)
    {
        mp3.on = bg_music_on;

        if(runMp3FileName == null || runMp3FileName.equals(File_URL + death_bg_music) == false)
        {
            if(downloadFilesTask != null)
            {
                downloadFilesTask = null;
            }

            runMp3FileName = File_URL + death_bg_music;

            //내부 저장소로 mp3 파일 다운로드
            LogService.info(runMp3FileName);

            mp3.mp3_date(runMp3FileName, downloadFilesTask, ProfileViewPage.this);
        }
        else
        {
            mp3.mp3Run();

            //내부 저장소로 mp3 파일 다운로드
            LogService.info(runMp3FileName +"은 이전 mp3와 동잃하기 때문에 재 다운로드 하지 않습니다.");
        }
    }


    /**
     * USB 가 연결되었을때 호출됨
     */
    private final BroadcastReceiver mUsbDeviceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            profile_usb_img.setVisibility(View.VISIBLE);
            off_img.setVisibility(View.GONE);
            profile_server_img.setVisibility(View.GONE);


            if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) { // usb 가 연결 되었을때 실행됨

                TimerTask timerTask =new TimerTask() {
                    @Override
                    public void run() {
                        getUsbFileData();

                    }
                };

                usbRunTimer.schedule(timerTask,0,3000);

            }else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) { // usb 가 분리 되었을때 다시 서버 호출
                LogService.info("USB 불리");

                beforeRoomsInfo="";
                serverConnection();
            }
        }
    };

    /**
     * USB 연결 되었을때 File : [ Ehwa_photo ] 찾음
      */
    private void getUsbFileData()
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                // 파일 경로

                String path = "/storage/";

                File file = new File(path);

                if(file.exists())
                {
                    LogService.info("성공");


                    //전체 폴더 검사
                    File[] file_path = file.listFiles();
                    try
                    {


                        for(int i = 0; i < file_path.length; i++)
                        {

                            try
                            {
                                //특정 폴더 찾기
                                File[] file_name = new File(file_path[i].getAbsolutePath()).listFiles();

                                if(file_name == null)
                                {
                                    LogService.info("경로 : " + file_path[i].getAbsolutePath() + " is null");
                                    continue;
                                }

                                for(int j = 0; j < file_name.length; j++)
                                {



                                    //  Ehwa_photo 폴더이름
                                    if (file_name[j].getName().equals("Ehwa_photo"))
                                    {
                                        //폴더 데이터 찾기
                                        File[] file_data = new File(file_name[j].getAbsolutePath()).listFiles();
                                        for (int k =0; k< file_data.length;k++)
                                        {

                                            //usb 사진 보여줌
                                            Glide.with(ProfileViewPage.this).load(file_data[k].getPath()).into(profile_usb_img);
                                            error_textview.setVisibility(View.GONE);
                                            usbRunTimer.cancel();


                                            LogService.info("폴더 안에 데이터 list : "+file_data[k].getPath());
                                        }
                                    }
                                    else
                                    {
                                        LogService.info("Ehwa_photo : 폴더 없음");
                                    }
                                }
                            }
                            catch (Exception ex)
                            {
                                LogService.error(file_path[i].getAbsolutePath() + " : " + ex.getMessage(), ex);
                            }

                        }
                    }
                    catch(Exception ex)
                    {
                        LogService.error("getUsbFileData :"+ex.getMessage(),ex);
                    }
                }
                else
                {
                    LogService.error("파일접근실패",null);
                }


            }
        });
    }

//    /**
//     * @param ribbon_on_off : 리본 상태확인
//     * @param ribbon_url : 리본 사진 경로
//     * @return : 리본 사진 리턴
//     */
//    private int ribbon_Img(String ribbon_on_off,String ribbon_url)
//    {
//
//        if (ribbon_on_off.equals("Y"))
//        {
//            int ribbon_list_img = 0;
//
//            if (ribbon_url.equals("리본 이미지_1"))
//            {
//                ribbon_list_img = R.drawable.rb_img_1;
//            }
//            else if (ribbon_url.equals("리본 이미지_2"))
//            {
//                ribbon_list_img = R.drawable.rb_img_2;
//            }
//            else if (ribbon_url.equals("리본 이미지_3"))
//            {
//                ribbon_list_img = R.drawable.rb_img_3;
//            }
//            else if (ribbon_url.equals("리본 이미지_4"))
//            {
//                ribbon_list_img = R.drawable.rb_img_4;
//            }
//
//            return ribbon_list_img;
//        }
//        else
//        {
//            LogService.info("리본이미지가 없습니다");
//            return 0;
//        }
//    }
}









