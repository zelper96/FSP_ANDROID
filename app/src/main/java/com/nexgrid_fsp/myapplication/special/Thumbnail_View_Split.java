package com.nexgrid_fsp.myapplication.special;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nexgrid_fsp.myapplication.BeginFSPActivity;
import com.nexgrid_fsp.myapplication.R;
import com.nexgrid_fsp.myapplication.sharinglive.WebviewScript;
import com.nexgrid_fsp.myapplication.thumbil.Style.Thumbnail_sub;
import com.nexgrid_fsp.myapplication.sharinglive.ModuleDataReturn;
import com.nexgrid_fsp.myapplication.vo.SharingVO;
import com.nexgrid_fsp.myapplication.sharinglive.HttpUtil;
import com.nexgrid_fsp.myapplication.sharinglive.ScreenImageDataCall;
import com.nexgrid_fsp.myapplication.sharinglive.RoomsValidation;

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


public class Thumbnail_View_Split extends BeginFSPActivity {


    // 방 레이아웃 관리 리스트
    private Thumbnail_sub[] roomLayoutManageList;

    // 방 한개 표시 레이아웃
    private Thumbnail_sub oneRoomLayout;
    private Thumbnail_sub toeRoomLayout;

    //Screen 이미지 다운로드
    private ImageView top_on_off;
    private String screen_img;

    // 이전 룸 정보
    private String beforeRoomsInfo = "{}";


    // 상세 정보 포함 룸 리스트
    private List<SharingVO> detailInfoRoomList = new ArrayList<>();
    private int totalRoomCnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ScreenWidht(this);
        hideNavigationBar();


        reqRoomList(room_url, cereal);


    }

    private void init()
    {
        // 방 하나 표시 레이아웃 초기화
        oneRoomLayout = findViewById(R.id.view_page_sub_0);
        toeRoomLayout = findViewById(R.id.view_page_sub_1);
        //off 경우 사진
        top_on_off    = findViewById(R.id.top_on_off);


        // 레리아웃 표시
        roomLayoutManageList = new Thumbnail_sub[]{oneRoomLayout, toeRoomLayout};
    }

    private void reqRoomList(final String reqUrl, final String reqSerial)
    {
        TimerTask room_timerTask = new TimerTask() {
            @Override
            public void run() {
                JSONObject resBody = HttpUtil.requestBody(reqUrl,"POST",reqSerial);


                //sceen  = 오프라인 상태 일때 나오는 사진
                screen_img = ScreenImageDataCall.screenDataCall(Thumbnail_View_Split.this,resBody);

                //server = 서버 데이터 연결
                server_Date(resBody);

            }
        };
        Timer timer =new Timer();
        timer.schedule(room_timerTask,0,3000);
    }

    private void server_Date(JSONObject resBody)
    {
        try {

            if (resBody != null && resBody.has("rooms") && resBody.get("rooms").toString().equals(beforeRoomsInfo.toString()) == false )
            {

                beforeRoomsInfo = resBody.get("rooms").toString();

                boolean jsonCheck = RoomsValidation.roomsValidation(resBody);

                if (jsonCheck)
                {
                    jsonDataViewAdd(resBody);
                }
                else
                {
                    Log.e("EAST", "json value not found");
                }

            }
            else
            {
                Log.i("EAST", "응답 룸 정보 동일");
            }
        }
        catch (Exception ex)
        {
            Log.e("EAST", " server_Date : JSON 취득 에러", ex);
        }
    }

    private void jsonDataViewAdd(JSONObject jsonObject)
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
                    String name = json_rooms_data.get("death_name").toString();

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

                    if (death_id != null) {
                        if (json_rooms_data.get("monitor_on").equals("on")) {

                            // Back 그라운드 이미지
                            // Back 그라운드 이미지 리턴 받음
                            int back_img = ModuleDataReturn.backgroundImageDataReturn(back_img_ON_OFF, back_img_JSON);


                            // 이미지 사진 Strain 비트맵 으로 저장
                            //이부분 삭제해야함
                            Bitmap profileBitmap = null;



                            if (gender.equals("남자")) {
                                gender_age = "(남/" + age +"세)";
                            } else if (gender.equals("여자")) {
                                gender_age = "(여/" + age + "세)";
                            }

                            // 데이터  Thunbanll_View 저장
                            setRoomInfos(num, name,gender_age,profileImg,profileBitmap, back_img, webresident, start_date, end_date, burialPlot);
                        }
                        else
                        {
                            Log.i("EAST", "room : off 됬을경우");
                        }

                    }
                    else
                    {
                        Log.e("EAST", "death_id Null");
                    }
                }
                //  Layout 개수 구분
                totalRoomCnt = detailInfoRoomList.size();
                Activity_Change();

            }
            else
            {
                Log.i("EAST", "jsonarray_rooms.length 에러");
            }
        }
        catch (Exception ex)
        {
            Log.e("EAST", ex.getMessage(), ex);
        }
    }

    private void setRoomInfos(String num, String name, String gender_age ,String profile ,Bitmap profileBitmap, int backImg,String webresident, String startDate, String endDate, String burialPlot)
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
            Glide.with(Thumbnail_View_Split.this).load(data.getProfile()).into(lyt.profile_img);
            Glide.with(Thumbnail_View_Split.this).load(data.getBackImg()).dontAnimate().into(lyt.server_back_img);
            lyt.WebText_View.loadDataWithBaseURL(null, finalHtml,"text/html", "utf-8", null);
            lyt.start_date.setText(data.getStartDate());
            lyt.end_date.setText(data.getEndDate());
            lyt.survival_gps.setText(data.getBurialPlot());
        }
        catch (Exception ex)
        {
            Log.e("EAST", "setLayoutData : "+ ex.getMessage());
        }
    }


    private void Activity_Change()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (totalRoomCnt == 0)
                {
                    setContentView(R.layout.activity_thumbnail_view_single_h);
                    init();
                    Screen_Glide(Thumbnail_View_Split.this,screen_img,top_on_off);
                    roomLayoutManageList[0].setVisibility(View.GONE);
                }
                else if (totalRoomCnt == 1)
                {
                    setContentView(R.layout.activity_thumbnail_view_single_h);
                    init();
                }
                else if (totalRoomCnt == 2)
                {
                    setContentView(R.layout.activity_thunmbnail_view_split);
                    init();
                }

                for (int i = 0; i<totalRoomCnt;i++)
                {
                    setLayoutData(roomLayoutManageList[i],detailInfoRoomList.get(i));
                }

            }
        });
    }

}
