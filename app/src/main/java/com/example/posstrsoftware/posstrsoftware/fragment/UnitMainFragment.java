package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.AddUnitActivity;
import com.example.posstrsoftware.posstrsoftware.activity.FixUnitActivity;
import com.example.posstrsoftware.posstrsoftware.activity.MainActivity;
import com.example.posstrsoftware.posstrsoftware.adapter.UnitAdapter;
import com.example.posstrsoftware.posstrsoftware.dao.UnitDAO;
import com.example.posstrsoftware.posstrsoftware.model.UnitList;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.posstrsoftware.posstrsoftware.R.id.btn_back;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class UnitMainFragment extends Fragment implements View.OnClickListener {
    ListView listView_Unit;
    ImageButton btn_add_unit;
    Toolbar my_toolbar;
    ImageButton btn_back;
    SearchView searchViewUnit;

    public UnitMainFragment() {
        super();
    }

    public static UnitMainFragment newInstance() {
        UnitMainFragment fragment = new UnitMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_unit, container, false);
        initInstances(rootView);


        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        listView_Unit = (ListView) rootView.findViewById(R.id.listView_Unit);

        btn_add_unit = (ImageButton) rootView.findViewById(R.id.btn_add_unit);
        btn_back = (ImageButton) rootView.findViewById(R.id.btn_back);
        searchViewUnit = (SearchView)rootView.findViewById(R.id.searchViewUnit);
        searchViewUnit.setQueryHint("Search..");
        btn_back.setOnClickListener(this);
        btn_add_unit.setOnClickListener(this);


    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        UnitDAO unitDAO = new UnitDAO(getActivity());
        unitDAO.open();
        ArrayList<UnitList> unitLists = unitDAO.getAllUnitList();
        final UnitAdapter adapter = new UnitAdapter(getActivity(), unitLists);
        listView_Unit.setAdapter(adapter);
        unitDAO.close();

     listView_Unit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             Intent editIntent = new Intent(getActivity(), FixUnitActivity.class);
             editIntent.putExtra("editUnit", (Serializable) adapter.getItem(position));
             startActivity(editIntent);
         }
     });



    }

    @Override
    public void onClick(View v) {
        if (btn_add_unit == v) {

                Intent intent = new Intent(getActivity(), AddUnitActivity.class);
                startActivity(intent);

        }else if (btn_back == v){

        getActivity().finish();

        }

    }
}
