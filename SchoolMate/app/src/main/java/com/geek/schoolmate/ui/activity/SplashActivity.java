package com.geek.schoolmate.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geek.schoolmate.R;
import com.geek.schoolmate.utils.Utils;

public class SplashActivity extends BaseActivity implements View.OnClickListener {
    private ImageView imageViewLogo;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewCopyrights;
    private TextView textViewAppName;
    private LinearLayout linearLayoutControlWrapper;
    private LinearLayout linearLayoutLogoWrapper;
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private String[] perms = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
    private int permsRequestCode = 200;
    private boolean readExternalAccepted;
    private boolean writeExternalAccepted;
    private boolean alreadyCalled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageViewLogo = (ImageView) findViewById(R.id.imageViewLogo);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        textViewCopyrights = (TextView) findViewById(R.id.textViewCopyrights);
        textViewAppName = (TextView) findViewById(R.id.textViewAppName);
        linearLayoutControlWrapper = (LinearLayout) findViewById(R.id.linearLayoutControlWrapper);
        linearLayoutLogoWrapper = (LinearLayout) findViewById(R.id.linearLayoutLogoWrapper);

        buttonLogin.setOnClickListener(this);
        linearLayoutControlWrapper.setAlpha(0.0f);
        textViewCopyrights.setAlpha(0.0f);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!alreadyCalled) {
                    alreadyCalled = true;
                    doPostDelayedTask();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void doPostDelayedTask() {
        linearLayoutLogoWrapper.animate().y(250f).setDuration(500);
        textViewAppName.animate().alpha(0.0f).setDuration(500);
        linearLayoutControlWrapper.animate().alpha(1.0f).setDuration(1000);
        textViewCopyrights.animate().alpha(1.0f).setDuration(1000);
        if (Utils.canMakeSmores()) {
            boolean permsGranted_1 = false;
            boolean permsGranted_2 = false;
            permsGranted_1 = hasPermission(perms[0]);
            permsGranted_2 = hasPermission(perms[1]);
            if (!permsGranted_1 || !permsGranted_2) {
                requestPermissions(perms, permsRequestCode);
            }else{
                readExternalAccepted = writeExternalAccepted = true;
            }
        } else {
            readExternalAccepted = writeExternalAccepted = true;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.buttonLogin:
                if (readExternalAccepted && writeExternalAccepted) {
                    Utils.copyFileFromAssetsToExternalStorage("response_get_blog_list", getAssets());
                    Utils.copyFileFromAssetsToExternalStorage("response_get_task_list", getAssets());
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {
            case 200:
                writeExternalAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                readExternalAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                break;
        }
    }

    private boolean hasPermission(String permission) {
        return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
    }
}