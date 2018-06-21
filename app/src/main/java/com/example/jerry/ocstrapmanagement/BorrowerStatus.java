package com.example.jerry.ocstrapmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BorrowerStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrower_status);

        Borrower clickedBorrower = (Borrower) getIntent().getSerializableExtra("borrower");
        TextView nameTV = findViewById(R.id.borrower_name_status);
        TextView emailTV = findViewById(R.id.borrower_email_status);
        TextView phoneTV = findViewById(R.id.borrower_phone_status);

        nameTV.setText(clickedBorrower.getName());
        emailTV.setText(clickedBorrower.getEmail());
        if (clickedBorrower.getPhone() != null){
            phoneTV.setText(clickedBorrower.getPhone());
        }
    }
}
