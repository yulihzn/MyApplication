package com.example.yulihe.myapplication.utils.map;

import android.graphics.Point;


/**
 * Created by BanditCat on 2016/10/9.
 */

public class Village extends Area{
    private int cx,cy;//要塞
    private Point[] exits = new Point[4];//4个门,0,1,2,3,上下左右

    public Village() {
    }

    public Village(int x0, int y0) {
        super(x0, y0);
        this.type = MapEditor.VILLAGE;
        init();
    }

    public Village init() {
        //4-11的位置选择中心点
        cx = 4+random.nextInt(8);
        cy = 4+random.nextInt(8);
//        exits[0] = new GridPoint2(0,cy);
//        exits[1] = new GridPoint2(15,cy);
//        exits[2] = new GridPoint2(cx,0);
//        exits[3] = new GridPoint2(cx,15);
        exits[0] = new Point(-1,cy);
        exits[1] = new Point(15+1,cy);
        exits[2] = new Point(cx,-1);
        exits[3] = new Point(cx,15+1);
        int type = SegType.VILLAGE_DIRT.getValue();
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                type = SegType.VILLAGE_DIRT.getValue();
                if(random.nextDouble()<0.15){
                    type = SegType.VILLAGE_HOUSE.getValue();
                }
                //主干道
                if(i==cx||j==cy) {
                    type = SegType.VILLAGE_AVENUE.getValue();
                }
                //要塞
                if(i==cx&&j==cy){
                    type = SegType.VILLAGE_FORTRESSES.getValue();
                }
                arr[i][j]= type;
            }
        }
        return this;
    }

    public Point[] getExits() {
        return exits;
    }

    public int getCx() {
        return cx;
    }

    public int getCy() {
        return cy;
    }

}
