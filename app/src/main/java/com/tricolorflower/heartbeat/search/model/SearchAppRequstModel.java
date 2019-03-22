package com.tricolorflower.heartbeat.search.model;

import com.tricolorflower.heartbeat.common.model.BaseRequestModel;
import com.qsmaxmin.qsbase.common.model.QsModel;

import java.io.Serializable;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/2/24 17:44
 * @Description
 */

public class SearchAppRequstModel extends BaseRequestModel implements Serializable {

    public SearchMapModel searchMap;

    public static class SearchMapModel extends QsModel  implements Serializable {
        public String name;

        public SearchMapModel(String name) {
            this.name = name;
        }
    }

    public SearchAppRequstModel(SearchMapModel searchMap) {
        this.searchMap = searchMap;
    }
}
