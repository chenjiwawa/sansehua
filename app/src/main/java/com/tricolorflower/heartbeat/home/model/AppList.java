package com.tricolorflower.heartbeat.home.model;

import com.tricolorflower.heartbeat.appdetail.model.App;
import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:38
 * @Description
 */

public class AppList extends BaseModel {

    public List<App> apps;

    @Override
    public boolean isLastPage() {
        return !(apps != null && apps.size() > 0);
    }

    @Override
    public String toString() {
        return "AppList{" +
                "apps=" + apps +
                '}';
    }
}
