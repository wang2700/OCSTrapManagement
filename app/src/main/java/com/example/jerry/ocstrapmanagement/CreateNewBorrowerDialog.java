package com.example.jerry.ocstrapmanagement;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by jerry on 3/15/2018.
 */

@SuppressLint("ValidFragment")
public class CreateNewBorrowerDialog extends DialogFragment {

    private DatabaseReference userRef;
    private EditText nameET, phoneET, emailET;
    private String name,phone,email = "";

    @SuppressLint("ValidFragment")
    public CreateNewBorrowerDialog(DatabaseReference userRef) {
        this.userRef = userRef;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.create_borrower_dialog, null);

        nameET = view.findViewById(R.id.name_create_borrower_edit_text);
        phoneET = view.findViewById(R.id.phone_create_borrower_edit_text);
        emailET = view.findViewById(R.id.email_create_borrower_edit_text);

        builder.setView(view)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        name = nameET.getText().toString();
                        email = emailET.getText().toString();
                        phone = phoneET.getText().toString();
                        Borrower borrower = new Borrower(name, email, phone, userRef);
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        return builder.create();
    }
}
