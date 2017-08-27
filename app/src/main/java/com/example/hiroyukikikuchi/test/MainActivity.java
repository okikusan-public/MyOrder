package com.example.hiroyukikikuchi.test;

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
import 	android.widget.PopupWindow;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private PopupWindow popupWin_;

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

        try {

            // 合計金額の入力が0の場合
            if( GoukeiKingaku.getText().length() == 0 ){
                throw new GoukeiKingakuError("合計金額に数字を入れてください");
            }

            // カンパ金額の入力が0の場合
            else if( KanpaKingaku.getText().length() == 0 ){
                throw new KanpaKingakuError("カンパ金額に数字を入れてください");
            }

            // 人数の入力が0の場合
            else if(Ninzu.getText().length() == 0){
                throw new NinzuError("人数に数字を入れてください");
            }

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
            intent.putExtra("Oturi", iOturi);
            intent.putExtra("OkaikeiResult", iOkaikekingaku);
            startActivity(intent);
        }
        catch ( Exception e ){
            // PopupのViewのインスタンス生成

            popupWin_ = new PopupWindow(this);

            View popLayout = getLayoutInflater().inflate(R.layout.warning_pop, null);
            popLayout.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if( popupWin_.isShowing() ) {
                        popupWin_.dismiss();
                    }
                }
            });

            popupWin_.setContentView(popLayout);

            // 文字列設定
            TextView ErrorText = (TextView)popLayout.findViewById(R.id.WarningText);
            ErrorText.setText(e.getMessage());

            // 背景設定
            popupWin_.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_background));

            // タッチ時に他のviewにキャッチされないように設定
            popupWin_.setOutsideTouchable(true);
            popupWin_.setFocusable(true);

            // 表示サイズの設定 今回は幅300dp
            float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
            popupWin_.setWidth((int)width);
            popupWin_.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

            // 画面中央に表示
            popupWin_.showAtLocation(findViewById(R.id.button100), Gravity.CENTER, 0, 0);

        }

    }
}
