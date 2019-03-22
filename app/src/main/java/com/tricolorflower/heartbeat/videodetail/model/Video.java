package com.tricolorflower.heartbeat.videodetail.model;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.io.Serializable;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:38
 * @Description
 */

public class Video extends BaseModel implements Serializable {

    /**
     * id : 27
     * title : #搞笑的#
     * cover_url : http://pizj829no.bkt.clouddn.com/f5iXsX8Q7okSuZ5zTQCAVctR
     * share_url : http://dappore.store
     * view_count : 0
     * created_at : 2018-12-27T17:16:01.005+08:00
     * author : {"id":11,"name":"1","avatar_url":"http://pizj829no.bkt.clouddn.com/XZpGDWZz9VdZEMC13LhCLMQ6"}
     * media : {"duration":"0:00","url":"http://pizj829no.bkt.clouddn.com/7rBdPeUcBbJUD6r6sogN1qSZ?attname=video"}
     * comments_count : 0
     */

    @SerializedName("id")
    public String id;
    @SerializedName("title")
    public String title;
    @SerializedName("cover_url")
    public String coverUrl;
    @SerializedName("share_url")
    public String shareUrl;
    @SerializedName("view_count")
    public int viewCount;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("author")
    public Author author;
    @SerializedName("media")
    public Media media;
    @SerializedName("comments_count")
    public int commentsCount;
    public boolean starred;
    public boolean liked;
    @SerializedName("liked_count")
    public int likedCount;
    public boolean rewardable;
    public boolean viewed;

    public static class Media implements Serializable{
        /**
         * duration : 0:10
         * url : http://pizj829no.bkt.clouddn.com/oXPJSYH6eFdFZhzvTURxroTU?attname=1543993768088060.mp4
         */
        @SerializedName("duration")
        public String duration;
        @SerializedName("url")
        public String url;

        @Override
        public String toString() {
            return "Media{" +
                    "duration='" + duration + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public static class VideoTaxon implements Serializable {
        /**
         * id : 33
         * name : 好玩的~
         */

        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;

        @Override
        public String toString() {
            return "VideoTaxon{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", shareUrl='" + shareUrl + '\'' +
                ", viewCount=" + viewCount +
                ", createdAt='" + createdAt + '\'' +
                ", author=" + author +
                ", media=" + media +
                ", commentsCount=" + commentsCount +
                ", starred=" + starred +
                ", liked=" + liked +
                ", likedCount=" + likedCount +
                ", rewardable=" + rewardable +
                '}';
    }
}
