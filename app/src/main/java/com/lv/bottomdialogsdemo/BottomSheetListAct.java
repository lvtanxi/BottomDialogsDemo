package com.lv.bottomdialogsdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.cocosw.query.CocoQuery;

/**
 * User: 吕勇
 * Date: 2016-07-29
 * Time: 15:04
 * Description:
 */
public class BottomSheetListAct extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static void startBottomSheetListAct(Activity activity) {
        activity.startActivity(new Intent(activity, BottomSheetListAct.class));
    }

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_main);
        CocoQuery q = new CocoQuery(this);

        String[] items = new String[]{"From Xml", "Without Icon", "Dark Theme", "Grid", "Style", "Style from Theme", "ShareAction", "FullScreen", "Menu Manipulate", "HeaderLayout"};
        q.id(R.id.listView)
                .adapter(adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, items))
                .itemClicked(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, BottomSheetAct.class).setFlags(position).putExtra("title", adapter.getItem(position));
        if (position == 5) {
            intent.putExtra("style", true);
        }
        startActivity(intent);
    }

}

