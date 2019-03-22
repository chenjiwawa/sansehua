package com.tricolorflower.heartbeat.appdetail.model;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:38
 * @Description
 */

public class TradeHistoryResponse extends BaseModel {


    @SerializedName("user_data")
    public List<Data> userData;
    @SerializedName("transaction_data")
    public List<Data> transactionData;
    @SerializedName("turnover_data")
    public List<Data> turnoverData;
    @SerializedName("times")
    public List<String> times;

    public static class Data {
        /**
         * value : 1
         */

        @SerializedName("value")
        public Float value;
    }
}
