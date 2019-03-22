package com.tricolorflower.heartbeat.photo.model;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.io.Serializable;

public class UploadResponse extends BaseModel {

    /**
     * id : 2214
     * key : eyMBG6rVFxBsQK9EpeGzEqut
     * filename : sample.png
     * content_type : image/png
     * metadata : {}
     * byte_size : 23
     * checksum :
     * created_at : 2018-12-04T13:59:06.254+08:00
     * signed_id : eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaHBBcVlJIiwiZXhwIjpudWxsLCJwdXIiOiJibG9iX2lkIn19--568977a9197dedd3f6694cba31e41d75ff5bc2a9
     * direct_upload : {"url":"http://up-z2.qiniu.com/mkblk/23","headers":{"Content-Type":"application/octet-stream","Content-MD5":"","Authorization":"UpToken l5REcHUhawYT_HzPx2M8qsbk-lHmC18WD6zmSTND:zHRBDSVWYC8nY3U4BE5RUbFTz5Y=:eyJzY29wZSI6ImluZWUtZGV2OmV5TUJHNnJWRnhCc1FLOUVwZUd6RXF1dCIsImRlYWRsaW5lIjoxNTQzOTA2NzQ2LCJ1cGhvc3RzIjpbImh0dHA6Ly91cC16Mi5xaW5pdS5jb20iLCJodHRwOi8vdXBsb2FkLXoyLnFpbml1LmNvbSIsIi1IIHVwLXoyLnFpbml1LmNvbSBodHRwOi8vMTQuMTUyLjM3LjQiXSwiZ2xvYmFsIjpmYWxzZX0=","Up-Token":"l5REcHUhawYT_HzPx2M8qsbk-lHmC18WD6zmSTND:zHRBDSVWYC8nY3U4BE5RUbFTz5Y=:eyJzY29wZSI6ImluZWUtZGV2OmV5TUJHNnJWRnhCc1FLOUVwZUd6RXF1dCIsImRlYWRsaW5lIjoxNTQzOTA2NzQ2LCJ1cGhvc3RzIjpbImh0dHA6Ly91cC16Mi5xaW5pdS5jb20iLCJodHRwOi8vdXBsb2FkLXoyLnFpbml1LmNvbSIsIi1IIHVwLXoyLnFpbml1LmNvbSBodHRwOi8vMTQuMTUyLjM3LjQiXSwiZ2xvYmFsIjpmYWxzZX0=","Content-Url":"http://cloud.1314-edu.com/eyMBG6rVFxBsQK9EpeGzEqut"}}
     */

    @SerializedName("id")
    public int id;
    @SerializedName("key")
    public String key;
    @SerializedName("filename")
    public String filename;
    @SerializedName("content_type")
    public String contentType;
    @SerializedName("metadata")
    public MetadataBean metadata;
    @SerializedName("byte_size")
    public int byteSize;
    @SerializedName("checksum")
    public String checksum;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("signed_id")
    public String signedId;
    @SerializedName("direct_upload")
    public DirectUpload directUpload;

    public static class MetadataBean {
    }

    public static class DirectUpload {
        /**
         * url : http://up-z2.qiniu.com/mkblk/23
         * headers : {"Content-Type":"application/octet-stream","Content-MD5":"","Authorization":"UpToken l5REcHUhawYT_HzPx2M8qsbk-lHmC18WD6zmSTND:zHRBDSVWYC8nY3U4BE5RUbFTz5Y=:eyJzY29wZSI6ImluZWUtZGV2OmV5TUJHNnJWRnhCc1FLOUVwZUd6RXF1dCIsImRlYWRsaW5lIjoxNTQzOTA2NzQ2LCJ1cGhvc3RzIjpbImh0dHA6Ly91cC16Mi5xaW5pdS5jb20iLCJodHRwOi8vdXBsb2FkLXoyLnFpbml1LmNvbSIsIi1IIHVwLXoyLnFpbml1LmNvbSBodHRwOi8vMTQuMTUyLjM3LjQiXSwiZ2xvYmFsIjpmYWxzZX0=","Up-Token":"l5REcHUhawYT_HzPx2M8qsbk-lHmC18WD6zmSTND:zHRBDSVWYC8nY3U4BE5RUbFTz5Y=:eyJzY29wZSI6ImluZWUtZGV2OmV5TUJHNnJWRnhCc1FLOUVwZUd6RXF1dCIsImRlYWRsaW5lIjoxNTQzOTA2NzQ2LCJ1cGhvc3RzIjpbImh0dHA6Ly91cC16Mi5xaW5pdS5jb20iLCJodHRwOi8vdXBsb2FkLXoyLnFpbml1LmNvbSIsIi1IIHVwLXoyLnFpbml1LmNvbSBodHRwOi8vMTQuMTUyLjM3LjQiXSwiZ2xvYmFsIjpmYWxzZX0=","Content-Url":"http://cloud.1314-edu.com/eyMBG6rVFxBsQK9EpeGzEqut"}
         */

        @SerializedName("url")
        public String url;
        @SerializedName("headers")
        public Headers headers;

        public static class Headers {
            /**
             * Content-Type : application/octet-stream
             * Content-MD5 :
             * Authorization : UpToken l5REcHUhawYT_HzPx2M8qsbk-lHmC18WD6zmSTND:zHRBDSVWYC8nY3U4BE5RUbFTz5Y=:eyJzY29wZSI6ImluZWUtZGV2OmV5TUJHNnJWRnhCc1FLOUVwZUd6RXF1dCIsImRlYWRsaW5lIjoxNTQzOTA2NzQ2LCJ1cGhvc3RzIjpbImh0dHA6Ly91cC16Mi5xaW5pdS5jb20iLCJodHRwOi8vdXBsb2FkLXoyLnFpbml1LmNvbSIsIi1IIHVwLXoyLnFpbml1LmNvbSBodHRwOi8vMTQuMTUyLjM3LjQiXSwiZ2xvYmFsIjpmYWxzZX0=
             * Up-Token : l5REcHUhawYT_HzPx2M8qsbk-lHmC18WD6zmSTND:zHRBDSVWYC8nY3U4BE5RUbFTz5Y=:eyJzY29wZSI6ImluZWUtZGV2OmV5TUJHNnJWRnhCc1FLOUVwZUd6RXF1dCIsImRlYWRsaW5lIjoxNTQzOTA2NzQ2LCJ1cGhvc3RzIjpbImh0dHA6Ly91cC16Mi5xaW5pdS5jb20iLCJodHRwOi8vdXBsb2FkLXoyLnFpbml1LmNvbSIsIi1IIHVwLXoyLnFpbml1LmNvbSBodHRwOi8vMTQuMTUyLjM3LjQiXSwiZ2xvYmFsIjpmYWxzZX0=
             * Content-Url : http://cloud.1314-edu.com/eyMBG6rVFxBsQK9EpeGzEqut
             */

            @SerializedName("Content-Type")
            public String ContentType;
            @SerializedName("Content-MD5")
            public String ContentMD5;
            @SerializedName("Authorization")
            public String Authorization;
            @SerializedName("Up-Token")
            public String UpToken;
            @SerializedName("Content-Url")
            public String ContentUrl;

            @Override
            public String toString() {
                return "Headers{" +
                        "ContentType='" + ContentType + '\'' +
                        ", ContentMD5='" + ContentMD5 + '\'' +
                        ", Authorization='" + Authorization + '\'' +
                        ", UpToken='" + UpToken + '\'' +
                        ", ContentUrl='" + ContentUrl + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DirectUpload{" +
                    "url='" + url + '\'' +
                    ", headers=" + headers +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UploadResponse{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", filename='" + filename + '\'' +
                ", contentType='" + contentType + '\'' +
                ", metadata=" + metadata +
                ", byteSize=" + byteSize +
                ", checksum='" + checksum + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", signedId='" + signedId + '\'' +
                ", directUpload=" + directUpload +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", isLastPage=" + isLastPage +
                '}';
    }
}