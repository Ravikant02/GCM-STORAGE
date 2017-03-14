package com.example.ravikant.gcm;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main3Activity extends AppCompatActivity {

    DatabaseReference databaseReference;
    private String userId;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");
        /*database.getReference("app_title").setValue("real time database");
        // userId = databaseReference.push().getKey();
        userId = "ravikant_blazeautomation_com";
        User user = new User("Ravikant Verma", "ravikant@blazeautomation.com");
        databaseReference.child(userId).setValue(user);
        addUserChangeListener();*/
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText pd = (EditText) findViewById(R.id.password);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = username.getText().toString().trim();
                password = pd.getText().toString().trim();
                addGetUserListener();
            }
        });
    }

    private void addUserChangeListener() {
        // User data change listener
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait");
        dialog.show();
        String tmpEmail = userId;
        tmpEmail = tmpEmail.replace("@", "_");
        tmpEmail = tmpEmail.replace(".", "_");
         databaseReference.child(tmpEmail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                dialog.dismiss();
                // Check for null
                if (user == null) {
                    Log.e("TAG", "User data is null!");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                dialog.dismiss();
                Log.e("TAG", "Failed to read user", error.toException());
            }
        });
    }

    private void addGetUserListener() {
        // User data change listener
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait");
        dialog.show();
        String tmpEmail = userId;
        tmpEmail = tmpEmail.replace("@", "_");
        tmpEmail = tmpEmail.replace(".", "_");
        databaseReference.child(tmpEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                dialog.dismiss();
                // Check for null
                if (user == null) {
                    Toast.makeText(getApplicationContext(), "Invalid user name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (user.email.equalsIgnoreCase(userId) && user.password.equalsIgnoreCase(password)){
                    Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid user name", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                dialog.dismiss();
                Log.e("TAG", "Failed to read user", error.toException());
            }
        });
    }
}
