package com.example.myhamburgerapplication;

import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

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

//        if (savedInstanceState == null) {
//            Log.d("save null", "ElementDetailActivity");
//
//            ElementDetailFragment detail_frag = (ElementDetailFragment)
//                    getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
//
//        } else {
//            Log.d("save not null", "ElementDetailActivity");
//        }
    }

}
