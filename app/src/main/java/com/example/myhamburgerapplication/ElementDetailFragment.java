package com.example.myhamburgerapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    static interface Listener {
        void detailItemClicked(long id);
    };

    private ElementDetailFragment.Listener listener;

    private static int selectedEID; // selected element position
    private static int selectedIID; // selected item position
    private static String[] items;
    private static TextView title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate", "ElementDetailFragment");
        //setRetainInstance(true);

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            selectedEID = extras.getInt("currelement");
            Log.d("ElementDetailFragment!!", "elementid: " + selectedEID);
        } else {
            Log.d("no extra", "ElementDetailFragment");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("onCreateView", "ElementDetailFragment");


        View frag_view = inflater.inflate(R.layout.fragment_element_detail, container, false);
        title = (TextView) frag_view.findViewById(R.id.titleText);

        Log.d("ElementDetailFragment", "/selectedEID: " + selectedEID);
        Log.d("ElementDetailFragment", "/selectedIID: " + selectedIID);

        // landscape already has data and view
        if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("portrait", "ElementDetailFragment");
            updateDetailData(selectedEID);
        }

        // set title to selected element
        title.setText(Element.sample_data[selectedEID].getElement());

        return frag_view;
//        return inflater.inflate(R.layout.fragment_element_detail, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("onAttach", "ElementDetailFragment");
    }


    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        Log.d("onListItemClick", "ElementDetailFragment");
        selectedIID = (int)id;
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setItemChecked(selectedIID, true);
        getListView().setSelector(android.R.color.holo_orange_dark);
        Log.d("ElementDetailFragment", "/item id: " + selectedIID);
        if (listener != null) {
            Log.d("click item" ,": " + id);
            listener.detailItemClicked(id);
        }

        // need to update shared pref
        SharedPreferences prefs = getActivity().getSharedPreferences("prefselection", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("selectedItem", selectedIID);
        editor.commit();

    }

    // called when the fragment's activity has been created/ rotation
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("onActivityCreated", "ElementDetailFragment");

        SharedPreferences prefs = getActivity().getSharedPreferences("prefselection", getActivity().MODE_PRIVATE);
        selectedIID = prefs.getInt("selectedItem", -1);
        selectedEID = prefs.getInt("selectedEle", -1);

        Log.d("selectedIID", "ElementDetailFragment " + selectedIID);
        Log.d("selectedEID", "ElementDetailFragment " + selectedEID);

        getListView().setItemChecked(selectedIID, true);
    }


    // called from ElementFragment when element is selected
    public void updateDetailData(int position) {
        Log.d("updateDetailData", "ElementDetailFragment");
        selectedEID = position;

        // set title to selected element
        title.setText(Element.sample_data[selectedEID].getElement());

        items = Item.itemToArray(Item.items[position]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);
    }

    String TAG  = "detail_test";

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
