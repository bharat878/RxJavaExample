package com.example.ObservablesWithJust;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ObservablesWithJust.model.model;
import com.example.rxjavaexample.R;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class JustActivity extends AppCompatActivity {

    private Button clickBtn;
    private Disposable disposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just);

        clickBtn = findViewById(R.id.clickBtn);

        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observable<String> booknameObservable = getBookNameObservable();

                Observer<String> booknameobserver = getBookNameObserver();

                booknameObservable.observeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(booknameobserver);

                Single<model> bookSingle = getSingleBook();

                SingleObserver<model> bookSingleObserver = getSingleBookObserver();

                bookSingle.observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(bookSingleObserver);

                Maybe<String> maybeName = getMaybeName();

                MaybeObserver<String> maybeObserver = getMaybeObserver();

                maybeName.observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(maybeObserver);
            }
        });
    }


    private Observable<String> getBookNameObservable() {
        return Observable.just("C","C++", "Java", "Android", "Nodejs");
    }

    private Observer<String> getBookNameObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;

                Log.d("justCheck", "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d("justCheck", "onNext: "+s);

            }

            @Override
            public void onError(Throwable e) {
                Log.d("justCheck", "onError "+e.getMessage());

            }

            @Override
            public void onComplete() {
                Log.d("justCheck", "onComplete");

            }
        };
    }

    private Single<model> getSingleBook() {
        return Single.just(new model("java"));
    }


    private SingleObserver<model> getSingleBookObserver() {
        return new SingleObserver<model>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("bookName", "onSubscribe");

            }

            @Override
            public void onSuccess(model model) {
                Log.d("bookName", "onSuccess: "+model.getBookName());

            }

            @Override
            public void onError(Throwable e) {
                Log.d("bookName","onError"+e.getMessage());

            }
        };
    }


    private Maybe<String> getMaybeName() {
        return Maybe.just("Bharat");
    }

    private MaybeObserver<String> getMaybeObserver() {
        return new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("maybe","onSubscription");
            }

            @Override
            public void onSuccess(String s) {
                Log.d("maybe","onSuccess: "+s);

            }

            @Override
            public void onError(Throwable e) {
                Log.d("maybe","onError: "+e.getMessage());

            }

            @Override
            public void onComplete() {
                Log.d("maybe","onComplete");

            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        disposable.dispose();
        Log.d("justCheck", "disposed");


    }
}
