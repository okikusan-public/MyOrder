package com.example.hiroyukikikuchi.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * Created by HiroyukiKikuchi on 2017/07/08.
 */

public class Result extends AppCompatActivity implements OnClickListener{

    private PopupWindow popupWin_;
    private SQLiteDatabase db_;

    private final static String DB_NAME      = "test.db";        // DB名
    private final static String DB_TABLE     = "test";           // テーブル名
    private final static int    DB_VERSION  = 1;                 // バージョン

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_screen);

        // MainActivity からのデータを受け取る
        Intent intent = getIntent();
        int iOkakeiKingaku = intent.getIntExtra("OkaikeiResult", 0);
        int iOturi = intent.getIntExtra("Oturi", 0);

        // 通貨数値フォーマット（デフォルト）
        NumberFormat NF = NumberFormat.getCurrencyInstance();

        // 合計金額を出力する
        TextView ResultView = (TextView) findViewById(R.id.ResultText);

        // デフォルトロケールで支払い金額出力
        ResultView.setText(NF.format(iOkakeiKingaku));

        // おつり金額を出力する
        TextView OturiView = (TextView) findViewById(R.id.OturiText);

        // デフォルトロケールでおつり金額出力
        OturiView.setText(NF.format(iOturi));

        // 保存ボタンのリスナー登録
        Button save_button = (Button) findViewById(R.id.Save);
        save_button.setOnClickListener(this);

        // データベースオブジェクトの取得
          DBHelper dbHelper = new DBHelper(this);
          db_ = dbHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View view) {

        do {
            // 保存ボタン以外の場合
            if (view.getId() != R.id.Save) {
                break;
            }

            // popup windowを生成する
            popupWin_ = new PopupWindow(this);

            View popLayout = getLayoutInflater().inflate(R.layout.save_confirm, null);

            popLayout.findViewById(R.id.close_save_button).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if( popupWin_.isShowing() ) {
                        popupWin_.dismiss();
                    }

                    // タイトル情報を取得
                    EditText Title = (EditText) findViewById(R.id.save_text);




                }
            });

            popupWin_.setContentView(popLayout);

            // 背景設定
            popupWin_.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_background));

            // タッチ時に他のviewにキャッチされないように設定
            popupWin_.setOutsideTouchable(true);
            popupWin_.setFocusable(true);

            // 表示サイズの設定 今回は幅300dp
            float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, getResources().getDisplayMetrics());
            popupWin_.setWidth((int)width);
            popupWin_.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

            // 画面中央に表示
            popupWin_.showAtLocation(findViewById(R.id.ResultText), Gravity.CENTER, 0, 0);

        }while (false);

        return;
    }

    // データベースへの書き込み
    private void writeDB( String info ){
        ContentValues values = new ContentValues();
        values.put("id", "0");
        values.put("info", info);

    }

    // データベースヘルパーの定義
    private static class DBHelper extends SQLiteOpenHelper{

        // DBヘルパーのコンストラクタ
        public DBHelper(Context context){
            super( context, DB_NAME, null, DB_VERSION );
        }

        // DBの作成
        @Override
        public void onCreate( SQLiteDatabase db ){
            db.execSQL("create table if not exists " + DB_TABLE + "(id text primary key, title text, kingaku text, oturi text )" );
        }

        // DBのアップグレード
        @Override
        public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ){
            db.execSQL("drop table if exists " + DB_TABLE);
            onCreate(db);
        }
    }
    /*
    private class SaveConfirm extends View implements OnClickListener {

        public SaveConfirm(Context context) {
            super(context);
        }

        @Override
        public void onClick(View v) {

            if (popupWin_.isShowing()) {
                popupWin_.dismiss();
            }

            // タイトル情報を取得
            EditText Title = (EditText) findViewById(R.id.save_text);


        }
    }
    */
}
