package com.os.tenxapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class KycActivity extends AppCompatActivity {
    boolean isFirstPage = true;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nextBtn = findViewById(R.id.button);
        nextBtn.setText(getString(R.string.go_to_step_2));
        findViewById(R.id.incPage1).setVisibility(View.VISIBLE);
        findViewById(R.id.incPage2).setVisibility(View.GONE);
        findViewById(R.id.button).setOnClickListener(view -> {
            isFirstPage = !isFirstPage;
            setUi(isFirstPage);
        });
    }

    private void setUi(boolean isFirstPage) {
        if (isFirstPage) {
            findViewById(R.id.view3).setBackgroundColor(getColor(R.color.profile_progress_half));
            nextBtn.setText(R.string.go_to_step_2);
            findViewById(R.id.imageView10).setVisibility(View.GONE);
            findViewById(R.id.incPage1).setVisibility(View.VISIBLE);
            findViewById(R.id.incPage2).setVisibility(View.GONE);
        } else {
            findViewById(R.id.view3).setBackgroundColor(getColor(R.color.profile_progress_full));
            findViewById(R.id.imageView10).setVisibility(View.VISIBLE);
            nextBtn.setText(R.string.complete_kyc);
            findViewById(R.id.incPage1).setVisibility(View.GONE);
            findViewById(R.id.incPage2).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        if (isFirstPage) {
            isFirstPage = false;
            setUi(false);
            super.onBackPressed();
        } else {
            isFirstPage = true;
            setUi(true);
        }
    }
}
