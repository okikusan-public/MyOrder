package com.example.hiroyukikikuchi.test;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.TextView;
import java.util.*;
import java.text.*;

/**
 * Created by HiroyukiKikuchi on 2017/07/08.
 */

public class Result extends AppCompatActivity implements OnClickListener{

    private static SQLiteDatabase DB_;

    private static int iOkakeiKingaku_;
    private static int iOturi_;
    private static int iGoukeiKingaku_;
    private static int iKanpa_;
    private static int iNinzu_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_screen);

        // MainActivity からのデータを受け取る
        Intent intent = getIntent();
        iOkakeiKingaku_ = intent.getIntExtra(MainActivity.kOkaikekingaku, 0);
        iGoukeiKingaku_ = intent.getIntExtra(MainActivity.kGoukeiKingaku, 0);
        iOturi_ = intent.getIntExtra(MainActivity.kOturi, 0);
        iKanpa_ = intent.getIntExtra(MainActivity.kKanpaKingaku, 0);
        iNinzu_ = intent.getIntExtra(MainActivity.kNinzu, 0);

        // 通貨数値フォーマット（デフォルト）
        NumberFormat NF = NumberFormat.getCurrencyInstance();

        // 合計金額を出力する
        TextView ResultView = (TextView) findViewById(R.id.ResultText);

        // デフォルトロケールで支払い金額出力
        ResultView.setText(NF.format(iOkakeiKingaku_));

        // おつり金額を出力する
        TextView OturiView = (TextView) findViewById(R.id.OturiText);

        // デフォルトロケールでおつり金額出力
        OturiView.setText(NF.format(iOturi_));

        // 保存ボタンのリスナー登録
        Button save_button = (Button) findViewById(R.id.Save);
        save_button.setOnClickListener(this);

        // データベースオブジェクトの取得
        DBHelper dbHelper = new DBHelper(this);
        DB_ = dbHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View view) {

        do {
            // 保存ボタン以外の場合
            if (view.getId() != R.id.Save) {
                break;
            }

            SaveDialog.show(this);

        }while (false);

        return;
    }

    // データベースへの書き込み
    private static void writeDB( String title,  String Date, String kingaku, String oturi ){

        ContentValues values = new ContentValues();
        values.put(Constants.DatabaseItem.kTitle, title);
        values.put(Constants.DatabaseItem.kTime, Date);
        values.put(Constants.DatabaseItem.kGoukei, String.valueOf(iGoukeiKingaku_));
        values.put(Constants.DatabaseItem.kNinzu, String.valueOf(iNinzu_));
        values.put(Constants.DatabaseItem.kKanpa, String.valueOf(iKanpa_));
        values.put(Constants.DatabaseItem.kOturi, oturi);
        values.put(Constants.DatabaseItem.kShiharai, kingaku);
        Result.DB_.insert(DBHelper.getDbTableName(), "", values);
    }

    public static class SaveDialog extends DialogFragment{
        private EditText editText;
        private static final String kTitletag           = "title";
        private static final String kTextTag            = "text";
        private static final String kSaveDialogTag      = "SaveDialog";
        private static final String kTitleText          = "お会計履歴保存";
        private static final String kTextString         = "タイトルを入力してください";
        private static final String kPositiveButton     = "Save";
        private static final String kNegativeButton     = "Cancel";
        private static final String kTimeFormat         = "yyyy/MM/dd/ HH:mm";


        // Dialogの確認
        public static void show( Activity activity ){
            SaveDialog f = new SaveDialog();
            Bundle args = new Bundle();
            args.putString(kTitletag, kTitleText);
            args.putString(kTextTag, kTextString);
            f.setArguments(args);

            f.show(activity.getFragmentManager(), kSaveDialogTag);
        }

        // テキスト入力ダイアログの作成
        @Override
        public Dialog onCreateDialog( Bundle bundle ){

            // リスナーの作成
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){

                // ダイアログボタン押下時に呼ばれる
                public void onClick( DialogInterface dialog, int which ){

                    if( DialogInterface.BUTTON_POSITIVE == which ) {

                        // 入力したタイトルを取得
                        String title = editText.getText().toString();

                        // 現在日時の取得
                        Date now = new Date(System.currentTimeMillis());
                        DateFormat formatter = new SimpleDateFormat(kTimeFormat);
                        String time = formatter.format(now);

                        Result.writeDB(title, time, String.valueOf(Result.iOkakeiKingaku_), String.valueOf(Result.iOturi_));

                        CompleteMessage.show(getActivity());
                    }
                }
            };


            // テキストボックス作成
            editText = new EditText(getActivity());
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

            // テキスト入力ダイアログの作成
            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
            ad.setTitle(getArguments().getString(kTitletag));
            ad.setMessage(getArguments().getString(kTextTag));
            ad.setView(editText);
            ad.setPositiveButton(kPositiveButton, listener);
            ad.setNegativeButton(kNegativeButton, listener);

            // フラグメントの状態復帰
            if( bundle != null ){
                editText.setText(bundle.getString("editText"));
            }
            return ad.create();
        }
    }

    // メッセージダイアログの定義
    public static class CompleteMessage extends DialogFragment{

        private static final String kTitletag           = "title";
        private static final String kTextTag            = "text";
        private static final String kCompleteDialogTag = "CompleteMessage";
        private static final String kTitleText          = "Complete";
        private static final String kTextString         = "登録完了しました";
        private static final String kPositiveButton     = "OK";

        // Diagログの表示
        public static void show( Activity activity ){
            CompleteMessage f = new CompleteMessage();
            Bundle args = new Bundle();
            args.putString(kTitletag, kTitleText);
            args.putString(kTextTag, kTextString);
            f.setArguments(args);
            f.show(activity.getFragmentManager(), kCompleteDialogTag);
        }

        // ダイアログの生成
        @Override
        public Dialog onCreateDialog( Bundle bundle ){

            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
            ad.setTitle(getArguments().getString(kTitletag));
            ad.setMessage(getArguments().getString(kTextTag));
            ad.setPositiveButton(kPositiveButton, null);

            return ad.create();

        }
    }
}
