package com.zl.dappore.common.presenter;

import android.os.Bundle;
import android.text.TextUtils;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.zl.dappore.account.LoginActivity;
import com.zl.dappore.account.model.LoginConstant;
import com.zl.dappore.common.model.BaseModel;
import com.qsmaxmin.qsbase.mvp.QsIView;
import com.qsmaxmin.qsbase.mvp.presenter.QsPresenter;
import com.zl.dappore.common.model.UserConfig;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/3 13:30
 * @Description 如果需要，可以重写父类方法
 */

public abstract class DapporePresenter<V extends QsIView> extends QsPresenter<V> {


//    private Interceptor requestInterceptor = new Interceptor() {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//
//            Request request = chain.request();
//            if (UserConfig.getInstance().isLogin()) {
//                request.newBuilder()
//                        .addHeader("Auth-Token", UserConfig.getInstance().getAuthToken())
//                        .build();
//            }
//
//            AgoraLog.i(initTag(), " requestInterceptor " + request.url().toString());
//            AgoraLog.i(initTag(), " requestInterceptor " + request.toString());
//            AgoraLog.i(initTag(), " requestInterceptor " + request.headers().toString());
//            Response response = chain.proceed(request);
//            return response;
//        }
//    };


//    protected <T> T createHttpRequest(Class<T> clazz, String requestTag) {
//        QsHelper.getInstance().getHttpHelper().getHttpClient().newBuilder().addInterceptor(requestInterceptor).build();
//        return super.createHttpRequest(clazz, requestTag);
//    }

//    protected <T> T createHttpRequest(Class<T> clazz, String requestTag) {
//        QsHelper.getInstance().getHttpHelper().getHttpClient().newBuilder().addInterceptor(requestInterceptor).build();
//        T t = super.createHttpRequest(clazz, requestTag);
//        return t;
//    }

    /**
     * 当服务端返回状态异常时打印信息
     */
    protected void showFailMsg(BaseModel baseModel) {
        if (baseModel != null && !(BaseModel.CODE_SUCESS == baseModel.code) && !TextUtils.isEmpty(baseModel.message)) {
            QsToast.show(baseModel.message);
        }
    }

    public boolean checkLogin(int requestCode) {
        if (UserConfig.getInstance().isLogin()) {
            return true;
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt(LoginConstant.LoginActivity_RequestCode, requestCode);
            QsHelper.getInstance().intent2Activity(LoginActivity.class, bundle);
            return false;
        }
    }

}
