package com.tricolorflower.heartbeat.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.tricolorflower.heartbeat.account.model.UserResponse;
import com.tricolorflower.heartbeat.photo.model.UploadRequestBody;
import com.tricolorflower.heartbeat.photo.model.UploadResponse;


public interface UploadHttp {

    @POST("/rails/active_storage/direct_uploads")
    UploadResponse requestUpload(@Body UploadRequestBody body);

    @POST("/api/rails/active_storage/direct_uploads")
    UserResponse requestUploadImage(@Body UploadRequestBody body);

    @POST("/api/rails/active_storage/direct_uploads")
    UserResponse requestUploadVideo(@Body UploadRequestBody body);

}
