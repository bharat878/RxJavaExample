package com.example.ObservablesWithJust;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.rxjavaexample.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class JustActivity extends AppCompatActivity {

    private Button clickBtn;

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


}
