package com.example.rxjavaexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private TextView textView, textView2;
    private EditText edtxt, edtxt2;
    private Button btn;
    private Observable<String> observable;
    private Observer<String> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        edtxt = findViewById(R.id.editText);
        edtxt2 = findViewById(R.id.editText2);
        btn = findViewById(R.id.button);

        createObservable();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                observable.subscribe(observer);
            }
        });
    }

    private void createObservable() {

        observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(edtxt.getText().toString());
                emitter.onNext(edtxt2.getText().toString());
                emitter.onComplete();
            }
        });

        observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

                Log.d("onCheck","onSubscribe");
            }

            @Override
            public void onNext(String s) {
                textView.setText(s);
                textView2.setText(s);
                Log.d("onCheck","onNext");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("onCheck","onError");
            }

            @Override
            public void onComplete() {
                Log.d("onCheck","onComplete");
            }
        };
    }
}
