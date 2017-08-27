package com.example.hiroyukikikuchi.test;

import android.content.Intent;
import android.os.Bundle;
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
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);
    }


}
