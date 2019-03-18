package com.zl.dappore.voiceroom.fragment.roomsetting;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.widget.dialog.QsDialogFragment;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.model.LabelList;
import com.zl.dappore.voiceroom.model.TypeList;
import com.zl.dappore.voiceroom.model.VoiceRole;
import com.zl.dappore.voiceroom.model.VoiceRoom;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;

import butterknife.OnClick;

public class RoomLabelDialogFragment extends QsDialogFragment implements LabelGridFragment.ItemListener<LabelList.Label> {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.selectlabel)
    RelativeLayout selectlabel;
    @Bind(R.id.editlabel)
    RelativeLayout editlabel;
    @Bind(R.id.edit)
    EditText edit;
    @Bind(R.id.option)
    TextView option;
    @Bind(R.id.swichover)
    TextView swichover;
    @Bind(R.id.cancel)
    TextView cancel;
    @Bind(R.id.confirm)
    TextView confirm;
    @Bind(R.id.image)
    ImageView image;

    LabelGridFragment fragment;
    private OnDialogListener mListener;
    private String mTitle = "房间类型";
    private String mMessage = "";
    private String mConfirm = "确认类型";
    private String mCancel = QsHelper.getInstance().getString(R.string.cancel);
    private String selectLabel = "选择标签";
    private String editLabel = "自定义标签";
    private int mIconId = R.mipmap.icon_my_font;

    VoiceRoom room;
    VoiceRole user;
    Bundle bundle;
    String data = "";

    public static RoomLabelDialogFragment getInstance(Bundle extras) {
        RoomLabelDialogFragment fragment = new RoomLabelDialogFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    public static RoomLabelDialogFragment getInstance() {
        return new RoomLabelDialogFragment();
    }

    @Override
    protected int getDialogTheme() {
        return R.style.evaluate_dialog_style;
    }

    @Override
    protected View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_room_label, viewGroup);
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
        swichover.setText(selectLabel);

        fragment = (LabelGridFragment) getChildFragmentManager().findFragmentById(R.id.labels);
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
                    QsToast.show("请选择标签！");
                    return;
                }

                if (mListener != null) {
                    mListener.onLabelSetting(data);
                }
                break;
            case R.id.swichover:
                if (swichover.getText().toString().equals(selectLabel)) {
                    selectlabel.setVisibility(View.VISIBLE);
                    editlabel.setVisibility(View.GONE);
                } else {
                    selectlabel.setVisibility(View.GONE);
                    editlabel.setVisibility(View.VISIBLE);
                }
                break;
        }
        dismissAllowingStateLoss();
    }

    @Override
    public void onItemSelect(LabelList.Label data) {
        if (data == null)
            return;

        this.data = data.id;
    }

    public interface OnDialogListener {
        void onLabelSetting(String data);
    }

    public void setOnDialogListener(OnDialogListener listener) {
        this.mListener = listener;
    }

}
