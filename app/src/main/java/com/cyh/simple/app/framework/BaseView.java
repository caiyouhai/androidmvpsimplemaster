package com.cyh.simple.app.framework;

/**
 * @author youhai.cai
 *         create by 2018/1/9 11:36.
 */

public interface BaseView<M> {

    void onResponseSucceed(M model);

    void onResponseFaild(int code,String msg);

    void onStartRequest();

    void onFinishRequest();
}
