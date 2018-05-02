package com.example.rogge.bus.test;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.rogge.R;
import com.example.rogge.aop.KSingleClick;
import com.rogge.base.BaseActivity;
import com.rogge.base.BaseSubscriber;
import com.rogge.base_bean.BaseRespModel;
import com.rogge.baserx.RxSchedulers;
import com.rogge.widget.single_check.RelevanceSingleCheck;
import com.rogge.widget.single_check.SingleCheckView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.FuncN;
import rx.schedulers.Schedulers;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2018/5/1 0001.
 * @since 1.0.0
 */

public class TestActivity extends BaseActivity {

    @BindView(R.id.test_rv)
    RecyclerView recyclerView;
    @BindView(R.id.test_we)
    SingleCheckView testWe;
    @BindView(R.id.test_your)
    SingleCheckView testYour;
    @BindView(R.id.test_they)
    SingleCheckView testThey;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {
        testCase();
    }

    @KSingleClick
    @OnClick(R.id.test_fab)
    public void onClick(View view) {
        recyclerView.smoothScrollToPosition(0);
    }

    private void testCase() {
        mToolBarManager.setTitle("测试用例");
        //利用观察者模式实现的一个单选例子
        RelevanceSingleCheck lRelevanceSingleCheck = new RelevanceSingleCheck();
        lRelevanceSingleCheck.relevanceSingleCheck(testWe, testYour, testThey);
        lRelevanceSingleCheck.setSingleCheckClickCallBack(singleCheckListener -> {
        });

        //RxJava操作符测试
        List<Integer> lIntegers = new ArrayList<>();
        Random lRandom = new Random();
        for (int lI = 0; lI < 50; lI++) {
            lIntegers.add(lRandom.nextInt(100));
        }

        mRxManager.add(Observable.from(lIntegers)
                .distinct()
                .toSortedList(Integer::compareTo)
                .subscribeOn(Schedulers.computation())
                .compose(RxSchedulers.io_main())
                .subscribe(integers -> Log.e("haha", integers.toString()), Throwable::printStackTrace));

        mRxManager.add(Observable.from(lIntegers)
                .distinct()
                .compose(RxSchedulers.io_main())
                .subscribe(integer -> Log.e("haha", integer.toString()), Throwable::printStackTrace));

        mRxManager.add(Observable.interval(1000, 1000, TimeUnit.MILLISECONDS)
                .compose(RxSchedulers.io_main())
                .subscribe(aLong -> Log.e("haha", aLong.toString()), Throwable::printStackTrace));

        mRxManager.add(Observable.timer(2, TimeUnit.SECONDS)
                .compose(RxSchedulers.io_main())
                .subscribe(l -> Log.e("haha", "定时器" + l)));

        mRxManager.add(Observable.just(2, 3, 4, 8)
                .filter(integer -> integer == 8)
                .flatMap(ii -> Observable.just(false))
                .map(bool -> {
                    BaseRespModel<Boolean> ss = new BaseRespModel<>();
                    ss.setError(bool);
                    return ss;
                })
                .doOnUnsubscribe(() -> Log.e("haha", "doOnUnsubscribe"))
//                .doOnNext(booleanBaseRespModel -> booleanBaseRespModel.setData(false))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseSubscriber<Boolean>(TestActivity.this) {
                    @Override
                    public void onSucCall(Boolean response) {
                        Log.e("haha", response.toString());
                    }
                }));

        List<Observable<String>> lObservables = new ArrayList<>();
        for (int lS : lIntegers) {
            lObservables.add(Observable.just(lS + ""));
        }
        mRxManager.add(Observable.zip(lObservables, (FuncN<Object>) args -> args)
                .subscribe(
                        o -> {
                            Object[] aaa = (Object[]) o;
                            for (Object lS : aaa) {
                                Log.e("aaa", (String) lS);
                            }
                        },
                        throwable -> Log.e("aaa", throwable.toString())
                ));

        mRxManager.add(Observable.merge(lObservables.get(0), lObservables.get(1).delay(100, TimeUnit.MILLISECONDS), lObservables.get(2), lObservables.get(3), lObservables.get(4))
                .subscribe(s -> Log.e("merge", s), throwable -> Log.e("merge", "Throwable" + throwable.getMessage())));

        mRxManager.add(Observable.concat(lObservables.get(0), lObservables.get(1).delay(100, TimeUnit.MILLISECONDS), lObservables.get(2), lObservables.get(3), lObservables.get(4))
                .takeFirst(s -> s != null)
                .subscribe(s -> Log.e("concat", s), throwable -> Log.e("concat", "Throwable" + throwable.getMessage())));

    }
}
