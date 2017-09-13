package com.example.yulihe.myapplication.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yulihe.myapplication.R;
import com.example.yulihe.myapplication.utils.map.Dungeon;
import com.example.yulihe.myapplication.utils.map.MapEditor;
import com.example.yulihe.myapplication.utils.map.circle.elements.CircleDungeon;

public class MainActivity extends AppCompatActivity{
    private TextView textView;
    private static final int MAXSIZE = 12;
    private int textSize = MAXSIZE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView1);
        final CircleDungeon circleDungeon = new CircleDungeon();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleDungeon.createDungeon();
                String str = circleDungeon.getDisPlay();
                textView.setText(str);

            }
        });
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextSize(++textSize);

            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextSize(--textSize);

            }
        });
    }



}
