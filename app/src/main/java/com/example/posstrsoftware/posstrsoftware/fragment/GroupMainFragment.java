package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.AddGroupActivity;
import com.example.posstrsoftware.posstrsoftware.activity.FixGroupActivity;
import com.example.posstrsoftware.posstrsoftware.activity.MainActivity;
import com.example.posstrsoftware.posstrsoftware.adapter.GroupAdapter;
import com.example.posstrsoftware.posstrsoftware.dao.GroupDAO;
import com.example.posstrsoftware.posstrsoftware.model.GroupList;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class GroupMainFragment extends Fragment implements View.OnClickListener {
    ImageButton btn_back;
    ImageButton btn_add_group;
    SearchView searchViewGroup;
    ListView listView_group;

    public GroupMainFragment() {
        super();
    }

    public static GroupMainFragment newInstance() {
        GroupMainFragment fragment = new GroupMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_group, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_back = (ImageButton)rootView.findViewById(R.id.btn_back);
        btn_add_group = (ImageButton)rootView.findViewById(R.id.btn_add_group);
        listView_group = (ListView)rootView.findViewById(R.id.listView_group);
        searchViewGroup = (SearchView)rootView.findViewById(R.id.searchViewGroup);
        searchViewGroup.setQueryHint("Search..");
        btn_add_group.setOnClickListener(this);
        btn_back.setOnClickListener(this);

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
    public void onResume() {
        super.onResume();

        final GroupDAO groupDAO = new GroupDAO(getActivity());
        groupDAO.open();
        final ArrayList<GroupList> groupLists = groupDAO.getAllGroupList();
        groupDAO.close();
        final GroupAdapter adapter = new GroupAdapter(getActivity(),groupLists);
        listView_group.setAdapter(adapter);
        listView_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editIntent = new Intent(getActivity(), FixGroupActivity.class);
                editIntent.putExtra("editGroup", (Serializable) adapter.getItem(position));
                startActivity(editIntent);
            }
        });

    }

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
    public void onClick(View v) {
        if(btn_back == v) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }else if (btn_add_group == v){
            Intent intent = new Intent(getActivity(), AddGroupActivity.class);
            startActivity(intent);
        }
    }
}
