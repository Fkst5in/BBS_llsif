package com.example.bbs_llsif;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //储存session数据到本地
                SharedPreferences sessionStorage = getSharedPreferences("main", 0);
                String expire = sessionStorage.getString("expire", "0");
                Integer expire_ = Integer.parseInt(expire);
                Integer timestamp = (int)(System.currentTimeMillis() / 1000);
                if(expire_ <= timestamp){
                    Toast.makeText(getApplicationContext(), "Session已过期！", Toast.LENGTH_SHORT).show();
                    Intent mainIntent = new Intent(StartScreen.this, Regist.class);
                    startActivity(mainIntent);
                    finish();
                }else{
                    String uid = sessionStorage.getString("user_id", "0");
                    String name = sessionStorage.getString("name", "");
                    String session = sessionStorage.getString("session", "");
                    //跳转到MainActivity，并发送用户数据（主要是 User_id 和 Session ）
                    Intent main = new Intent(StartScreen.this, MainActivity.class);
                    main.putExtra("user_id", uid);
                    main.putExtra("session", session);
                    main.putExtra("skip", true);
                    startActivity(main);

                }



            }
        },2000);
        setContentView(R.layout.activity_start_screen);
    }
}
