package com.zl.dappore.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.zl.dappore.account.model.UserResponse;
import com.zl.dappore.photo.model.UploadRequestBody;
import com.zl.dappore.photo.model.UploadResponse;


public interface UploadHttp {

    @POST("/rails/active_storage/direct_uploads")
    UploadResponse requestUpload(@Body UploadRequestBody body);

    @POST("/api/rails/active_storage/direct_uploads")
    UserResponse requestUploadImage(@Body UploadRequestBody body);

    @POST("/api/rails/active_storage/direct_uploads")
    UserResponse requestUploadVideo(@Body UploadRequestBody body);

}
