package com.zl.dappore.search.model;

import com.zl.dappore.common.model.BaseModel;
import com.qsmaxmin.qsbase.common.model.QsModel;

import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/23  上午9:35
 * @Description
 */
public class ModelHotWord extends BaseModel {


    public ResponseDataModel responseData;

    public static class ResponseDataModel extends QsModel {
        public List<String> hotWord;
    }
}
