package com.tricolorflower.heartbeat.voiceroom.fragment.roomsetting;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.dialog.QsDialogFragment;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.listener.ItemSelectListener;
import com.tricolorflower.heartbeat.common.listener.ItemSingleSelectListener;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.TypeList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;

import java.util.ArrayList;
import java.util.List;

public class RoomTypeDialogFragment extends QsDialogFragment implements ItemSelectListener<TypeList.Type> {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.content)
    RelativeLayout content;
    @Bind(R.id.cancel)
    TextView cancel;
    @Bind(R.id.confirm)
    TextView confirm;
    @Bind(R.id.image)
    ImageView image;

    TypeGridFragment fragment;
    private OnDialogListener mListener;
    private String mTitle = "房间类型";
    private String mMessage = "";
    private String mConfirm = "确认类型";
    private String mCancel = QsHelper.getInstance().getString(R.string.cancel);
    private int mIconId = R.mipmap.icon_my_font;

    VoiceRoom voiceRoom;
    VoiceRole voiceHolder;
    List<VoiceRole> voiceClients;
    VoiceRole user;
    String data;


    public static RoomTypeDialogFragment getInstance(Bundle extras) {
        RoomTypeDialogFragment fragment = new RoomTypeDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    public static RoomTypeDialogFragment getInstance() {
        return new RoomTypeDialogFragment();
    }

    @Override
    protected int getDialogTheme() {
        return R.style.evaluate_dialog_style;
    }

    @Override
    protected View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_room_type, viewGroup);
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

        fragment = (TypeGridFragment) getChildFragmentManager().findFragmentById(R.id.labels);
        fragment.setItemListener(this);
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
                if (TextUtils.isEmpty(data)) {
                    QsToast.show("请选择类型！");
                    return;
                }

                if (mListener != null) {
                    mListener.onTypeSetting(data);
                }
                break;
        }
        dismissAllowingStateLoss();
    }

    @Override
    public void onItemSelect(TypeList.Type data) {
        if (data == null)
            return;

        this.data = data.id;
    }

    public interface OnDialogListener {
        void onTypeSetting(String data);
    }

    public void setOnDialogListener(OnDialogListener listener) {
        this.mListener = listener;
    }

}
