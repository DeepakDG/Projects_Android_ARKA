package com.arkaapps.puttaraj;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arkaapps.puttaraj.BaseConfig.NotiConstant;
import com.arkaapps.puttaraj.util.NotificationUtil;
import com.arkaapps.puttaraj.util.TypeWriter;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by DeepakGanachari on 3/23/2018.
 */

public class Splash extends Activity {

    private static final String TAG = NotificationFCM.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private TextView txtRegId, txtMessage;
    private char mState = 0;
    private View normal;
    private int mCount = 1;
//    private CircleImageView mSplash1, mSplash2, mSplash3, mSplash4, mSplash5, mSplash6;
    private Runnable MyRun;
    private Handler mHandler;
    private TypeWriter mTvHeading;
    private ImageView mImagePuttarajBg;
    private Animation animSlide;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        mTvHeading = (TypeWriter) findViewById(R.id.splashappname);
        mImagePuttarajBg=(ImageView) findViewById(R.id.image_puttaraj_bg);
        mTvHeading.setCharacterDelay(150);
        mTvHeading.animateText("ಡಾ.ಗಾನಯೋಗಿ ಪುಟ್ಟರಾಜ ಗವಾಯಿ");
        animSlide = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        mImagePuttarajBg.startAnimation(animSlide);

//        mSplash1 = (CircleImageView) findViewById(R.id.splash_anim_1);
//        mSplash2 = (CircleImageView) findViewById(R.id.splash_anim_2);
//        mSplash3 = (CircleImageView) findViewById(R.id.splash_anim_3);
//        mSplash4 = (CircleImageView) findViewById(R.id.splash_anim_4);
//        mSplash5 = (CircleImageView) findViewById(R.id.splash_anim_5);
//        mSplash6 = (CircleImageView) findViewById(R.id.splash_anim_6);
//        final ScheduledExecutorService mSchedulerThread = Executors.newScheduledThreadPool(1);
//
//        mSchedulerThread.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d("Count", "Count" + mCount);
//                        if (mCount == 1) {
//                            mSplash1.setVisibility(View.VISIBLE);
//                            mCount++;
//                        } else if (mCount == 2) {
//                            mSplash2.setVisibility(View.VISIBLE);
//                            mCount++;
//                        } else if (mCount == 3) {
//                            mSplash3.setVisibility(View.VISIBLE);
//                            mCount++;
//                        } else if (mCount == 4) {
//                            mSplash4.setVisibility(View.VISIBLE);
//                            mCount++;
//                        } else if (mCount == 5) {
//                            mSplash5.setVisibility(View.VISIBLE);
//                            mCount++;
//                        } else if (mCount == 6) {
//                            mSplash6.setVisibility(View.VISIBLE);
//                            mFrameLayout.setVisibility(View.GONE);
//                            mCount++;
//                        } else if (mCount == 7) {
//                            startActivity(new Intent(Splash.this, HomeScreen.class));
//                            mSchedulerThread.shutdown();
//                            finish();
//                        } else {
//
//                        }
//                    }
//                });
//            }
//        }, 0L, 1L, TimeUnit.SECONDS);


        txtRegId = (TextView) findViewById(R.id.txt_reg_id);
        txtMessage = (TextView) findViewById(R.id.txt_push_message);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(NotiConstant.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(NotiConstant.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(NotiConstant.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                    txtMessage.setText(message);
                }
            }
        };

        displayFirebaseRegId();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(Splash.this, HomeScreen.class));
                finish();
            }
        }, 3000);
    }

    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(NotiConstant.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId))
            txtRegId.setText("Firebase Reg Id: " + regId);
        else
            txtRegId.setText("Firebase Reg Id is not received yet!");
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(NotiConstant.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(NotiConstant.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtil.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

}

