package com.cyh.simple.app.framework;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;


/**
 * @author youhai.cai
 *         create by 2018/1/9 10:45.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P presenter;
    protected Activity activity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter=createPresenter();
        super.onCreate(savedInstanceState);
        activity=this;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);

    }
    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detachView();
        }
    }


    /**
     * Toast 显示
     */
    protected void toastShow(String string) {
        Toast.makeText(activity, string, Toast.LENGTH_SHORT).show();
    }


    public ProgressDialog progressDialog;
    public ProgressDialog showProgressDialog() {
        return showProgressDialog("加载中");
    }
    public ProgressDialog showProgressDialog(CharSequence message) {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }
    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // progressDialog.hide();会导致android.view.WindowLeaked
            progressDialog.dismiss();
        }
    }
}
