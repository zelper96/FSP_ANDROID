package com.nexgrid_fsp.myapplication.thumbil.View;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nexgrid_fsp.myapplication.BeginFSPActivity;
import com.nexgrid_fsp.myapplication.sharinglive.ModuleDataReturn;
import com.nexgrid_fsp.myapplication.sharinglive.HttpUtil;
import com.nexgrid_fsp.myapplication.R;
import com.nexgrid_fsp.myapplication.sharinglive.ScreenImageDataCall;
import com.nexgrid_fsp.myapplication.sharinglive.RoomsValidation;
import com.nexgrid_fsp.myapplication.sharinglive.WebviewScript;
import com.nexgrid_fsp.myapplication.vo.SharingVO;
import com.nexgrid_fsp.myapplication.thumbil.Style.Thumbnail_sub;
import com.nexgrid_fsp.myapplication.log.LogService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import static com.nexgrid_fsp.myapplication.sharinglive.ScreenImageDataCall.Screen_Glide;
import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.File_URL;


import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.cereal;
import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.room_url;
import static com.nexgrid_fsp.myapplication.sharinglive.ScreenImageUiSet.before_screen_string;


public class Thumbnail_View_V extends BeginFSPActivity{

    // 방 레이아웃 관리 리스트
    private Thumbnail_sub[] roomLayoutManageList;

    // 방 한개 표시 레이아웃
    private Thumbnail_sub oneRoomLayout;
    private Thumbnail_sub toeRoomLayout;
    private Thumbnail_sub threeRoomLayout;
    private Thumbnail_sub fourRoomLayout;



    // 광고 이미지
    private String screen_img;
    private ImageView spawn_img_1;
    private ImageView spawn_img_2;


    private String spawn_img_url_2 = "";
    private Bitmap spawn_img_url_Bitmap = null;
    private Timer spawn_timer = new Timer();


    // 상세 정보 포함 룸 리스트
    private List<SharingVO> detailInfoRoomList = new ArrayList<>();

    // 이전 룸 정보
    private String beforeRoomsInfo = "{}";
    private String beforeScreenStr = null;

    //spawnRoom 정보
    private String spawnRoom = "{}";


    // 룸 정보 받아오기 Timer
    private Timer timer = new Timer();

    // 현재 표시 방 상태
    private int currentState = 0;

    // 방 여러개 표시시 점멸 타이머
    private Timer roomChangeTimer = null;

    private int totalRoomCnt = 0;

    //Screen 이미지 다운로드
    private  ImageView top_on_off;
    private  String  screen_string;




    //맨뒤 배경 색상
    private ConstraintLayout mainGround_color;

    //main Color
    private String mainC = "#024188";
    //sub Color
    private String subC = "#DDE9F0";
    //line Color
    private String lineC = "#8B9FB5";



    private    View mainViewOld = null;
    private    View mainView = null;
    private int  mainViewIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

            ScreenHeight(this);
            hideNavigationBar();

            setContentView(R.layout.activity_thumbnail_default_v);

            mainGround_color = findViewById(R.id.mainbackground_color);
            top_on_off = findViewById(R.id.top_on_off);


            reqRoomList(room_url, cereal);

    }



    private void init()
    {
        // 방 하나 표시 레이아웃 초기화
        oneRoomLayout = mainView.findViewById(R.id.view_page_sub_0);
        toeRoomLayout = mainView.findViewById(R.id.view_page_sub_1);
        threeRoomLayout = mainView.findViewById(R.id.view_page_sub_2);
        fourRoomLayout = mainView.findViewById(R.id.view_page_sub_3);
        spawn_img_1 = mainView.findViewById(R.id.spawn_img_1);
        spawn_img_2 = mainView.findViewById(R.id.spawn_img_2);




        // 레리아웃 표시
        roomLayoutManageList = new Thumbnail_sub[]{oneRoomLayout, toeRoomLayout, threeRoomLayout, fourRoomLayout};

    }


    private void reqRoomList(final String reqUrl, final String reqSerial)
    {
        TimerTask task = new TimerTask()
        {

            @Override
            public void run()
            {

                JSONObject resBody = HttpUtil.requestBody(reqUrl, "POST", reqSerial);

                //sceen  = 오프라인 상태 일때 나오는 사진
                screen_img = ScreenImageDataCall.screenDataCall(Thumbnail_View_V.this,resBody);

                //server = 서버 데이터 연결
                server_Date(resBody);

            }
        };
        timer.scheduleAtFixedRate(task, 0, 10000);
    }

    private void server_Date(final JSONObject resBody)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    if
                    (
                        resBody != null &&
                        resBody.has("rooms") &&
                        (
                            resBody.get("rooms").toString().equals(beforeRoomsInfo.toString()) == false ||
                            (
                                resBody.has("screen_save_image_v") &&
                                resBody.getString("screen_save_image_v").toString().equals(beforeScreenStr) == false
                            )
                        )
                    )

                    {

                        beforeRoomsInfo = resBody.get("rooms").toString();
                        before_screen_string = beforeScreenStr;
                        beforeScreenStr = resBody.getString("screen_save_image_v").toString();

                        boolean jsonCheck = RoomsValidation.roomsValidation(resBody);

                        if (jsonCheck)
                        {
                            jsonrooms_data_Result(resBody);
                        }
                        else
                        {
                            LogService.info("json value not found  = 에러");
                        }

                    }
                    else
                    {
                        LogService.info("응답 룸 정보 동일");
                    }
                }
                catch (Exception ex)
                {
                    LogService.info("server_Date : JSON 취득 에러");
                    LogService.error(ex.getMessage(),ex);
                }
            }
        });
    }


    private void jsonrooms_data_Result(JSONObject jsonObject)
    {
        try {
            JSONArray jsonarray_rooms = ((JSONArray) jsonObject.get("rooms"));

            if (jsonarray_rooms != null) {
                totalRoomCnt = 0;
                // 룸 이전 정보 삭제
                detailInfoRoomList.clear();
                // room 갯수 만큼 data 불러오기
                for (int i = 0; i < jsonarray_rooms.length(); i++) {

                    // JSON 파싱 자료
                    JSONObject json_rooms_data = (JSONObject) jsonarray_rooms.get(i);

                    // 방번호
                    String num = json_rooms_data.get("funeralroom_name").toString();

                    // 이름
                    String name ="故"+json_rooms_data.get("death_name").toString();

                    //성별
                    String gender =  json_rooms_data.get("death_sex").toString();

                    //나이
                    String age    =  json_rooms_data.get("death_age").toString();

                    //성별,나이
                    String gender_age ="";

                    // 뒤 배경 이미지 , ON OFF 상태 ,내부 사진주소
                    String back_img_ON_OFF = json_rooms_data.get("board_use_bg").toString();

                    //뒤 배경 이미지
                    String back_img_JSON = json_rooms_data.get("board_bg_detail").toString();

                    // 입관일 월, 일, 시, 분
                    String death_enter = json_rooms_data.get("death_exit").toString();
                    String start_date=new SimpleDateFormat("MM월 dd일 HH시 mm분").format(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(death_enter));


                    // 발인일 월, 일, 시, 분
                    String death_exit = json_rooms_data.get("death_burrow").toString();
                    String end_date=new SimpleDateFormat("MM월 dd일 HH시 mm분").format(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(death_exit));


                    // 상주 Web  리스트
                    String webresident= json_rooms_data.toString();


                    //이미지 사진
                    String profileImg = File_URL + json_rooms_data.get("board_face_image").toString();


                    //장지
                    String burialPlot = json_rooms_data.get("death_burrow_place").toString();

                    //분양소가 데이터 가 있는지 없는지 확인
                    String death_id = json_rooms_data.get("death_id").toString();

                    String monitor_on = json_rooms_data.get("monitor_on").toString();

                    // Back 그라운드 이미지
                    // Back 그라운드 이미지 리턴 받음
                    int back_img = ModuleDataReturn.backgroundImageDataReturn(back_img_ON_OFF, back_img_JSON);


                    if (gender.equals("남자")) {
                        gender_age = "(남/" + age +"세)";
                    } else if (gender.equals("여자")) {
                        gender_age = "(여/" + age + "세)";
                    }


                    // 이미지 사진 Strain 비트맵 으로 저장
                    //이부분 삭제해야함
                    Bitmap profileBitmap = null;

                    //text_Color
                    String bright_text_Color = "#ffffff";

                    //입관,발인,장지 상세 text_Color
                    String dark_text_Color = "#000000";

                    //Color data
                    String background_Colors = json_rooms_data.get("board_template").toString();


                    //background box Color
                    String mainC = "#024188";
                    //backgrond Color
                    String subC = "#DDE9F0";
                    //line Color
                    String lineC = "#8B9FB5";

                    //Color Index 초기갑
                    int colorIndex = 0;

                    if (background_Colors.equals("블랙") || background_Colors.equals(""))
                    {
                        mainC = Color_AllChange(colorIndex)[1];
                        //backgrond Color
                        subC = Color_AllChange(colorIndex)[2];
                        //line Color
                        lineC = Color_AllChange(colorIndex)[3];
                    }
                    else
                    {
                        //Color Index
                        colorIndex = Integer.parseInt(background_Colors);
                        //background box Color
                        mainC = Color_AllChange(colorIndex)[1];
                        //backgrond Color
                        subC = Color_AllChange(colorIndex)[2];
                        //line Color
                        lineC = Color_AllChange(colorIndex)[3];
                    }




                    if (death_id != null) {
                        if (json_rooms_data.get("monitor_on").equals("on")) {

                            // 데이터  Thunbanll_View 저장
                            setRoomInfos(num, name,gender_age,profileImg,profileBitmap, back_img, webresident, start_date, end_date, burialPlot,bright_text_Color,dark_text_Color,mainC,subC,lineC);
                        }
                        else
                        {
                            LogService.info("dataoff");
                        }

                    }
                    else
                    {
                        LogService.info("death_id Null 상태");
                    }
                }
                //  Layout 개수 구분
                totalRoomCnt = detailInfoRoomList.size();
                Activity_Change();


            }
            else
            {
                LogService.info("jsonarray_rooms.length 에러");
            }
        }
        catch (Exception ex)
        {
            LogService.error(ex.getMessage(),ex);
        }
    }



    private void setRoomInfos(String num, String name, String gender_age ,String profile ,Bitmap profileBitmap, int backImg,String webresident, String startDate, String endDate, String burialPlot,String bright_text_Color,String dark_text_Color, String mainC,String subC,String lineC)
    {
        // 썸네일 데이터 VO 추카
        SharingVO thumbnailView_dataVO = new SharingVO();

        // 썸네일 데이터 아이템에 추가
        thumbnailView_dataVO.setnum(num);
        thumbnailView_dataVO.setName(name);
        thumbnailView_dataVO.setGender_age(gender_age);
        thumbnailView_dataVO.setProfile(profile);
        thumbnailView_dataVO.setProfileBitmap(profileBitmap);
        thumbnailView_dataVO.setBackImg(backImg);
        thumbnailView_dataVO.setWebresident(webresident);
        thumbnailView_dataVO.setStartDate(startDate);
        thumbnailView_dataVO.setEndDate(endDate);
        thumbnailView_dataVO.setBurialPlot(burialPlot);
        thumbnailView_dataVO.setBright_text_Color(bright_text_Color);
        thumbnailView_dataVO.setDark_text_Color(dark_text_Color);
        thumbnailView_dataVO.setmainC(mainC);
        thumbnailView_dataVO.setsubC(subC);
        thumbnailView_dataVO.setlineC(lineC);


        //썸네일 테이터를 ArrayList Add
        detailInfoRoomList.add(thumbnailView_dataVO);
    }


    private void setLayoutData(final Thumbnail_sub lyt, final SharingVO data)
    {

        try
        {
            String finalHtml = WebviewScript.getMultiDataScript("v" , 50,30,30);
            finalHtml = finalHtml.replace("__data__", data.getWebresident());


            lyt.title_num.setText(data.getnum());
            lyt.name.setText(data.getName());
            lyt.gender_age.setText(data.getGender_age());
            Glide.with(Thumbnail_View_V.this).load(data.getProfile()).into(lyt.profile_img);
            Glide.with(Thumbnail_View_V.this).load(data.getBackImg()).dontAnimate().into(lyt.server_back_img);
            lyt.WebText_View.loadDataWithBaseURL(null, finalHtml,"text/html", "utf-8", null);
            lyt.start_date.setText(data.getStartDate());
            lyt.end_date.setText(data.getEndDate());
            lyt.survival_gps.setText(data.getBurialPlot());


            setColors(lyt, data);

        }
        catch (Exception ex)
        {
            LogService.error("setLayoutData",ex);
        }
    }

    private void setColors(final Thumbnail_sub lyt ,final SharingVO data)
    {
        try
        {

            //main_Color
            lyt.title_tab_box.setBackgroundColor(Color.parseColor(data.getmainC()));
            lyt.people_title.setBackgroundColor(Color.parseColor(data.getmainC()));
            lyt.start_date_box.setBackgroundColor(Color.parseColor(data.getmainC()));
            lyt.end_date_box.setBackgroundColor(Color.parseColor(data.getmainC()));
            lyt.survival_gps_box.setBackgroundColor(Color.parseColor(data.getmainC()));

            // sub_Color
            lyt.start_date.setBackgroundColor(Color.parseColor(data.getsubC()));
            lyt.end_date.setBackgroundColor(Color.parseColor(data.getsubC()));
            lyt.survival_gps.setBackgroundColor(Color.parseColor(data.getsubC()));
            lyt.WebText_View.setBackgroundColor(Color.parseColor(data.getsubC()));

            //line_Color
            lyt.lineC_background_color.setBackgroundColor(Color.parseColor(data.getlineC()));
            mainGround_color.setBackgroundColor(Color.parseColor(data.getlineC()));

            this.mainC = data.getmainC();
            this.subC  = data.getsubC();
            this.lineC = data.getlineC();

        }
        catch (Exception ex)
        {
            LogService.error("Color",ex);
        }
    }





    private void resetLayoutData(final Thumbnail_sub lyt ,final SharingVO data)
    {

        try {
            lyt.title_num.setText("0호실");
            lyt.name.setText("");
            lyt.profile_img.setImageBitmap(null);
            Glide.with(Thumbnail_View_V.this).load("").into(lyt.profile_img);
            Glide.with(Thumbnail_View_V.this).load("").dontAnimate().into(lyt.server_back_img);
            lyt.gender_age.setText("");
            lyt.WebText_View.loadData("","text/html", "UTF-8");
            lyt.start_date.setText("");
            lyt.end_date.setText("");
            lyt.survival_gps.setText("");

            setResetLayout_Color(lyt);

        }
        catch (Exception ex)
        {
            LogService.error("resetLayoutData",ex);
        }
    }

    private void setResetLayout_Color(final Thumbnail_sub lyt)
    {
        try
        {

            //main_Color
            lyt.title_tab_box.setBackgroundColor(Color.parseColor(mainC));
            lyt.people_title.setBackgroundColor(Color.parseColor(mainC));
            lyt.start_date_box.setBackgroundColor(Color.parseColor(mainC));
            lyt.end_date_box.setBackgroundColor(Color.parseColor(mainC));
            lyt.survival_gps_box.setBackgroundColor(Color.parseColor(mainC));

            // sub_Color
            lyt.start_date.setBackgroundColor(Color.parseColor(subC));
            lyt.end_date.setBackgroundColor(Color.parseColor(subC));
            lyt.survival_gps.setBackgroundColor(Color.parseColor(subC));
            lyt.WebText_View.setBackgroundColor(Color.parseColor(subC));

            // line_Color
            lyt.lineC_background_color.setBackgroundColor(Color.parseColor(lineC));
            mainGround_color.setBackgroundColor(Color.parseColor(lineC));



        }
        catch (Exception ex)
        {
            LogService.error(ex.getMessage(),ex);
        }
    }





    private void Activity_Change()
    {

        Thumbnail_View_V.this.runOnUiThread(new Runnable()
        {
            @Override
            public void run() {
                int tmpFourLytRooms = 0;
                int tmpOneLytRooms = 0;

                tmpFourLytRooms = totalRoomCnt / 4;

                if (((totalRoomCnt % 4) == 0) == false)
                {
                    if ((totalRoomCnt % 4) == 1)
                    {
                        tmpOneLytRooms = tmpOneLytRooms + 1;
                    } else
                        {
                        tmpFourLytRooms = tmpFourLytRooms + 1;
                    }
                }

                //( 점멸 되었을 때 전에 있는 쓰레드 종료
                if (roomChangeTimer != null)
                {
                    roomChangeTimer.cancel();
                    roomChangeTimer = null;
                }
                //)


                if (totalRoomCnt == 0)
                {
                    //모니터가 0 개 였을경우 호출
                    mainViewOld = mainView;
                    mainView = findViewById(R.id.top_on_off);

                    mainView.setVisibility(View.VISIBLE);

                    init();

                    mainViewCleaning();

                    toggleView();

                    //천제 뒤 배경 색깔 확인
                    Screen_Glide(Thumbnail_View_V.this,screen_img,top_on_off);

                }
                else
                {
                    final int oneLytRooms = tmpOneLytRooms;
                    final int fourLytRooms = tmpFourLytRooms;
                    final int totalRooms = oneLytRooms + fourLytRooms;

                    currentState = 0;

                    roomChangeTimer = new Timer();

                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try
                                    {
                                        if (totalRoomCnt == 0)
                                        {
                                            //모니터 0개 있을경우 호출
                                            //혹시 모를 모니터 잘못 갈수도 있음으로
                                            mainViewOld = mainView;


                                            mainView = findViewById(R.id.top_on_off);


                                            init();

                                            mainViewCleaning();
                                            toggleView();


                                        }
                                        else if (currentState < fourLytRooms)
                                        {
                                            // 점멸될 필요가 있는 경우
                                            if (currentState >= 0)
                                            {

                                                //모니터 4개짜리 view

                                                mainViewOld = mainView;

                                                if (mainViewIndex == 0)
                                                {
                                                    mainView = findViewById(R.id.thumbnail_view_v_4_1);
                                                }
                                                else
                                                {
                                                    mainView = findViewById(R.id.thumbnail_view_v_4_2);
                                                }

                                                ++mainViewIndex;
                                                mainViewIndex %= 2;

                                                init();

                                                mainViewCleaning();
                                                toggleView();

                                                spawn_img_1.setVisibility(View.INVISIBLE);
                                                spawn_img_2.setVisibility(View.INVISIBLE);

//

                                                for(int i = currentState * 4; i < currentState * 4 + 4; i++)
                                                {
                                                    if(i >= totalRoomCnt)
                                                    {
//                                                        LogService.info("reset i =>" + i);
                                                        resetLayoutData(roomLayoutManageList[i % 4], new SharingVO());
                                                    }
                                                    else
                                                    {
//                                                        LogService.info("set i =>" + i);
                                                        setLayoutData(roomLayoutManageList[i % 4], detailInfoRoomList.get(i));
                                                    }
                                                }

                                            }
                                        }
                                        else if (oneLytRooms > 0)
                                        {

                                            //모니터 1개짜리 view
                                            mainViewOld = mainView;

                                            if (mainViewIndex == 0)
                                            {
                                                mainView = findViewById(R.id.thumbnail_view_v_1_1);
                                            }
                                            else
                                            {
                                                mainView = findViewById(R.id.thumbnail_view_v_1_2);
                                            }

                                            ++mainViewIndex;
                                            mainViewIndex %= 2;

                                            init();

                                            mainViewCleaning();
                                            toggleView();

                                            setLayoutData(roomLayoutManageList[0], detailInfoRoomList.get(fourLytRooms * 4));
                                        }

                                        currentState++;

                                        if (totalRooms <= currentState || totalRoomCnt <= 4)
                                        {
                                            // 광고가 들어갈 상황인 경우


                                            mainViewCleaning();
                                            toggleView();



                                            if ((totalRoomCnt + 1) % 4 == 0)
                                            {
                                                // 광고 한개
                                                Screen_Glide(Thumbnail_View_V.this,screen_img,spawn_img_2);
                                                spawn_img_2.setVisibility(View.VISIBLE);

                                            }
                                            else if ((totalRoomCnt + 2) % 4 == 0)
                                            {
                                                // 광고 두개
//                                                Screen_Glide(Thumbnail_View_V.this,screen_img,spawn_img_1);
                                                Screen_Glide(Thumbnail_View_V.this,screen_img,spawn_img_2);



                                                //효장례식장만 on 2개 있을경우 광고 1개  만나옴
//                                                spawn_img_1.setVisibility(View.VISIBLE);
                                                spawn_img_2.setVisibility(View.VISIBLE);


                                                //spawn_img_1.setImageBitmap(spawn_img_url_Bitmap);
                                                // spawn_img_2.setImageBitmap(spawn_img_url_Bitmap);
                                            }

                                            currentState = 0;
                                        }

                                        if (totalRoomCnt <= 4)
                                        {
                                            // 한 레이아웃당 방이 4개 있는데
                                            // 총 방의 갯수가 4개 이하일 경우는
                                            // 방이 점멸될 필요가 없다.
                                            currentState = -1;
                                        }
                                    }
                                    catch (Exception ex)
                                    {
                                        LogService.error(ex.getMessage(), ex);
                                    }

                                }
                            });

                        }
                    };

                    roomChangeTimer.schedule(timerTask, 0, 5000);
                }
            }
        });
    }

    private void toggleView()
    {
        //web 데이터 읽어 오기 위해 딜레이

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {

                // WebText 2초 딜레이 화면 그리기

                if( mainViewOld != null) {
                    mainViewOld.setVisibility(View.INVISIBLE);
                }
                mainView.setVisibility(View.VISIBLE);

            }
        }, 3000);

    }

    private void mainViewCleaning()
    {
        //  mainView == backgroundView 같을 경우
        // backgroundView = null 변경

        if (mainViewOld == mainView) {
            mainViewOld = null;
        }
    }



}
