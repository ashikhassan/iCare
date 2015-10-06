package com.emythmakers.ashik.icare.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emythmakers.ashik.icare.R;
import com.emythmakers.ashik.icare.database.ContactModel;
import com.emythmakers.ashik.icare.database.DataSource;


/**
 * A simple {@link Fragment} subclass.
 */
public class InsertFragment extends Fragment {
    EditText sName;
    EditText iAge;
    EditText iHeight;
    EditText iWeight;
    Button btnSave;
    ContactModel contactModel;
    DataSource dataSource;
    Fragment fragment;



    public InsertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_insert, container, false);
        sName=(EditText)view.findViewById(R.id.sName);
        iAge=(EditText)view.findViewById(R.id.iAge);
        iHeight=(EditText)view.findViewById(R.id.iHeight);
        iWeight=(EditText)view.findViewById(R.id.iWeight);
        btnSave=(Button)view.findViewById(R.id.btnSubmite);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=sName.getText().toString();
                String age=iAge.getText().toString();
                String height=iHeight.getText().toString();
                String weight=iWeight.getText().toString();
                contactModel=new ContactModel(name, age, height, weight);
                dataSource=new DataSource(getActivity());
                long result=dataSource.insertData(contactModel);
                if(result>0){
                    FragmentManager fragmentManager=getFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragment=new ListFragment();
                    fragmentTransaction.replace(R.id.loginFragment, fragment);
                    fragmentTransaction.commit();
                    Toast.makeText(getActivity(), "Successfully inserted your data", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }


}
