package com.tricolorflower.heartbeat.search.model;

import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.util.ArrayList;
import java.util.List;


/**
 * @CreateBy qsmaxmin
 * @Date 2016/11/4 10:46
 * @Description
 */

public class ModelSearchHistory extends BaseModel {
    public List<String> searchHistory = new ArrayList<>();

    @Override
    public String toString() {
        return "ModelSearchHistory{" +
                "searchHistory=" + searchHistory +
                '}';
    }
}
