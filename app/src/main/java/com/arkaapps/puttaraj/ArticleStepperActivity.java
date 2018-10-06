package com.arkaapps.puttaraj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arkaapps.puttaraj.Adapters.ArticleStepperAdapter;
import com.stepstone.stepper.StepperLayout;

public class ArticleStepperActivity extends AppCompatActivity {

    private StepperLayout mStepperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_stepper);
        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new ArticleStepperAdapter(getSupportFragmentManager(), this));
    }
}
