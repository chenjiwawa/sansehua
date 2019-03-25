package com.tricolorflower.heartbeat.voiceroom.model.voicerole;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.util.List;

public class EmojiPageList extends BaseModel {


    @SerializedName("data")
    public List<EmojiPage> data;

    public static class EmojiPage {
        /**
         * page_id : 0
         * num : 2
         * size : 10
         */

        @SerializedName("page_id")
        public String pageId;
        @SerializedName("num")
        public int num;
        @SerializedName("size")
        public int size;
    }
}
