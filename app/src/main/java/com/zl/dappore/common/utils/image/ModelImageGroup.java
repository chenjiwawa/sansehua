package com.zl.dappore.common.utils.image;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @CreateBy qsmaxmin
 * @Date 16/10/26
 * @Description
 */
public class ModelImageGroup implements Serializable {
    private String dirName = "";//文件夹名
    private ArrayList<ModelImage> images  = new ArrayList<>();//文件夹下所有图片

    public String getDirName() {
        return dirName;
    }

    void setDirName(String dirName) {
        this.dirName = dirName;
    }

    /**
     * 获取第一张图片的路径(作为封面)
     */
    public String getFirstImgPath() {
        if (images.size() > 0 && images.get(0) != null) {
            return images.get(0).path;
        }
        return null;
    }

    public int getImageCount() {
        return images.size();
    }

    public ArrayList<ModelImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<ModelImage> images) {
        this.images = images;
    }

    void addImage(ModelImage image) {
        if (images == null) {
            images = new ArrayList<>();
        }
        images.add(image);
    }

    public boolean removeImage(String imagePath) {
        return images != null && images.remove(imagePath);
    }

    @Override
    public String toString() {
        return "ModelImageGroup{" +
                "dirName='" + dirName + '\'' +
                ", images=" + images +
                '}';
    }

    /**
     * 重写该方法
     * 使只要图片所在的文件夹名称(dirName)相同就属于同一个图片组
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ModelImageGroup && this.dirName.equals(((ModelImageGroup) o).dirName);
    }
}
