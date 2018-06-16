package com.example.android.fido1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {
    Button sign,register,google,pno;
    EditText email,password,username;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        sign=(Button) findViewById(R.id.a4);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(LogIn.this,SignUp.class);
                startActivity(i);
            }
        });
        email = (EditText)findViewById(R.id.b1);
        password=(EditText)findViewById(R.id.b2);
        username=(EditText)findViewById(R.id.b3);
        register=(Button)findViewById(R.id.a1);
        google=(Button)findViewById(R.id.a2);
        pno=(Button)findViewById(R.id.a3);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null)
        {
            finish();
            Intent i = new Intent(LogIn.this,Main.class);
            startActivity(i);
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String Username = username.getText().toString().trim();

                if(Email.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Email is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Password.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Username.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Username is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
                {
                    Toast.makeText(getApplicationContext(), "Please provide a correct Email Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Password.length()<6)
                {
                    Toast.makeText(getApplicationContext(), "Please provide a Password with more than 6 length", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            finish();
                            Toast.makeText(getApplicationContext(), "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LogIn.this,Main.class);
                            startActivity(i);
                        }
                    }
                });
            }
        });
    }
}
