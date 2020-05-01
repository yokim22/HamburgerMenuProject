package com.example.myhamburgerapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ElementFragment.Listener {

    private static int selected_menu;
    private static boolean hasPrevVal;
    private static MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "MainActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // not destroyed even restart app - need to check!!!!!
        SharedPreferences prefs = getSharedPreferences("prefselection", this.MODE_PRIVATE);
        selected_menu = prefs.getInt("selectedEle", -1);
        if (selected_menu != -1) {
            hasPrevVal = true;
            Log.d("restore sharedpref", "MainActivity: " + selected_menu);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("onCreateOptionsMenu", "MainActivity");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.hamburger_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // called when menu button is clicked
        Log.d("onPrepareOptionsMenu", "MainActivity");
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        // restore previous selection
        if (hasPrevVal) {
            Log.d("hasPrevVal! options", "MainActivity: " + selected_menu);
            if (selected_menu == 0) {
                menuItem = (MenuItem) menu.findItem(R.id.hamburger);
                menuItem.setChecked(true);
            } else if (selected_menu == 1) {
                menuItem = (MenuItem) menu.findItem(R.id.beverage);
                menuItem.setChecked(true);
            } else if (selected_menu == 2) {
                menuItem = (MenuItem) menu.findItem(R.id.breakfast);
                menuItem.setChecked(true);
            }
        }

        return !isLandscape;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.hamburger:
                item.setChecked(true);
                callDetailActivity(0);
                break;
            case R.id.beverage:
                item.setChecked(true);
                callDetailActivity(1);
                break;
            case R.id.breakfast:
                item.setChecked(true);
                callDetailActivity(2);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void callDetailActivity(int id) {
        selected_menu = id;

        // save selected element to sharedpreference
        SharedPreferences prefs = getSharedPreferences("prefselection", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("selectedEle", selected_menu);
        editor.putInt("selectedItem", -1);
        editor.commit();


        Intent intent = new Intent(this, ElementDetailActivity.class);
        intent.putExtra("currelement", selected_menu);
        startActivity(intent);
    }

    @Override
    public void itemClicked(long id) {

    }

}
