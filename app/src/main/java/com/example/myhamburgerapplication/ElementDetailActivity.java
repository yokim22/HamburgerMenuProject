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
        Log.d("ElementDetailActivity", "onCreate()");

        if (savedInstanceState == null) {
            setContentView(R.layout.activity_detail);
            ElementDetailFragment detail_frag = (ElementDetailFragment)
                    getSupportFragmentManager().findFragmentById(R.id.detail_fragment);

            int elementID = (int) getIntent().getExtras().get("currelement");
            Log.d("ElementDetailActivity!!", "elementid: " + elementID);
            detail_frag.setElementID(elementID);
//            detail_frag.setItemID();
        }
    }

//    @Override
//    public void detailItemClicked(long id) {
//        Log.d("test", "test");
//
//    }


}
