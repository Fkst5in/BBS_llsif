package com.example.bbs_llsif;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent LaLaLa = getIntent();
        Toast.makeText(this, LaLaLa.getIntExtra("pid",0)+"\n"+LaLaLa.getIntExtra("user_id",0), Toast.LENGTH_LONG).show();
    }

}
