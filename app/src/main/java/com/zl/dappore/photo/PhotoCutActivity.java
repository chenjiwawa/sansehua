package com.zl.dappore.photo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.zl.dappore.R;
import com.zl.dappore.common.model.UserConfig;
import com.zl.dappore.common.widget.CutImageView;
import com.zl.dappore.photo.model.PhotoConstants;
import com.zl.dappore.photo.presenter.PhotoCutPresenter;

import java.io.ByteArrayOutputStream;


/**
 * @CreateBy qsmaxmin
 * @Date 16/9/18  下午4:53
 * @Description 裁剪图片
 */
public class PhotoCutActivity extends QsABActivity<PhotoCutPresenter> {

    @Bind(R.id.civ_photo)
    CutImageView civ_photo;
    @Bind(android.R.id.title)
    TextView title;
    @Bind(R.id.tv_right)
    TextView tv_right;

    @Bind(R.id.cut_cancel)
    Button cut_cancel;
    @Bind(R.id.cut_ok)
    Button cut_ok;

    /**
     * 设置标题
     */
    @Override
    public int actionbarLayoutId() {
        return R.layout.actionbar_right_text;
    }

    /**
     * 设置布局
     */
    @Override
    public int layoutId() {
        return R.layout.activity_cutphoto;
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData(Bundle savedInstanceState) {
        tv_right.setVisibility(View.GONE);
        String head_path = getIntent().getExtras().getString(PhotoConstants.BUNDLE_KEY_PHOTO_PATH);
        civ_photo.setFilePath(head_path);
        title.setText(QsHelper.getInstance().getString(R.string.move_zoom));
    }

    /**
     * 返回按钮
     */
    @OnClick({R.id.layout_title_back, R.id.cut_cancel, R.id.cut_ok})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.layout_title_back:
                onBackPressed();
                break;

            case R.id.cut_cancel:
                onBackPressed();
                break;
            case R.id.cut_ok:
                Bitmap bitmap = civ_photo.clip();
                L.i(initTag(),"cut bitmap success width:" + bitmap.getWidth() + "  charHeight:" + bitmap.getHeight() + "  byte:" + bitmap.getByteCount());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                getPresenter().requestChangeHead(UserConfig.getInstance().getId(),baos.toByteArray());
                bitmap.recycle();
                break;
            default:
                break;
        }
    }
}
