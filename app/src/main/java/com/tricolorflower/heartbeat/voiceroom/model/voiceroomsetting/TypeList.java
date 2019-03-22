package com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting;

import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.util.List;

public class TypeList extends BaseModel {


    public List<Type> data;

    public static class Type {
        /**
         * id : 1
         * name : 聊天交友
         */

        public String id;
        public String name;
        public boolean isSelect;
    }
}
