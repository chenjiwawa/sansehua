package com.zl.dappore.voiceroom.model;

import com.google.gson.annotations.SerializedName;

public class VoiceClient extends BaseVoiceRole {

    @SerializedName("position")
    public int position;

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

}
