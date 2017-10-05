package com.example.hiroyukikikuchi.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HiroyukiKikuchi on 2017/09/06.
 */

// データベースヘルパーの定義
public class DBHelper extends SQLiteOpenHelper {

    final String kCreateTable = "create table if not exists ";
    final String kRowItems    = "(title text, time text, goukei text, ninzu text, kanpa text, oturi text, shiharai text )";
    final String kDropTable    = "drop table if exists ";

    // DBヘルパーのコンストラクタ
    public DBHelper(Context context){
        super( context, Constants.DatabaseItem.kDbName, null, Constants.DatabaseItem.kDbVersion );
    }

    // テーブル情報取得
    public static String getDbTableName(){
        return Constants.DatabaseItem.kDbTable;
    }

    // DBの作成
    @Override
    public void onCreate( SQLiteDatabase db ){
        db.execSQL(kCreateTable + Constants.DatabaseItem.kDbTable + kRowItems );
    }

    // DBのアップグレード
    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ){
        db.execSQL(kDropTable + Constants.DatabaseItem.kDbTable);
        onCreate(db);
    }

}