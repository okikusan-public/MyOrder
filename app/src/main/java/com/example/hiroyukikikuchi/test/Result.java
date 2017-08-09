package com.example.hiroyukikikuchi.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * Created by HiroyukiKikuchi on 2017/07/08.
 */

public class Result extends AppCompatActivity {

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

        TextView ResultView = (TextView) findViewById(R.id.ResultText);

        // デフォルトロケールで通過出力
        ResultView.setText(NF.format(iOkakeiKingaku));

    }

}
