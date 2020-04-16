package com.nexgrid_fsp.myapplication.sharinglive;

import android.content.Context;

import com.nexgrid_fsp.myapplication.log.LogService;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class HttpUtil
{
    // Http Request Body 요청
    public static JSONObject requestBody(String reqUrl, String method, String reqBody)
    {

        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;

        try
        {
            URL url = new URL(reqUrl);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod(method);

            conn.setRequestProperty("Content-Type", "application/json");

            byte[] postDataBytes = reqBody.getBytes("UTF-8");

            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));

            conn.setDoInput(true);

            conn.setDoOutput(true);

            if(reqBody != null && reqBody.isEmpty() == false)
            {
                conn.getOutputStream().write(postDataBytes);
            }

            inputStreamReader = new InputStreamReader(conn.getInputStream(), "UTF-8");

            bufferedReader  = new BufferedReader(inputStreamReader);

            StringBuffer buffer = new StringBuffer();

            String inputLine = null;

            while((inputLine = bufferedReader.readLine()) != null)
            {
                buffer.append(inputLine);

            }

            return new JSONObject(buffer.toString());
        }
        catch (Exception ex)
        {
//            Log.e("EAST", "HttpUtil : Exception = "+ex.getMessage());
            LogService.error(ex.getMessage(),ex);
        }
        finally
        {
            try
            {
                if(inputStreamReader != null)
                {
                    inputStreamReader.close();
                }

                if(bufferedReader != null)
                {
                    bufferedReader.close();
                }
            }
            catch (IOException ioEx)
            {
//                Log.e("EAST", "HttpUtil : IOException = " + ioEx.getMessage());
                LogService.error(ioEx.getMessage(),ioEx);
            }
        }

        return null;
    }


}
