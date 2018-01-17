package com.example.daniel.empresas;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.daniel.empresas.model.Company;
import com.example.daniel.empresas.model.DataCache;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGeneral extends ListFragment {

    @SuppressLint("StaticFieldLeak")
    static CustomAdapter customAdapter;
    private OnGeneralSelectedListener mCallback;

    public FragmentGeneral() {
        // Required empty public constructor
    }

    public interface OnGeneralSelectedListener {
        void OnDetailsSelected(int position, Company company, CustomAdapter customAdapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnGeneralSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        Toast.makeText(getActivity(), "FragmentGeneral -> onCreateView", Toast.LENGTH_SHORT).show();
        getActivity().setTitle(getString(R.string.empresas));

        customAdapter = new CustomAdapter(getActivity().getBaseContext(), DataCache.getCompanyList());
        setListAdapter(customAdapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        FetchSpeakersAsyncTask fetchSpeakersAsyncTask = new FetchSpeakersAsyncTask();
        fetchSpeakersAsyncTask.execute(CompaniesData.RESPONSE);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        mCallback.OnDetailsSelected(position, customAdapter.getItem(position), customAdapter);
    }

    @SuppressLint("StaticFieldLeak")
    private class FetchSpeakersAsyncTask extends AsyncTask<String, Void, List<Company>> {

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected List<Company> doInBackground(String... strings) {
            List<Company> companies = new ArrayList<>();

            if(null != strings[0]) {
                String urlString = strings[0];

                try {
                    JSONArray jsonArray = new JSONArray(urlString);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String name = jsonObject.optString("Nome", "");
                        String category = jsonObject.optString("Categoria", "");
                        String image = jsonObject.optString("Imagem", "");
                        String description = jsonObject.optString("Descrição", "");
                        String phoneNumber = jsonObject.optString("Telefone", "");
                        String email = jsonObject.optString("Email", "");
                        String web = jsonObject.optString("Web", "");
                        String localization = jsonObject.optString("Localização", "");
                        String facebook = jsonObject.optString("Facebook", "");
                        String linkedIn = jsonObject.optString("LinkedIn", "");
                        String twitter = jsonObject.optString("Twitter", "");
                        String google = jsonObject.optString("Google+", "");
                        String youtube = jsonObject.optString("Youtube", "");

                        Company company = new Company(name, category, image, description,
                                phoneNumber, email, web, localization, facebook, linkedIn,
                                twitter, google, youtube);

                        companies.add(company);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return companies;
        }

        @Override
        protected void onPostExecute(List<Company> companies) {
            DataCache.setCompanyList(companies);
            customAdapter.updateCompanies(companies);
        }
    }
}
