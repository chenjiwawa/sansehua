package com.tricolorflower.heartbeat.photo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UploadRequestBody implements Serializable {

    /**
     * blob : {"byte_size":23,"content_type":"image/png","filename":"sample.png","checksum":""}
     */

    @SerializedName("blob")
    public Blob blob;

    public UploadRequestBody(Blob blob) {
        this.blob = blob;
    }

    public static class Blob {
        /**
         * byte_size : 23
         * content_type : image/png
         * filename : sample.png
         * checksum :
         */

        @SerializedName("filename")
        public String filename;
        @SerializedName("content_type")
        public String contentType;
        @SerializedName("byte_size")
        public long byteSize;
        @SerializedName("checksum")
        public String checksum;

        public Blob(String filename, String contentType, long byteSize, String checksum) {
            this.filename = filename;
            this.contentType = contentType;
            this.byteSize = byteSize;
            this.checksum = checksum;
        }
    }

}