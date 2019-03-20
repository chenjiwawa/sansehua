package com.zl.dappore.voiceroom.fragment.roomsetting;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.dialog.QsDialogFragment;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.model.voicerole.VoiceRole;
import com.zl.dappore.voiceroom.model.voiceroom.VoiceRoom;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;

import java.util.ArrayList;
import java.util.List;

public class RoomLogoDialogFragment extends QsDialogFragment {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.logo)
    ImageView logo;
    @Bind(R.id.option)
    TextView option;
    @Bind(R.id.cancel)
    TextView cancel;
    @Bind(R.id.confirm)
    TextView confirm;
    @Bind(R.id.image)
    ImageView image;

    private OnDialogListener mListener;
    private String mTitle = "房间头像修改";
    private String mMessage = "";
    private String mConfirm = "确认修改";
    private String mCancel = QsHelper.getInstance().getString(R.string.cancel);
    private int mIconId = R.mipmap.icon_my_font;

    VoiceRoom voiceRoom;
    VoiceRole voiceHolder;
    List<VoiceRole> voiceClients;
    VoiceRole user;

    public static RoomLogoDialogFragment getInstance(Bundle extras) {
        RoomLogoDialogFragment fragment = new RoomLogoDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    public static RoomLogoDialogFragment getInstance() {
        return new RoomLogoDialogFragment();
    }

    @Override
    protected int getDialogTheme() {
        return R.style.evaluate_dialog_style;
    }

    @Override
    protected View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_room_logo, viewGroup);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        initArgumentData();

        setContentView(voiceRoom);
    }

    private void setContentView(VoiceRoom voiceRoom) {
        if (voiceRoom == null)
            return;

        title.setText(mTitle);
        confirm.setText(mConfirm);
        cancel.setText(mCancel);
        image.setImageResource(mIconId);
        if (!TextUtils.isEmpty(voiceRoom.voiceRoomLogo)) {
            QsHelper.getInstance().getImageHelper().createRequest().load(voiceRoom.voiceRoomLogo).circleCrop().into(logo);
        }
    }

    private void initArgumentData() {
        Bundle arguments = getArguments();
        if (arguments == null) return;

        voiceRoom = (VoiceRoom) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM);
        voiceHolder = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_HOLDER);
        voiceClients = (ArrayList<VoiceRole>) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_CLIENTS);
        user = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER);

        L.i(initTag(), " initArgumentData voiceRoom " + voiceRoom);
        L.i(initTag(), " initArgumentData voiceHolder " + voiceHolder);
        L.i(initTag(), " initArgumentData voiceClients " + voiceClients);
        L.i(initTag(), " initArgumentData user " + user);

    }

    public void setIconId(int iconId) {
        this.mIconId = iconId;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setMessage(String message) {
        this.mTitle = message;
    }

    public void setCancel(String cancel) {
        this.mCancel = cancel;
    }

    public void setConfirm(String confirm) {
        this.mConfirm = confirm;
    }

    @Override
    public void onStart() {
        super.onStart();
        L.i(initTag(), " onStart ");

        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @OnClick({R.id.cancel, R.id.confirm})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.cancel:
                break;
            case R.id.confirm:
                if (mListener != null) {
                    mListener.onLogoSetting("");
                }
                break;
        }
        dismissAllowingStateLoss();
    }

    public interface OnDialogListener {
        void onLogoSetting(String data);
    }

    public void setOnDialogListener(OnDialogListener listener) {
        this.mListener = listener;
    }

}
