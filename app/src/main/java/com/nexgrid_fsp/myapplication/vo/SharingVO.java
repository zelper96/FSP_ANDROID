package com.nexgrid_fsp.myapplication.vo;

import android.graphics.Bitmap;

public class SharingVO
{

    // 호실
    private String num;

    // 이름
    private String name;

    // 성별 , 나이
    private String gender_age;


    // 고인사진
    private Bitmap profileBitmap;

    // 프로필사진
    private String profile;

    // 종교사진
    private int backImg;


    //Web 상주 리스트
    private String webresident;


    // 입관일
    private String startDate;

    // 발인일
    private String endDate;

    // 장지
    private String burialPlot;


    //text_Color
    private String bright_text_Color;

    //입관,발인,장지 상세 text_Color
    private String dark_text_Color;

    //background Colors
    private String mainC;

    // 선 색상
    private String lineC;

    // backgrond 색상
    private String subC;



    public String getmainC() {
        return mainC;
    }

    public void setmainC(String mainC) {
        this.mainC = mainC;
    }

    public String getsubC() {
        return subC;
    }

    public void setsubC(String subC) {
        this.subC = subC;
    }

    public String getlineC() {
        return lineC;
    }

    public void setlineC(String lineC) {
        this.lineC = lineC;
    }

    public String getBright_text_Color() {
        return bright_text_Color;
    }

    public void setBright_text_Color(String bright_text_Color) {
        this.bright_text_Color = bright_text_Color;
    }

    public String getDark_text_Color() {
        return dark_text_Color;
    }

    public void setDark_text_Color(String dark_text_Color) {
        this.dark_text_Color = dark_text_Color;
    }


    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }




    public Bitmap getProfileBitmap() {
        return profileBitmap;
    }

    public void setProfileBitmap(Bitmap profileBitmap) {
        this.profileBitmap = profileBitmap;
    }





    public String getWebresident() {
        return webresident;
    }

    public void setWebresident(String webresident) {
        this.webresident = webresident;
    }
    public String getnum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public String getGender_age() {
        return gender_age;
    }

    public void setGender_age(String gender_age) {
        this.gender_age = gender_age;
    }


    public int getBackImg() {
        return backImg;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getBurialPlot() {
        return burialPlot;
    }

    public void setnum(String num) {
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setBackImg(int backImg) {
        this.backImg = backImg;
    }


    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setBurialPlot(String burialPlot) {
        this.burialPlot = burialPlot;
    }
}
