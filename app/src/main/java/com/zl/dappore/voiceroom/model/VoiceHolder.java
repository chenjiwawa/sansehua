package com.zl.dappore.voiceroom.model;

import com.google.gson.annotations.SerializedName;

public class VoiceHolder {
    /**
     * id : 63
     * title : null
     * content : 解决
     * state : null
     * score : null
     * star_count : 3
     * created_at : 2018-12-20T15:58:17.886+08:00
     * commenter : {"id":13,"name":null,"avatar_url":"http://pizj829no.bkt.clouddn.com/XZpGDWZz9VdZEMC13LhCLMQ6"}
     * liked : false
     * liked_count : 0
     */

    @SerializedName("id")
    public String id;
    @SerializedName("title")
    public String title;
    @SerializedName("content")
    public String content;
    @SerializedName("state")
    public String state;
    @SerializedName("score")
    public String score;
    @SerializedName("star_count")
    public float starCount;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("commenter")
    public Commenter commenter;
    @SerializedName("liked")
    public boolean liked;
    @SerializedName("liked_count")
    public int likedCount;

    public static class Commenter {
        /**
         * id : 13
         * name : null
         * avatar_url : http://pizj829no.bkt.clouddn.com/XZpGDWZz9VdZEMC13LhCLMQ6
         */

        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("avatar_url")
        public String avatarUrl;

        @Override
        public String toString() {
            return "Commenter{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", avatarUrl='" + avatarUrl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", state='" + state + '\'' +
                ", score='" + score + '\'' +
                ", starCount=" + starCount +
                ", createdAt='" + createdAt + '\'' +
                ", commenter=" + commenter +
                ", liked=" + liked +
                ", likedCount=" + likedCount +
                '}';
    }
}
