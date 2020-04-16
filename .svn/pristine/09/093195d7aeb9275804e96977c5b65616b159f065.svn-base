package com.nexgrid_fsp.myapplication.sharinglive;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nexgrid_fsp.myapplication.R;
import com.nexgrid_fsp.myapplication.log.LogService;
import com.nexgrid_fsp.myapplication.thumbil.Style.Thumbnail_sub;
import com.nexgrid_fsp.myapplication.vo.SharingTestVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.nexgrid_fsp.myapplication.sharinglive.SuperVariable.File_URL;


public class ModuleDataReturn{

    // JSON 으로 가져올 데이터 리스트
    public static String[] roomDataAll = new String[]
            {
                    "funeralroom_name", // 방번호
                    "death_name",       // 이름
                    "death_sex",        // 성별
                    "death_age",        // 나이
                    "death_face_image", // 이미지사진
                    "board_use_bg",     // 모소리 이미지 on_off
                    "board_bg_detail",  // 모소리 이미지
                    "death_exit",       // 입관일
                    "death_burrow",     // 발인일
                    "death_burrow_place",// 장지
                    "death_id",          // 분양소 데이터 확인여부
                    "monitor_on",       // 모니터 on_off
                    "board_template",   // Color index
            };

    // 분양소 컬러 리스트
    public static String[][] color_arr = new String[][]
            {
                    {"블루", "#024188", "#DDE9F0", "#8B9FB5"},

                    {"퍼블", "#34014F", "#CDD1DA", "#929191"},

                    {"그린", "#004E26", "#E6E8E1", "#747873"},

                    {"브라운", "#42210B", "#DAD8D7", "#666666"},

                    {"그레이", "#494645", "#D1D1D2", "#B3B3B3"},

                    {"블랙", "#333333", "#d1d1d2", "#7f7f80"}
            };


    public static String beforeRoomsInfo = "";
    public static String beforeScreenStr = "";
    public static String screenOffImage = "";


    /*================================================================================================*/
    // JSON 데이터 String 형으로 변환
    public static String resBodyToString(JSONObject resBody, String roomValue) {

        String roomDataReturn = "";

        try {
            roomDataReturn = resBody.get(roomValue).toString();

            if (roomDataReturn != null) {
                return roomDataReturn;
            } else {
                return "";
            }

        } catch (Exception ex) {
            LogService.error(ex.getMessage(), ex);
        }
        return roomDataReturn;

    }
    // 날자 데이터
    public static String dateDataReturn(String ReceiveDate) {

        String dateData = "";
        try {
            dateData = new SimpleDateFormat("MM월 dd일 HH시 mm분").format(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(ReceiveDate));

            return dateData;
        } catch (ParseException e) {
            LogService.error(e.getMessage(), e);
        }
        return dateData;
    }
    // 성별,나이 데이터
    public static String genderAgeDataReturn(String gender, String age) {

        String gender_age = "";

        if (gender.equals("남자")) {
            gender_age = "(남/" + age + "세)";
        } else if (gender.equals("여자")) {
            gender_age = "(여/" + age + "세)";
        } else {
            gender_age = "(" + gender + "/" + age + "세)";
        }
        return gender_age;
    }
    // view 에서 5시 반향 있는 Image
    public static int backgroundImageDataReturn(String jsonBack_On_Off, String jsonBack_img) {
        // Back 그라운드 이미지 데이터 res 폴터 안에 있는엇들
        if (jsonBack_On_Off.equals("Y")) {
            int back_img = 0;

            if (jsonBack_img.equals("기독교_1")) {
                back_img = R.drawable.gdg_2;
            } else if (jsonBack_img.equals("기독교_2")) {
                back_img = R.drawable.gdg_1;
            } else if (jsonBack_img.equals("천주교_1")) {
                back_img = R.drawable.jg_1;
            } else if (jsonBack_img.equals("천주교_2")) {
                back_img = R.drawable.jg_2;
            } else if (jsonBack_img.equals("불교_1")) {
                back_img = R.drawable.fg_1;
            } else if (jsonBack_img.equals("불교_2")) {
                back_img = R.drawable.fg_2;
            } else if (jsonBack_img.equals("국기_1")) {
                back_img = R.drawable.gg_1;
            } else if (jsonBack_img.equals("국기_2")) {
                back_img = R.drawable.gg_2;
            } else if (jsonBack_img.equals("국화_1")) {
                back_img = R.drawable.gh_1;
            } else if (jsonBack_img.equals("국화_2")) {
                back_img = R.drawable.gh_2;
            } else if (jsonBack_img.equals("국화_3")) {
                back_img = R.drawable.gh_3;
            }

            Log.i("EAST", "Back 이미지 저장");
            return back_img;
        } else {
            Log.i("EAST", "BackImg_OFF 상태입니다.");
        }
        Log.i("EAST", "Back 이미지 가 없습니다.");
        return 0;
    }
    // 서버 JSON 데이터 변경 되었는지 확인
    public static boolean roomDataSameCheck(JSONObject resBody) {


        try {
            String rooms = "rooms";
            if (
                    resBody != null &&
                            resBody.has(rooms) &&
                            (
                                    resBody.get(rooms).toString().equals(beforeRoomsInfo) == false ||
                                            (
                                                    resBody.has("screen_save_image_v") &&
                                                            resBody.getString("screen_save_image_v").toString().equals(beforeScreenStr) == false
                                            )
                            )
            ) {

                beforeRoomsInfo = resBodyToString(resBody, rooms);
                beforeScreenStr = resBodyToString(resBody, "screen_save_image_v");


                // 데이터 변경 안되었을떄
                return true;
            } else {
                //데이터가 변경 되었을때
                return false;
            }
        } catch (Exception ex) {
            LogService.error(ex.getMessage(), ex);
        }
        return false;
    }
    // 서버 JSON 데이터 Set
    public static SharingTestVO deviceRoomDataReturnTest(JSONObject resBody) {

        SharingTestVO sharingTestVO = new SharingTestVO();

        try {

            sharingTestVO.setFuneralroom_name(resBodyToString(resBody, roomDataAll[0]));
            sharingTestVO.setDeath_name(resBodyToString(resBody, roomDataAll[1]));
            sharingTestVO.setDeath_sex(resBodyToString(resBody, roomDataAll[2]));
            sharingTestVO.setDeath_age(resBodyToString(resBody, roomDataAll[3]));
            sharingTestVO.setDeath_face_image(resBodyToString(resBody, roomDataAll[4]));
            sharingTestVO.setBoard_use_bg(resBodyToString(resBody, roomDataAll[5]));
            sharingTestVO.setBoard_bg_detail(resBodyToString(resBody, roomDataAll[6]));
            sharingTestVO.setDeath_exit(resBodyToString(resBody, roomDataAll[7]));
            sharingTestVO.setDeath_burrow(resBodyToString(resBody, roomDataAll[8]));
            sharingTestVO.setDeath_burrow_place(resBodyToString(resBody, roomDataAll[9]));
            sharingTestVO.setDeath_id(resBodyToString(resBody, roomDataAll[10]));
            sharingTestVO.setMonitor_on(resBodyToString(resBody, roomDataAll[11]));
            sharingTestVO.setBoard_template(resBodyToString(resBody, roomDataAll[12]));
            sharingTestVO.setWebFamilyList(resBody.toString());


            return sharingTestVO;

        } catch (Exception ex) {
            LogService.error(ex.getMessage(), ex);
        }

        return sharingTestVO;
    }
    // view Ui 레이아웃 색상 결정
    public static String[] colorAllChange(String colorDataindex) {
        //color_arr[0][0] = 색상결정
        //color_arr[0][1] = mainC
        //color_arr[0][2] = subC
        //color_arr[0][3] = lineC

        //main Color  = mainC
        //sub  Color  = subC
        //line Color  = lineC

        String color_name = "블루";
        //background box Color
        String mainC = "#024188";
        //backgrond Color
        String subC = "#DDE9F0";
        //line Color
        String lineC = "#8B9FB5";

        // Color Data Index 컬러 값
        // String 으로 올수 있기 때문에 else 부터 결정됨
        int index = 0;

        // 기본 디폴드 값
        if (colorDataindex.equals("블랙") || colorDataindex.equals("") || colorDataindex == null) {
            color_name = color_arr[0][0];
            mainC = color_arr[0][1];
            subC = color_arr[0][2];
            lineC = color_arr[0][3];
        } else {
            index = Integer.parseInt(colorDataindex);
            color_name = color_arr[index][0];
            mainC = color_arr[index][1];
            subC = color_arr[index][2];
            lineC = color_arr[index][3];
        }


        return new String[]{color_name, mainC, subC, lineC};
    }
    // view Ui 레이아웃 색상 변경
    public static void setBoxColorTest(Thumbnail_sub lyt, SharingTestVO sharingTestVO, LinearLayout layoutColor) {
        try {
//            String nameColor = ModuleDataReturn.colorAllChange(sharingTestVO.getBoard_template())[0];
            String mainC = ModuleDataReturn.colorAllChange(sharingTestVO.getBoard_template())[1];
            String subC = ModuleDataReturn.colorAllChange(sharingTestVO.getBoard_template())[2];
            String lineC = ModuleDataReturn.colorAllChange(sharingTestVO.getBoard_template())[3];


//            String mainC = sharingTestVO.getMainC();
//            String subC  = sharingTestVO.getSubC();
//            String lineC = sharingTestVO.getLineC()
//


            // Background Color
            lyt.title_tab_box.setBackgroundColor(Color.parseColor(mainC));
            lyt.people_title.setBackgroundColor(Color.parseColor(mainC));
            lyt.start_date_box.setBackgroundColor(Color.parseColor(mainC));
            lyt.end_date_box.setBackgroundColor(Color.parseColor(mainC));
            lyt.survival_gps_box.setBackgroundColor(Color.parseColor(mainC));
            //Background Box Color
            lyt.start_date.setBackgroundColor(Color.parseColor(subC));
            lyt.end_date.setBackgroundColor(Color.parseColor(subC));
            lyt.survival_gps.setBackgroundColor(Color.parseColor(subC));
            lyt.WebText_View.setBackgroundColor(Color.parseColor(subC));
            //LineBackGroundColor
            layoutColor.setBackgroundColor(Color.parseColor(lineC));

        } catch (Exception ex) {
            LogService.error(ex.getMessage(), ex);
        }
    }

    /*================================================================================================*/

    /**
     *
     * @param context : Activity
     * @param resBody : 서버 JSON 데이터
     * @param lyt : Activity 해당 xml 연결
     * @param layoutColor :  Activity 해당 xml 연결
     * @param whCheck :  상주 리스트 View 타입 확인
     */



    // 메인 실행되는곳
    public static void getDeviceRoom(final Context context, final JSONObject resBody, Thumbnail_sub[] lyt, LinearLayout layoutColor, String whCheck) {

        try {

            screenOffImage = ScreenImageDataCall.screenDataCall(context, resBody);


            if (ModuleDataReturn.roomDataSameCheck(resBody)) {

                JSONArray deviceRoomArrayList = ((JSONArray) resBody.get("rooms"));
                for (int i = 0; i < deviceRoomArrayList.length(); i++) {

                    // rooms Data
                    JSONObject deviceRoomData = (JSONObject) deviceRoomArrayList.get(i);
                    //vo Data set
                    SharingTestVO deviceRoomDatastest = ModuleDataReturn.deviceRoomDataReturnTest(deviceRoomData);


                    // 썸네일형 까지 사용가능
                    ModuleDataReturn.setUiRoomDataTest(context, lyt[i%4], deviceRoomDatastest, layoutColor, whCheck);



                }
            } else {
                LogService.info("deviceRoomData 중복");
            }

        } catch (Exception ex) {
            LogService.error("deviceRoomConnection : ", ex);
        }

    }

    /*================================================================================================*/

    /**
     * @param context       : Activity
     * @param lyt           : 레이아웃 Ui 데이터
     * @param sharingTestVO : room 데이터
     * @param layoutColor   : 뒤배경 컬러
     * @param whCheck       : 모니터 세로 형 가로형 체크
     */
    public static void setUiRoomDataTest(final Context context, final Thumbnail_sub lyt, final SharingTestVO sharingTestVO, final LinearLayout layoutColor, final String whCheck) {



        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (sharingTestVO.getMonitor_on().equals("on")) {

                    lyt.view_off.setVisibility(View.GONE);

                    // WebView text StyleData 정해진다.
                    String finalHtml = WebviewScript.getSingieDataScript("v" , 50,30,30);

                    if (whCheck.equals("v")) {

                        // WebView 데이터 Ui
                        finalHtml = WebviewScript.getSingieDataScript("v" , 50,30,30);


                        lyt.name.setText("故" + sharingTestVO.getDeath_name());

                    } else if (whCheck.equals("h")) {

                        // WebView 데이터 Ui
                        finalHtml = WebviewScript.getSingieDataScript("h" , 50,30,30);

                        lyt.name.setText(sharingTestVO.getDeath_name());
                    }

                    int board_bg = ModuleDataReturn.backgroundImageDataReturn(sharingTestVO.getBoard_use_bg(), sharingTestVO.getBoard_bg_detail());

                    // WebView 데이터 넣음
                    finalHtml = finalHtml.replace("__data__", sharingTestVO.getWebFamilyList());

                    Log.i("TAG",sharingTestVO.getWebFamilyList());

                    lyt.title_num.setText(sharingTestVO.getFuneralroom_name());

                    lyt.gender_age.setText(genderAgeDataReturn(sharingTestVO.getDeath_sex(), sharingTestVO.getDeath_age()));
                    Glide.with(context).load(File_URL + sharingTestVO.getDeath_face_image()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(lyt.profile_img);
                    Glide.with(context).load(board_bg).dontAnimate().into(lyt.server_back_img);
                    lyt.WebText_View.loadDataWithBaseURL(null, finalHtml, "text/html", "UTF-8", null);
                    lyt.start_date.setText(dateDataReturn(sharingTestVO.getDeath_exit()));
                    lyt.end_date.setText(dateDataReturn(sharingTestVO.getDeath_burrow()));
                    lyt.survival_gps.setText(sharingTestVO.getDeath_burrow_place());



                } else {

                    lyt.view_off.setVisibility(View.VISIBLE);
                    resetUiRoomDataTest(context, lyt, new SharingTestVO());

                }
                // Off 되있어도 색깔 유지됨
                ModuleDataReturn.setBoxColorTest(lyt, sharingTestVO, layoutColor);
            }
        });
    }

    public static void resetUiRoomDataTest(Context context, Thumbnail_sub lyt, SharingTestVO sharingTestVO) {

        lyt.title_num.setText("0호실");
        lyt.name.setText("故");
        lyt.gender_age.setText(genderAgeDataReturn("", ""));
        Glide.with(context).load("").into(lyt.profile_img);
        Glide.with(context).load("").into(lyt.server_back_img);
        lyt.WebText_View.loadDataWithBaseURL(null, "", "text/html", "UTF-8", null);
        lyt.start_date.setText("");
        lyt.end_date.setText("");
        lyt.survival_gps.setText("");
        ScreenImageDataCall.Screen_Glide(context, screenOffImage, lyt.off_img);
    }

    public static void webViewSettings(Thumbnail_sub lyt , int size){

                lyt.WebText_View.setHorizontalScrollBarEnabled(false);
                lyt.WebText_View.setVerticalScrollBarEnabled(false);
                lyt.webSettings.setJavaScriptEnabled(true);
                lyt.WebText_View.setHorizontalScrollBarEnabled(false);
                lyt.WebText_View.setVerticalScrollBarEnabled(false);
                lyt.webSettings = lyt.WebText_View.getSettings();
                lyt.webSettings.setJavaScriptEnabled(true);
                // Initial webview
                lyt.webSettings.setUseWideViewPort(true);       // wide viewport를 사용하도록 설정
                lyt.webSettings.setLoadWithOverviewMode(true);  // 컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 조정

                lyt.webSettings.setDefaultFontSize(8);
                lyt.WebText_View.setInitialScale(size);


    }
}
