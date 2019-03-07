package com.zl.dappore.common.model;

import com.qsmaxmin.qsbase.common.model.QsModel;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/18  下午3:14
 * @Description 请求体基类, 请求体必须继承该类
 */
public class BaseRequstModel extends QsModel {
    /**
     * 当前页(分页加载必须)
     */
    public int pageNumber;

    public int pageSize = 10;

}
