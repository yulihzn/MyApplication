package com.example.yulihe.myapplication.utils.map;

import android.content.Context;
import android.content.res.Resources;

import com.example.yulihe.myapplication.R;

import java.lang.reflect.Field;

/**
 * Created by yuli.he on 2017/6/15.
 */

public class Utils {
    public static int getResourceByReflect(String imageName){
        Class mipmap  = R.mipmap.class;
        Field field = null;
        int r_id ;
        try {
            field = mipmap.getField(imageName);
            r_id = field.getInt(field.getName());
        } catch (Exception e) {
            r_id=R.mipmap.ic_launcher;
        }
        return r_id;
    }
    public static int getMipmapIdByName(Context context,String name){
        Resources resources = context.getResources();
        return resources.getIdentifier(name,"mipmap",context.getPackageName());
    }
}
