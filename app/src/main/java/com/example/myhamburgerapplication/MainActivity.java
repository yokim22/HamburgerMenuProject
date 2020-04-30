package com.example.myhamburgerapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ElementFragment.Listener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.hamburger_menu, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        return !isLandscape;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // TO DO: save id -> restore

        switch (id) {
            case R.id.hamburger:
                callDetailActivity(0);
                break;
            case R.id.beverage:
                callDetailActivity(1);
                break;
            case R.id.breakfast:
                callDetailActivity(2);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void callDetailActivity(int id) {
        Intent intent = new Intent(this, ElementDetailActivity.class);
        intent.putExtra("currelement", id);
        startActivity(intent);
    }

    @Override
    public void itemClicked(long id) {

    }

}
