package com.cds.foodordering.model.utils;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cds.foodordering.R;
import com.cds.foodordering.views.activities.AppUpdateDialog;

import org.apache.commons.lang3.StringUtils;


/**
 * Created by Cogniti Digital Solutions.
 */

public class BaseActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    View parentLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentLayout = findViewById(android.R.id.content);
        showFirstSnack(ConnectivityReceiver.isConnected());
    }

    public void initToolbar(Toolbar toolbar, String title, boolean isBackEnabled, boolean isDoneEnabled) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView titleTV = (TextView) toolbar.findViewById(R.id.toolbar_title);
        TextView doneTV = (TextView) toolbar.findViewById(R.id.toolbar_done);
        TextView backTV = (TextView) toolbar.findViewById(R.id.toolbar_back);
        titleTV.setText(StringUtils.abbreviate(title, 20));
        doneTV.setVisibility(isDoneEnabled ? View.VISIBLE : View.GONE);
        backTV.setVisibility(isBackEnabled ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    private void showSnack(boolean isConnected) {
        Log.w("Success", "Called Show snack");
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar
                .make(parentLayout, message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    private void showFirstSnack(boolean isConnected) {
        Log.w("Success", "Called Show snackgfdgfg");
        if (!isConnected) {
            String  message = "Sorry! Not connected to internet";
            int  color = Color.RED;
            Snackbar snackbar = Snackbar
                    .make(parentLayout, message, Snackbar.LENGTH_LONG);

            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(color);
            snackbar.show();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        AppController.getInstance().setConnectivityListener(this);
    }

    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar
                .make(parentLayout, message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public void callUpdateDialogActivity(){
        Intent updateIntent = new Intent(this, AppUpdateDialog.class);
        startActivity(updateIntent);
    }

}
