package com.nexgrid_fsp.myapplication.impl;

import android.graphics.Bitmap;

import com.nexgrid_fsp.myapplication.thumbil.Style.Thumbnail_sub;
import com.nexgrid_fsp.myapplication.vo.SharingVO;

import org.json.JSONObject;

public interface BasesImple {


    public void init();

    // String jsonBody : 서버로 보낼 데이터
    public void serverConnection(String url,String jsonBody);

    // JSONObject resBody : 서버 에서 받은 return값
    public void setServerDataCheck(String url,JSONObject resBody);

    public void getDeviceRoom(JSONObject resBody);


}
