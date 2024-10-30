package com.example.chatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    private ProgressDialog loadingBar;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);

        button = findViewById(R.id.btnStart);
        button.setOnClickListener(view -> {
            showLoadingBar();
            Intent intent = new Intent(IntroActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void showLoadingBar() {
        loadingBar = new ProgressDialog(this);
        loadingBar.setMessage("Loading...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
    }
}