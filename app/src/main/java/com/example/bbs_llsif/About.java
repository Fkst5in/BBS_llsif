package com.example.bbs_llsif;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;

public class About extends AppCompatActivity {

    Button btn_back;
    TextView mer_1;
    TextView mer_2;
    TextView mer_3;
    TextView mer_4;
    TextView git;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        init();

        doit();
    }

    private void doit() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String html_mer_1 = "<a href='https://github.com/lijun0326'>双草酸酯</a>";
        mer_1.setMovementMethod(LinkMovementMethod.getInstance());
        mer_1.setText(Html.fromHtml(html_mer_1));

        String html_mer_2 = "<a href='https://github.com/ad-j'>ad-j</a>";
        mer_2.setMovementMethod(LinkMovementMethod.getInstance());
        mer_2.setText(Html.fromHtml(html_mer_2));

        String html_mer_3 = "<a href='https://github.com/IchinoseHimeki'>一之濑姬月</a>";
        mer_3.setMovementMethod(LinkMovementMethod.getInstance());
        mer_3.setText(Html.fromHtml(html_mer_3));

        String html_mer_4 = "<a href='https://user.qzone.qq.com/1369880674'>爱芊</a>";
        mer_4.setMovementMethod(LinkMovementMethod.getInstance());
        mer_4.setText(Html.fromHtml(html_mer_4));

        String html_git = "GitHub ： "+"<a href='https://github.com/ad-j/BBS_llsif'>BBS_LL</a>";
        git.setMovementMethod(LinkMovementMethod.getInstance());
        git.setText(Html.fromHtml(html_git));
    }

    private void init() {

        btn_back = (Button) findViewById(R.id.about_btn_back);

        mer_1 = (TextView) findViewById(R.id.about_tv_d);
        mer_2 = (TextView) findViewById(R.id.about_tv_e);
        mer_3 = (TextView) findViewById(R.id.about_tv_f);
        mer_4 = (TextView) findViewById(R.id.about_tv_g);

        git = (TextView) findViewById(R.id.about_tv_h);
    }
}
