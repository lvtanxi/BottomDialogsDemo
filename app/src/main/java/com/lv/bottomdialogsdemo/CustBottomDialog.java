package com.lv.bottomdialogsdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lv.bottomdialogsdemo.selectpop.SelectPopupWindow;

/**
 * User: 吕勇
 * Date: 2016-07-29
 * Time: 16:19
 * Description:
 */
public class CustBottomDialog extends AppCompatActivity{
    public static void startCustBottomDialog(Activity activity) {
        activity.startActivity(new Intent(activity, CustBottomDialog.class));
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cust_dialog);
    }

    public void oneItem(View view) {
        new SelectPopupWindow<>(this)
                .setTitle("测试")
                .addDefExtendItems("一个item").show(view);
    }
    public void twoItem(View view) {
        new SelectPopupWindow<>(this)
                .addDefExtendItems("两个item","两个item").show(view);
    }
}
