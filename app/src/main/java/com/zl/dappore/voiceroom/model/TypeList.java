package com.zl.dappore.voiceroom.model;

import com.zl.dappore.common.model.BaseModel;

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
