package com.zl.dappore.account.model;

import com.google.gson.annotations.SerializedName;
import com.zl.dappore.common.model.BaseModel;

import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:38
 * @Description
 */

public class User extends BaseModel {


    /**
     * id : 1
     * name : Dapp小助手
     * email : mingyuan0715@foxmail.com
     * mobile : 18571856813
     * locale : null
     * avatar_url : http://staging.dappore.com/JQxd9vQahWX9V4QNZvRxDL7U
     * getui_token : c3db4e4d6da8a5c7a7802d3476f8e2af
     * wechat_name : null
     * oauth_providers : []
     * videos_count : 15
     * video_liked_count : 32
     * video_comments_count : 27
     * followers_count : 0
     * following_count : 0
     * invite_token : 28e37d2c
     * oauth_users : []
     * wallet : {"id":"6","amount":"0.0"}
     * coin : {"id":"\u201c6\u201d","amount":"5.0","income_amount":"10.0"}
     * cash : {"id":"\u201c6\u201d","amount":"0.0"}
     * invite_url : http://dappore.store/register?invite_token=28e37d2c
     */

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("email")
    public String email;
    @SerializedName("mobile")
    public String mobile;
    @SerializedName("locale")
    public Object locale;
    @SerializedName("avatar_url")
    public String avatarUrl;
    @SerializedName("getui_token")
    public String getuiToken;
    @SerializedName("wechat_name")
    public Object wechatName;
    @SerializedName("videos_count")
    public int videosCount;
    @SerializedName("video_liked_count")
    public int videoLikedCount;
    @SerializedName("video_comments_count")
    public int videoCommentsCount;
    @SerializedName("followers_count")
    public int followersCount;
    @SerializedName("following_count")
    public int followingCount;
    @SerializedName("invite_token")
    public String inviteToken;
    @SerializedName("wallet")
    public Wallet wallet;
    @SerializedName("coin")
    public Coin coin;
    @SerializedName("cash")
    public Cash cash;
    @SerializedName("invite_url")
    public String inviteUrl;
    @SerializedName("oauth_providers")
    public List<?> oauthProviders;
    @SerializedName("oauth_users")
    public List<?> oauthUsers;
    @SerializedName("auth_token")
    public String authToken;

    public static class Wallet {
        /**
         * id : 6
         * amount : 0.0
         */

        @SerializedName("id")
        public String id;
        @SerializedName("amount")
        public String amount;
    }

    public static class Coin {
        /**
         * id : “6”
         * amount : 5.0
         * income_amount : 10.0
         */

        @SerializedName("id")
        public String id;
        @SerializedName("amount")
        public String amount;
        @SerializedName("income_amount")
        public String incomeAmount;
    }

    public static class Cash {
        /**
         * id : “6”
         * amount : 0.0
         */

        @SerializedName("id")
        public String id;
        @SerializedName("amount")
        public String amount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", locale=" + locale +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", getuiToken='" + getuiToken + '\'' +
                ", wechatName=" + wechatName +
                ", videosCount=" + videosCount +
                ", videoLikedCount=" + videoLikedCount +
                ", videoCommentsCount=" + videoCommentsCount +
                ", followersCount=" + followersCount +
                ", followingCount=" + followingCount +
                ", inviteToken='" + inviteToken + '\'' +
                ", wallet=" + wallet +
                ", coin=" + coin +
                ", cash=" + cash +
                ", inviteUrl='" + inviteUrl + '\'' +
                ", oauthProviders=" + oauthProviders +
                ", oauthUsers=" + oauthUsers +
                ", authToken='" + authToken + '\'' +
                '}';
    }
}
