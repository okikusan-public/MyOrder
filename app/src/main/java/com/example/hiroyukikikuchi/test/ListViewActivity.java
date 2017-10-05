package com.example.hiroyukikikuchi.test;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HiroyukiKikuchi on 2017/09/06.
 */

public class ListViewActivity extends AppCompatActivity {

    private SQLiteDatabase Db_;
    private ArrayList<ListItems> Lists_;
    public static final String kData = "data";

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // データベースオブジェクトの取得
        DBHelper dbHelper = new DBHelper(this);
        Db_ = dbHelper.getWritableDatabase();

        Cursor c = Db_.query( dbHelper.getDbTableName(), Constants.kDatabaseRows, null, null, null, null, null  );
        int count = c.getCount();
        c.moveToFirst();

        // リスト一覧の動的配列を作成
        Lists_ = new ArrayList<ListItems>();

        // 各リスト一覧にアイテムを代入する
        for( int i = 0; i < count; i++ ){

            ListItems item = new ListItems(c);
            Lists_.add( item );
            c.moveToNext();
        }

        c.close();

        // ListViewの作成
        ListView listView = new ListView(this);
        listView.setScrollingCacheEnabled(false);
        listView.setAdapter(new MyAdapter());
        setContentView(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id ){

                Bundle bundle = new Bundle();


                Intent intent = new Intent(getApplication(), RirekiDetail.class);
                intent.putExtra(ListViewActivity.kData, Lists_.get(position));

                startActivity(intent);

            }

        });

    }

    // 自作アダプタの作成
    private class MyAdapter extends BaseAdapter{

        private final String kTitleTag = "text";
        private final String kTimeTag  = "time";

        //要素の取得
        @Override
        public int getCount(){
            return Lists_.size();
        }

        // 要素の取得
        @Override
        public ListItems getItem( int pos ){
            return Lists_.get(pos);
        }

        // 要素IDの取得
        @Override
        public long getItemId(int pos){
            return pos;
        }

        // セルのView作成
        @Override
        public View getView(int pos, View view, ViewGroup parent){

            Context context = ListViewActivity.this;
            ListItems item = Lists_.get(pos);

            // レイアウトの生成
            if( view == null ){

                LinearLayout layout = new LinearLayout(context);
                layout.setBackgroundColor(Color.WHITE);
                layout.setPadding(15,15,0,0);
                layout.setOrientation(LinearLayout.VERTICAL);
                view = layout;

                // 日時の指定
                TextView timeTextView = new TextView(context);
                timeTextView.setTag(kTimeTag);
                timeTextView.setPadding(0,0,0,0);
                timeTextView.setTextSize(14);
                layout.addView(timeTextView);

                // タイトルの指定
                TextView titleTextView = new TextView(context);
                titleTextView.setTag(kTitleTag);
                titleTextView.setPadding(0,0,0,15);
                titleTextView.setTextSize(24);
                layout.addView(titleTextView);

            }

            // 値の指定
            TextView textTitleView = (TextView)view.findViewWithTag(kTitleTag);
            textTitleView.setText( item.title_);

            TextView timeTextView = (TextView)view.findViewWithTag(kTimeTag);
            timeTextView.setText( item.time_);

            return view;
        }
    }
}
