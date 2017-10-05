package com.example.hiroyukikikuchi.test;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    public final static String kGoukeiKingaku = "GoukeiKingaku";
    public final static String kOturi          = "Oturi";
    public final static String kNinzu          = "Ninzu";
    public final static String kKanpaKingaku  = "KanpaKingaku";
    public final static String kOkaikekingaku = "Okaikekingaku";

    private final String kGoukeiError = "合計金額に数字を入れてください";
    private final String kNinzuError  = "人数に数字を入れてください";
    private final String kKapnaError  = "カンパ金額に数字を入れてください";

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
                throw new GoukeiKingakuError(kGoukeiError);
            }

            // カンパ金額の入力が0の場合
            else if( KanpaKingaku.getText().length() == 0 ){
                throw new KanpaKingakuError(kKapnaError);
            }

            // 人数の入力が0の場合
            else if(Ninzu.getText().length() == 0){
                throw new NinzuError(kNinzuError);
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
            intent.putExtra(kGoukeiKingaku, iGoukeiKingaku);
            intent.putExtra(kOturi, iOturi);
            intent.putExtra(kNinzu, iNinzu);
            intent.putExtra(kKanpaKingaku, iKanpaKingaku);
            intent.putExtra(kOkaikekingaku, iOkaikekingaku);
            startActivity(intent);
        }
        catch ( Exception e ){

            ErrorMessage.show(this, e.getMessage());

        }
    }

    // メッセージダイアログの定義
    public static class ErrorMessage extends DialogFragment {

        private static final String kTitle  = "title";
        private static final String kError  = "Error";
        private static final String kText   = "text";
        private static final String kTag     = "ErrorMessage";

        // Diagログの表示
        public static void show( Activity activity, String error ){
            ErrorMessage f = new ErrorMessage();
            Bundle args = new Bundle();
            args.putString(kTitle, kError);
            args.putString(kText, error);
            f.setArguments(args);
            f.show(activity.getFragmentManager(), kTag);
        }

        // ダイアログの生成
        @Override
        public Dialog onCreateDialog(Bundle bundle ){

            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
            ad.setTitle(getArguments().getString(kTitle));
            ad.setMessage(getArguments().getString(kText));
            ad.setPositiveButton("OK", null);

            return ad.create();

        }
    }
}
