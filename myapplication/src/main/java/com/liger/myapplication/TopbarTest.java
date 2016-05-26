package com.liger.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Liger on 2016/5/25.
 */
public class TopbarTest extends Activity {

    private MyTopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topbar);

        topBar = (MyTopBar) findViewById(R.id.topbar);
        topBar.setOnTopbarClickListener(new MyTopBar.TopbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(TopbarTest.this,"left",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(TopbarTest.this,"right",Toast.LENGTH_SHORT).show();
            }
        });

        //控制topbar组件上的状态
        topBar.setButtonVisable(0,true);
        topBar.setButtonVisable(1,true);
    }
}
