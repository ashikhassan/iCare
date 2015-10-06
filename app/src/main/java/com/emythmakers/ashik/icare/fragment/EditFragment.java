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
public class EditFragment extends Fragment {
    DataSource dataSource;
    EditText sName;
    EditText sAge;
    EditText sHeight;
    EditText sWeight;
    Button btnUpdate;
    ContactModel contactModel;
    Fragment fragment;



    public EditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_edit, container, false);
        sName=(EditText)view.findViewById(R.id.sName);
        sAge=(EditText)view.findViewById(R.id.iAge);
        sHeight=(EditText)view.findViewById(R.id.iHeight);
        sWeight=(EditText)view.findViewById(R.id.iWeight);
        btnUpdate=(Button)view.findViewById(R.id.btnUpdate);

        final String positionID=ContactModel.getPositionID();
        dataSource=new DataSource(getActivity());

        contactModel=new ContactModel(positionID);
        contactModel=dataSource.singleContact(positionID);

        sName.setText(contactModel.getName());
        sAge.setText(contactModel.getAge());
        sHeight.setText(contactModel.getHeight());
        sWeight.setText(contactModel.getWeight());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=sName.getText().toString();
                String age=sAge.getText().toString();
                String height=sHeight.getText().toString();
                String weight=sWeight.getText().toString();
                contactModel=new ContactModel(name, age, height, weight);
                dataSource=new DataSource(getActivity());
                boolean result=dataSource.updateData(positionID, contactModel);
                if(result){
                    FragmentManager fragmentManager=getFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragment=new ListFragment();
                    fragmentTransaction.replace(R.id.loginFragment, fragment);
                    fragmentTransaction.commit();
                    Toast.makeText(getActivity(), "Successfully Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


}
