package com.example.lavibanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lavibanner.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView Email;
    private TextView Password;
    private Button Button1;
    private Button Button2;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        Button1 = findViewById(R.id.button1);
        Button2 = findViewById(R.id.button2);

        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        if (view == Button2 && Email.getText().toString().contains("@") && Email.getText().toString().contains(".com")) {
            Intent j = new Intent(this, HomeActivity.class);
            startActivity(j);
        }
       if (view == Button1) {
           Intent i = new Intent(this, SignUpActivity.class);
           startActivity(i);


        }

    }

    public void signin_user(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(i);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

}
