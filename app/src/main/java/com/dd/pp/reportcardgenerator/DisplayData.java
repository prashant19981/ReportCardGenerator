package com.dd.pp.reportcardgenerator;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayData extends AppCompatActivity {
    private String userID;
    TextView userName ;
    TextView regNumber ;
    FirebaseAuth mAuth ;
    FirebaseDatabase mFirebase;
    DatabaseReference ref;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_display_data);
        userName = (TextView) findViewById(R.id.username);
        regNumber = (TextView)findViewById(R.id.registration_no);
        mAuth = FirebaseAuth.getInstance();
        mFirebase  = FirebaseDatabase.getInstance();
        ref  =  mFirebase.getReference();
        super.onCreate(savedInstanceState);

        user  =mAuth.getCurrentUser();
        userID = user.getUid();
        ref.child("user").child(userID);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                   // Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    //Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
      ref.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
                 dataPopulate(dataSnapshot);
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });
    }
public void dataPopulate(DataSnapshot dataSnapshot){
//   for(DataSnapshot ds: dataSnapshot.getChildren()){
       userInfo data = new userInfo();
       data.setUserName(dataSnapshot.child("user").child(userID).child("Name").getValue(String.class));
       data.setRegNumber(dataSnapshot.child("user").child(userID).child("regNumber").getValue(Long.class));
       userName.setText("Name: "+data.getUserName());
       regNumber.setText("Registration number: "+data.getRegNumber());
  // }

}

}
