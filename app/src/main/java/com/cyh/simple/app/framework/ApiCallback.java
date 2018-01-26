package com.cyh.simple.app.framework;

import android.util.Log;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 数据接收源，接口回调。
 * @author youhai.cai
 *         create by 2018/1/9 11:15.
 */

public abstract class ApiCallback<M> extends Subscriber<M> {

    public abstract void onSuccess(M model);

    public abstract void onFailure(int code,String msg);

    public abstract void onFinish();

    @Override
    public void onCompleted() {
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            String msg = httpException.getMessage();
            Log.d("ApiCallback","code=" + code);
            if (code == 504) {
                msg = "网络不给力";
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试";
            }
            onFailure(code,msg);
        } else {
            onFailure(-10000,e.getMessage());
        }
        onFinish();
    }

    @Override
    public void onNext(M m) {

        // TODO: 2018/1/9 这里可以进一步解析数据

        onSuccess(m);
    }
}
