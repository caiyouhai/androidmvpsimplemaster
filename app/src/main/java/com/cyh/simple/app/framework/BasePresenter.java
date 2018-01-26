package com.cyh.simple.app.framework;

import com.cyh.simple.app.model.ApiStores;

import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * @author youhai.cai
 *         create by 2018/1/9 11:35.
 */
public class BasePresenter<M> {

    protected BaseView<M>  mvpView;
    protected ApiStores apiStores;

    private CompositeSubscription compositeSubscription;//

    public BasePresenter(BaseView<M>  mvpView) {
        this.mvpView = mvpView;
        attachView(mvpView);
    }

    protected void attachView(BaseView<M>  mvpView){
        this.mvpView=mvpView;
        apiStores= RetrofitKit.retrofit().create(ApiStores.class);
    }

    public void detachView() {
        this.mvpView = null;
        unsubscribe();
    }

    public void addSubcription(Observable<?> observable, Subscriber<?> subscriber){
        if (null==compositeSubscription){
            compositeSubscription = new CompositeSubscription();
        }
        RxJavaKit.addSubcription(compositeSubscription,observable,subscriber);
    }

    /**
     * 取消订阅，以避免内存泄露
     */
    public void unsubscribe(){
        RxJavaKit.unsubscribe(compositeSubscription);
    }
}
