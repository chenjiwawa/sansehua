package com.tricolorflower.heartbeat.voiceroom.model.voicerole;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.util.List;

public class EmojiList extends BaseModel {


    /**
     * data : [{"id":2,"emoji_name":"汽车","emoji_pic":"htt","price":"300.00","emoji_svga":""},{"id":1,"emoji_name":"鲜花","emoji_pic":"","price":"11.00","emoji_svga":""}]
     * number_count : 2
     */

    @SerializedName("number_count")
    public int numberCount;
    @SerializedName("data")
    public List<Emoji> data;

    public static class Emoji {
        /**
         * id : 2
         * emoji_name : 汽车
         * emoji_pic : htt
         * price : 300.00
         * emoji_svga :
         */

        @SerializedName("id")
        public String id;
        @SerializedName("emoji_name")
        public String emojiName;
        @SerializedName("emoji_pic")
        public String emojiPic;
        @SerializedName("price")
        public String price;
        @SerializedName("emoji_svga")
        public String emojiSvga;
    }
}
