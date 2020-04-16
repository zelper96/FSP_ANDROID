package com.nexgrid_fsp.myapplication.list;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.nexgrid_fsp.myapplication.BeginFSPActivity;
import com.nexgrid_fsp.myapplication.impl.BasesImple;
import com.nexgrid_fsp.myapplication.log.LogService;
import com.nexgrid_fsp.myapplication.sharinglive.HttpUtil;
import com.nexgrid_fsp.myapplication.R;
import com.nexgrid_fsp.myapplication.sharinglive.RoomsValidation;
import com.nexgrid_fsp.myapplication.vo.ListViewItemVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.cereal;
import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.room_url;

public class ViewListPage extends BeginFSPActivity implements BasesImple {

    private RecyclerView rv;
    private TextView todata;
    private TextView titlename;

    private String to_date_time;
    private String title_name;

    private setListUiDataItemVH list_view;
    private ArrayList<ListViewItemVO> items = new ArrayList<>();
    private String beforeRoomsInfo = "{}";

    private int currentState = 0;

    // 서버 호출 Timer
    private Timer serverStarTimer = new Timer();
    // 리스트가 8개 이상일 경우 자동 페이지 변경
    private Timer roomChangeTimer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScreenHeight(this);
        hideNavigationBar();

        setContentView(R.layout.activity_list_view_page);
        init();

        list_view = new setListUiDataItemVH(ViewListPage.this, items);


        serverConnection(room_url, cereal);

    }

    @Override
    public void init() {
        rv = findViewById(R.id.rv);
        todata = findViewById(R.id.todata);
        titlename = findViewById(R.id.title_name);
    }

    private void TimeTab(final String url, final JSONObject resBody) {
        try {
            // 분양소 이름
            if (resBody != null && resBody.has("funeralhall_name") && resBody.get("funeralhall_name").toString().equals(title_name) == false) {
                title_name = resBody.get("funeralhall_name").toString();
            } else {
                LogService.info("funeralhall_name 데이터가 동일 합니다.");
            }
            // 현제 시간 , 장례식장 이름 Tap 보여줌
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 a kk:mm");

            Calendar time = Calendar.getInstance();

            to_date_time = simpleDateFormat.format(time.getTime());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    titlename.setText(title_name);
                    todata.setText(to_date_time);
                }
            });
        } catch (Exception ex) {
            LogService.error(url, null);
            LogService.error(ex.getMessage(), ex);
        }
    }


    @Override
    public void serverConnection(final String url, final String jsonBody) {
        TimerTask serverRecive = new TimerTask() {

            @Override
            public void run() {

                JSONObject resBody = HttpUtil.requestBody(url, "POST", jsonBody);

                // 상단 Tab 메소드
                TimeTab(url, resBody);

                //server = 서버 데이터 연결
                setServerDataCheck(url, resBody);

            }
        };
        serverStarTimer.scheduleAtFixedRate(serverRecive, 0, 10000);
    }

    @Override
    public void setServerDataCheck(String url, JSONObject resBody) {
        try {


            // 서버 데이터
            if (resBody != null && resBody.has("rooms") && resBody.get("rooms").toString().equals(beforeRoomsInfo.toString()) == false) {

                beforeRoomsInfo = resBody.get("rooms").toString();

                boolean jsonCheck = RoomsValidation.roomsValidation(resBody);

                if (jsonCheck) {
                    getDeviceRoom(resBody);
                } else {
                    LogService.error("json value not found", null);
                }

            } else {
                LogService.info("응답 룸 정보 동일");
            }
        } catch (Exception ex) {
            LogService.error(url, null);
            LogService.error(" server_Date : JSON 취득 에러", ex);
        }
    }


    @Override
    public void getDeviceRoom(JSONObject resBody) {

        try {

            JSONArray jsonarray_rooms = ((JSONArray) resBody.get("rooms"));

            if (jsonarray_rooms != null) {

                // 룸 이전 정보 삭제
                items.clear();

                Boolean lineDivision = false;

                // room 갯수 만큼 data 불러오기
                for (int i = 0; i < jsonarray_rooms.length(); i++) {

                    // JSON 파싱 자료
                    JSONObject json_rooms_data = (JSONObject) jsonarray_rooms.get(i);

                    // 방번호
                    String num = json_rooms_data.get("funeralroom_name").toString();

                    //사람 이름
                    String name = json_rooms_data.get("death_name").toString();

                    // 입관일 월, 일, 시, 분
                    String death_enter = json_rooms_data.get("death_exit").toString();
                    String start_date = new SimpleDateFormat("MM월 dd일 HH시 mm분").format(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(death_enter));


                    // 발인일 월, 일, 시, 분
                    String death_exit = json_rooms_data.get("death_burrow").toString();
                    String end_date = new SimpleDateFormat("MM월 dd일 HH시 mm분").format(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(death_exit));


                    String burialPlot = json_rooms_data.get("death_burrow_place").toString();

                    // 상주 JSONArray 리스트
                    JSONArray json_death_families = (JSONArray) json_rooms_data.get("death_families");
                    String death_families = "";

                    //분양소가 데이터 가 있는지 없는지 확인
                    String death_id = json_rooms_data.get("death_id").toString();

                    if (json_rooms_data.get("monitor_on").equals("on")) {

                        if (death_id.equals("0")) {
                            Log.e("EAST", "Time 비어있습니다");
                        } else {
                            for (int j = 0; j < json_death_families.length(); j++) {
                                JSONObject json_death_families_data = (JSONObject) json_death_families.get(j);

                                try {

                                    if (json_death_families_data != null) {

                                        if (j == 3) {
                                            death_families += json_death_families_data.get("name").toString() + "\n";
                                        } else {
                                            death_families += json_death_families_data.get("name").toString();
                                            if (j != json_death_families.length() - 1) {
                                                death_families += ", ";
                                            }
                                        }

                                    } else {
                                        Log.i("EAST", "상주가 없습니다.");
                                    }
                                } catch (Exception ex) {
                                    Log.e("EAST", "" + ex);
                                }
                            }

                            death_families = death_families.trim();
                        }
                        //장지


                        // true = !false , false = !true
                        lineDivision = !lineDivision;

                        // list color (true : #0080FF, false : #4FA2F5)
                        String back_color = lineDivision ? "#0080FF" : "#4FA2F5";

                        // 빈소, 고인, 상주, 입관일, 발인일, 장지, 리스트 색깔
                        items.add(new ListViewItemVO(num, name, death_families, start_date, end_date, burialPlot, back_color));
                    } else {
                        LogService.info("room 데이터 off 상태");
                    }
                }

                setUiRoomData(items);

                //  Layout 개수 구분
            } else {
                LogService.error("jsonarray_rooms.length 에러", null);
            }

        } catch (Exception ex) {
            LogService.error(ex.getMessage(), ex);
        }

    }

    private void setUiRoomData(final ArrayList<ListViewItemVO> items) {
        currentState = 0;

        if (roomChangeTimer != null) {
            roomChangeTimer.cancel();
            roomChangeTimer = null;
        }

        roomChangeTimer = new Timer();

        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ArrayList<ListViewItemVO> currentItemList = new ArrayList<>();
                        int loopCnt = (items.size() / 8) + ((items.size() % 8) > 0 ? 1 : 0);

                        for (int i = currentState * 8; i < (((items.size() - currentState * 8) > 8) ? currentState * 8 + 8 : items.size()); i++) {
                            currentItemList.add(items.get(i));
                        }

                        list_view = new setListUiDataItemVH(ViewListPage.this, currentItemList);

                        GridLayoutManager manager = new GridLayoutManager(ViewListPage.this, 1);
                        rv.setLayoutManager(manager);
                        rv.setHasFixedSize(true);

                        rv.setAdapter(list_view);

                        currentState++;

                        if (loopCnt <= currentState) {
                            currentState = 0;
                        }

                    }
                });

            }
        };

        roomChangeTimer.schedule(timerTask, 0, 5000);
    }


}

