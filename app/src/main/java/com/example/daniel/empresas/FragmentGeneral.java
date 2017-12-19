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
public class FragmentGeneral extends Fragment {


    public FragmentGeneral() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "FragmentGeneral -> onCreateView", Toast.LENGTH_SHORT).show();

        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_general_fragment);
        return textView;
    }

}
