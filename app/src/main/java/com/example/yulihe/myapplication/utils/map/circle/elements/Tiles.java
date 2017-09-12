package com.example.yulihe.myapplication.utils.map.circle.elements;

/**
 * Created by yuli.he on 2017/9/11.
 */

public class Tiles {
    private static Tiles instance;

    public static Tiles getInstance() {
        if (instance == null) {
            instance = new Tiles();
        }
        return instance;
    }

    public TileType empty;
    public TileType floor;
    public TileType wall;
    public TileType closedoor;
    public TileType opendoor;
    public TileType upstairs;
    public TileType downstairs;
    public TileType water;

    private Tiles() {
        empty = getObstacle("　", 0);
        floor = getPassable("．", 1);
        wall = getObstacle("＃", 2);
        closedoor = getObstacle("Ｎ", 3);
        opendoor = getPassable("Ｏ", 4);
        upstairs = getPassable("Ｕ", 5);
        downstairs = getPassable("Ｄ", 6);
        water = getObstacle("～", 7);
    }

    private TileType getObstacle(String name, int value) {
        return new TileType(name, value, true);
    }

    private TileType getPassable(String name, int value) {
        return new TileType(name, value, false);
    }
    public TileType getTileType(String name,int value){
        return new TileType(name,value,false);
    }
}
