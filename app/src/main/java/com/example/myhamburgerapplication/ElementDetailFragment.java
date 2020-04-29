package com.example.myhamburgerapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ElementDetailFragment extends ListFragment  {

//    static interface Listener {
//        void detailItemClicked(long id);
//    };
//
//    private ElementDetailFragment.Listener listener;

    private int selectedEID; // selected element position
    private int selectedIID; // selected item position
    String[] items;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ElementDetailFragment", "onCreate()");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_element_detail, container, false);
    }

        @Override
    public void onStart() {
        super.onStart();
        Log.d("ElementDetailFragment", "onStart");
        View view = getView();
        if (view != null) {

            Log.d("ElementDetailFragment", "/selectedEID: " + selectedEID);
            Log.d("ElementDetailFragment", "/selectedIID: " + selectedIID);

            items = Item.itemToArray(Item.items[selectedEID]);

            getListView().setSelector(android.R.color.holo_blue_dark);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, items);
            setListAdapter(adapter);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        this.listener = (ElementDetailFragment.Listener)context;
    }

    public void setElementID(int id) {
        Log.d("setElementID" , ": "+id);
        this.selectedEID = id;
    }

//    public void setItemID(int id) {
//        Log.d("setItemID" , ": "+id);
//        this.selectedIID = id;
//    }

    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        selectedIID = (int)id;
        getListView().setSelector(android.R.color.holo_blue_dark);
        Log.d("ElementDetailFragment", "/item id: " + selectedIID);
//        if (listener != null) {
//            Log.d("click item" ,": " + id);
//            listener.itemClicked(id);
//        }

    }

    // called when the fragment's activity has been created/ rotation
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("ElementDetailFragmenet", "onActivityCreated");

        if (savedInstanceState != null) {
            // reset the last selected position
            selectedIID = savedInstanceState.getInt("selectedItem", 0);
            Log.d("ElementDetailFragmenet", "selectedIID " + selectedIID);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("ElementDetailFragmenet", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putInt("selectedItem", selectedIID);
    }

    public void updateDetailData(int position) {
        Log.d("ElementDetailFragment", "updateDetailData");
        items = Item.itemToArray(Item.items[position]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);
    }

}
