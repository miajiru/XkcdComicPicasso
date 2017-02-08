package com.desarrollomovil.angel.xkcdcomicpicasso.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by angel on 05/02/2017.
 */

public class ComicModel {

    @SerializedName("month")
    @Expose
    public String month;
    @SerializedName("num")
    @Expose
    public Integer num;
    @SerializedName("link")
    @Expose
    public String link;
    @SerializedName("year")
    @Expose
    public String year;
    @SerializedName("news")
    @Expose
    public String news;
    @SerializedName("safe_title")
    @Expose
    public String safeTitle;
    @SerializedName("transcript")
    @Expose
    public String transcript;
    @SerializedName("alt")
    @Expose
    public String alt;
    @SerializedName("img")
    @Expose
    public String img;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("day")
    @Expose
    public String day;

    public String getMonth() {
        return month;
    }

    public Integer getNum() {
        return num;
    }

    public String getLink() {
        return link;
    }

    public String getYear() {
        return year;
    }

    public String getNews() {
        return news;
    }

    public String getSafeTitle() {
        return safeTitle;
    }

    public String getTranscript() {
        return transcript;
    }

    public String getAlt() {
        return alt;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getDay() {
        return day;
    }
}
