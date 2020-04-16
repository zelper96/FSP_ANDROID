package com.nexgrid_fsp.myapplication.entrance;

import android.os.Bundle;
import android.widget.LinearLayout;
import com.nexgrid_fsp.myapplication.BeginFSPActivity;

import com.nexgrid_fsp.myapplication.impl.BasicsDataBase;
import com.nexgrid_fsp.myapplication.sharinglive.ModuleDataReturn;
import com.nexgrid_fsp.myapplication.sharinglive.HttpUtil;
import com.nexgrid_fsp.myapplication.thumbil.Style.Thumbnail_sub;

import com.nexgrid_fsp.myapplication.R;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;


import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.cereal;
import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.room_url;


public class Entance_View_H extends BeginFSPActivity implements BasicsDataBase{

    private Thumbnail_sub[] roomLayoutManageList;
    private Thumbnail_sub oneRoomLayout;
    private LinearLayout layoutColor ;

    private Timer serverDataReciveTimer = new Timer();
    private Thread serverThread = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScreenWidht(this);
        hideNavigationBar();

        setContentView(R.layout.activity_thumbnail_view_single_h);


        /* xml 데이터 연결*/
        init();

        /*서버 실행*/
        serverConnection();


    }


    @Override
    public void init() {

        oneRoomLayout = findViewById(R.id.view_page_sub_0);
        layoutColor  = findViewById(R.id.layout_Color);

        roomLayoutManageList = new Thumbnail_sub[]{oneRoomLayout};
    }

    @Override
    public void serverConnection() {
        TimerTask serverStartTimerTask = new TimerTask()
        {

            @Override
            public void run()
            {
                final JSONObject resBody = HttpUtil.requestBody(room_url, "POST", cereal);



                if (serverThread != null){
                    // stop 사용을 불가능 함으로 대신 사용
                    serverThread.setDaemon(true);
                    serverThread = null;
                }

                serverThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ModuleDataReturn.getDeviceRoom  (Entance_View_H.this, resBody, roomLayoutManageList, layoutColor, "h");
                    }
                });

                serverThread.start();

            }
        };
        serverDataReciveTimer.scheduleAtFixedRate(serverStartTimerTask, 0, 10000);
    }
}

