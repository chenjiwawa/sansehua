package com.tricolorflower.heartbeat.videodetail.model;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:38
 * @Description
 */

public class RewardResponse extends BaseModel {


    /**
     * aim_logs : [{"id":2623,"aim_id":4,"user_id":12,"created_at":"2019-01-16T14:02:17.543+08:00","ip":{"family":2,"addr":3723021965,"mask_addr":4294967295},"entity_type":"App","entity_id":4026,"code":"share_app","rewarded":true}]
     * reward : {"amount":"0.73","code":"success"}
     */

    @SerializedName("reward")
    public Reward reward;
    @SerializedName("aim_logs")
    public List<AimLog> aimLogs;

    public static class Reward {
        /**
         * amount : 0.73
         * code : success
         */

        @SerializedName("amount")
        public String amount;
        @SerializedName("code")
        public String code;

        @Override
        public String toString() {
            return "Reward{" +
                    "amount='" + amount + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }
    }

    public static class AimLog {
        /**
         * id : 2623
         * aim_id : 4
         * user_id : 12
         * created_at : 2019-01-16T14:02:17.543+08:00
         * ip : {"family":2,"addr":3723021965,"mask_addr":4294967295}
         * entity_type : App
         * entity_id : 4026
         * code : share_app
         * rewarded : true
         */

        @SerializedName("id")
        public String id;
        @SerializedName("aim_id")
        public String aimId;
        @SerializedName("user_id")
        public String userId;
        @SerializedName("created_at")
        public String createdAt;
        @SerializedName("ip")
        public Ip ip;
        @SerializedName("entity_type")
        public String entityType;
        @SerializedName("entity_id")
        public String entityId;
        @SerializedName("code")
        public String code;
        @SerializedName("rewarded")
        public boolean rewarded;

        public static class Ip {
            /**
             * family : 2
             * addr : 3723021965
             * mask_addr : 4294967295
             */

            @SerializedName("family")
            public String family;
            @SerializedName("addr")
            public String addr;
            @SerializedName("mask_addr")
            public String maskAddr;

            @Override
            public String toString() {
                return "Ip{" +
                        "family='" + family + '\'' +
                        ", addr='" + addr + '\'' +
                        ", maskAddr='" + maskAddr + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "AimLog{" +
                    "id='" + id + '\'' +
                    ", aimId='" + aimId + '\'' +
                    ", userId='" + userId + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", ip=" + ip +
                    ", entityType='" + entityType + '\'' +
                    ", entityId='" + entityId + '\'' +
                    ", code='" + code + '\'' +
                    ", rewarded=" + rewarded +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RewardResponse{" +
                "reward=" + reward +
                ", aimLogs=" + aimLogs +
                '}';
    }
}
