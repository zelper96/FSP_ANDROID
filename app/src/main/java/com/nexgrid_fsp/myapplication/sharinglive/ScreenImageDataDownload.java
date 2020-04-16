package com.nexgrid_fsp.myapplication.sharinglive;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;

import com.nexgrid_fsp.myapplication.log.LogService;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


public  class ScreenImageDataDownload{

    public static String test(Context context, String url_str) {
        //사용자가 다운로드 중 파워 버튼을 누르더라도 CPU가 잠들지 않도록 해서
        //다시 파워버튼 누르면 그동안 다운로드가 진행되고 있게 됩니다.
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, context.getClass().getName());
        mWakeLock.acquire();

        int count;
        long FileSize = -1;
        InputStream input = null;
        OutputStream output = null;
        URLConnection connection = null;
        String nameScreen = "Android_fsp.screen_img";

        try {
            URL url = new URL(url_str);
            connection = url.openConnection();
            connection.connect();

            //파일 크기를 가져옴
            FileSize = connection.getContentLength();

            //URL 주소로부터 파일다운로드하기 위한 input stream
            input = new BufferedInputStream(url.openStream(), 8192);

            //내부 저장소 에서 파일 가져옴
            File path = context.getFilesDir();
            File outputFile = new File(path, nameScreen); //파일명까지 포함함 경로의 File 객체 생성

            // SD카드에 저장하기 위한 Output stream
            output = new FileOutputStream(outputFile);


            byte data[] = new byte[1024];
            long downloadedSize = 0;

            while ((count = input.read(data)) != -1) {
                //사용자가 BACK 버튼 누르면 취소가능


                //Flie Down Load : 용량 , 숫자%
                FileDownLoadcheck(FileSize,downloadedSize,count);

                //파일에 데이터를 기록합니다.
                output.write(data, 0, count);
            }

            // Flush output
            output.flush();

            // Close streams
            output.close();
            input.close();

        } catch (Exception ex) {
            LogService.error("Screen Download : Exception  = ",ex);
        }
        finally {

            try {

                if (output != null) { output.close(); }

                if (input != null) { input.close(); }

            }
            catch (IOException ignored) {
//                Log.e("EAST",""+ignored.getMessage());
                LogService.error("Screen Download : IOException : ",ignored);
            }

            mWakeLock.release();

        }

        return context.getFilesDir() + "/"+nameScreen;
    }

    public static void FileDownLoadcheck(Long FileSize, Long downloadedSize, int count) {
        downloadedSize += count;

                // File Download  Size check
                if (FileSize > 0)
                {
                    float per = ((float)downloadedSize/FileSize) * 100;
                    String str = "Downloaded " + downloadedSize + "KB / " + FileSize + "KB (" + (int)per + "%)";
                // publishProgress("" + (int) ((downloadedSize * 100) / FileSize), str);
                // 다운로드 확인 로고
                LogService.info("Screen File Download : " + (int) ((downloadedSize * 100) / FileSize)+""+str);
                }
    }




}