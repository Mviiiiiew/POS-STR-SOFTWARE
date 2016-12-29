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
import com.example.posstrsoftware.posstrsoftware.model.GroupList;
import com.example.posstrsoftware.posstrsoftware.util.Util_String;
import com.gc.materialdesign.views.ButtonRectangle;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class AddGroupFragment extends Fragment implements View.OnClickListener {
    ButtonRectangle btn_back;
    EditText editText_Group;
    ButtonRectangle btn_save;



    public AddGroupFragment() {
        super();
    }

    public static AddGroupFragment newInstance() {
        AddGroupFragment fragment = new AddGroupFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_group, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_save = (ButtonRectangle) rootView.findViewById(R.id.btn_save);
        editText_Group = (EditText) rootView.findViewById(R.id.editText_Group);
        btn_back = (ButtonRectangle) rootView.findViewById(R.id.btn_back);
        btn_save.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_back.setRippleSpeed(40);
        btn_save.setRippleSpeed(40);
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
        int ex = 0;
        if (v == btn_save) {
            if (editText_Group.getText().toString().trim().replaceAll("", "").matches("")) {
                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setTitle("กรุณาใส่ชื่อกลุ่ม : ?");
                alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialogder.show();
            } else {
                GroupList groupList = new GroupList();
                groupList.setGroupText(Util_String.getGennerlateString(editText_Group.getText().toString()));
                GroupDAO groupDAO = new GroupDAO(getActivity());
                groupDAO.open();
                ex = groupDAO.add(groupList);
                groupDAO.close();
                if (ex == 0) {
                    AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                    alertDialogder.setTitle("มีข้อมูล 'กลุ่ม' ซ้ำในระบบ");
                    alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
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
        } else if (v == btn_back) {
            getActivity().finish();
        }
    }
}
