package com.nexgrid_fsp.myapplication.special;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nexgrid_fsp.myapplication.BeginFSPActivity;
import com.nexgrid_fsp.myapplication.R;

import com.nexgrid_fsp.myapplication.sharinglive.WebviewScript;
import com.nexgrid_fsp.myapplication.special.Style.Special_sub;
import com.nexgrid_fsp.myapplication.sharinglive.ModuleDataReturn;
import com.nexgrid_fsp.myapplication.vo.SharingVO;
import com.nexgrid_fsp.myapplication.sharinglive.HttpUtil;
import com.nexgrid_fsp.myapplication.sharinglive.RoomsValidation;
import com.nexgrid_fsp.myapplication.log.LogService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.File_URL;

import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.cereal;
import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.room_url;
import static com.nexgrid_fsp.myapplication.sharinglive.ScreenImageUiSet.before_screen_string;

public class Thumbnail_View_BlueFlag extends BeginFSPActivity {


    // 방 레이아웃 관리 리스트
    private Special_sub[] roomLayoutManageList;

    // 방 한개 표시 레이아웃
    private Special_sub oneRoomLayout;
    private Special_sub toeRoomLayout;


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

    // json 데이터 원하는 방 정보
//    private JSONObject[] jsonObjects_arr = null;

    private    View mainViewOld = null;
    private    View mainView = null;
    private int  mainViewIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScreenHeight(this);
        hideNavigationBar();

        setContentView(R.layout.activity_special_default_blueflag);

        mainGround_color = findViewById(R.id.mainbackground_color);

        reqRoomList(room_url, cereal);


    }

    private void init()
    {
        // 방 하나 표시 레이아웃 초기화
        oneRoomLayout = mainView.findViewById(R.id.view_page_sub_0);
        toeRoomLayout = mainView.findViewById(R.id.view_page_sub_1);


        // 레리아웃 표시
        roomLayoutManageList = new Special_sub[]{oneRoomLayout, toeRoomLayout};

    }


    private void reqRoomList(final String reqUrl, final String reqSerial)
    {
        TimerTask task = new TimerTask()
        {

            @Override
            public void run()
            {

                JSONObject resBody = HttpUtil.requestBody(reqUrl, "POST", reqSerial);


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
            public void run() {

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



//=======================================================

                JSONObject json_monitor = resBody.getJSONObject("monitor");
                // 현황판 단말 코드 intex
                // 현황판 단말 연결 단말 코드 순서 가져오기
                String attach_order =json_monitor.get("attach_order").toString();

//=======================================================

                beforeRoomsInfo = resBody.get("rooms").toString();
                before_screen_string = beforeScreenStr;
                beforeScreenStr = resBody.getString("screen_save_image_v").toString();

                boolean jsonCheck = RoomsValidation.roomsValidation(resBody);

                if (jsonCheck)
                {
                    jsonrooms_data_Result(resBody,attach_order);
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



    private void jsonrooms_data_Result(final JSONObject jsonObject,String attach_order) {
        try {
            JSONArray jsonarray_rooms = (JSONArray) jsonObject.get("rooms");



            if (jsonarray_rooms != null) {
                totalRoomCnt = 0;
                // 룸 이전 정보 삭제
                detailInfoRoomList.clear();
                // room 갯수 만큼 data 불러오기


                // 장례식장 모든 모니터(OFF 포함) JSONObject 배열로 변경
                // 모니터 Data ON,OFF 데이터 전부 가져오기
                List<JSONObject> monitorList = new ArrayList<>();


                // 모니터 번호에 맞는 모니터의 데이터 리스트
                List<JSONObject> setMonitorList = new ArrayList<>();

                // 모니터 넘버 (attach_id)
                int monitor_num;

                try
                {
                    //현황판 단말코드 연결 순서
                    monitor_num = Integer.parseInt(attach_order);
                }
                catch (Exception ex)
                {
                    monitor_num = 0;
                    LogService.error(ex.getMessage(), ex);
                }

                for(int i = 0; i < jsonarray_rooms.length(); i++)
                {
                    // 장례식장 모니터가 ON 상태인지 아닌지를 확인
                    String monitor_on = ((JSONObject) jsonarray_rooms.get(i)).get("monitor_on").toString();

                    if (monitor_on.equals("on"))
                    {
                        // 장례식장 모니터가 ON Data 전부 ArrList 저장
                        monitorList.add((JSONObject) jsonarray_rooms.get(i));
                    }
                }

                // 모니터 하나당 2개씩 들어가기 때문에
                // i = 0 : 0호실, 1호실
                // i = 1 : 2호실, 3호실
                // i = 2 : 4호실, 5호실
                // i = 3 : 6호실, 7호실

                // monitor_num : 현황판 단말코드 index 순서
                // monitorList.size() : monitor ON  갯수
                for(int i = monitor_num * 2; i < monitorList.size(); i++)
                {

                    // monitor_num * 2 => [현황판 단말 코드 번호]는 각각 배치되어있는 [모니터들의 번호]를 의미한다. (ex. 0번 현황판, 1번 현황판 => 기기가 두개)
                    //                 => 이 현황판들의 번호는 0번부터 시작되어 갯수마다 1씩 늘어난다.
                    //                 => 이 각각의 현황판들은 한 화면에 두개의 레이아웃을 가지고 있다.
                    //                 => 즉 0 ~ 3번 현황판 있을 경우 레이아웃의 갯수는 8개가 된다.
                    //                 => (monitor_num * 2) 이 의미는 현황판 두개의 레이아웃을 갖고있고
                    //                 => 0번 현황판일 경우 시작 레이아웃 번호는 0 * 2 = 0, 1번 현황판일 경우 시작 레이아웃 번호는 1 * 2 = 2 가 된다.
                    //                 => 즉 (monitor_num + 1) * 2 의 의미는 현재 현황판 0 번모니터일 경우 1번 모니터의 레이아웃 번호가된다.
                    //                 => 즉 (i >= ((monitor_num + 1) * 2)) 의 의미는 현재 레이아웃 번호가 다음 현황판(1번 현황판) 의 순서의 차례가 왔을경우
                    //                 => 레이아웃을 더이상 넣어줄 필요가 없기때문에 사용한다. ( else => break)
                    //                 => 화면의 점멸하는 경우 (monitor_num == 0 && monitorList.size() > 8) 는 0번 현황판일 경우의므로 예외처리하는 것이다.
                    if(i >= ((monitor_num + 1) * 2))
                    {
                        // 현황판 단말코드 index 순서가 0번째 && monitor : On 인 data 갯수가 > 8 개 보다 많을경우
                        if(monitor_num == 0 && monitorList.size() > 8)
                        {

                            // 9번째 데이터 부터 시작
                            if(i < 8)
                            {
                                // 9호실부터 시작
                                i = 8;
                            }

                        }
                        else
                        {
                            // 현황판 단말코드 0번째 순서 가 안일경우
                            break;
                        }
                    }
                    // 데이터 추가
                    setMonitorList.add(monitorList.get(i));
                }

                // 10개 또는 9개인 것은 고정
                for (int i = 0; i < setMonitorList.size(); i++)
                {
                    // 방번호
                    String num  = setMonitorList.get(i).getString("funeralroom_name").toString();

                    // 이름
                    String name = "故"+setMonitorList.get(i).getString("death_name").toString();

                    //성별
                    String gender = setMonitorList.get(i).getString("death_sex").toString();

                    //나이
                    String age = setMonitorList.get(i).getString("death_age").toString();

                    //성별,나이
                    String gender_age = "";

                    // 뒤 배경 이미지 , ON OFF 상태 ,내부 사진주소
                    String back_img_ON_OFF = setMonitorList.get(i).getString("board_use_bg").toString();

                    //뒤 배경 이미지
                    String back_img_JSON = setMonitorList.get(i).getString("board_bg_detail").toString();

                    // 입관일 월, 일, 시, 분
                    String death_enter = setMonitorList.get(i).getString("death_exit").toString();
                    String start_date = new SimpleDateFormat("MM월 dd일 HH시 mm분").format(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(death_enter));


                    // 발인일 월, 일, 시, 분
                    String death_exit = setMonitorList.get(i).getString("death_burrow").toString();
                    String end_date = new SimpleDateFormat("MM월 dd일 HH시 mm분").format(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(death_exit));


                    // 상주 Web  리스트
                    String webresident = setMonitorList.get(i).toString();


                    //이미지 사진
                    String profileImg = File_URL + setMonitorList.get(i).get("board_face_image").toString();


                    //장지
                    String burialPlot = setMonitorList.get(i).get("death_burrow_place").toString();

                    //분양소가 데이터 가 있는지 없는지 확인
                    String death_id = setMonitorList.get(i).get("death_id").toString();

                    // 장례식장 모니터가 ON 상태인지 아닌지를 확인
                    String monitor_on = setMonitorList.get(i).get("monitor_on").toString();

                    // Back 그라운드 이미지
                    // Back 그라운드 이미지 리턴 받음
                    int back_img = ModuleDataReturn.backgroundImageDataReturn(back_img_ON_OFF, back_img_JSON);




                    if (gender.equals("남자"))
                    {
                        gender_age = "(남/" + age + "세)";
                    }
                    else if (gender.equals("여자"))
                    {
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
                    String background_Colors = setMonitorList.get(i).get("board_template").toString();

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
                        //background box Color
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


                    if (death_id != null)
                    {

                        if (monitor_on.equals("on"))
                        {
                            // 데이터  Thunbanll_View 저장
                            setRoomInfos(num, name, gender_age, profileImg, profileBitmap, back_img, webresident, start_date, end_date, burialPlot, bright_text_Color, dark_text_Color, mainC, subC, lineC);
                        }
                        else
                        {
                            //모니터가 off 되었을때 호출 됨
                        }

                    }
                    else
                    {
                        LogService.error("death_id Null 상태", new Exception("death_id is null"));
                    }
                }

                //  Layout 개수 구분
                totalRoomCnt = detailInfoRoomList.size();


                Activity_Change();



            } else {
                LogService.info("jsonarray_rooms.length 에러");
            }
        } catch (Exception ex) {
            LogService.error(ex.getMessage(), ex);
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


    private void setLayoutData(final Special_sub lyt, final SharingVO data)
    {

        try
        {
            String finalHtml = WebviewScript.getMultiDataScript("v" , 50,30,30);
            finalHtml = finalHtml.replace("__data__", data.getWebresident());






            lyt.title_num.setText(data.getnum());
            lyt.name.setText(data.getName());
            lyt.gender_age.setText(data.getGender_age());
            Glide.with(Thumbnail_View_BlueFlag.this).load(data.getProfile()).into(lyt.profile_img);
            Glide.with(Thumbnail_View_BlueFlag.this).load(data.getBackImg()).dontAnimate().into(lyt.server_back_img);
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

    private void resetLayoutData(final Special_sub lyt ,final SharingVO data)
    {

        try {
            lyt.title_num.setText("0호실");
            lyt.name.setText("");
            lyt.profile_img.setImageBitmap(null);
            Glide.with(Thumbnail_View_BlueFlag.this).load(R.drawable.view_on_off_v).into(lyt.profile_img);
            Glide.with(Thumbnail_View_BlueFlag.this).load("").dontAnimate().into(lyt.server_back_img);
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


    private void setResetLayout_Color(final Special_sub lyt)
    {
        try
        {


            //main_Color
            lyt.title_tab_box.setBackgroundColor(Color.parseColor(mainC));
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

    private void setColors(final Special_sub lyt ,final SharingVO data)
    {
        try
        {

            //main_Color
            lyt.title_tab_box.setBackgroundColor(Color.parseColor(data.getmainC()));
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


    private void Activity_Change()
    {

        Thumbnail_View_BlueFlag.this.runOnUiThread(new Runnable()
        {
            @Override
            public void run() {
                int tmpFourLytRooms = 0;
                int tmpOneLytRooms = 0;

                tmpFourLytRooms = totalRoomCnt /2;


                if (((totalRoomCnt % 2) == 0) == false)
                {
                    if ((totalRoomCnt % 2) == 1)
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


                    final int oneLytRooms = tmpOneLytRooms;
                    final int fourLytRooms = tmpFourLytRooms;
                    final int totalRooms = oneLytRooms + fourLytRooms;



                    currentState = 0;

                    if(roomChangeTimer != null)
                    {
                        roomChangeTimer.cancel();
                        roomChangeTimer = null;
                    }

                    roomChangeTimer = new Timer();

                    TimerTask timerTask =  new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run()
                                {

                                    try
                                    {

                                        // currentState : 점멸 상태 ( 0, 1 )
                                        // totalRooms : 화면에 점멸되는 레이아웃 숫자 ( 2개 )
                                        if (currentState >= 0)
                                        {
                                            mainViewOld = mainView;

                                            if (mainViewIndex == 0)
                                            {
                                                mainView = findViewById(R.id.special_view_blueflag_2_1);
                                            }
                                            else
                                            {
                                                mainView = findViewById(R.id.special_view_blueflag_2_2);
                                            }

                                            ++mainViewIndex;

                                            mainViewIndex %= 2;

                                            init();

                                            mainViewCleaning();

                                            toggleView();

                                            for (int i = currentState * 2; i < currentState * 2 + 2; i++)
                                            {
                                                if (i >= totalRoomCnt)
                                                {
                                                    if(currentState == 1)
                                                    {
                                                        setLayoutData(roomLayoutManageList[i % 2], detailInfoRoomList.get(i - currentState * 2));
                                                    }
                                                    else
                                                    {
                                                        LogService.info("Exception Case : " + i);
                                                        resetLayoutData(roomLayoutManageList[i % 2], new SharingVO());
                                                    }
                                                }
                                                else
                                                {
                                                    LogService.info("set i =>" + i);
                                                    setLayoutData(roomLayoutManageList[i%2], detailInfoRoomList.get(i));
                                                }
                                            }

                                            currentState++;

                                            // 총 레이아웃의 갯수 (2) 와 현재 방 점멸 상태가 같거나 점멸 상태보다 클경우
                                            // 초기 점멸상태로 돌아가야 한다.
                                            // 또한 총 방의 갯수 (2) 보다 작을경우
                                            // 화면이 점멸될 필요가 없기 때문에 점멸상태를 0으로 초기화 한다.
                                            if (totalRooms <= currentState || totalRoomCnt <= 2)
                                            {
                                                currentState = 0;
                                            }
                                        }

                                        // 방의 갯수가 2개보다 작거나 같을겨우 점멸될 필요가없다
                                        // 그렇기때문에 -1로 점멸상태를 고정시켜
                                        // 위의 로직( 한번 실행했기때문에 다시 달 필요없는 불필요한 로직)을
                                        // 실행하지 않도록 한다. ( if (currentState >= 0) 로직 )
                                        if (totalRoomCnt <= 2)
                                        {
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
