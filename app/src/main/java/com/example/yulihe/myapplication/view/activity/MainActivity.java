package com.example.yulihe.myapplication.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.example.yulihe.myapplication.R;
import com.example.yulihe.myapplication.utils.map.circle.elements.AStarMapUtils;
import com.example.yulihe.myapplication.utils.map.circle.elements.CircleDungeon;

public class MainActivity extends AppCompatActivity{
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private static final int MAXSIZE = 8;
    private int textSize = MAXSIZE;
    private AStarMapUtils amu;
    private int currentIndex = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView1);
        textView1 = (TextView)findViewById(R.id.textView2);
        textView2 = (TextView)findViewById(R.id.textView3);
        textView.setTextSize(textSize);
        textView1.setTextSize(textSize);
        textView2.setTextSize(textSize);
        textView.setText("");
        textView1.setText("");
        textView2.setText("");
        final CircleDungeon circleDungeon = new CircleDungeon();
        amu = new AStarMapUtils(circleDungeon);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reBuildDungeon(circleDungeon);
                String str = circleDungeon.getDisPlay();
                textView.setText(str);
                textView1.setText(amu.mapStr());
                textView2.setText(amu.pathStr());
                currentIndex =1;


            }
        });
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++textSize;
                textView.setTextSize(textSize);
                textView1.setTextSize(textSize);
                textView2.setTextSize(textSize);

            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --textSize;
                textView.setTextSize(textSize);
                textView1.setTextSize(textSize);
                textView2.setTextSize(textSize);

            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int next = currentIndex+1;
                if(next>circleDungeon.getMainSectionsIndex()){
                    next = 1;
                }
                amu.find(currentIndex,next);
                textView2.setText(amu.pathStr());
                currentIndex++;
                if(currentIndex>circleDungeon.getMainSectionsIndex()){
                    currentIndex = 1;
                }
            }
        });
    }

    private void reBuildDungeon(CircleDungeon circleDungeon){
        circleDungeon.createDungeon();
        amu.upDateMap();

    }

}
