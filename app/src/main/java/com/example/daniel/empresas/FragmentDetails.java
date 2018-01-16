package com.example.daniel.empresas;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.daniel.empresas.model.Company;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetails extends Fragment {

    static Company company;

    public FragmentDetails() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Toast.makeText(getActivity(), "FragmentDetails -> onCreateView", Toast.LENGTH_SHORT).show();

        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            int companyPosition = args.getInt("position");
            company = FragmentGeneral.customAdapter.getItem(companyPosition);

            getActivity().setTitle(company.getName());

            ImageView companyLogo = (ImageView) getActivity().findViewById(R.id.companyImageView);
            Glide.with(getActivity().getBaseContext()).load(company.getImage()).into(companyLogo);

            TextView companyCategory = (TextView) getActivity().findViewById(R.id.companyCategoryTextView);
            companyCategory.setText(company.getCategory());

            TextView companyNumber = (TextView) getActivity().findViewById(R.id.companyNumberTextView);
            companyNumber.setText(Html.fromHtml("<b>" + getString(R.string.phone_number) + "</b> " + company.getPhoneNumber()));

            TextView companyEmail = (TextView) getActivity().findViewById(R.id.companyEmailTextView);
            companyEmail.setText(Html.fromHtml("<b>" + getString(R.string.email) + "</b> " + company.getEmail()));

            TextView details = (TextView) getActivity().findViewById(R.id.companyDescriptionTextView);
            details.setText(company.getDescription());
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        ImageButton callButton = (ImageButton) getActivity().findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (company.getPhoneNumber().isEmpty()){
                    Toast.makeText(getActivity().getBaseContext(), "A empresa não tem número de telefone.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Uri number = Uri.parse("tel:" + company.getPhoneNumber());
                Intent callIntent = new Intent(Intent.ACTION_CALL, number);

                PackageManager manager = getActivity().getBaseContext().getPackageManager();
                List<ResolveInfo> info = manager.queryIntentActivities(callIntent, 0);
                if (info.size() > 0) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        callIntent = new Intent(Intent.ACTION_DIAL, number);
                    }
                    startActivity(callIntent);
                    return;
                }
                appSupportError();
            }
        });

        ImageButton emailButton = (ImageButton) getActivity().findViewById(R.id.emailButton);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (company.getEmail().isEmpty()){
                    Toast.makeText(getActivity().getBaseContext(), "A empresa não tem endereço de E-mail.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Uri email = Uri.parse("mailto:" + company.getEmail());
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, email);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, email);

                PackageManager manager = getActivity().getBaseContext().getPackageManager();
                List<ResolveInfo> info = manager.queryIntentActivities(emailIntent, PackageManager.MATCH_DEFAULT_ONLY);
                if (info.size() > 0) {
                    startActivity(emailIntent);
                    return;
                }
                appSupportError();
            }
        });

        ImageButton webButton = (ImageButton) getActivity().findViewById(R.id.webButton);
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (company.getWeb().isEmpty()){
                    Toast.makeText(getActivity().getBaseContext(), "A empresa não tem página Web.", Toast.LENGTH_SHORT).show();
                    return;
                }
                appSupportIntent(company.getWeb());
            }
        });

        ImageButton locationButton = (ImageButton) getActivity().findViewById(R.id.locationButton);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (company.getLocalization().isEmpty()){
                    Toast.makeText(getActivity().getBaseContext(), "A empresa não tem localização da Sede.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String locationLabel = company.getName();
                Uri location = Uri.parse("geo:" + company.getLocalization() + "?q=" + company.getLocalization() + "(" + locationLabel + ")");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

                PackageManager manager = getActivity().getBaseContext().getPackageManager();
                List<ResolveInfo> info = manager.queryIntentActivities(mapIntent, PackageManager.MATCH_DEFAULT_ONLY);
                if (info.size() > 0) {
                    startActivity(mapIntent);
                    return;
                }
                appSupportError();
            }
        });

        ImageButton linkedInButton = (ImageButton) getActivity().findViewById(R.id.linkedInButton);
        linkedInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (company.getLinkedIn().isEmpty()){
                    Toast.makeText(getActivity().getBaseContext(), "A empresa não tem página de LinkedIn.", Toast.LENGTH_SHORT).show();
                    return;
                }
                appSupportIntent(company.getLinkedIn());
            }
        });

        ImageButton facebookButton = (ImageButton) getActivity().findViewById(R.id.facebookButton);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (company.getFacebook().isEmpty()){
                    Toast.makeText(getActivity().getBaseContext(), "A empresa não tem página de Facebook.", Toast.LENGTH_SHORT).show();
                    return;
                }
                appSupportIntent(company.getFacebook());
            }
        });

        ImageButton twitterButton = (ImageButton) getActivity().findViewById(R.id.twitterButton);
        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (company.getTwitter().isEmpty()){
                    Toast.makeText(getActivity().getBaseContext(), "A empresa não tem página de Twitter.", Toast.LENGTH_SHORT).show();
                    return;
                }
                appSupportIntent(company.getTwitter());
            }
        });

        ImageButton youtubeButton = (ImageButton) getActivity().findViewById(R.id.youtubeButton);
        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (company.getYoutube().isEmpty()){
                    Toast.makeText(getActivity().getBaseContext(), "A empresa não tem canal de Youtube.", Toast.LENGTH_SHORT).show();
                    return;
                }
                appSupportIntent(company.getYoutube());
            }
        });

        ImageButton googlePlusButton = (ImageButton) getActivity().findViewById(R.id.googlePlusButton);
        googlePlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (company.getGoogle().isEmpty()){
                    Toast.makeText(getActivity().getBaseContext(), "A empresa não tem página de Google+.", Toast.LENGTH_SHORT).show();
                    return;
                }
                appSupportIntent(company.getGoogle());
            }
        });
    }

    private void appSupportIntent(String data) {
        Uri uri = Uri.parse(data);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        String title = getResources().getString(R.string.chooser_title);
        Intent chooser = Intent.createChooser(intent, title);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(chooser);
            return;
        }
        appSupportError();
    }

    private void appSupportError() {
        Toast.makeText(getContext(), "There are no supported apps from this phone!", Toast.LENGTH_SHORT).show();
    }
}
