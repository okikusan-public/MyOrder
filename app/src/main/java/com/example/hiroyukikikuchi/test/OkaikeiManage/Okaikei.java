package com.example.hiroyukikikuchi.test.OkaikeiManage;

/**
 * Created by HiroyukiKikuchi on 2017/07/02.
 */

public class Okaikei {


    public static final int k100Giri  = 100;
    public static final int k500Giri  = 500;
    public static final int k1000Giri = 1000;

    private int iGoukeiKingaku_       = 0;
    private int iPersonNumberes_      = 0;
    private int iKanpaKingaku_        = 0;
    private int iSettingWarikiri_     = k1000Giri;
    private int iOturi_                = 0;

    public void setGoukeiKingaku( int num )
    {
        iGoukeiKingaku_ = num;
    }

    public void setPersonNumberes( int num ){
        iPersonNumberes_ = num;
    }

    public void setKanpaKingaku( int num ){
        iKanpaKingaku_ = num;
    }

    public void setWarikiri( int num )
    {
        iSettingWarikiri_ = num;
    }

    public int  getiOturi(){ return iOturi_; }

    public int getOkaikeiResult(){

        int iOkaikei   =  iGoukeiKingaku_ - iKanpaKingaku_;
        iOkaikei       /= iPersonNumberes_;
        iOkaikei       /= iSettingWarikiri_;
        iOkaikei       *=  iSettingWarikiri_;
        iOkaikei       +=  iSettingWarikiri_;

        // おつり金額を保持する
        iOturi_ = ( iOkaikei * iPersonNumberes_ ) - iGoukeiKingaku_;

        return iOkaikei;
    }

}
