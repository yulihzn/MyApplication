package com.example.yulihe.myapplication.utils.map.circle.elements;


import com.example.yulihe.myapplication.utils.map.circle.section.Rect;
import com.example.yulihe.myapplication.utils.map.circle.section.Section;
import com.example.yulihe.myapplication.utils.map.circle.section.SectionUtils;
import com.example.yulihe.myapplication.utils.map.circle.utils.MathUtils;
import com.example.yulihe.myapplication.utils.map.circle.utils.RandomUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yuli.he on 2017/9/11.
 * 环形地牢
 * 生成策略
 * 房间元素
 */

public class CircleDungeon {
    private int width = 64;
    private int height = 64;
    private TileType[][] maps;
    private List<Section> sections = new ArrayList<>();
    private Path mainPath;
    private Path mainPathWall;
    private List<Path> sectionPaths;
    private List<Path> sectionPathWalls;

    public CircleDungeon(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public CircleDungeon() {

    }

    /**
     * 生成地牢
     */
    public void createDungeon() {
        sections.clear();
        mainPath = null;
        maps = new TileType[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                maps[i][j] = Tiles.getInstance().empty;
            }
        }
        //添加圆路径
        addCirclePath(width / 2, height / 2, RandomUtils.nextInt(width / 4, width / 2 - 1));
        //添加房间
        for (int i = 0; i < 500; i++) {
            addSection(RandomUtils.nextInt(width), RandomUtils.nextInt(height));
        }
        //修改和圆路径相交的房间
        int index = 1;
        for (Point p : mainPath.getList()) {
            for (Section sec : sections) {
                if (sec.contains(p.x, p.y)) {
                    if (sec.getIndex() == 0) {
                        sec.setIndex(index++);
                    }
                    if (sec.isSide(p.x, p.y)) {
                        sec.setExitTileType(p.x, p.y);
                        changeSectionCorner(sec,p.x,p.y);
                    }
                }
            }
        }

        //遍历房间修改属性
        for (Section sec : sections) {
            //修改主圈房间
            if (sec.getIndex() > 0) {
                sec.updateArea(sec.left + sec.width() / 2, sec.top + sec.height() / 2, Tiles.getInstance().getTileType(Tiles.ToSBC("" + sec.getIndex()), 0));

            }else{
                //修改余下房间
            }
        }
        //放置房间和圆圈然后生成通路
        putPathIntoMap(mainPathWall);
        putPathIntoMap(mainPath);
        for (Section sec : sections) {
            putSectionIntoMap(sec);
        }
        addSectionPath();
        Iterator<Section> it = sections.iterator();
        while(it.hasNext()){
            Section sec = it.next();
            //去掉封闭房间
            if(sec.getExitCount() == 0){
                it.remove();

            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                maps[i][j] = Tiles.getInstance().empty;
            }
        }

        putPathIntoMap(mainPathWall);
        putPathIntoMap(mainPath);
        for(Path path : sectionPaths){
            Path wall = new Path(getPathWallPoints(path),Tiles.getInstance().corridorwall);
            putPathIntoMap(wall);
            putPathIntoMap(path);
        }

        for (Section sec : sections) {
            putSectionIntoMap(sec);
        }

    }


    /**
     * 生成房间之间的通路
     * 1.选取非主圈房间四条边的一个点
     * 点满足条件：1.不是角落,2两边都是障碍物
     * 2.从这个点沿着边的垂直方向往外走，没有障碍且遇到其它主圈房间的边，则形成通路
     */
    private void addSectionPath() {
        sectionPaths = new ArrayList<>();
        for (Section sec : sections) {
            for (int dir = 0; dir < 4; dir++) {
                Path path = getSectionPath(sec,dir);
                if(path != null){
                    sectionPaths.add(path);
                }
            }
//            Path path = getSectionPath(sec,3);
//            if(path != null){
//                sectionPaths.add(path);
//            }

        }
    }
    private Path getSectionPath(Section sec,int type){
        if(sec.getIndex() > 0){
            return null;
        }
        //去掉两头
        int x = RandomUtils.nextInt(sec.left+1,sec.right-2);
        int y = RandomUtils.nextInt(sec.top+1,sec.bottom-2);
        Path path = new Path();
        boolean isPath = false;
        int i=0;
        boolean flag=true;
        int offset=0;
        int tempx = x;
        int tempy = i;
        int count = 0;
        switch (type){
            case 0:i = sec.left;offset = -1;break;
            case 1:i = sec.top;offset = -1;break;
            case 2:i = sec.right-1;offset = 1;break;
            case 3:i = sec.bottom-1;offset = 1;break;
            default:break;
        }
        while (flag){

            switch (type){
                case 0:flag = i > 0;tempx= i;tempy=y;break;
                case 1:flag = i > 0;tempx= x;tempy=i;break;
                case 2:flag = i < width-1;tempx= i;tempy=y;break;
                case 3:flag = i < height-1;tempx= x;tempy=i;break;
                default:break;
            }
            i+=offset;
            if(!flag||tempx<0||tempy<0){
                break;
            }
            path.getList().add(new Point(tempx,tempy));
            if(maps[tempx][tempy].getValue() == Tiles.getInstance().roomwall.getValue()){
                if(count != 0){
                    isPath = true;
                    sec.setExitTileType(path.getList().get(0).x,path.getList().get(0).y);
                    for(Section secOther : sections){
                        if(secOther.contains(tempx,tempy)){
                            secOther.setExitTileType(tempx,tempy);
                        }
                    }
                    break;
                }
            }
            int value = maps[tempx][tempy].getValue();
            if(value == Tiles.getInstance().roomcorner.getValue()
                    ||value == Tiles.getInstance().corridorwall.getValue()
                    ){
                path.getList().remove(path.getList().size()-1);
                isPath = false;
                break;
            }
            if(count++ > 16){
                isPath = false;
                break;
            }

        }
        if(!isPath){
            path = null;
        }
        return path;
    }

    /**
     * 如果相交的是角落，那么选择角落和它旁边的块为地板
     * @param sec
     * @param x
     * @param y
     */
    private void changeSectionCorner(Section sec,int x,int y){
        if(sec.isCorner(x,y)){
            sec.updateArea(x, y, Tiles.getInstance().roomfloor);
            int offsetX = (x == sec.left)?1:-1;
            int offsetY = (y == sec.top)?1:-1;
            if(sec.getArea()[x-sec.left+offsetX][y-sec.top].isObstacle()&&sec.getArea()[x-sec.left][y-sec.top+offsetY].isObstacle()){
                sec.getArea()[x-sec.left+offsetX][y-sec.top] = Tiles.getInstance().roomfloor;
                sec.getArea()[x-sec.left][y-sec.top+offsetY] = Tiles.getInstance().roomfloor;
            }
        }
    }

    /**
     * 添加圆形路径和对应墙体
     * @param x0
     * @param y0
     * @param r
     */
    private void addCirclePath(int x0, int y0, int r) {
        mainPath = new Path(MathUtils.getCircleDungeonPoints(x0, y0, r), Tiles.getInstance().corridorfloor);
        mainPathWall = new Path(getPathWallPoints(mainPath), Tiles.getInstance().corridorwall);
    }
    private List<Point> getPathWallPoints(Path main){
        List<Point> walls = new ArrayList<>();
        for(Point p : main.getList()){
            walls.add(new Point(p.x,p.y+1));
            walls.add(new Point(p.x,p.y-1));
            walls.add(new Point(p.x+1,p.y));
            walls.add(new Point(p.x+1,p.y+1));
            walls.add(new Point(p.x+1,p.y-1));
            walls.add(new Point(p.x-1,p.y));
            walls.add(new Point(p.x-1,p.y+1));
            walls.add(new Point(p.x-1,p.y-1));
        }
        return walls;
    }
    private void putTileIntoMap(int x,int y,TileType tileType){
        if (x >= 0 && x < width && y >= 0 && y < height) {
            maps[x][y] = tileType;
        }
    }
    private void putPathIntoMap(Path path) {
        for (int i = 0; i < path.size(); i++) {
            Point p = path.getList().get(i);
            int x = p.x;
            int y = p.y;
            if (x >= 0 && x < width && y >= 0 && y < height) {
//                maps[x][y] = Tiles.getInstance().getTileType("z"+i,0);
//                maps[x][y] = Tiles.getInstance().wall;
                maps[x][y] = path.getTileType();
            }
        }
    }

    private void addSection(int left, int top) {
        int right = left + RandomUtils.nextInt(8, 16);
        int bottom = top + RandomUtils.nextInt(8, 16);
        if (right >= width || bottom >= height) {
            return;
        }
        for (Section sec : sections) {
            if (sec.isIntersect(sec, new Rect(left, top, right, bottom))) {
                return;
            }
        }
        Section section = SectionUtils.getRestSection(left, top, right, bottom);
        sections.add(section);
    }


    private void putSectionIntoMap(Section section) {
        for (int j = 0; j < section.height(); j++) {
            for (int i = 0; i < section.width(); i++) {
                maps[section.left + i][section.top + j] = section.getArea()[i][j];
            }
        }
    }

    public String getDisPlay() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                stringBuilder.append(maps[i][j].getName());
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
