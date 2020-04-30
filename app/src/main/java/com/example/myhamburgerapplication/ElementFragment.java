package com.example.myhamburgerapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ElementFragment extends ListFragment {

    int mSelectedElement;
    String[] elements;
    boolean menuclick;


    static interface Listener {
        void itemClicked(long id);
    };

    private Listener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate", "ElementFragment");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("onCreateView", "ElementFragment");

        elements = new String[Element.sample_data.length];
        for(int i = 0; i < elements.length; i++) {
            elements[i] = Element.sample_data[i].getElement();
        }
        /////////////////////////////////

        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(),
                android.R.layout.simple_list_item_1,
                elements);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        Log.d("onAttach", "ElementFragment");
        super.onAttach(context);
        this.listener = (Listener)context;
    }

    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        Log.d("onListItemClick", "ElementFragment");
        mSelectedElement = (int)id;
        getListView().setSelector(android.R.color.holo_blue_dark);
        getListView().setItemChecked(mSelectedElement, true);
        if (listener != null) {
            Log.d("click item" ,": " + id);
            listener.itemClicked(id);
        }

        if (!menuclick) {
            Log.d("ElementFragment", "not menu click");
            createElementDetailView(mSelectedElement);
        }
    }

    public void createElementDetailView(int position) {
        Log.d("createElementDetailView", "ElementFragment");

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) { // landscape mode
            Log.d("ElementFragment: " , "landscape mode");
            getListView().setItemChecked(position, true);

            ElementDetailFragment details = (ElementDetailFragment) getFragmentManager()
                    .findFragmentById(R.id.detail_fragment);

            // update detail list data
            details.updateDetailData(position);

            // transition to ElementDetailFragment is working!
            FragmentTransaction ft = getFragmentManager()
                    .beginTransaction();
            ft.replace(R.id.detail_fragment, details);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

        } else { // portrait mode
            Intent intent = new Intent(getActivity(), ElementDetailActivity.class);
            intent.putExtra("currelement", position);
            startActivity(intent);
        }

    }

    // called when the fragment's activity has been created/ rotation
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("onActivityCreated", "ElementFragment");

        // rotation restore position
        if (savedInstanceState != null) {
            // reset the last selected position
            mSelectedElement = savedInstanceState.getInt("selectedElement", 0);
            Log.d("elementfragment", "last pos: " + mSelectedElement);
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            createElementDetailView(mSelectedElement);
        } else {
            getListView().setItemChecked(mSelectedElement, true);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("onSaveInstanceState", "ElementFragment");
        outState.putInt("selectedElement", mSelectedElement);
    }

    // update elements item
    public void updateItemData(int position) {
        Log.d("updateItemData", "ElementFragment");
        menuclick = true;
        elements = Item.itemToArray(Item.items[position]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, elements);
        setListAdapter(adapter);
    }

    String TAG = "test";

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, " onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, " onDetach()");
    }
}
