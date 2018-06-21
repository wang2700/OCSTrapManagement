package com.example.jerry.ocstrapmanagement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by jerry on 3/22/2018.
 */

public class BorrowerArrayAdapter extends ArrayAdapter<Borrower> {


    public BorrowerArrayAdapter(@NonNull Context context, int resource, @NonNull Borrower[] borrowers) {
        super(context, resource, borrowers);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if (listItem == null)
            listItem = LayoutInflater.from(super.getContext()).inflate(R.layout.adapter_borrower_list, parent, false);

        Borrower borrower = super.getItem(position);

        TextView nameTV = listItem.findViewById(R.id.adapter_borrower_name_text_view);
        nameTV.setText(borrower.getName());

        TextView emailTV = listItem.findViewById(R.id.adapter_borrower_email_text_view);
        emailTV.setText(borrower.getEmail());

        return listItem;
    }
}
