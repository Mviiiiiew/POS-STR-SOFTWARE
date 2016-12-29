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
import com.example.posstrsoftware.posstrsoftware.activity.UnitMainActivity;
import com.example.posstrsoftware.posstrsoftware.dao.UnitDAO;
import com.example.posstrsoftware.posstrsoftware.model.UnitList;
import com.example.posstrsoftware.posstrsoftware.util.Util_String;
import com.gc.materialdesign.utils.Utils;
import com.gc.materialdesign.views.ButtonRectangle;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class AddUnitFragment extends Fragment implements View.OnClickListener {
    EditText editText_Unit;
    ButtonRectangle btn_save;
    ButtonRectangle btn_back;


    public AddUnitFragment() {
        super();
    }

    public static AddUnitFragment newInstance() {
        AddUnitFragment fragment = new AddUnitFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_unit, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        editText_Unit = (EditText) rootView.findViewById(R.id.editText_Unit);
        btn_save = (ButtonRectangle) rootView.findViewById(R.id.btn_save);
        btn_back = (ButtonRectangle ) rootView.findViewById(R.id.btn_back);

        btn_back.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        btn_back.setRippleSpeed(40);
        btn_save.setRippleSpeed(15);

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
                    if (editText_Unit.getText().toString().trim().replaceAll("", "").matches("")) {

                        AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                        alertDialogder.setTitle("กรุณาใส่ชื่อหน่วยนับ : ?");
                        alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        alertDialogder.show();
                    } else {
                        UnitList unitList = new UnitList();
                        unitList.setUnitText(Util_String.getGennerlateString(editText_Unit.getText().toString()));
                        UnitDAO unitDAO = new UnitDAO(getActivity());
                        unitDAO.open();
                        ex = unitDAO.add(unitList);
                        unitDAO.close();
                        if (ex == 0) {
                            AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                            alertDialogder.setTitle("มีข้อมูล 'หน่วยนับ' ซ้ำในระบบ");
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

        }else if (v == btn_back){
           getActivity().finish();
        }


    }
}
