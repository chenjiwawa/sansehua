package com.tricolorflower.heartbeat.appdetail.model;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.comment.model.Comment;
import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:38
 * @Description
 */

public class App extends BaseModel {

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("source")
    public String source;
    @SerializedName("state")
    public String state;
    @SerializedName("chain")
    public String chain;
    @SerializedName("language")
    public Object language;
    @SerializedName("website")
    public String website;
    @SerializedName("logo_url")
    public String logoUrl;
    @SerializedName("main_image_url")
    public Object mainImageUrl;
    @SerializedName("score")
    public int score;
    @SerializedName("history")
    public Object history;
    @SerializedName("app_taxon")
    public AppTaxon appTaxon;
    @SerializedName("image_urls")
    public List<String> imageUrls;
    @SerializedName("contracts")
    public List<Contract> contracts;
    @SerializedName("tags")
    public List<Tag> tags;
    @SerializedName("comments")
    public List<Comment> comments;
    public boolean commentable;
    public boolean starred;
    @SerializedName("liked_count")
    public String likedCount;
    @SerializedName("star_count")
    public String starCount;
    @SerializedName("share_url")
    public String shareUrl;
    public boolean liked;

    public static class AppTaxon extends BaseModel {

        /**
         * id : 1
         * name : DEX
         */

        public String id;
        public String name;
        public List<App> apps;
    }

    public static class Contract extends BaseModel {
        /**
         * address : 0x8ef2C5A579D779b79584773d6DA1FD637676136e
         * safety : 3.6
         * safety_str : middle
         * opened : true
         */

        @SerializedName("address")
        public String address;
        @SerializedName("safety")
        public float safety;
        @SerializedName("safety_str")
        public String safetyStr;
        @SerializedName("opened")
        public boolean opened;
        @SerializedName("url")
        public String url;

        @Override
        public String toString() {
            return "Contract{" +
                    "address='" + address + '\'' +
                    ", safety=" + safety +
                    ", safetyStr='" + safetyStr + '\'' +
                    ", opened=" + opened +
                    ", url=" + url +
                    '}';
        }
    }

    public static class Tag extends BaseModel {
        /**
         * id : 53636
         * name : 高风险
         */

        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;

        @Override
        public String toString() {
            return "Tag{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "App{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", source='" + source + '\'' +
                ", state='" + state + '\'' +
                ", chain='" + chain + '\'' +
                ", language=" + language +
                ", website='" + website + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", mainImageUrl=" + mainImageUrl +
                ", score=" + score +
                ", history=" + history +
                ", appTaxon=" + appTaxon +
                ", imageUrls=" + imageUrls +
                ", contracts=" + contracts +
                ", tags=" + tags +
                ", comments=" + comments +
                ", commentable=" + commentable +
                ", starred=" + starred +
                ", likedCount='" + likedCount + '\'' +
                ", starCount='" + starCount + '\'' +
                ", shareUrl='" + shareUrl + '\'' +
                ", liked=" + liked +
                '}';
    }
}
