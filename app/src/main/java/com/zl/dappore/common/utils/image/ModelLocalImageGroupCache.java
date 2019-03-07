package com.zl.dappore.common.utils.image;

import com.zl.dappore.common.model.BaseModel;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @CreateBy qsmaxmin
 * @Date 2016/10/31 17:39
 * @Description 用来序列化到本地
 */
public class ModelLocalImageGroupCache extends BaseModel implements Serializable {
    public ArrayList<ModelImageGroup> localImageList;

    public ModelLocalImageGroupCache(ArrayList<ModelImageGroup> localImageList) {
        this.localImageList = localImageList;
    }
}
