package com.example.yulihe.myapplication.utils.map.circle.section;

import com.example.yulihe.myapplication.utils.map.circle.elements.TileType;
import com.example.yulihe.myapplication.utils.map.circle.elements.Tiles;

/**
 * Created by yuli.he on 2017/9/11.
 * 区域有一个二维数组
 */

public abstract class Section extends Rect implements Comparable<Section>{
    protected TileType[][] area;
    public int type;
    public int index;
    public int exitCount;

    public Section(int left, int top, int right, int bottom, int type) {
        super(left, top, right, bottom);
        area = new TileType[width()][height()];
        this.type = type;
        updateArea();
    }

    public TileType[][] getArea() {
        return area;
    }

    public void setArea(TileType[][] area) {
        this.area = area;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public abstract void updateArea();

    public void updateArea(int x, int y, TileType tileType) {
        if(x>=left&&x<right&&y>=top&&y<bottom){
            area[x-left][y-top] = tileType;
        }
    }

    /**
     * 判断点是否在边界
     * @param x
     * @param y
     * @return
     */
    public boolean isSide(int x,int y){
        return (x==left||y==top||x==right-1||y==bottom-1);
    }

    /**
     * 是否是角落
     * @param x
     * @param y
     * @return
     */
    public boolean isCorner(int x,int y){
        return (x==left&&y==top)||(x==left&&y==bottom-1)||(x==right-1&&y==top)||(x==right-1&&y==bottom-1);
    }

    public void setExitTileType(int x,int y){
        exitCount++;
        updateArea(x,y,Tiles.tile().opendoor);
    }

    public int getExitCount() {
        return exitCount;
    }
}
