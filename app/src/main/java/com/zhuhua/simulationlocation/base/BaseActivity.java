package com.zhuhua.simulationlocation.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.zhuhua.simulationlocation.R;
import com.zhuhua.simulationlocation.dialog.CustomProgressDialog;


/**
 * @author zhuhua
 * @ClassName: BaseActivity
 * @Description:Acitivity基类
 * @date 2018年2月5日 上午11:54:43
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected View mView;
    protected CustomProgressDialog progressDialog;
    protected Toolbar toolbar;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Object object = getContentViewId();
        if (object instanceof Integer) {
            mView = LayoutInflater.from(this).inflate((Integer) object, null);
        } else if (object instanceof View) {
            mView = (View) object;
        }
        setContentView(mView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mContext = this;
        IniView();

        progressDialog = new CustomProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);

        IniLister();
        IniData();

    }

    /**
     * @Title: getContentViewId
     * @Description: 布局文件Id
     */
    protected abstract Object getContentViewId();

    /**
     * @Title: IniView
     * @Description: 初始化View
     */
    protected abstract void IniView();

    /**
     * @Title: IniLister
     * @Description: 初始化接口
     */
    protected abstract void IniLister();

    /**
     * @Title: IniData
     * @Description: 初始化数据
     */
    protected abstract void IniData();

    /**
     * thisFinish 当前关闭
     */
    protected abstract void thisFinish();

    @Override
    public void onBackPressed() {
        thisFinish();
    }

    /**
     * showProgressDialog 显示等待框
     *
     * @param text 显示文字
     */
    public void showProgressDialog(String text) {
        if (progressDialog != null) {
            progressDialog.show();
            progressDialog.setMessage(text);
        }
    }

    /**
     * cancelProgressDialog 取消等待框
     */
    public void cancelProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
