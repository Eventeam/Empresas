package com.example.daniel.empresas;

import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.daniel.empresas.model.Company;

public class MainActivity extends AppCompatActivity implements
        FragmentGeneral.OnGeneralSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast.makeText(this, "MainActivity -> onCreate", Toast.LENGTH_SHORT).show();

        if (findViewById(R.id.fragment_container) != null) {
            if (FragmentCache.portraitFragmentGeneral != null) {
                Toast.makeText(this, "The FragmentGeneral was already created", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Create new FragmentGeneral", Toast.LENGTH_SHORT).show();
            setPortraitFragment(FragmentCache.getPortraitFragmentGeneral());
        }else if ((findViewById(R.id.fragment_general_container) != null) && ((findViewById(R.id.fragment_details_container) != null))){
            if ((FragmentCache.landscapeFragmentGeneral != null) && (FragmentCache.landscapeFragmentDetails != null)) {
                Toast.makeText(this, "The Fragments were already created", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Create new Fragments", Toast.LENGTH_SHORT).show();
            setLandscapeFragments(FragmentCache.getLandscapeFragmentGeneral(), FragmentCache.getLandscapeFragmentDetails());
        }
    }

    private void setPortraitFragment(FragmentGeneral fragmentGeneral) {
        fragmentGeneral.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                fragmentGeneral).commit();
    }

    private void setLandscapeFragments(FragmentGeneral fragmentGeneral, FragmentDetails fragmentDetails) {
        fragmentGeneral.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_general_container,
                fragmentGeneral).commit();

        fragmentDetails.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_details_container,
                fragmentDetails).commit();
    }

    @Override
    public void OnDetailsSelected(int position, Company company, CustomAdapter customAdapter) {
        if (findViewById(R.id.fragment_container) != null){
//            Toast.makeText(getActivity().getBaseContext(), "Clicked Portrait. " + company.getName(), Toast.LENGTH_SHORT).show();

            FragmentDetails fragmentDetails = new FragmentDetails();
            Bundle args = new Bundle();
            args.putInt("position", position);
            fragmentDetails.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, fragmentDetails);
            transaction.addToBackStack(null);

            transaction.commit();
        } else{
            FragmentDetails fragmentDetails = new FragmentDetails();
            Bundle args = new Bundle();
            args.putInt("position", position);
            fragmentDetails.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_details_container, fragmentDetails);
            transaction.addToBackStack(null);

            transaction.commit();

//            setTitle(company.getName());
//
//            ImageView companyLogo = (ImageView) findViewById(R.id.companyImageView);
//            Glide.with(getBaseContext()).load(company.getImage()).into(companyLogo);
//
//            TextView companyCategory = (TextView) findViewById(R.id.companyCategoryTextView);
//            companyCategory.setText(company.getCategory());
//
//            TextView companyNumber = (TextView) findViewById(R.id.companyNumberTextView);
//            companyNumber.setText(Html.fromHtml("<b>" + getString(R.string.phone_number) + "</b> " + company.getPhoneNumber()));
//
//            TextView companyEmail = (TextView) findViewById(R.id.companyEmailTextView);
//            companyEmail.setText(Html.fromHtml("<b>" + getString(R.string.email) + "</b> " + company.getEmail()));
//
//            TextView details = (TextView) findViewById(R.id.companyDescriptionTextView);
//            details.setText(company.getDescription());
        }
    }
}
