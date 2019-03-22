package com.tricolorflower.heartbeat.voiceroom.fragment.roomsetting;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.dialog.QsDialogFragment;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;

import java.util.ArrayList;
import java.util.List;

public class RoomAnnounceDialogFragment extends QsDialogFragment implements TextWatcher {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.message)
    TextView message;
    @Bind(R.id.edit)
    EditText edit;
    @Bind(R.id.confirm)
    TextView confirm;
    @Bind(R.id.image)
    ImageView image;

    private OnDialogListener mListener;
    private String mTitle = "房间公告";
    private String mMessage = "";
    private int mIconId = R.mipmap.icon_my_font;

    VoiceRoom voiceRoom;
    VoiceRole voiceHolder;
    List<VoiceRole> voiceClients;
    VoiceRole user;

    public static RoomAnnounceDialogFragment getInstance(Bundle extras) {
        RoomAnnounceDialogFragment fragment = new RoomAnnounceDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    public static RoomAnnounceDialogFragment getInstance() {
        return new RoomAnnounceDialogFragment();
    }

    @Override
    protected int getDialogTheme() {
        return R.style.evaluate_dialog_style;
    }

    @Override
    protected View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_room_announce, viewGroup);
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
        message.setVisibility(View.GONE);
        message.setText(voiceRoom.voiceRoomAnnounce);
        edit.setVisibility(View.VISIBLE);
        edit.setText(voiceRoom.voiceRoomAnnounce);
        edit.addTextChangedListener(this);
        confirm.setVisibility(View.VISIBLE);
        image.setVisibility(View.VISIBLE);
        image.setImageResource(mIconId);
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s == null)
            return;
    }

    public void setIconId(int iconId) {
        this.mIconId = iconId;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setMessage(String message) {
        this.mMessage = message;
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

    @OnClick({R.id.confirm, R.id.image})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.confirm:
                break;
            case R.id.image:
                if (TextUtils.isEmpty(edit.getText().toString())) {
                    QsToast.show("请输入密码！");
                    return;
                }

                if (mListener != null) {
                    mListener.onLockSetting(edit.getText().toString());
                }
                break;
        }
        dismissAllowingStateLoss();
    }

    public interface OnDialogListener {
        void onLockSetting(String data);
    }

    public void setOnDialogListener(OnDialogListener listener) {
        this.mListener = listener;
    }

}
