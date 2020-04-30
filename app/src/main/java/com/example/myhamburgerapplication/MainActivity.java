package com.example.myhamburgerapplication;

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

        switch (id) {
            case R.id.hamburger:
                setItems(0);
                break;
            case R.id.beverage:
                setItems(1);
                break;
            case R.id.breakfast:
                setItems(2);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setItems(int id) {
        ElementFragment element_frag = (ElementFragment)
                getSupportFragmentManager().findFragmentById(R.id.element_fragment);

        // update detail list data
        element_frag.updateItemData(id);
    }

    @Override
    public void itemClicked(long id) {

    }

}
