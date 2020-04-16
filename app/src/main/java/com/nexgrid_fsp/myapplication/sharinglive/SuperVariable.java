package com.nexgrid_fsp.myapplication.sharinglive;


import com.nexgrid_fsp.myapplication.log.LogService;

public class SuperVariable {

    //    상용서버
   //public  static String url = "http://ehwaphoto.com:80/REV3/";

    //    테스트 서버
    public  static String url = "http://ehwaphoto.com:90/";
    // 테스트 서버
//    public  static String url = "http://ehwaphoto.com:9090/REV3/";




    //Delete 예정
    //스크린 내부 저장소 string
    public static String getScreen_img;
    public static String before_screen_string;


    //  파일 읽어 오는  URL
    //  public   static String server_url_cali = "http://ehwaphoto.com/downloadImage/";
    public static String File_URL = url+"downloadImage/";



    //시리얼넘버
    public static String cereal = "";



    //쉐어드 프리퍼런스 파일 이름
    public static String PREFERENCE = "com.example.myapplication";

    //시리얼 넘버  전송
    public static String cerealServerSend = url+"generateDeviceSerial";

    // 승인 요청
    public static String cerealServerRequest = url+"checkDeviceSerial";

    // 룸 요청 서버 URL
    public static String room_url = url+"getDeviceRoom";
    // 광고 URL
    public static  String spawn_url = url+"uploadAdvertising";
    //spawn_img_url
    public static String Spawn_url =  url+"getLatAdvertising";




}
