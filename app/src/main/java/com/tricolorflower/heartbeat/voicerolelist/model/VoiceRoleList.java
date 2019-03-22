package com.tricolorflower.heartbeat.voicerolelist.model;

import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.util.List;

public class VoiceRoleList extends BaseModel {


    public List<VoiceRole> data;

    public static class VoiceRole extends BaseModel {
        /**
         * nickname : 左手
         * sex : 1
         * birthday : 1990-06-30
         * vip : 0
         * wealth_level : 0
         * charm_level : 0
         * pic : 20190314/4e13ef391efec231addbeba7f5a7a9ae.png
         * age : 28
         */

        public String id;//TODO
        public String nickname;
        public int sex;
        public String birthday;
        public int vip;
        public int wealth_level;//TODO
        public int charm_level;
        public String pic;
        public int age;
        public String constellation;
        public boolean isSelect;
    }
}
