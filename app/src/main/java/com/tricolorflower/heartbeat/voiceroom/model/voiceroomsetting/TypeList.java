package com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting;

import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.util.List;

public class TypeList extends BaseModel {


    public List<Type> data;

    public static class Type {

        public static final int ID_EMPTY = 0;
        /**
         * id : 1
         * name : 聊天交友
         */

        public int id;
        public String name;
        public boolean isSelect;
    }
}
