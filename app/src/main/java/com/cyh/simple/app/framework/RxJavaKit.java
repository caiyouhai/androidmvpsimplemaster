package com.cyh.simple.app.framework;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 *
 * RXJava工具类
 * @author youhai.cai
 *         create by 2018/1/9 11:25.
 */
public class RxJavaKit {



    public static void addSubcription(@NonNull CompositeSubscription subscriptions,Subscriber subscriber){
        addSubcription(subscriptions,null,subscriber);
    }

    /**
     * 订阅，
     * rxjava发射数据
     */
    public static void addSubcription(@NonNull CompositeSubscription subscriptions, Observable<?> observable, Subscriber subscriber){
        if (observable!=null){
            subscriptions.add(observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber));
        }else {
            subscriptions.add(subscriber);
        }
    }
    /**
     * 取消订阅，以避免内存泄露
     */
    public static void unsubscribe(CompositeSubscription subscriptions){
        if (subscriptions!=null && subscriptions.hasSubscriptions()){
            subscriptions.unsubscribe();
        }
    }
}
