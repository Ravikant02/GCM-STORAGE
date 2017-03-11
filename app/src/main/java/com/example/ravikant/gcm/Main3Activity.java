package com.example.ravikant.gcm;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        TextView username = (TextView) findViewById(R.id.username);
        TextView pd = (TextView) findViewById(R.id.password);
        userId = username.getText().toString().trim();
        password = pd.getText().toString().trim();

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserChangeListener();
            }
        });
    }

    private void addUserChangeListener() {
        // User data change listener
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait");
        dialog.show();
         databaseReference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                dialog.dismiss();
                // Check for null
                if (user == null) {
                    Log.e("TAG", "User data is null!");
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
