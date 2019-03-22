package com.tricolorflower.heartbeat.account;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.utils.AcountUtils;
import com.tricolorflower.heartbeat.account.model.LoginConstant;
import com.tricolorflower.heartbeat.account.presenter.LoginPresenter;
import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.QsActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @CreatedBy zhuanggy
 * @Date 16/8/3
 * @Description 关于我们
 */
public class LoginActivity extends QsActivity<LoginPresenter> implements View.OnFocusChangeListener {

    @Bind(R.id.iv_back_login)
    ImageView iv_back_login;
    @Bind(R.id.iv_close_login)
    ImageView iv_close_login;
    @Bind(R.id.tv_title_login)
    TextView tv_title_login;

    @Bind(R.id.ll_id_login)
    LinearLayout ll_id_login;
    @Bind(R.id.tv_prefixid_login)
    TextView tv_prefixid_login;
    @Bind(R.id.divider_prefixid_login)
    View divider_prefixid_login;
    @Bind(R.id.et_id_login)
    EditText et_id_login;

    @Bind(R.id.rl_check_login)
    RelativeLayout rl_check_login;
    @Bind(R.id.et_check_login)
    EditText et_check_login;
    @Bind(R.id.divider_check_login)
    View divider_check_login;
    @Bind(R.id.tv_check_login)
    TextView tv_check_login;

    @Bind(R.id.et_psd)
    EditText et_psd;
    @Bind(R.id.et_repsd)
    EditText et_repsd;

    @Bind(R.id.btn_login)
    Button btn_login;
    @Bind(R.id.tv_forget_psd)
    TextView tv_forget_psd;
    @Bind(R.id.tv_switch_login)
    TextView tv_switch_login;

    private int state = LoginConstant.STATE_LOGIN_EMAIL;
    private String id = "";
    private String psd = "";
    private Disposable mdDisposable;

    @Override
    public void initData(Bundle savedInstanceState) {
        et_id_login.setOnFocusChangeListener(this);
        et_check_login.setOnFocusChangeListener(this);
        et_psd.setOnFocusChangeListener(this);
        et_repsd.setOnFocusChangeListener(this);

        updateActionView(LoginConstant.STATE_LOGIN_EMAIL);
        et_id_login.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                id = s.toString();

                L.i(initTag(), " id " + id + " state " + state + " " + AcountUtils.isEmail(s.toString()) + " " + AcountUtils.isMobile(s.toString()));
                switch (state) {
                    case LoginConstant.STATE_LOGIN_PHONE:
                        if (AcountUtils.isEmail(s.toString())) {
                            state = LoginConstant.STATE_LOGIN_EMAIL;
                            updateActionView(state);
                        }
                        break;
                    case LoginConstant.STATE_LOGIN_EMAIL:
                        if (AcountUtils.isMobile(s.toString())) {
                            state = LoginConstant.STATE_LOGIN_PHONE;
                            updateActionView(state);
                        }
                        break;
                    case LoginConstant.STATE_FORGET_PHONE:
                        if (AcountUtils.isEmail(s.toString())) {
                            state = LoginConstant.STATE_FORGET_EMAIL;
                            updateActionView(state);
                        }
                        break;
                    case LoginConstant.STATE_FORGET_EMAIL:
                        if (AcountUtils.isMobile(s.toString())) {
                            state = LoginConstant.STATE_FORGET_PHONE;
                            updateActionView(state);
                        }
                        break;
                    case LoginConstant.STATE_REGISTER_PHONE:
                        if (AcountUtils.isEmail(s.toString())) {
                            state = LoginConstant.STATE_REGISTER_EMAIL;
                            updateActionView(state);
                        }
                        break;
                    case LoginConstant.STATE_REGISTER_EMAIL:
                        if (AcountUtils.isMobile(s.toString())) {
                            state = LoginConstant.STATE_REGISTER_PHONE;
                            updateActionView(state);
                        }
                        break;
                }
            }
        });

    }

    public void setCountDownView() {
        mdDisposable = Flowable.intervalRange(0, 10, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        tv_check_login.setEnabled(false);
                        tv_check_login.setTextColor(QsHelper.getInstance().getColor(R.color.color_light_white));
                        if (state == LoginConstant.STATE_FORGET_PHONE || state == LoginConstant.STATE_REGISTER_PHONE) {
                            tv_check_login.setText("倒计时 " + (10 - aLong) + "s");
                        } else {
                            tv_check_login.setText("去邮箱查看 " + (10 - aLong) + "s");
                        }
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        tv_check_login.setEnabled(true);
                        tv_check_login.setTextColor(QsHelper.getInstance().getColor(R.color.color_white));
                        tv_check_login.setText("获取验证码");
                    }
                })
                .subscribe();
    }


    @Override
    public int layoutId() {
        return R.layout.activity_login;
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.iv_back_login, R.id.iv_close_login, R.id.tv_check_login, R.id.btn_login, R.id.tv_forget_psd, R.id.tv_switch_login})
    public void onViewClick(View view) {
        super.onViewClick(view);

        String id = et_id_login.getText().toString().replace(" ", "");
        String checkCode = et_check_login.getText().toString().replace(" ", "");
        String psd = et_psd.getText().toString().replace(" ", "");
        String repsd = et_repsd.getText().toString().replace(" ", "");

        switch (view.getId()) {
            case R.id.iv_back_login:
                switch (state) {
                    case LoginConstant.STATE_FORGET_PHONE:
                        state = LoginConstant.STATE_LOGIN_PHONE;
                        updateActionView(state);
                        break;
                    case LoginConstant.STATE_FORGET_EMAIL:
                        state = LoginConstant.STATE_LOGIN_EMAIL;
                        updateActionView(state);
                        break;
                }
                break;
            case R.id.iv_close_login:
                finish();
                break;
            case R.id.tv_check_login:
                L.i(initTag(), "tv_check_login ");

                if (TextUtils.isEmpty(id)) {
                    QsToast.show("请输入手机号或邮箱!");
                    return;
                }
                if (!(AcountUtils.isEmail(id) || AcountUtils.isMobile(id))) {
                    QsToast.show("请输入正确手机号或邮箱!");
                    return;
                }
                switch (state) {
                    case LoginConstant.STATE_FORGET_PHONE:
                    case LoginConstant.STATE_REGISTER_PHONE:
                        //手机验证码
                        getPresenter().requestCheckCode(id);
                        setCountDownView();
                        break;
                    case LoginConstant.STATE_FORGET_EMAIL:
                    case LoginConstant.STATE_REGISTER_EMAIL:
                        //邮箱验证码
                        getPresenter().requestCheckCode(id);
                        setCountDownView();
                        break;
                }
                break;
            case R.id.btn_login:
                L.i(initTag(), "btn_login " + id + " " + psd);

                if (TextUtils.isEmpty(id)) {
                    QsToast.show("请输入手机号或邮箱!");
                    return;
                }
                if (!(AcountUtils.isEmail(id) || AcountUtils.isMobile(id))) {
                    QsToast.show("请输入正确手机号或邮箱!");
                    return;
                }
                switch (state) {
                    case LoginConstant.STATE_LOGIN_PHONE:
                    case LoginConstant.STATE_LOGIN_EMAIL:
                        if (TextUtils.isEmpty(psd)) {
                            QsToast.show("请输入密码!");
                            return;
                        }
                        //登录
                        getPresenter().requestLogin(id, psd, getIntent().getExtras());
                        break;
                    case LoginConstant.STATE_REGISTER_PHONE:
                    case LoginConstant.STATE_REGISTER_EMAIL:
                        //注册
                        if (TextUtils.isEmpty(checkCode)) {
                            QsToast.show("请输入验证码!");
                            return;
                        }
                        if (TextUtils.isEmpty(psd)) {
                            QsToast.show("请输入密码!");
                            return;
                        }
                        getPresenter().requestRegiste(id, checkCode, psd, getIntent().getExtras());
                        break;
                    case LoginConstant.STATE_FORGET_PHONE:
                    case LoginConstant.STATE_FORGET_EMAIL:
                        if (TextUtils.isEmpty(checkCode)) {
                            QsToast.show("请输入验证码!");
                            return;
                        }
                        if (TextUtils.isEmpty(psd) || TextUtils.isEmpty(repsd)) {
                            QsToast.show("请输入密码!");
                            return;
                        }
                        if (!psd.equals(repsd)) {
                            QsToast.show("密码不一致！");
                            return;
                        }
                        getPresenter().requestRegiste(id, et_check_login.getText().toString(), psd, getIntent().getExtras());
                        break;
                }
                break;
            case R.id.tv_forget_psd:
                switch (state) {
                    case LoginConstant.STATE_LOGIN_PHONE:
                    case LoginConstant.STATE_REGISTER_PHONE:
                        state = LoginConstant.STATE_FORGET_PHONE;
                        updateActionView(state);
                        break;
                    case LoginConstant.STATE_LOGIN_EMAIL:
                    case LoginConstant.STATE_REGISTER_EMAIL:
                        state = LoginConstant.STATE_FORGET_EMAIL;
                        updateActionView(state);
                        break;
                }
            case R.id.tv_switch_login:
                switch (state) {
                    case LoginConstant.STATE_LOGIN_PHONE:
                        if (AcountUtils.isEmail(et_id_login.getText().toString())) {
                            state = LoginConstant.STATE_REGISTER_EMAIL;
                            updateActionView(state);
                        } else {
                            state = LoginConstant.STATE_REGISTER_PHONE;
                            updateActionView(state);
                        }
                        break;
                    case LoginConstant.STATE_LOGIN_EMAIL:
                        if (AcountUtils.isMobile(et_id_login.getText().toString())) {
                            state = LoginConstant.STATE_REGISTER_PHONE;
                            updateActionView(state);
                        } else {
                            state = LoginConstant.STATE_REGISTER_EMAIL;
                            updateActionView(state);
                        }
                        break;
                    case LoginConstant.STATE_REGISTER_PHONE:
                        if (AcountUtils.isEmail(et_id_login.getText().toString())) {
                            state = LoginConstant.STATE_LOGIN_EMAIL;
                            updateActionView(state);
                        } else {
                            state = LoginConstant.STATE_LOGIN_PHONE;
                            updateActionView(state);
                        }
                        break;
                    case LoginConstant.STATE_REGISTER_EMAIL:
                        if (AcountUtils.isMobile(et_id_login.getText().toString())) {
                            state = LoginConstant.STATE_LOGIN_PHONE;
                            updateActionView(state);
                        } else {
                            state = LoginConstant.STATE_LOGIN_EMAIL;
                            updateActionView(state);
                        }
                        break;
                }
                break;
            default:
                break;
        }
    }

    private void updateActionView(int state) {
        switch (state) {
            case LoginConstant.STATE_LOGIN_PHONE:
                iv_back_login.setVisibility(View.GONE);
                iv_close_login.setVisibility(View.VISIBLE);
                tv_title_login.setText("欢迎回来！");

                tv_prefixid_login.setVisibility(View.GONE);
                divider_prefixid_login.setVisibility(View.GONE);
                et_id_login.setHint("请输入手机号/邮箱");

                rl_check_login.setVisibility(View.GONE);

                et_repsd.setVisibility(View.GONE);

                btn_login.setText("登录");
                tv_forget_psd.setVisibility(View.VISIBLE);
                tv_switch_login.setVisibility(View.VISIBLE);
                tv_switch_login.setText(getString(R.string.user_2_register));
                break;
            case LoginConstant.STATE_LOGIN_EMAIL:
                iv_back_login.setVisibility(View.GONE);
                iv_close_login.setVisibility(View.VISIBLE);
                tv_title_login.setText("欢迎回来！");

                tv_prefixid_login.setVisibility(View.GONE);
                divider_prefixid_login.setVisibility(View.GONE);
                et_id_login.setHint("请输入手机号/邮箱");

                rl_check_login.setVisibility(View.GONE);

                et_repsd.setVisibility(View.GONE);

                btn_login.setText("登录");
                tv_forget_psd.setVisibility(View.VISIBLE);
                tv_switch_login.setVisibility(View.VISIBLE);
                tv_switch_login.setText(getString(R.string.user_2_register));
                break;
            case LoginConstant.STATE_FORGET_PHONE:
                iv_back_login.setVisibility(View.VISIBLE);
                iv_close_login.setVisibility(View.GONE);
                tv_title_login.setText("找回密码");

                tv_prefixid_login.setVisibility(View.GONE);
                divider_prefixid_login.setVisibility(View.GONE);

                rl_check_login.setVisibility(View.VISIBLE);
                divider_check_login.setVisibility(View.VISIBLE);
                tv_check_login.setVisibility(View.VISIBLE);
                tv_check_login.setText("获取验证码");

                et_repsd.setVisibility(View.VISIBLE);
                et_repsd.setHint("请重复新密码");
                et_repsd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                btn_login.setText("登录");
                tv_forget_psd.setVisibility(View.GONE);
                tv_switch_login.setVisibility(View.GONE);
                break;
            case LoginConstant.STATE_FORGET_EMAIL:
                iv_back_login.setVisibility(View.VISIBLE);
                iv_close_login.setVisibility(View.GONE);
                tv_title_login.setText("找回密码");

                tv_prefixid_login.setVisibility(View.GONE);
                divider_prefixid_login.setVisibility(View.GONE);

                rl_check_login.setVisibility(View.VISIBLE);
                divider_check_login.setVisibility(View.VISIBLE);
                tv_check_login.setVisibility(View.VISIBLE);
                tv_check_login.setText("获取验证码");

                et_repsd.setVisibility(View.VISIBLE);
                et_repsd.setHint("请重复新密码");
                et_repsd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                btn_login.setText("登录");
                tv_forget_psd.setVisibility(View.GONE);
                tv_switch_login.setVisibility(View.GONE);
                break;
            case LoginConstant.STATE_REGISTER_PHONE:
                iv_back_login.setVisibility(View.GONE);
                iv_close_login.setVisibility(View.VISIBLE);
                tv_title_login.setText("欢迎加入！");

                tv_prefixid_login.setVisibility(View.GONE);
                divider_prefixid_login.setVisibility(View.GONE);
                et_id_login.setHint("请输入手机号/邮箱");

                rl_check_login.setVisibility(View.VISIBLE);
                divider_check_login.setVisibility(View.VISIBLE);
                tv_check_login.setVisibility(View.VISIBLE);
                tv_check_login.setText("获取验证码");

                et_repsd.setVisibility(View.VISIBLE);
                et_repsd.setHint("请输入邀请码");
                et_repsd.setInputType(InputType.TYPE_CLASS_TEXT);

                btn_login.setText("注册");
                tv_forget_psd.setVisibility(View.VISIBLE);
                tv_switch_login.setVisibility(View.VISIBLE);
                tv_switch_login.setText(getString(R.string.user_2_login));
                break;
            case LoginConstant.STATE_REGISTER_EMAIL:
                iv_back_login.setVisibility(View.GONE);
                iv_close_login.setVisibility(View.VISIBLE);
                tv_title_login.setText("欢迎加入！");

                tv_prefixid_login.setVisibility(View.GONE);
                divider_prefixid_login.setVisibility(View.GONE);
                et_id_login.setHint("请输入手机号/邮箱");

                rl_check_login.setVisibility(View.VISIBLE);
                divider_check_login.setVisibility(View.VISIBLE);
                tv_check_login.setVisibility(View.VISIBLE);
                tv_check_login.setText("获取验证码");

                et_repsd.setVisibility(View.VISIBLE);
                et_repsd.setHint("请输入邀请码");
                et_repsd.setInputType(InputType.TYPE_CLASS_TEXT);

                btn_login.setText("注册");
                tv_forget_psd.setVisibility(View.VISIBLE);
                tv_switch_login.setVisibility(View.VISIBLE);
                tv_switch_login.setText(getString(R.string.user_2_login));
                break;
        }
    }

    private void setCheckCodeView(String code) {
        et_check_login.setText(code);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setLoginSuceessView() {
        QsToast.show("登录成功！");
        finish();
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setRegisteSuceessView() {
        QsToast.show("注册成功！");
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mdDisposable != null) {
            mdDisposable.dispose();
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.et_id_login:
                if (hasFocus) {
                    ll_id_login.setBackgroundResource(R.drawable.divider_buttom_light);
                } else {
                    ll_id_login.setBackgroundResource(R.drawable.divider_buttom_dark);
                }
                break;
            case R.id.et_check_login:
                if (hasFocus) {
                    rl_check_login.setBackgroundResource(R.drawable.divider_buttom_light);
                } else {
                    rl_check_login.setBackgroundResource(R.drawable.divider_buttom_dark);
                }
                break;
            default:
                if (hasFocus) {
                    v.setBackgroundResource(R.drawable.divider_buttom_light);
                } else {
                    v.setBackgroundResource(R.drawable.divider_buttom_dark);
                }
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
