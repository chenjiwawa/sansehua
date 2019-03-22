package com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting;

import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.util.List;

public class LabelList extends BaseModel {


    public List<Label> data;

    public static class Label {
        /**
         * tag_name : 男神
         * id : 1
         */

        public String tag_name;
        public String id;
        public boolean isSelect;
    }
}
