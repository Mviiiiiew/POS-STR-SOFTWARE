package com.example.posstrsoftware.posstrsoftware.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.dao.ProductDAO;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class SaleProductFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener {
    private  ArrayList<String> arrayList;

    private ArrayAdapter<String> adapterx;
    String []  item ={};

    ListView listView_SaleProduct;
    ImageButton btn_back;
    EditText edit_Barcode;
    TextView txtTest;
    public SaleProductFragment() {
        super();
    }

    public static SaleProductFragment newInstance() {
        SaleProductFragment fragment = new SaleProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_saleproduct, container, false);
        initInstances(rootView);
        arrayList = new ArrayList<>(Arrays.asList(item));
        adapterx = new ArrayAdapter<String>(getActivity(),R.layout.list_item_saleproduct,R.id.txt_id_barcode,arrayList);

        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_back = (ImageButton) rootView.findViewById(R.id.btn_back);
        listView_SaleProduct = (ListView)rootView.findViewById(R.id.listView_SaleProduct);
        edit_Barcode = (EditText)rootView.findViewById(R.id.edit_Barcode);
        txtTest = (TextView)rootView.findViewById(R.id.txtTest);
        btn_back.setOnClickListener(this);
        edit_Barcode.setOnEditorActionListener(this);


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
        if(btn_back == v){
            getActivity().finish();
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        ProductDAO productDAO = new ProductDAO(getActivity());
        if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN){
            txtTest.setText(edit_Barcode.getText());
            productDAO.open();
            arrayList.add(productDAO.SearchID(edit_Barcode.getText().toString()));

            productDAO.close();
            listView_SaleProduct.setAdapter(adapterx);
            adapterx.notifyDataSetChanged();
            edit_Barcode.setText("");
            return true;

        }
        return false;
    }
}
