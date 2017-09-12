package com.example.yulihe.myapplication.utils.map.circle.elements;


import com.example.yulihe.myapplication.utils.map.circle.section.Rect;
import com.example.yulihe.myapplication.utils.map.circle.section.RestSection;
import com.example.yulihe.myapplication.utils.map.circle.section.Section;
import com.example.yulihe.myapplication.utils.map.circle.section.SectionUtils;
import com.example.yulihe.myapplication.utils.map.circle.utils.MathUtils;
import com.example.yulihe.myapplication.utils.map.circle.utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuli.he on 2017/9/11.
 * 环形地牢
 * 生成策略
 * 房间元素
 */

public class CircleDungeon {
    private int width = 32;
    private int height = 32;
    private TileType[][] maps;
    private List<Section> sections = new ArrayList<>();
    private Path mainPath;
    private Path mainPathWall;

    public CircleDungeon(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public CircleDungeon() {

    }

    public void createDungeon() {
        sections.clear();
        mainPath = null;
        maps = new TileType[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                maps[i][j] = Tiles.getInstance().empty;
            }
        }
        //加圆
        addPath(width / 2, height / 2, RandomUtils.nextInt(width / 4, width / 2 - 1));
        //加房间
        for (int i = 0; i < 500; i++) {
            addSection(RandomUtils.nextInt(width), RandomUtils.nextInt(height));
        }
        //替换圆路径的房间
        int index = 1;
        for (Point p : mainPath.getList()) {
            for (Section sec : sections) {
                if (sec.contains(p.x, p.y)) {
                    if (sec.getIndex() == 0) {
                        sec.setIndex(index++);
                    }
                    if (sec.isSide(p.x, p.y)) {
                        sec.updateArea(p.x, p.y, Tiles.getInstance().opendoor);
                    }
                }
            }
        }
        for (Section sec : sections) {

            if (sec.getIndex() > 0) {
                sec.updateArea(sec.left + sec.width() / 2, sec.top + sec.height() / 2, Tiles.getInstance().getTileType(" " + sec.getIndex(), 0));
            }
//            putSectionIntoMap(sec);
            System.out.println(sec.getType() + "," + sec.getIndex());
        }

    }

    private void addPath(int x0, int y0, int r) {
        mainPath = new Path(MathUtils.getCircleDungeonPoints(x0, y0, r), Tiles.getInstance().floor);
        List<Point> walls = new ArrayList<>();


        mainPathWall = new Path(walls, Tiles.getInstance().wall);
        putPathIntoMap(mainPathWall);
        putPathIntoMap(mainPath);

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
        int right = left + RandomUtils.nextInt(4, 8);
        int bottom = top + RandomUtils.nextInt(4, 8);
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
