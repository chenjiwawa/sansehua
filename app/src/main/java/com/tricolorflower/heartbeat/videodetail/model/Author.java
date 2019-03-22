package com.tricolorflower.heartbeat.videodetail.model;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.io.Serializable;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:38
 * @Description
 */

public class Author extends BaseModel implements Serializable {


    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("avatar_url")
    public String avatarUrl;

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
