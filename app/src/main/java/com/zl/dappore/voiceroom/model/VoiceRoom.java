package com.zl.dappore.voiceroom.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VoiceRoom implements Serializable {

    @SerializedName("id")
    public String id;
    @SerializedName("type")
    public int type;
    @SerializedName("typeName")
    public String typeName;
    @SerializedName("name")
    public String name;
    @SerializedName("logo")
    public String logo;
    @SerializedName("isLock")
    public boolean isLock;
    @SerializedName("password")
    public String password;
    @SerializedName("isLock")
    public boolean isOpenChatRoom;
    @SerializedName("greeting")
    public String greeting;

    public VoiceRole voiceHolder;
    public List<VoiceRole> voiceRoles;


    public VoiceRoom(String id, int type, String typeName, String name, String logo, boolean isLock, String password, boolean isOpenChatRoom, String greeting, VoiceRole voiceHolder, List<VoiceRole> voiceRoles) {
        this.id = id;
        this.type = type;
        this.typeName = typeName;
        this.name = name;
        this.logo = logo;
        this.isLock = isLock;
        this.password = password;
        this.isOpenChatRoom = isOpenChatRoom;
        this.greeting = greeting;
        this.voiceHolder = voiceHolder;
        this.voiceRoles = voiceRoles;
    }
}
