package com.example.jerry.ocstrapmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private final int CHECK_IN_REQUEST_CODE = 1;
    private final int CHECK_OUT_REQUEST_CODE = 2;

    private Button checkInBT, checkOutBT, createNewBorrowerBT, signOutBT;
    private static boolean isSignedIn = false;
    private FirebaseAuth mAuth;
    private TextView userEmailTV;
    private DatabaseReference mUserRef;
    private FirebaseDatabase mDatabase;
    private ListView borrowerLV;

    private static FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkInBT = findViewById(R.id.check_in_button);
        checkOutBT = findViewById(R.id.check_out_button);
        createNewBorrowerBT = findViewById(R.id.create_new_borrower);
        signOutBT = findViewById(R.id.sign_out_button);
        userEmailTV = findViewById(R.id.user_email_text_view);
        borrowerLV = findViewById(R.id.borrower_list_view);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mUserRef = mDatabase.getReference("users");
    }

    @Override
    protected void onStart() {
        super.onStart();

        currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            userEmailTV.setText(currentUser.getEmail());
            isSignedIn = true;
        }

        checkInBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanBarcode(CHECK_IN_REQUEST_CODE);
            }
        });

        signOutBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
            }
        });

        createNewBorrowerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateNewBorrowerDialog createNewBorrowerDialog = new CreateNewBorrowerDialog(mUserRef);
                createNewBorrowerDialog.show(getSupportFragmentManager(), "Create New Borrower");
            }
        });

        Borrower[] borrowers = new Borrower[2];
        borrowers[0] = new Borrower("John Doe","ghtehsf@gmail.com","405415632400");
        borrowers[1] = new Borrower("Held held ", "ghdafdfa@gaf.com","495675448");
        BorrowerArrayAdapter arrayAdapter = new BorrowerArrayAdapter(this, R.layout.adapter_borrower_list, borrowers);
        borrowerLV.setAdapter(arrayAdapter);

        borrowerLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Borrower clickedBorrower = (Borrower) adapterView.getItemAtPosition(pos);
                Intent intentShowBorrowerStatus = new Intent(MainActivity.this, BorrowerStatus.class);
                intentShowBorrowerStatus.putExtra("borrower", clickedBorrower);
                startActivity(intentShowBorrowerStatus);
            }
        });

    }

    private void scanBarcode(int requestCode) {
        Intent intent = new Intent(this, ScanBarcodeActivity.class);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHECK_IN_REQUEST_CODE && resultCode == RESULT_OK){
            if (data != null) {
                Barcode barcode = data.getParcelableExtra("barcode");
                Toast.makeText(this, barcode.displayValue, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No Barcode Found", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void setSignedIn(boolean signedIn) {
        MainActivity.isSignedIn = signedIn;
    }

    public static void setCurrentUser(FirebaseUser currentUser) {
        MainActivity.currentUser = currentUser;
    }

    private class BorrowerListEventListener implements ChildEventListener {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }
}
