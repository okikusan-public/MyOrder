package com.example.hiroyukikikuchi.test;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by HiroyukiKikuchi on 2017/08/19.
 */

public class Menu extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_menu);

        Button KeisanButton = (Button) findViewById(R.id.KeisanButton);
        KeisanButton.setOnClickListener(this);

        Button RirekiButton = (Button) findViewById(R.id.RirekiButton);
        RirekiButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        // お会計計算
        if( view.getId() == R.id.KeisanButton ) {

            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        }

        // お会計履歴
        else if( view.getId() == R.id.RirekiButton ){

            // データベースオブジェクトの取得
            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor c = db.query(dbHelper.getDbTableName(), new String[]{Constants.DatabaseItem.kTitle,
                    Constants.DatabaseItem.kTime,
                    Constants.DatabaseItem.kOturi,
                    Constants.DatabaseItem.kShiharai,},
                    null, null, null, null, null  );

            // 1件以上登録されている場合
            if( c.getCount() > 0 ){
                Intent intent = new Intent(getApplication(), ListViewActivity.class);
                startActivity(intent);
            }
            // 登録数が0の場合
            else{
                ErrorMessage.show(this, "何も登録されていません");
            }

            c.close();

        }
    }

    // メッセージダイアログの定義
    public static class ErrorMessage extends DialogFragment {

        // Diagログの表示
        public static void show(Activity activity, String error ){
            ErrorMessage f = new ErrorMessage();
            Bundle args = new Bundle();
            args.putString("title", "Error");
            args.putString("text", error);
            f.setArguments(args);
            f.show(activity.getFragmentManager(), "ErrorMessage");
        }

        // ダイアログの生成
        @Override
        public Dialog onCreateDialog(Bundle bundle ){

            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
            ad.setTitle(getArguments().getString("title"));
            ad.setMessage(getArguments().getString("text"));

            return ad.create();

        }
    }


}
