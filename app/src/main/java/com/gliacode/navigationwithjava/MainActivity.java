package com.gliacode.navigationwithjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    EditText mEmail,mPassword;
    Button loginButton;
    TextView mForgetPassword,mRegisterButton ;
  FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       mEmail =findViewById(R.id.editEmail);
       mPassword=findViewById(R.id.editPassword);
       loginButton =findViewById(R.id.btnGiris);
       mRegisterButton=findViewById(R.id.btnLoginUyeol);
       mForgetPassword=findViewById(R.id.forgetPassword);
        firebaseAuth=FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password= mPassword.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email boş olmamalıdır");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password  boş olmamalıdır");
                    return;
                }

                if(password.length()< 6 ){
                    mPassword.setError("Pasword must be > = 6");
                    return;

                }


                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener( MainActivity.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            FirebaseAuth .getInstance().signOut();
                           // startActivity(new Intent(getApplicationContext(),GirisActivity.class));
                            startActivity(new Intent(MainActivity.this,GirisActivity.class));
                            finish();;
                          //  Toast.makeText(MainActivity.this,"Logged in Successfully",Toast.LENGTH_LONG).show();
                        }

                        else {

                            Toast.makeText(MainActivity.this,"ERROR"+task.getException().getMessage(),Toast.LENGTH_LONG).show();

                        }
                    }
                });

            }
        });

   mRegisterButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           FirebaseAuth .getInstance().signOut();
           startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
       }
   });


    mForgetPassword.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FirebaseAuth .getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),ResetPassword.class));
        }
    });
    }
}