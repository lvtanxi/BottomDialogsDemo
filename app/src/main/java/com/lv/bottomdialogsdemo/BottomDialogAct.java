package com.lv.bottomdialogsdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.github.javiersantos.bottomdialogs.BottomDialog;

/**
 * User: 吕勇
 * Date: 2016-07-29
 * Time: 14:38
 * Description:
 */
public class BottomDialogAct extends AppCompatActivity{
    public static void startBottomDialogAct(Activity activity) {
        activity.startActivity(new Intent(activity, BottomDialogAct.class));
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_bottom_dialog);

    }

    public void showNomal(View view) {
        new BottomDialog.Builder(this)
                .setTitle("Awesome!")
                .setContent("What can we improve? Your feedback is always welcome.")
                .show();
    /*    BottomDialog bottomDialog = new BottomDialog.Builder(this)
                .setTitle("Awesome!")
                .setContent("What can we improve? Your feedback is always welcome.")
                .build();
        bottomDialog.show();*/
    }

    public void showIconDialog(View view) {
        new BottomDialog.Builder(this)
                .setTitle("Awesome!")
                .setContent("What can we improve? Your feedback is always welcome.")
                .setIcon(R.drawable.ic_action_social_share)
                //.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_launcher))
                .show();
    }

    public void showPositiveDialog(View view) {
        new BottomDialog.Builder(this)
                .setTitle("Awesome!")
                .setContent("What can we improve? Your feedback is always welcome.")
                .setPositiveText("OK")
                .onPositive(new BottomDialog.ButtonCallback() {
                    @Override
                    public void onClick(BottomDialog dialog) {
                        Log.d("BottomDialogs", "Do something!");
                    }
                }).show();
    }

    public void showNegativeDialog(View view) {
        new BottomDialog.Builder(this)
                .setTitle("Awesome!")
                .setContent("What can we improve? Your feedback is always welcome.")
                .setNegativeText("Exit")
                .onNegative(new BottomDialog.ButtonCallback() {
                    @Override
                    public void onClick(BottomDialog dialog) {
                        Log.d("BottomDialogs", "Do something!");
                    }
                }).show();
    }

    public void showCustViewDialog(View view) {
        new BottomDialog.Builder(this)
                .setTitle("Awesome!")
                .setContent("What can we improve? Your feedback is always welcome.")
                .setCustomView(LayoutInflater.from(this).inflate(R.layout.activity_main,null))
                .show();
    }

}
