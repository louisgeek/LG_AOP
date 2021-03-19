package com.louisgeek.lg_aop;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding3.view.RxView;
import com.louisgeek.aop.CheckNetwork;
import com.louisgeek.aop.OnThrottleClickListener;
import com.louisgeek.aop.ThrottleClick;
import com.louisgeek.aop.ThrottleClickProxy;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
import kotlin.Unit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView id_tv_1 = findViewById(R.id.id_tv_1);
        TextView id_tv_2 = findViewById(R.id.id_tv_2);
        TextView id_tv_3 = findViewById(R.id.id_tv_3);
        TextView id_tv_4 = findViewById(R.id.id_tv_4);
        TextView id_tv_5 = findViewById(R.id.id_tv_5);
        id_tv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: ");
                checkNetworkConn();
            }
        });
        id_tv_2.setOnClickListener(new OnThrottleClickListener() {
            @Override
            protected void onThrottleClick(View v) {
                Log.e(TAG, "onClick: OnThrottleClickListener ");
            }
        });
        id_tv_3.setOnClickListener(new ThrottleClickProxy(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: ThrottleClickProxy ");
            }
        }));
        RxView.clicks(id_tv_4)
                .throttleFirst(3000, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Unit>() {
                    @Override
                    public void accept(Unit unit) throws Exception {
                        Log.e(TAG, "onClick: throttleFirst ");
                    }
                });
        /*id_tv_4.setOnClickListener(new View.OnClickListener() {
            @ThrottleClick
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: @ThrottleClick ");
            }
        });*/
        id_tv_5.setOnClickListener(new View.OnClickListener() {
            @ThrottleClick
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: @ThrottleClick ");
            }
        });
    }

    @CheckNetwork(tip = "网络开了小差")
    public void checkNetworkConn() {
        Toast.makeText(this, "有网络继续执行逻辑", Toast.LENGTH_SHORT).show();
    }
}