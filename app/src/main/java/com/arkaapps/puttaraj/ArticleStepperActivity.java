package com.arkaapps.puttaraj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.arkaapps.puttaraj.Adapters.ArticleStepperAdapter;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class ArticleStepperActivity extends AppCompatActivity implements StepperLayout.StepperListener{

    private StepperLayout mStepperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_stepper);
        mStepperLayout = findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new ArticleStepperAdapter(getSupportFragmentManager(), this));
    }

    @Override
    public void onCompleted(View completeButton) {

    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int newStepPosition) {

    }

    @Override
    public void onReturn() {

    }
}
