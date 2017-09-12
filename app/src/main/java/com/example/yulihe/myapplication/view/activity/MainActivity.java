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
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView1);
//        String room = "Dark Fall";
//        SpannableString ss = new SpannableString("press enter into "+room+" quest.");
//        ss.setSpan(new NoLineClickSpan(),ss.toString().indexOf(room),ss.toString().indexOf(room)+room.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        textView.setText(ss);
//        textView.setMovementMethod(LinkMovementMethod.getInstance());
//        imageView = (ImageView)findViewById(R.id.imageView);
//        final Avatar avatar = new Avatar(imageView);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                avatar.upDateAvatar();
//            }
//        });
        imageView = (ImageView)findViewById(R.id.imageView);
        final StringBuilder stringBuilder = new StringBuilder();
        final MapEditor mapEditor = new MapEditor(MapEditor.SEED);
        final Dungeon dungeon = new Dungeon();
        final CircleDungeon circleDungeon = new CircleDungeon();

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mapEditor.create();
//                textView.setText(mapEditor.getArrayString());
//                dungeon.createDungeon(32,32,5000);
//                textView.setText(dungeon.showDungeon());
                circleDungeon.createDungeon();
                String str = circleDungeon.getDisPlay();
                textView.setText(str);
                Log.i("test",str);

            }
        });
    }



}
