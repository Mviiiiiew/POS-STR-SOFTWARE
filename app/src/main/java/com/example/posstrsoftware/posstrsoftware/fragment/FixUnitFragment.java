package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
import com.gc.materialdesign.views.ButtonRectangle;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FixUnitFragment extends Fragment implements View.OnClickListener {

    EditText editText_Unit;
    ButtonRectangle btn_back;
    ButtonRectangle btn_delete;
    ButtonRectangle btn_edit_unit;
    String mUnit;

    public FixUnitFragment() {
        super();
    }

    public static FixUnitFragment newInstance() {
        FixUnitFragment fragment = new FixUnitFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fix_unit, container, false);
        initInstances(rootView);
        UnitList editUnitList = (UnitList) getActivity().getIntent().getSerializableExtra("editUnit");
        editText_Unit.setText(editUnitList.getUnitText());
        mUnit = editUnitList.getUnitText();
        Log.d("mUnit",mUnit);

        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here

        btn_back = (ButtonRectangle) rootView.findViewById(R.id.btn_back);
        editText_Unit = (EditText) rootView.findViewById(R.id.editText_Unit);
        btn_edit_unit = (ButtonRectangle) rootView.findViewById(R.id.btn_edit_unit);
        btn_delete = (ButtonRectangle) rootView.findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);
        btn_edit_unit.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_delete.setRippleSpeed(15);
        btn_edit_unit.setRippleSpeed(15);
        btn_back.setRippleSpeed(40);


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
        UnitList editUnitList = (UnitList) getActivity().getIntent().getSerializableExtra("editUnit");
        int ex = 0;
        if (v == btn_edit_unit) {
            if (editText_Unit.getText().toString().trim().replaceAll("", "").matches("")) {
                Toast.makeText(getActivity(), "กรุณาใส่หน่วย : ?", Toast.LENGTH_SHORT).show();
            }else if(editText_Unit.getText().toString().matches(mUnit)){

                UnitList eUnitList = new UnitList();
                eUnitList.setId(editUnitList.getId());
                eUnitList.setUnitText(Util_String.getGennerlateString(editText_Unit.getText().toString()));
                UnitDAO unitDAO = new UnitDAO(getActivity());
                unitDAO.open();
                unitDAO.updatenow(eUnitList);
                unitDAO.close();
                getActivity().finish();

            } else {
                UnitList eUnitList = new UnitList();
                eUnitList.setId(editUnitList.getId());
                eUnitList.setUnitText(Util_String.getGennerlateString(editText_Unit.getText().toString()));
                UnitDAO unitDAO = new UnitDAO(getActivity());
                unitDAO.open();
                ex = unitDAO.update(eUnitList);
                unitDAO.close();
                if (ex == 0) {
                    AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                    alertDialogder.setMessage("มีข้อมูล 'หน่วย' ซ้ำในระบบ");
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
        } else if (btn_back == v) {
            getActivity().finish();
        } else if (btn_delete == v) {
            UnitDAO unitDAODel = new UnitDAO(getActivity());
            unitDAODel.open();
            unitDAODel.delete(editUnitList);
            unitDAODel.close();
            getActivity().finish();

        }
    }
}
