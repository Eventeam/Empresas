package com.example.daniel.empresas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daniel.empresas.model.Company;

import java.util.List;

/**
 * Created by Daniel on 03-01-2018.
 */

public class CustomAdapter extends BaseAdapter {
    private List<Company> companies;
    private Context context;

    CustomAdapter(Context context, List<Company> companies) {
        this.context = context;
        this.companies = companies;
    }

    @Override
    public int getCount() {
        return this.companies.size();
    }

    @Override
    public Company getItem(int position) {
        return this.companies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.row_list, parent,
                    false);
        }
        Company company = this.getItem(position);

        ImageView image = convertView.findViewById(R.id.companyImage);
        TextView name = convertView.findViewById(R.id.companyName);

        Glide.with(context).load(company.getImage()).into(image);
        name.setText(company.getName());

        return convertView;
    }

    void updateCompanies(List<Company> companies) {
        this.companies = companies;
        notifyDataSetChanged();
    }
}
