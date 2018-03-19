package com.example.tufei.facedemo.utils;

import com.example.tufei.facedemo.bean.FaceHttpResult;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author wzh
 * @date 2017/12/29
 */
public class RxUtil {

    /**
     * 实现io->main切换
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T,T> io_Main() {
        return upStream->upStream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

//    public static <T> ObservableTransformer<FaceHttpResult, T> io_main_handleFaceHttpResult() {
//        return httpResultObservable ->
//                httpResultObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
//                        flatMap(httpResult->{
//                            if (httpResult.getError_code() != 0 || httpResult.getError_msg() != null) {
//                                return Observable.error(new Exception(httpResult.getError_msg()));
//                            } else {
//                                return Observable.just(httpResult.getResult());
//                            }
//                        });
//    }


    /**
     * 使用的时候，这么写：Observable<FaceHttpResult>
     * @return
     */
    public static ObservableTransformer<FaceHttpResult, FaceHttpResult> io_main_handleFaceNoData() {
        return httpResultObservable ->
                httpResultObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                        flatMap(httpResult -> {
                            if (httpResult.getError_code() != 0 || httpResult.getError_msg() != null) {
                                return Observable.error(new Exception(httpResult.getError_msg()));
                            } else {
                                return Observable.just(httpResult);
                            }
                        });
    }
}
