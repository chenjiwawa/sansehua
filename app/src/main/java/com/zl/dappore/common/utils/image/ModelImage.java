package com.zl.dappore.common.utils.image;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * @CreateBy qsmaxmin
 * @Date 2016/10/31 9:43
 * @Description
 */

public class ModelImage implements Serializable {
    public String path;
    public String md5Str;

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ModelImage && !TextUtils.isEmpty(((ModelImage) obj).md5Str) && ((ModelImage) obj).md5Str.equals(this.md5Str);
    }
}
