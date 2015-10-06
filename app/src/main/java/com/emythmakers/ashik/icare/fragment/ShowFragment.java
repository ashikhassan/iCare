package com.emythmakers.ashik.icare.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.emythmakers.ashik.icare.R;
import com.emythmakers.ashik.icare.database.ContactModel;
import com.emythmakers.ashik.icare.database.DataSource;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFragment extends Fragment {
    DataSource dataSource;
    TextView sName;
    TextView sAge;
    TextView sHeight;
    TextView sWeight;
    Button btnEdit;
    Button btnDelete;
    ContactModel contactModel;
    Fragment fragment;


    public ShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_show, container, false);

        sName=(TextView)view.findViewById(R.id.sName);
        sAge=(TextView)view.findViewById(R.id.sAge);
        sHeight=(TextView)view.findViewById(R.id.sHeight);
        sWeight=(TextView)view.findViewById(R.id.sWeight);
        btnEdit=(Button)view.findViewById(R.id.btnEdit);
        btnDelete=(Button)view.findViewById(R.id.btnDelete);

        final String positionID=ContactModel.getPositionID();
        dataSource=new DataSource(getActivity());

        contactModel=new ContactModel(positionID);
        contactModel=dataSource.singleContact(positionID);

        sName.setText(contactModel.getName());
        sAge.setText(contactModel.getAge());
        sHeight.setText(contactModel.getHeight());
        sWeight.setText(contactModel.getWeight());
        final String dataID=contactModel.getId();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactModel.setPositionID(dataID);

                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragment=new EditFragment();
                fragmentTransaction.replace(R.id.loginFragment, fragment);
                fragmentTransaction.commit();
                Toast.makeText(getActivity(), "Going for Edit", Toast.LENGTH_SHORT).show();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result=dataSource.deleteData(positionID);
                if(result){
                    FragmentManager fragmentManager=getFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragment=new ListFragment();
                    fragmentTransaction.replace(R.id.loginFragment, fragment);
                    fragmentTransaction.commit();
                    Toast.makeText(getActivity(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


}
