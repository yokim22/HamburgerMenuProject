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

/**
 * A simple {@link Fragment} subclass.
 */
public class ElementFragment extends ListFragment {

    int mSelectedElement;

    static interface Listener {
        void itemClicked(long id);
    };

    private Listener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ElementFragment", "onCreate()");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] elements = new String[Element.sample_data.length];
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
        super.onAttach(context);
        this.listener = (Listener)context;
    }

    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        mSelectedElement = (int)id;
        getListView().setSelector(android.R.color.holo_blue_dark);
        if (listener != null) {
            Log.d("click item" ,": " + id);
            listener.itemClicked(id);
        }

        createElementDetailView(mSelectedElement);
    }

    public void createElementDetailView(int position) {
        Log.d("ElementFragment" ,"createElementDetailView");

        // TO DO: landcape mode
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("ElementFragment: " , "landscape mode");

            // TO DO:  remove tag1
            ElementDetailFragment details = (ElementDetailFragment) getFragmentManager()
                    .findFragmentById(R.id.detail_fragment);

            if (details == null) {
                //            if (details == null || getArguments().getInt("currelement", 0); != position) {
                details = ElementDetailFragment.newInstance(position);

                // transition to ElementDetailFragment is working!
                FragmentTransaction ft = getFragmentManager()
                        .beginTransaction();
                ft.replace(R.id.detail_fragment, details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
                //////////////////////////////////////////////////////////////////////////

            }

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
        Log.d("ElementFragmenet", "onActivityCreated");

        if (savedInstanceState != null) {
            // reset the last selected position
            mSelectedElement = savedInstanceState.getInt("selectedElement", 0);
            Log.d("elementfragment", "last pos: " + mSelectedElement);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("ElementFragmenet", "onSaveInstanceState");
        outState.putInt("selectedElement", mSelectedElement);
    }
}
