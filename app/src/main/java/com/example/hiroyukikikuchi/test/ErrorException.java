package com.example.hiroyukikikuchi.test;

/**
 * Created by HiroyukiKikuchi on 2017/08/12.
 */

// 例外エラークラス
public class ErrorException extends Exception {
    public ErrorException(String str){
        super(str);
    }
}

// 合計金額エラー
class GoukeiKingakuError extends ErrorException{
    public GoukeiKingakuError(String str){
        super(str);
    }
}

class KanpaKingakuError extends ErrorException{
    public KanpaKingakuError(String str){
        super(str);
    }
}

// おあいそ金額エラー
class OaisoKingakuError extends ErrorException{
    public OaisoKingakuError(String str){
        super(str);
    }
}

// 人数エラー
class NinzuError extends ErrorException{
    public NinzuError(String str){
        super(str);
    }
}