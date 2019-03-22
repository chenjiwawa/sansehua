package com.tricolorflower.heartbeat.common.model;

import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/22  上午10:28
 * @Description 列表页数据模型基类
 */
public class BaseListModel extends BaseModel {
    public int startRow;
    public int endRow;
    public int firstPage;
    public int lastPage;
    public int nextPage;
    public int navigatePages;
    public int pageNum;
    public int pageSize;
    public int pages;
    public int prePage;
    public int size;  //一页的条数
    public int total;

    public boolean hasPreviousPage;
    public boolean hasNextPage;
    public boolean isFirstPage;
    public boolean isLastPage;

    public List<Integer> navigatepageNums;
}
