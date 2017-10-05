package com.example.hiroyukikikuchi.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * Created by HiroyukiKikuchi on 2017/09/20.
 */

public class RirekiDetail extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.rireki);

        // ListViewからのデータを受け取る
        Intent intent = getIntent();
        ListItems items = (ListItems)getIntent().getParcelableExtra(ListViewActivity.kData);

        // タイトル
        TextView TitleText = (TextView) findViewById(R.id.RirekiTitleStrings);
        TitleText.setText( items.title_);

        // 時間
        TextView DateText = (TextView) findViewById(R.id.RirekiDateStrings);
        DateText.setText( items.time_);

        // 合計金額
        NumberFormat NF = NumberFormat.getCurrencyInstance();
        TextView OkaikeiText = (TextView) findViewById(R.id.RirekiGoukeiStrings);
        int iGoukeiKingaku = Integer.parseInt( items.goukei_);
        OkaikeiText.setText( NF.format(iGoukeiKingaku));

        // 人数
        TextView NinzuText = (TextView) findViewById(R.id.NinzuStrings);
        NinzuText.setText(items.ninzu_);

    }

    public void onClick(View view) {

    }


}
