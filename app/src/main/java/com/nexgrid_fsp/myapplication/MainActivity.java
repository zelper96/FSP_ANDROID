package com.nexgrid_fsp.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.nexgrid_fsp.myapplication.entrance.Entance_View_H;
import com.nexgrid_fsp.myapplication.entrance.Entance_View_V;
import com.nexgrid_fsp.myapplication.profile.ProfileViewPage;
import com.nexgrid_fsp.myapplication.special.List_Hyo_Jung;
import com.nexgrid_fsp.myapplication.special.Thumbnail_View_BlueFlag;
import com.nexgrid_fsp.myapplication.special.Thumbnail_View_Split;
import com.nexgrid_fsp.myapplication.thumbil.View.Thumbnail_View_H;
import com.nexgrid_fsp.myapplication.thumbil.View.Thumbnail_View_V;
import com.nexgrid_fsp.myapplication.sharinglive.HttpUtil;
import com.nexgrid_fsp.myapplication.list.ViewListPage;
import com.nexgrid_fsp.myapplication.log.LogService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.PREFERENCE;
import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.cereal;
import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.cerealServerRequest;
import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.cerealServerSend;
import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.room_url;


/**
 * @author 이동훈
 * @version 0.1
 */
public class MainActivity extends BeginFSPActivity {



    //  JSON 데이터 를 불러오기 위한 쓰레드
    private Thread serverCallThread = null;
    // 시리얼 넘버 승인 을 기다는 타임쓰레드
    private   Timer serverCheckReceiveThread = new Timer();
    // 모니터 Style 결정 기다리는 타림쓰레드
    private   Timer serverMonitorReceiveThread = new Timer();





   //시리얼 넘버 생산 코드 단어들
   private  String[] cerealAllData = new String[]
            {
            "a","b","c","d","e","f","g","h","i","j","k","l","n","m","o","p","q","r","s","t","u","v","w","x","y","z",
            "0","1","2","3","4","5","6","7","8","9"
            };
   

    //시리얼 넘버 랜덤으로 생성 하기 위함
    private   Random r = new Random();

    //시리얼 넘버 를 담는 곳
    private   StringBuilder stringBuilder_cereal = new StringBuilder();

    //쉐어드프리퍼런스
    private SharedPreferences sharedPreferences;


    // 모니터 타입
    private  String monitorRoleType;
    //모니터 템플릿
    private  String monitor_role_template;


    // 첫화면 Ui
    private  TextView check_code;
    private  TextView cereal_num_textview;
    private  TextView server_run_time_tTextView;
    private  int serverRunTime = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScreenHeight(this);
        hideNavigationBar();

        setContentView(R.layout.activity_cereal);
        init();



        // File 퍼미션 권한
        boolean file  = filePermission();



        if (file){
            cerealNumCheck();
        }else {
            cerealNumCheck();
        }
//
    }

    /**
     * init()
     * setContentView(R.layout.activity_cereal);
     * activity_cereal.xml 데이터 를 연결
     */

    public void init()
    {
        check_code                           = findViewById(R.id.check_code);
        cereal_num_textview                  = findViewById(R.id.cereal_num);
        server_run_time_tTextView            = findViewById(R.id.server_run_time);

    }

    private void cerealNumCheck() {


        sharedPreferences = getSharedPreferences(PREFERENCE,MODE_PRIVATE);

        if (sharedPreferences.contains("cereal_num")) {
            //시리얼넘버 서버로 전송
            cereal = "{\"deviceSerial\":\""+sharedPreferences.getString("cereal_num","")+"\"}";


            //시리얼 넘버가 있을경우 모니터 Style 으로 바로 넘어감
            monitorType();

        } else {

            //시리얼 넘버 생성
            cerealNumGenerate();

        }
    }



    /**
     * CerealNumStorage()
     * 1. 시리얼 넘버를 생성한다.
     * 2.  serverConnection(cerealServerSend) 시리얼 넘버를 server 로 보냄
     * 3.  serverApprovalReceive() 시리얼넘버 승인 요청
     */
    private void cerealNumGenerate() {

        // 시리얼 넘버가 없을경우 생성함
        for (int i=0; i<16;i++)
        {
            stringBuilder_cereal.append(cerealAllData[r.nextInt(cerealAllData.length)]);
        }


        //화면에 cereal_num 화면에 보여주기
        cereal_num_textview.setText(stringBuilder_cereal.toString());

        //시리얼넘버 서버로 전송
        cereal ="{\"deviceSerial\":\""+stringBuilder_cereal+"\"}";




        //처음 시리얼 넘버가 생성 된후 서버로 보냄
        serverConnection(cerealServerSend,cereal);

        //시리얼 넘버 승인 요청
        serverApprovalReceive();



    }

    /**
     * @param url :  serverConnection(cerealServerSend) 시리얼 넘버 생성 후 전송함
     * @param url :  serverConnection(cerealServerRequest) 홈페이지에서 승인 요청 기다림
     * @param url :  serverConnection(room_url) : room 모니터 Style 결정 후 액티비티 이동
     */
    public void serverConnection(final String url, final String jsonBody) {

        serverCallThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                JSONObject resBody = HttpUtil.requestBody(url,"POST",jsonBody);
                try
                {
                    //url 대응 JSON
                    setServerDataCheck(url,resBody);


                }
                catch (Exception ex)
                {
                    // 인터넷이 연결 되지 않코 실행 되엇을때 모니터 타입 으로 이동
                    monitorType();

                    LogService.error(ex.getMessage(),ex);

                }

            }
        });
        serverCallThread.start();

    }




    /**
     *@param url = generateDeviceSerial : 왔을경우 아무것도 안함
     *
     * @param url = checkDeviceSerial : 홈페이지 에서 승인 요청 기다림
     *
     *@param url = getDeviceRoom : monitor_style 요청 기다림
     *
     *@param url = Json 데이터 체크
     */
    public void setServerDataCheck(String url , JSONObject resBody)
    {

        try
        {
            if (url.contains("generateDeviceSerial"))// 처음 시리얼 넘버 실행 할경우 체크할게없음
            {
                LogService.info("시리얼 넘버 생성 성공");

            }
            if(url.contains("checkDeviceSerial")) // 시리얼 넘버 체크 URL 인지 확인
            {
                // 어드민 페이지 에서 단말 관리 상태 를 확인 하는 메소드
                checkDeviceSerial(resBody);
            }
            else if(url.contains("getDeviceRoom")) // 시리얼 승인후 Room 데이터 확인
            {
                // Room Data 에서  Monitor Type 확인
                getDeviceRoom(resBody);
            }
            else
            {
                // 처음 시리얼 넘버 생성 되었을때 한번 말생함
                LogService.error("알수없는에러",null);;
            }
        }
        catch (Exception ex)
        {

            LogService.error(ex.getMessage(),ex);
        }

    }

    /**
     * serverApprovalReceive()
     * 1. serverConnection(cerealServerRequest) 시리얼 넘버를
     * 홈페이지 에서 시리얼 넘버 승인 해줄때 까지 요청
     */
    private void serverApprovalReceive()
    {
        TimerTask serverRecive = new TimerTask() {
            @Override
            public void run() {


                //시리얼 넘버가 승인완료 될때까지 확인
                serverConnection(cerealServerRequest,cereal);

                //분양소 대한 정보가 없을때 응답을 기다림
                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        try
                        {
                            if (serverRunTime == 0) { server_run_time_tTextView.setText("."); }
                            else if (serverRunTime == 1) { server_run_time_tTextView.setText(".."); }
                            else if (serverRunTime == 2) { server_run_time_tTextView.setText("..."); }
                            else if (serverRunTime == 3) { server_run_time_tTextView.setText("...."); }
                            else if (serverRunTime == 4) { server_run_time_tTextView.setText("....."); }
                            else {serverRunTime = 0;}

                            serverRunTime++;

                        }
                        catch (Exception ex)
                        {
                            LogService.error(ex.getMessage(),ex);
                        }

                    }
                });

            }
        };
        serverCheckReceiveThread.schedule(serverRecive,0,3000);
    }




    /**
     *  checkDeviceSerial
     * @param resBody : 홈페이지 에서 승인 되었는지 확인
     */
    private void checkDeviceSerial(JSONObject resBody)
    {
        try
        {
            if(resBody.get("resultCode") != null && resBody.get("resultCode").equals("0"))
            {
                if (resBody.has("approve_state") && resBody.get("approve_state").equals("approved"))
                {
                    // 어드민 페이지에서 단말관리 승인 완료
                    serverConnection(room_url,cereal);
                }
                else
                {
                    // 어드민 페이지에서  단말관리 승인대기중
                    LogService.info("시리얼 넘버 승인 대기 : "+resBody.toString());
                }
            }
        }
        catch (JSONException ex)
        {
            LogService.error(ex.getMessage(),ex);
//            Log.e("EAST","checkDeviceSerial : "+ex.getMessage());
        }
    }

    /**
     *
     * 장례식 방정보 취득
     *
     * @param resBody 취득 방 json 정보
     */
    public void getDeviceRoom(JSONObject resBody)
    {
        //Room 모니터 타입 결정 되는곳
        try
        {
            // 모니터 타입 결정 될때 까지 기다림
            if(resBody.get("resultCode") != null && resBody.get("resultCode").equals("0"))
            {
                if(resBody.has("monitor"))
                {
                    JSONObject json_monitor = (JSONObject) resBody.get("monitor");

                    // 모니터 타입이 결정 되는곳 = rooms_url
                    if(json_monitor.has("monitor_role") && json_monitor.has("monitor_role_template"))
                    {

                        // 시리얼 넘버가 저장 되어있는지 확인
                        SharedPreferences.Editor editor = sharedPreferences.edit();


                            // 시리얼 넘버 String 내부 저장소 저장
                        editor.putString("cereal_num",stringBuilder_cereal.toString());
                            // 모니터 타입  내부 저장소 저장
                        editor.putString("monitor_role_style",json_monitor.get("monitor_role").toString());
                            // 모니터 템플릿 내부 저장소 저장
                        editor.putString("monitor_role_template",json_monitor.get("monitor_role_template").toString());


                        editor.commit();


                            // 모니터 타입이 결정되었으면 해당 모니터 로 이동
                            monitorType();

                            //쓰레드 종료
                            serverCheckReceiveThread.cancel();




                    }
                    else
                    {
                        LogService.error("필드에 monitor_role가 누락되었습니다. : "+json_monitor.toString(),null);

                    }
                }
                else
                {
                    LogService.error("필드에 monitor가 누락되었습니다. : "+resBody.toString(),null);

                }
            }
            else
            {
                LogService.info("모니터 타입 결정 대기중");

                // 어드민에서 monitor Type 경정 될때 까지 화면에 보여주는 Ui 메소드
                monitorTypeCheckUi();

            }
        }
        catch (JSONException ex)
        {
            LogService.error(ex.getMessage(),ex);
//            Log.e("EAST","getDeviceRoom : "+ex.getMessage());
        }
    }

    private void monitorTypeCheckUi()
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run() {

                if (cereal_num_textview != null && cereal_num_textview !=null && server_run_time_tTextView != null)
                {
                    check_code.setText("단말 코드 : "+stringBuilder_cereal);
                    cereal_num_textview.setText("분향소에 대한 정보가 없습니다.\n관리자에서 분향소 생성 후 기기를 연결하시기 바랍니다.");
                }
                else
                {

                    check_code.setText("서버 타입중 문제가 생겼습니다.\n담당자에게 연락해주세요.\n"+"시리얼 넘버 : "+cereal);
                    cereal_num_textview.setText("");
                }
            }
        });
    }


    private  void  monitorType()
    {

        monitorRoleType = sharedPreferences.getString("monitor_role_style","");
        monitor_role_template = sharedPreferences.getString("monitor_role_template","");


        LogService.info(monitorRoleType);
        LogService.info(monitor_role_template);

        TimerTask serverRecive = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        //모니터 타입은 내부 String = monitor_role_style 저장하여 비교한다
                        if (sharedPreferences.contains("cereal_num"))
                        {
                            if (sharedPreferences.contains("monitor_role_style"))
                            {

                                //(현황판 입구
                                if (monitorRoleType.equals("enterance"))
                                {
                                    //입구 현관판  세로형
                                    if (monitor_role_template.equals("h_default"))
                                    {
                                        monitorTypeActivity(MainActivity.this, Entance_View_H.class);
                                    }

                                    //입구 현관판  가로형
                                    else if (monitor_role_template.equals("v_default"))
                                    {
                                        monitorTypeActivity(MainActivity.this, Entance_View_V.class);
                                    }


                                    else
                                    {
                                        //알수 없는 값이 없을경우 가로로 보여준다
                                        //임시용으로 이동함
                                        monitorTypeActivity(MainActivity.this, Entance_View_V.class);
                                    }
                                }
                                //현황판 입구)



                                //(영정사진
                                else if (monitorRoleType.equals("face"))
                                {
                                        monitorTypeActivity(MainActivity.this, ProfileViewPage.class);
                                }
                                //영정사진)




                                //(썸네일형
                                else if (monitorRoleType.equals("board_thumbnail"))
                                {
                                    // 썸네일 이미지형

                                    //가로형 이미지형
                                    if (monitor_role_template.equals("v_default"))
                                    {
                                        monitorTypeActivity(MainActivity.this, Thumbnail_View_V.class);
                                    }
                                    //세로형 이미지형
                                    else if (monitor_role_template.equals("h_default"))
                                    {
                                        monitorTypeActivity(MainActivity.this, Thumbnail_View_H.class);
                                    }
                                    else if(monitor_role_template.equals("v_split"))
                                    {
                                        monitorTypeActivity(MainActivity.this, Thumbnail_View_Split.class);
                                    }
                                    else if (monitor_role_template.equals("v_blueflag"))
                                    {
                                        monitorTypeActivity(MainActivity.this, Thumbnail_View_BlueFlag.class);
                                    }
                                    else
                                    {
                                        //알수 없는 값이 없을경우 가로로 보여준다
                                        //임시용으로 이동함
                                        monitorTypeActivity(MainActivity.this, Thumbnail_View_V.class);
                                    }
                                }
                                //썸네일형)





                                //(리스트형
                                else if (monitorRoleType.equals("board_list"))
                                {

                                    if (monitor_role_template.equals("v_custom_hyo"))
                                    {

                                        monitorTypeActivity(MainActivity.this, List_Hyo_Jung.class);
                                    }
                                    else
                                    {
                                        // 모니터 리스트 형
                                        monitorTypeActivity(MainActivity.this, ViewListPage.class);
                                    }
                                }
                                //리스트형)


                            }

                        }
                        else
                        {//  sharedPreferences 없을경우
                            // 모니터 에러 나왔 을경우 임시적으로 보이는 xml
                            setContentView(R.layout.activity_main);
                        }
                    }
                });
            }
        };
        serverMonitorReceiveThread. schedule(serverRecive,0,5000);

    }

    private void monitorTypeActivity(Context context ,Class Activity)
    {
        Intent intent = new Intent(context, Activity);
        startActivityForResult(intent,0);
        serverMonitorReceiveThread.cancel();
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0)
        {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivityForResult(intent,0);
        }

    }


    public boolean filePermission()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if
            (
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                            checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            )
            {
                if(shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                {
                    Toast.makeText(this, "외부 저장소 사용을 위해 읽기/쓰기 필요", Toast.LENGTH_SHORT).show();
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 2);  //마지막 인자는 체크해야될 권한 갯수

                return shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            else
            {

                return shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

        }
        return false;
    }


    /*
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        cerealNumCheck();
    }
     */
}
