package com.nexgrid_fsp.myapplication.profile;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
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


public  class MP3
{

    private File outputFile; //파일명까지 포함한 경로
    private File path;//디렉토리경로

    //mp3 파일명
    private String nameMp3 = "Android_fsp.mp3";

    public String on ;

    private MediaPlayer   mPlayer ;
    private Context context;


    public void mp3_date(String url, DownloadFilesTask downloadTask, Context context)
    {

        this.context = context;
        downloadTask = new DownloadFilesTask(context);
        downloadTask.execute(url);

    }


    public void mp3Run()
    {
        if(mPlayer == null)
        {
            mPlayer = new MediaPlayer();
        }

        if (on.equals("off") && mPlayer != null)
        {
            mPlayer.stop();
            mPlayer.reset();
            mPlayer = null;
        }
        else if (on.equals("on"))
        {
            // 전에 있던 음악이 잇을지 모르니 stop 후 실행
            mPlayer.stop();
            mPlayer.reset();
            mPlayer = null;
            //==============================================

            mPlayer = MediaPlayer.create(context, Uri.parse(context.getFilesDir() + "/"+nameMp3));
            mPlayer.setLooping(true);
            mPlayer.start();

            LogService.info("mp3 다운로드 완료");
        }
    }

    public class DownloadFilesTask extends AsyncTask<String, String, Long> {

        private Context context;
        private PowerManager.WakeLock mWakeLock;

        private DownloadFilesTask(Context context) {
            this.context = context;
        }


        //파일 다운로드를 시작하기 전에 프로그레스바를 화면에 보여줍니다.
        @Override
        protected void onPreExecute() { //2
            super.onPreExecute();

            //사용자가 다운로드 중 파워 버튼을 누르더라도 CPU가 잠들지 않도록 해서
            //다시 파워버튼 누르면 그동안 다운로드가 진행되고 있게 됩니다.
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
            mWakeLock.acquire();

        }


        //파일 다운로드를 진행합니다.
        @Override
        protected Long doInBackground(String... string_url) { //3
            int count;
            long FileSize = -1;
            InputStream input = null;
            OutputStream output = null;
            URLConnection connection = null;

            try {
                URL url = new URL(string_url[0]);
                connection = url.openConnection();
                connection.connect();


                //파일 크기를 가져옴
                FileSize = connection.getContentLength();

                //URL 주소로부터 파일다운로드하기 위한 input stream
                input = new BufferedInputStream(url.openStream(), 8192);

                //내부 저장소 에서 파일 가져옴
                path= context.getFilesDir();
                outputFile= new File(path, nameMp3); //파일명까지 포함함 경로의 File 객체 생성



                // SD카드에 저장하기 위한 Output stream
                output = new FileOutputStream(outputFile);

                byte data[] = new byte[1024];
                long downloadedSize = 0;
                while ((count = input.read(data)) != -1)
                {
                    //사용자가 BACK 버튼 누르면 취소가능
                    if (isCancelled())
                    {
                        input.close();
                        return Long.valueOf(-1);
                    }

                    downloadedSize += count;

                    if (FileSize > 0)
                    {
                        float per = ((float)downloadedSize/FileSize) * 100;
                        String str = "Downloaded " + downloadedSize + "KB / " + FileSize + "KB (" + (int)per + "%)";
                        publishProgress("" + (int) ((downloadedSize * 100) / FileSize), str);

                    }

                    //파일에 데이터를 기록합니다.
                    output.write(data, 0, count);
                }
                // Flush output
                output.flush();

                // Close streams
                output.close();
                input.close();

            }
            catch (Exception e)
            {
                Log.e("Error: ", e.getMessage());
            }
            finally
            {
                try
                {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                }
                catch (IOException ignored)
                {
                    Log.e("EAST",""+ignored);
                }

                mWakeLock.release();

            }
            return FileSize;
        }


        //파일 다운로드 완료 후
        @Override
        protected void onPostExecute(Long size) { //5
            super.onPostExecute(size);



                try
                {
                    if (size>0)
                    {
                        mp3Run();
                    }

                }
                catch (Exception ex)
                {
                    Log.e("EAST",""+ex.getMessage());
                }

        }

    }

}