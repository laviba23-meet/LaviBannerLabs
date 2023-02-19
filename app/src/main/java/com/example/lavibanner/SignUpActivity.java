package com.example.lavibanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView signup;
    private TextView email1;
    private TextView password1;
    private TextView name1;
    private Button submit;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signup = findViewById(R.id.signup);
        email1 = findViewById(R.id.email1);
        mAuth=FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        password1 = findViewById(R.id.password1);
        name1 = findViewById(R.id.name1);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==submit ){
            creat_user(email1.getText().toString(), password1.getText().toString());

        }
    }

    public void creat_user(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(SignUpActivity.this, HomeActivity.class);
                            User user = new User(name1.getText().toString(), email1.getText().toString(), password1.getText().toString());
                            String uid = mAuth.getCurrentUser().getUid().toString();
                            database.getReference("Users").child(uid).setValue(user);
                            startActivity(i);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}