/**
 * ImageRequest.java
 * ImageChooser
 * <p>
 * Created by likebamboo on 2014-4-25
 * Copyright (c) 1998-2014 http://likebamboo.github.io/ All rights reserved.
 */

package com.zl.dappore.common.utils.image;

import android.graphics.Point;

/**
 * 本地图片请求封装
 *
 * @author likebamboo
 */
class ImageRequest {
    /**
     * 图片路径
     */
    private String mPath = "";

    /**
     * 图片size
     */
    private Point mSize = null;

    /**
     * 加载图片后回调
     */
    private LocalImageLoader.ImageCallBack mCallBack = null;

    ImageRequest(String mPath, Point mSize, LocalImageLoader.ImageCallBack mCallBack) {
        super();
        this.mPath = mPath;
        this.mSize = mSize;
        this.mCallBack = mCallBack;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        this.mPath = path;
    }

    public Point getSize() {
        return mSize;
    }

    public void setSize(Point size) {
        this.mSize = size;
    }

    LocalImageLoader.ImageCallBack getCallBack() {
        return mCallBack;
    }

    public void setCallBack(LocalImageLoader.ImageCallBack callBack) {
        this.mCallBack = callBack;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ImageRequest other = (ImageRequest) obj;
        return this.mPath.equals(other.mPath);
    }

    @Override
    public String toString() {
        return "ImageRequest [mPath=" + mPath + ", mSize=" + mSize;
    }

}
