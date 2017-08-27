package com.example.hiroyukikikuchi.test;

import java.text.ParseException;

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

    public int getOkaikeiResult() throws ErrorException{

        // 合計金額をチェック
        if( iGoukeiKingaku_ < 0 ){
            throw new GoukeiKingakuError("合計金額の入力が0以下です");
        }

        // カンパ金額をチェック
        else if( iKanpaKingaku_ < 0 ){
            throw new KanpaKingakuError("カンパ金額が0未満です");
        }

        // 人数をチェック
        else if( iPersonNumberes_ <= 0 ){
            throw new NinzuError("人数が0以下です");
        }

        int iOkaikei   =  iGoukeiKingaku_ - iKanpaKingaku_;

        // お会計金額が0を超える場合
        if( iOkaikei > 0 ) {

            iOkaikei /= iPersonNumberes_;
            iOkaikei /= iSettingWarikiri_;
            iOkaikei *= iSettingWarikiri_;
            iOkaikei += iSettingWarikiri_;

            // おつり金額を保持する
            iOturi_ = ( ( iOkaikei * iPersonNumberes_ ) + iKanpaKingaku_) - iGoukeiKingaku_;
        }

        // お会計金額を0以下の場合
        else{
            // おつり金額を設定し、お会計金額を0に設定する
            iOturi_ = ~(iOkaikei) + 1;
            iOkaikei = 0;
        }


        return iOkaikei;
    }

}
