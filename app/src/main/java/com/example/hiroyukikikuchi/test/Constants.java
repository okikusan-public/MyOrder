package com.example.hiroyukikikuchi.test;

import java.sql.RowId;

/**
 * Created by HiroyukiKikuchi on 2017/09/21.
 */

public final class Constants {

    class DatabaseItem {

        public final static String kDbName = "Okaikei.db";      // DB名
        public final static String kDbTable = "History";        // テーブル名
        public final static int kDbVersion = 1;                 // バージョン

        public final static String kTitle = "title";
        public final static String kTime = "time";
        public final static String kGoukei = "goukei";
        public final static String kNinzu = "ninzu";
        public final static String kKanpa = "kanpa";
        public final static String kOturi= "oturi";
        public final static String kShiharai= "shiharai";

        public final static int kTitleNum = 0;
        public final static int kTimeNum = 1;
        public final static int kGoukeiNum = 2;
        public final static int kNinzuNum = 3;
        public final static int kKanpaNum = 4;
        public final static int kOturiNum = 5;
        public final static int kShiharaiNum = 6;
        public final static int kKingakuMax = 7;

    }

    // データベース各種列要素
    public final static String[]  kDatabaseRows = {
            Constants.DatabaseItem.kTitle,
            Constants.DatabaseItem.kTime,
            Constants.DatabaseItem.kGoukei,
            Constants.DatabaseItem.kNinzu,
            Constants.DatabaseItem.kKanpa,
            Constants.DatabaseItem.kOturi,
            Constants.DatabaseItem.kShiharai
    };


}
