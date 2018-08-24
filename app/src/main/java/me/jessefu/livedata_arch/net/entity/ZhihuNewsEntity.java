package me.jessefu.livedata_arch.net.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by icourt on 2018/8/24.
 */

public class ZhihuNewsEntity {

    /**
     * images : ["http://pic3.zhimg.com/c132c867feb6f92dd8f0614881bfb172.jpg"]
     * type : 0
     * id : 9241280
     * ga_prefix : 022222
     * title : 小事 · 明明知道是凶手
     * multipic : true
     */

    @SerializedName("type")
    private int type;
    @SerializedName("id")
    private int id;
    @SerializedName("ga_prefix")
    private String gaPrefix;
    @SerializedName("title")
    private String title;
    @SerializedName("multipic")
    private boolean multipic;
    @SerializedName("images")
    private List<String> images;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGaPrefix() {
        return gaPrefix;
    }

    public void setGaPrefix(String gaPrefix) {
        this.gaPrefix = gaPrefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isMultipic() {
        return multipic;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "StoriesBean{" +
                "type=" + type +
                ", id=" + id +
                ", gaPrefix='" + gaPrefix + '\'' +
                ", title='" + title + '\'' +
                ", multipic=" + multipic +
                ", images=" + images +
                '}';
    }
}
