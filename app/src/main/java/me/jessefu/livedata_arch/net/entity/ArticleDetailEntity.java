package me.jessefu.livedata_arch.net.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icourt on 2018/8/24.
 */

public class ArticleDetailEntity {

    @SerializedName("body")
    private String body;
    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("share_url")
    private String shareUrl;
    @SerializedName("type")
    private int type;
    @SerializedName("id")
    private int id;

    public ArticleDetailEntity() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ArticleDetailEntity{");
        sb.append("body='").append(body).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", shareUrl='").append(shareUrl).append('\'');
        sb.append(", type=").append(type);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
