package com.tricolorflower.heartbeat.voiceroom.model.voicerole;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.util.List;

public class ProductList extends BaseModel {


    /**
     * data : [{"id":2,"gift_name":"汽车","gift_pic":"htt","price":"300.00","gift_svga":""},{"id":1,"gift_name":"鲜花","gift_pic":"","price":"11.00","gift_svga":""}]
     * number_count : 2
     */

    @SerializedName("number_count")
    public int numberCount;
    @SerializedName("data")
    public List<Product> data;

    public static class Product {
        /**
         * id : 2
         * gift_name : 汽车
         * gift_pic : htt
         * price : 300.00
         * gift_svga :
         */

        @SerializedName("id")
        public String id;
        @SerializedName("gift_name")
        public String giftName;
        @SerializedName("gift_pic")
        public String giftPic;
        @SerializedName("price")
        public String price;
        @SerializedName("gift_svga")
        public String giftSvga;
        public boolean isSelect;
    }
}
