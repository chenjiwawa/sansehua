package com.zl.dappore.home.model;

import com.zl.dappore.common.model.BaseModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:38
 * @Description
 */

public class PosterList extends BaseModel {


    public List<Poster> posters;

    public static class Poster {

        @SerializedName("id")
        public int id;
        @SerializedName("image_url")
        public String imageUrl;

        @Override
        public String toString() {
            return "Poster{" +
                    "id=" + id +
                    ", imageUrl='" + imageUrl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PosterList{" +
                "posters=" + posters +
                '}';
    }
}
