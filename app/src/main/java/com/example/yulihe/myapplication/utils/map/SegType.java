package com.example.yulihe.myapplication.utils.map;

/**
 * Created by BanditCat on 2016/12/22.
 * 大地图片段类型，目前只有基础类型，具体外观变化以后加入
 */

public enum SegType {
    /**
     * 通用
     **/
    TRANS(0,"　"),
    SHADOW(1,"　"),
    /**
     * 野外
     **/
    WILD_DIRT(2,"．"),//泥土
    WILD_WATER(3,"～"),//水
    WILD_GRASS(4,"草"),//草地
    WILD_STONE(5,"石"),//石头
    WILD_TREE(6,"木"),//树
    WILD_MAGMA(7,"ｘ"),//岩浆
    WILD_BUSH(8,"灌"),//灌木丛
    WILD_CAVE(9,"洞"),//洞穴
    WILD_CABIN(10,"屋"),//小屋
    WILD_BRIDGE(11,"＋"),//桥
    WILD_ROAD(12,"＃"),//小路
    WILD_STREET(13,"＝"),//干道
    /**
     * 城堡
     **/
    CASTLE_DIRT(14,"．"),//泥土
    CASTLE_WATER(15,"～"),//水
    CASTLE_GRASS(16,"草"),//草地
    CASTLE_STONE(17,"石"),//石头
    CASTLE_TREE(18,"木"),//树
    CASTLE_TOMB(19,"墓"),//墓地
    CASTLE_BUSH(20,"灌"),//灌木丛
    CASTLE_WALL(21,"城"),//城墙
    CASTLE_BATTLEMENT(22,"垛"),//城垛
    CASTLE_GATE(23,"门"),//城门
    CASTLE_BRIDGE(24,"Ｄ"),//城桥
    CASTLE_ROAD(25,"Ｅ"),//小路
    CASTLE_STREET(26,"路"),//干道
    CASTLE_AVENUE(27,"＝"),//主干道
    CASTLE_HOUSE(28,"房"),//民房
    CASTLE_FORTRESSES(29,"塞"),//要塞
    CASTLE_GUILD_WARRIOR(30,"Ｋ"),//战士公会
    CASTLE_GUILD_MAGIC(31,"Ｌ"),//法师公会
    CASTLE_GUILD_RANGER(32,"Ｍ"),//游侠公会
    CASTLE_GUILD_MINISTER(33,"Ｎ"),//占星家公会
    CASTLE_STORE(34,"Ｐ"),//杂货店
    CASTLE_SMITHY(35,"Ｑ"),//铁匠铺
    CASTLE_INN(36,"Ｓ"),//酒馆
    CASTLE_MARKET(37,"Ｔ"),//集市
    CASTLE_GUARD_WATER(38,"河"),//护城河
    /**
     * 村庄
     **/
    VILLAGE_DIRT(39,"｀"),//泥土
    VILLAGE_WATER(40,"－"),//水
    VILLAGE_GRASS(41,"草"),//草地
    VILLAGE_STONE(42,"石"),//石头
    VILLAGE_TREE(43,"木"),//树
    VILLAGE_TOMB(44,"墓"),//墓地
    VILLAGE_BUSH(45,"灌"),//灌木丛
    VILLAGE_WALL(46,"城"),//城墙
    VILLAGE_FARM(47,"农"),//农场
    VILLAGE_BATTLEMENT(48,"垛"),//城垛
    VILLAGE_GATE(49,"门"),//城门
    VILLAGE_BRIDGE(50,"桥"),//桥
    VILLAGE_ROAD(51,"ｔ"),//小路
    VILLAGE_STREET(52,"路"),//干道
    VILLAGE_AVENUE(53,"＝"),//主干道
    VILLAGE_HOUSE(54,"房"),//民房
    VILLAGE_FORTRESSES(55,"塞"),//要塞
    VILLAGE_GUILD_WARRIOR(56,"Ｋ"),//战士公会
    VILLAGE_GUILD_MAGIC(57,"Ｌ"),//法师公会
    VILLAGE_GUILD_RANGER(58,"Ｍ"),//游侠公会
    VILLAGE_GUILD_MINISTER(59,"Ｎ"),//占星家公会
    VILLAGE_STORE(60,"Ｐ"),//杂货店
    VILLAGE_SMITHY(61,"Ｑ"),//铁匠铺
    VILLAGE_INN(62,"Ｓ"),//酒馆
    VILLAGE_MARKET(63,"Ｔ"),//集市
    VILLAGE_FIELD(64,"ａ"),//田

    /**
     * 地牢
     **/
    DUNGEON(65,"");
    

    private int value;
    private String name;

    SegType(int value, String name) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getValue() {
        return value;
    }
    public static SegType getType(int value){
        for (SegType segType:values()){
            if(value==segType.getValue()){
                return segType;
            }
        }
        return null;
    }
    
}
