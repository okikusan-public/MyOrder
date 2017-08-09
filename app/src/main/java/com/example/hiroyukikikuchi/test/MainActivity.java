package com.example.hiroyukikikuchi.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.view.View.OnClickListener;
import com.example.hiroyukikikuchi.test.OkaikeiManage.Okaikei;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn100 = (Button) findViewById(R.id.button100);
        btn100.setOnClickListener(this);

        Button btn500 = (Button) findViewById(R.id.button500);
        btn500.setOnClickListener(this);

        Button btn1000 = (Button) findViewById(R.id.button1000);
        btn1000.setOnClickListener(this);

    }

    public void onClick(View view) {

        EditText GoukeiKingaku = (EditText) findViewById(R.id.GoukeiKingakuText);
        EditText KanpaKingaku  = (EditText) findViewById(R.id.KanpaText);
        EditText Ninzu         = (EditText) findViewById(R.id.NinzuText);

        String GoukeiKingakuString = GoukeiKingaku.getText().toString();
        int   iGoukeiKingaku = Integer.parseInt( GoukeiKingakuString );

        String KanpaKingakuString = KanpaKingaku.getText().toString();
        int   iKanpaKingaku = Integer.parseInt( KanpaKingakuString );

        String NinzuString = Ninzu.getText().toString();
        int   iNinzu = Integer.parseInt( NinzuString );

        Okaikei okaikei = new Okaikei();
        okaikei.setGoukeiKingaku(iGoukeiKingaku);
        okaikei.setKanpaKingaku(iKanpaKingaku);
        okaikei.setPersonNumberes(iNinzu);

        Button button = (Button) view;

        switch ( view.getId() ){

            case R.id.button100:
                okaikei.setWarikiri(Okaikei.k100Giri);
                break;

            case R.id.button500:
                okaikei.setWarikiri(Okaikei.k500Giri);
                break;

            case R.id.button1000:
                okaikei.setWarikiri(Okaikei.k1000Giri);
                break;
        }

        int iOkaikekingaku = okaikei.getOkaikeiResult();

        int iOturi = okaikei.getiOturi();

        Intent intent = new Intent(getApplication(), Result.class);
        intent.putExtra("OkaikeiResult", iOkaikekingaku);
        intent.putExtra("Oturi", iOturi );
        startActivity(intent);
    }

}
