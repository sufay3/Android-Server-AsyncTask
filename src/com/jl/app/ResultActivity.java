package com.jl.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tv = (TextView) findViewById(R.id.textview_result);

        Intent carrier = getIntent();
        tv.setText(carrier.getStringExtra("result"));
    }
}
