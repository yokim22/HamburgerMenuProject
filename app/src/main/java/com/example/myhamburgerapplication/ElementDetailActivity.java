package com.example.myhamburgerapplication;

import android.content.res.Configuration;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

public class ElementDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d("onCreate", "ElementDetailActivity");

        // finish the current activity when changing to landscape on detail view
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("onCreate-landscape", "ElementDetailActivity");
            finish();
            return;
        }

        if (savedInstanceState == null) {
            ElementDetailFragment detail_frag = new ElementDetailFragment();
            detail_frag.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, detail_frag).commit();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("onPause", "ElementDetailActivity");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("onStop", "ElementDetailActivity");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy", "ElementDetailActivity");
    }

}
