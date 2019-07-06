package com.whaletail.capecanaveral.rx;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.whaletail.capecanaveral.R;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

/**
 * Created by amitshekhar on 17/12/16.
 */

public class ReplaySubjectExampleActivity extends AppCompatActivity {

    private static final String TAG = ReplaySubjectExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSomeWork3();
            }
        });
    }

    /* ReplaySubject emits to any observer all of the items that were emitted
     * by the source Observable, regardless of when the observer subscribes.
     */
    private void doSomeWork() {

        ReplaySubject<Integer> source = ReplaySubject.create();

        source.subscribe(getFirstObserver()); // it will get 1, 2, 3, 4

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
        source.onNext(4);
        source.onComplete();

        /*
         * it will emit 1, 2, 3, 4 for second observer also as we have used replay
         */
        source.subscribe(getFirstObserver());

    }
    private void doSomeWork1() {

        PublishSubject<Integer> source = PublishSubject.create();

// It will get 1, 2, 3, 4 and onComplete
        source.subscribe(getFirstObserver());

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

// It will get 4 and onComplete for second observer also.
        source.subscribe(getSecondObserver());

        source.onNext(4);
        source.onComplete();
    }

    private void doSomeWork2() {
        BehaviorSubject<Integer> source = BehaviorSubject.create();
// It will get 1, 2, 3, 4 and onComplete
        source.subscribe(getFirstObserver());
        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
// It will get 3(last emitted)and 4(subsequent item) and onComplete
        source.subscribe(getSecondObserver());
        source.onNext(4);
        source.onComplete();
    }

    private void doSomeWork3() {
        AsyncSubject<Integer> source = AsyncSubject.create();
// It will get only 4 and onComplete
        source.subscribe(getFirstObserver());
        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
// It will also get only get 4 and onComplete
        source.subscribe(getSecondObserver());
        source.onNext(4);
        source.onComplete();
    }


    private Observer<Integer> getFirstObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " First onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                textView.append(" First onNext : value : " + value);
                textView.append("\n");

                Log.d(TAG, " First onNext value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" First onError : " + e.getMessage());
                textView.append("\n");
                Log.d(TAG, " First onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" First onComplete");
                textView.append("\n");
                Log.d(TAG, " First onComplete");
            }
        };
    }

    private Observer<Integer> getSecondObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                textView.append(" Second onSubscribe : isDisposed :" + d.isDisposed());
                Log.d(TAG, " Second onSubscribe : " + d.isDisposed());
                textView.append("\n");
            }

            @Override
            public void onNext(Integer value) {
                textView.append(" Second onNext : value : " + value);
                textView.append("\n");
                Log.d(TAG, " Second onNext value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" Second onError : " + e.getMessage());
                textView.append("\n");
                Log.d(TAG, " Second onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" Second onComplete");
                textView.append("\n");
                Log.d(TAG, " Second onComplete");
            }
        };
    }


}
