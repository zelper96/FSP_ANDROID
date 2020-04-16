package com.nexgrid_fsp.myapplication.special;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.nexgrid_fsp.myapplication.BeginFSPActivity;
import com.nexgrid_fsp.myapplication.R;
import com.nexgrid_fsp.myapplication.sharinglive.HttpUtil;
import com.nexgrid_fsp.myapplication.sharinglive.RoomsValidation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;


import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.cereal;
import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.room_url;

public class List_Hyo_Jung extends BeginFSPActivity {

    private TextView name_301;
    private TextView name_302;
    private TextView name_303;
    private TextView name_304;
    private TextView name_201;
    private TextView name_202;
    private TextView name_101;
    private TextView[] name_all  ;

    private TextView age_301;
    private TextView age_302;
    private TextView age_303;
    private TextView age_304;
    private TextView age_201;
    private TextView age_202;
    private TextView age_101;
    private TextView[] age_all  ;

    private TextView text_list_301;
    private TextView text_list_302;
    private TextView text_list_303;
    private TextView text_list_304;
    private TextView text_list_201;
    private TextView text_list_202;
    private TextView text_list_101;
    private TextView[] text_list_all  ;

    // 이전에 있던정보
    private String beforeRoomsInfo = "{}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScreenHeight(this);
        hideNavigationBar();

        setContentView(R.layout.activity_special_hyo_jung);
        init();








        reqRoomList(room_url, cereal);









    }

    public void init()
    {
        name_301 = findViewById(R.id.name_301);
        name_302 = findViewById(R.id.name_302);
        name_303 = findViewById(R.id.name_303);
        name_304 = findViewById(R.id.name_304);
        name_202 = findViewById(R.id.name_202);
        name_201 = findViewById(R.id.name_201);
        name_101 = findViewById(R.id.name_101);

        age_304 = findViewById(R.id.age_304);
        age_303 = findViewById(R.id.age_303);
        age_302 = findViewById(R.id.age_302);
        age_301 = findViewById(R.id.age_301);
        age_202 = findViewById(R.id.age_202);
        age_201 = findViewById(R.id.age_201);
        age_101 = findViewById(R.id.age_101);

        text_list_304 = findViewById(R.id.text_list_304);
        text_list_303 = findViewById(R.id.text_list_303);
        text_list_302 = findViewById(R.id.text_list_302);
        text_list_301 = findViewById(R.id.text_list_301);
        text_list_202 = findViewById(R.id.text_list_202);
        text_list_201 = findViewById(R.id.text_list_201);
        text_list_101 = findViewById(R.id.text_list_101);



        name_all = new TextView[]{name_301,name_302,name_303,name_304,name_201,name_202,name_101};
        age_all = new TextView[]{age_301,age_302,age_303,age_304,age_201,age_202,age_101};
        text_list_all= new TextView[]{text_list_301,text_list_302,text_list_303,text_list_304,text_list_201,text_list_202,text_list_101};

    }



    public void reqRoomList(final String reqUrl, final String reqSerial) {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                final JSONObject resBody = HttpUtil.requestBody(reqUrl,"POST",reqSerial);

                try
                {

                    if (resBody != null && resBody.has("rooms") && resBody.get("rooms").toString().equals(beforeRoomsInfo.toString()) == false) {

                        beforeRoomsInfo = resBody.get("rooms").toString();

                        boolean jsonCheck = RoomsValidation.roomsValidation(resBody);

                        if (jsonCheck) {
                            // 서버 데이터 항목이 있는지 검사
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    jsonDataViewAdd(resBody);
                                }
                            });

                        } else {
                            Log.e("EAST", "json value not found");
                        }

                    }
                    else
                    {
                        Log.i("EAST","데이터가 동일합니다.");
                    }


                }
                catch(Exception ex)
                {
                    Log.e("EAST", "JSON 취득 에러", ex);
                }

            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,0,5000);




    }

    private void  jsonDataViewAdd(JSONObject jsonObj)
    {
        try
        {

            JSONArray jsonarray_rooms = ((JSONArray) jsonObj.get("rooms"));

            for (int i = 0; i < name_all.length; i++)
            {
                JSONObject json_rooms_data = (JSONObject) jsonarray_rooms.get(i);

                String name = json_rooms_data.get("death_name").toString();
                String age    =  json_rooms_data.get("death_age").toString();

                // 상주 JSONArray 리스트
                JSONArray json_death_families = (JSONArray) json_rooms_data.get("death_families");
                String death_families = "";

                for (int j = 0; j < json_death_families.length(); j++) {
                    JSONObject json_death_families_data = (JSONObject) json_death_families.get(j);

                    try
                    {
                        if (json_death_families_data!=null)
                        {

                            if (j == 6)
                            {
                                death_families += json_death_families_data.get("name").toString()+"\n";
                            }
                            else
                            {
                                death_families += json_death_families_data.get("name").toString();
                                if(j != json_death_families.length() - 1)
                                {
                                    death_families += ", ";
                                }
                            }
                        }
                        else
                        {
                            Log.i("EAST","상주가 없습니다.");
                        }
                    }
                    catch (Exception ex)
                    {
                        Log.e("EAST","상주 에러"+ex);
                    }
                }
                death_families = death_families.trim();


                if (json_rooms_data.get("monitor_on").equals("on")) {



                    setRoomInfos(name, age, death_families, i);
                }
                else
                {
                    Log.i("EAST","모니터오프");
                    name = "";
                    age  = "";
                    death_families = "";
                    setRoomriset("","","",i);

                }
            }



        } catch (JSONException ex) {
            Log.e("EAST",ex.getMessage());
        }


    }

    private void setRoomInfos(String name , String age , String death_families ,int room_size)
    {
        name_all[room_size].setText(name);
        age_all[room_size].setText("("+age+")");
        text_list_all[room_size].setText(death_families);
    }

    private void setRoomriset(String name , String age , String death_families ,int room_size)
    {
        name_all[room_size].setText(name);
        age_all[room_size].setText(age);
        text_list_all[room_size].setText(death_families);
    }

}
