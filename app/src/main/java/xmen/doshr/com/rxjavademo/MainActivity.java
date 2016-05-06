package xmen.doshr.com.rxjavademo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 观察者和被观察者
 * 被观察者有观察者的引用
 * 被观察者调用了观察者的回调方法
 */
public class MainActivity extends Activity
{

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initView();

        // init();

        initViews();
    }


    //初始化
    private void initViews()
    {
        Observable observable = Observable.create(new Observable.OnSubscribe<String>()
        {
            @Override
            public void call(Subscriber<? super String> subscriber)
            {
                subscriber.onNext("hello");
            }
        }).map(new Func1<String, String>()
        {
            @Override
            public String call(String o)
            {
                return o + " world ";
            }
        });


        Subscriber<String> subscriber = new Subscriber<String>()
        {
            @Override
            public void onCompleted()
            {

            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onNext(String o)
            {
                Log.i(TAG, " s = " + o);
            }
        };

        observable.subscribe(subscriber);
    }

    //初始化
    private void initView()
    {
        Subscriber<String> subscriber = new Subscriber<String>()
        {
            @Override
            public void onCompleted()
            {
                Log.i(TAG, " onCompleted = " + " 完成 ");
            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onNext(String s)
            {
                Log.i(TAG, " onNext s = " + s);
            }
        };

        Observable observable = Observable.create(new Observable.OnSubscribe<String>()
        {
            @Override
            public void call(Subscriber<? super String> subscriber)
            {
                subscriber.onNext("hellos");
                subscriber.onNext("worlds");
                subscriber.onCompleted();
            }
        });
        observable.subscribeOn(Schedulers.io());
        observable.observeOn(AndroidSchedulers.mainThread());

        //Observable observable = Observable.just("hello", "world", "!");

        // String[] array = {"hello", "world", "!!"};
        //Observable observable = Observable.from(array);


       /* Action1<String> action = new Action1<String>()
        {
            @Override
            public void call(String string)
            {
                Log.i(TAG , " call = " + string);
            }
        };*/

        //observable.subscribe(subscriber);

        observable.subscribe(subscriber);
    }


    private void init()
    {
        Observable observable = Observable.just("1111");
        observable.map(new Func1<String, String>()
        {
            @Override
            public String call(String o)
            {
                return "好好学习天天向上";
            }
        }).subscribe(new Action1<String>()
        {
            @Override
            public void call(String string)
            {
                Log.i(TAG, " string = " + string);
            }
        });

    }
}
