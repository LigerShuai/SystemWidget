package com.liger.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyTopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.circleview);
//        setTopBar();
//        reverse(123);
    }

    public int reverse(int num) {
        int result = 0;
        String strNums = String.valueOf(num);
        StringBuilder builder = null;
        for (int i=0;i<strNums.length();i++) {
            int a = num % 10;
            result = result + a * 10;
        }
        return result;
    }

    private void setTopBar() {
        topBar = (MyTopBar) findViewById(R.id.topbar);
        topBar.setOnTopbarClickListener(new MyTopBar.TopbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity.this,"left",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(MainActivity.this,"right",Toast.LENGTH_SHORT).show();
            }
        });

        //控制topbar组件上的状态
        topBar.setButtonVisable(0,true);
        topBar.setButtonVisable(1,true);
    }
}
