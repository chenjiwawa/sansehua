package com.zl.dappore.voiceroom.fragment.roomsetting;

import android.os.Bundle;
import android.text.Editable;
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
import com.qsmaxmin.qsbase.common.widget.dialog.QsDialogFragment;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.model.VoiceRole;
import com.zl.dappore.voiceroom.model.VoiceRoom;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;

import butterknife.OnClick;

public class RoomLockDialogFragment extends QsDialogFragment implements TextWatcher {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.edit)
    EditText edit;
    @Bind(R.id.option)
    TextView option;
    @Bind(R.id.cancel)
    TextView cancel;
    @Bind(R.id.confirm)
    TextView confirm;
    @Bind(R.id.image)
    ImageView image;

    private OnDialogListener mListener;
    private String mTitle = "房间上锁";
    private String mMessage = "";
    private String mConfirm = "确认修改";
    private String mCancel = QsHelper.getInstance().getString(R.string.cancel);
    private int mIconId = R.mipmap.icon_my_font;

    VoiceRoom room;
    VoiceRole user;
    Bundle bundle;

    public static RoomLockDialogFragment getInstance(Bundle extras) {
        RoomLockDialogFragment fragment = new RoomLockDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    public static RoomLockDialogFragment getInstance() {
        return new RoomLockDialogFragment();
    }

    @Override
    protected int getDialogTheme() {
        return R.style.evaluate_dialog_style;
    }

    @Override
    protected View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_room_lock, viewGroup);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();

        Bundle bundle = getArguments();
        if (bundle == null) return;
        room = (VoiceRoom) bundle.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM);
        user = (VoiceRole) bundle.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER);

        L.i(initTag(), " room " + room + " user " + user);

        title.setText(mTitle);
        confirm.setText(mConfirm);
        cancel.setText(mCancel);
        image.setImageResource(mIconId);
        edit.addTextChangedListener(this);

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

        option.setText((s.toString().length() / 1) + "/" + 8);
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
                if (mListener != null) {
                    mListener.onCancel();
                }
                break;
            case R.id.confirm:
                if (mListener != null) {
                    mListener.onConfirm();
                }
                break;
        }
        dismissAllowingStateLoss();
    }

    public interface OnDialogListener {
        void onConfirm();

        void onCancel();
    }

    public void setOnDialogListener(OnDialogListener listener) {
        this.mListener = listener;
    }

}
