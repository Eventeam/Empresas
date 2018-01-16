package com.example.daniel.empresas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
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
//        Toast.makeText(this, "MainActivity -> onCreate", Toast.LENGTH_SHORT).show();

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState == null) {
                Toast.makeText(this, "Create new FragmentGeneral", Toast.LENGTH_SHORT).show();
                setPortraitFragment();
            }else {
                Toast.makeText(this, "The FragmentGeneral was already created", Toast.LENGTH_SHORT).show();
            }
        }else if ((findViewById(R.id.fragment_general_container) != null)
                && ((findViewById(R.id.fragment_details_container) != null))) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Toast.makeText(this, "Create new Fragments", Toast.LENGTH_SHORT).show();
                setLandscapeFragments();
            }else {
                Toast.makeText(this, "The Fragments were already created", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setPortraitFragment() {
        fragmentGeneral = new FragmentGeneral();
        fragmentGeneral.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                fragmentGeneral).commit();
    }

    private void setLandscapeFragments() {
        fragmentGeneral = new FragmentGeneral();
        fragmentGeneral.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_general_container,
                fragmentGeneral).commit();

        fragmentDetails = new FragmentDetails();
        fragmentDetails.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_details_container,
                fragmentDetails).commit();
    }
}
