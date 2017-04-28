package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.GroupMainActivity;
import com.example.posstrsoftware.posstrsoftware.dao.GroupDAO;
import com.example.posstrsoftware.posstrsoftware.dao.ProductDAO;
import com.example.posstrsoftware.posstrsoftware.dao.ProductSaleDAO;
import com.example.posstrsoftware.posstrsoftware.model.GroupList;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.example.posstrsoftware.posstrsoftware.util.Util_String;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.ArrayList;


/**
 * Created by nuuneoi on 11/16/2014.
 */
/**
 * เแก้ไขกลุ่ม
 * */
public class FixGroupFragment extends Fragment implements View.OnClickListener {
    ImageButton btn_back;
    EditText editText_Group;
    ButtonRectangle btn_edit_group;
    ButtonRectangle btn_delete;
    String mGroup;

    public FixGroupFragment() {
        super();
    }

    public static FixGroupFragment newInstance() {
        FixGroupFragment fragment = new FixGroupFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fix_group, container, false);
        initInstances(rootView);
        GroupList editGroupList = (GroupList) getActivity().getIntent().getSerializableExtra("editGroup");
        editText_Group.setText(editGroupList.getGroupText());
        mGroup = editGroupList.getGroupText();

        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_back = (ImageButton) rootView.findViewById(R.id.btn_back);
        editText_Group = (EditText) rootView.findViewById(R.id.editText_Group);
        btn_edit_group = (ButtonRectangle) rootView.findViewById(R.id.btn_edit_group);
        btn_delete = (ButtonRectangle) rootView.findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);
        btn_edit_group.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_delete.setRippleSpeed(15);
        btn_edit_group.setRippleSpeed(15);


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
    public void onClick(View v) {
        GroupList editGroupList = (GroupList) getActivity().getIntent().getSerializableExtra("editGroup");
        int ex = 0;
        if (v == btn_edit_group) {
            if (editText_Group.getText().toString().trim().replaceAll("", "").matches("")) {
                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setTitle(R.string.txt_pls_ent_group_name);
                alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialogder.show();

            } else if (editText_Group.getText().toString().equals(mGroup)) {
                GroupList eGroupList = new GroupList();
                eGroupList.setId(editGroupList.getId());
                eGroupList.setGroupText(Util_String.getGennerlateString(editText_Group.getText().toString()));
                GroupDAO groupDAO = new GroupDAO(getActivity());
                groupDAO.open();
                groupDAO.updatenow(eGroupList);
                groupDAO.close();
                getActivity().finish();
            } else {
                GroupList eGroupList = new GroupList();
                eGroupList.setId(editGroupList.getId());
                eGroupList.setGroupText(Util_String.getGennerlateString(editText_Group.getText().toString()));
                GroupDAO groupDAO = new GroupDAO(getActivity());
                groupDAO.open();
                ex = groupDAO.update(eGroupList);
                groupDAO.close();
                if (ex == 0) {
                    AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                    alertDialogder.setTitle(R.string.txt_duplicate);
                    alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alertDialogder.show();

                } else {
                    getActivity().finish();
                }
            }

        } else if (btn_back == v) {
            getActivity().finish();
        }
        else if (btn_delete == v) {

            AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
            alertDialogder.setTitle(getString(R.string.txt_pls_confrim_dle_group) + mGroup );
            alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    GroupList editGroupList = (GroupList) getActivity().getIntent().getSerializableExtra("editGroup");
                    GroupDAO groupDAO = new GroupDAO(getActivity());
                    groupDAO.open();
                    groupDAO.delete(editGroupList);
                    groupDAO.close();
                    getActivity().finish();
                }
            });
            alertDialogder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialogder.show();
        }
    }

}

