package com.nexgrid_fsp.myapplication.sharinglive;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class RoomsValidation
{
    // JSONOject 안에 자료가 있는지 없는지 확인
    public static boolean roomsValidation(JSONObject jsonObject)
    {

        try
        {
            if((jsonObject.has("resultCode") && jsonObject.get("resultCode").equals("0")) == false)
            {
                return false;
            }

            if (jsonObject.has("monitor"))
            {
                JSONObject jsonObj_monitor = (JSONObject)jsonObject.get("monitor");


                if(jsonObj_monitor.has("monitor_role")  == false)
                {
                    return  false;
                }

            }
            else
            {
                return false;
            }

            if (jsonObject.has("rooms"))
            {
                JSONArray jsonArray_rooms = (JSONArray) jsonObject.get("rooms");


                if((jsonArray_rooms.length() > 0) == false)
                {
                    return  false;
                }
                for(int i = 0; i < jsonArray_rooms.length(); i++)
                {
                    if (
                        //  JSON 데이터가 있는지 확인
                            ((JSONObject)jsonArray_rooms.get(i)).has("rooms")&&
                                    ((JSONObject)jsonArray_rooms.get(i)).has("monitor_on")&&
                                    ((JSONObject)jsonArray_rooms.get(i)).has("board_bg_detail")&&
                                    ((JSONObject)jsonArray_rooms.get(i)).has("death_enter") &&
                                    ((JSONObject)jsonArray_rooms.get(i)).has("death_families")&&
                                    ((JSONObject)jsonArray_rooms.get(i)).has("death_burrow")&&
                                    ((JSONObject)jsonArray_rooms.get(i)).has("death_face_image") &&
                                    ((JSONObject)jsonArray_rooms.get(i)).has("death_name")&&
                                    ((JSONObject)jsonArray_rooms.get(i)).has("death_burrow_place")&&
                                    ((JSONObject)jsonArray_rooms.get(i)).has("funeralroom_name")&&
                                    ((JSONObject)jsonArray_rooms.get(i)).has("death_exit")&&
                                    ((JSONObject) jsonArray_rooms.get(i)).has("bg_music_on") &&
                                    ((JSONObject) jsonArray_rooms.get(i)).has("death_use_ribbon") &&
                                    ((JSONObject) jsonArray_rooms.get(i)).has("death_bg_music")&&
                                    ((JSONObject)jsonArray_rooms.get(i)).has("monitors") == false)

                    {
                        return  false;
                    }
                }
            }
            else
            {
                return false;
            }
        }
        catch (Exception ex)
        {
            Log.e("EAST", "", ex);
            return false;
        }

        return true;
    }
}
