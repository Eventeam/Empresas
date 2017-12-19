package com.example.daniel.empresas;

import android.app.Fragment;
import android.app.FragmentContainer;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private FragmentGeneral fragmentGeneral;
    private FragmentDetails fragmentDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "MainActivity -> onCreate", Toast.LENGTH_SHORT).show();

        if (findViewById(R.id.fragment_container) != null) {
            if (this.fragmentGeneral != null) {
                Toast.makeText(this, "The FragmentGeneral was already created", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Create new FragmentGeneral", Toast.LENGTH_SHORT).show();
            setFragmentGeneral();
        }else if ((findViewById(R.id.fragment_general_container) != null)
                && ((findViewById(R.id.fragment_details_container) != null))) {
            if ((this.fragmentGeneral != null) && (this.fragmentDetails != null)) {
                Toast.makeText(this, "The Fragments were already created", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Create new Fragments", Toast.LENGTH_SHORT).show();
            setFragments();
        }
    }

    private void setFragmentGeneral() {
        this.fragmentGeneral = new FragmentGeneral();
        this.fragmentGeneral.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                this.fragmentGeneral).commit();
    }

    private void setFragments() {
        this.fragmentGeneral = new FragmentGeneral();
        this.fragmentGeneral.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_general_container,
                this.fragmentGeneral).commit();

        this.fragmentDetails = new FragmentDetails();
        this.fragmentDetails.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_details_container,
                this.fragmentDetails).commit();
    }
}
