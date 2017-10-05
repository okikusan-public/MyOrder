package com.example.hiroyukikikuchi.test;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HiroyukiKikuchi on 2017/10/02.
 */

public class ListItems implements Parcelable {

    public String title_;
    public String time_;
    public String goukei_;
    public String ninzu_;
    public String kanpa_;
    public String oturi_;
    public String shiharai_;

    public ListItems( Cursor cursor ){

        title_ = cursor.getString(Constants.DatabaseItem.kTitleNum);
        time_  = cursor.getString(Constants.DatabaseItem.kTimeNum);
        goukei_ = cursor.getString(Constants.DatabaseItem.kGoukeiNum);
        ninzu_ = cursor.getString(Constants.DatabaseItem.kNinzuNum);
        kanpa_ = cursor.getString(Constants.DatabaseItem.kKanpaNum);
        oturi_ = cursor.getString(Constants.DatabaseItem.kOturiNum);
        shiharai_ = cursor.getString(Constants.DatabaseItem.kShiharaiNum);

    }

    private ListItems(){
        // ありえない
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title_);
        dest.writeString(this.time_);
        dest.writeString(this.goukei_);
        dest.writeString(this.ninzu_);
        dest.writeString(this.kanpa_);
        dest.writeString(this.oturi_);
        dest.writeString(this.shiharai_);
    }

    protected ListItems(Parcel in) {
        this.title_ = in.readString();
        this.time_ = in.readString();
        this.goukei_ = in.readString();
        this.ninzu_ = in.readString();
        this.kanpa_ = in.readString();
        this.oturi_ = in.readString();
        this.shiharai_ = in.readString();
    }

    public final static Parcelable.Creator<ListItems> CREATOR = new Parcelable.Creator<ListItems>() {
        @Override
        public ListItems createFromParcel(Parcel source) {
            return new ListItems(source);
        }

        @Override
        public ListItems[] newArray(int size) {
            return new ListItems[size];
        }
    };
}
