package com.example.chatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout inputEmail, inputPassword;
    private RadioGroup radioCheckTerms;
    private Button btnRegister, btnRegisterGG;
    private TextView textLoginNow;
    FirebaseAuth auth;
    ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        anhXa();

        btnRegister.setOnClickListener(v -> {
            if (radioCheckTerms.getCheckedRadioButtonId() == -1) {
                Toast.makeText(RegisterActivity.this, "Please accept terms and conditions", Toast.LENGTH_SHORT).show();
                return;
            }

            AtemptRegister();

        });

        btnRegisterGG.setOnClickListener(v -> {

        });

        textLoginNow.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

    }

    private void AtemptRegister() {
        String email = inputEmail.getEditText().getText().toString();
        String password = inputPassword.getEditText().getText().toString();

        if (email.isEmpty() || !email.contains("@gmail")) {
            showError(inputEmail, "Email is not valid!!!");
        } else if (password.isEmpty() || password.length() < 6) {
            showError(inputPassword, "Password must be greater than 6 characters!!!");
        }else{
            loadingBar.setTitle("Registration");
            loadingBar.setMessage("Please wait a few second...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        loadingBar.dismiss();
                        Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }else{
                        loadingBar.dismiss();
                        Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                    }
                };
            });
        }
    };

    private void showError(TextInputLayout filed, String s) {
        filed.setError(s);
        filed.requestFocus();
    }

    private void anhXa() {
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        radioCheckTerms = findViewById(R.id.radioCheckTerms);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegisterGG = findViewById(R.id.btnRegisterGG);
        textLoginNow = findViewById(R.id.textLoginNow);
        auth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

    }
}