package com.tricolorflower.heartbeat.setting.model;

import android.text.TextUtils;

import com.tricolorflower.heartbeat.common.model.BaseModel;


/**
 * @CreateBy zhuanggy
 * @Date 2016/8/4 10:56
 * @Description
 */
public class CheckUpdateResponse extends BaseModel {

    public String downloadurl;

    public boolean foreUpdate;

    public boolean isNew;//true  代表是最新版本，false 代表不是最新版本

    public boolean hasNewVersion() {
        return (!isNew && !TextUtils.isEmpty(downloadurl));
    }

    public boolean mustUpdateToNewVersion() {
        return foreUpdate;
    }// 识别是否强制

}
