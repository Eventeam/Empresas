package com.example.daniel.empresas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetails extends Fragment {


    public FragmentDetails() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Toast.makeText(getActivity(), "FragmentDetails -> onCreateView", Toast.LENGTH_SHORT).show();

        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_details_fragment);
        return textView;
    }

}
