package com.nexgrid_fsp.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyAutoRunApp extends BroadcastReceiver {

    public static boolean wasScreenOn = true;

    @Override
    public void onReceive(Context context, Intent intent) {

        //        앱이 꺼졋을 경우 , 다시 켯을 경우 실행 되는 메소드
                if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
                    //     앱이 꺼졋을 경우 , 다시 켯을 경우 실행 되는 메소드   Intent 불르기
                    Intent it = new Intent(context, MainActivity.class);
                    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(it);
                }
              }
            }
