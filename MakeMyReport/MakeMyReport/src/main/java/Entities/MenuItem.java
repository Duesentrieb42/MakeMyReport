package Entities;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Created by Vitali on 26.05.13.
 */
public class MenuItem {

    private Bitmap mImage;
    private String mName;
    private int mViewID;
    private String mDesciption;
    private MenuType mMenuType;
    private boolean mVisible;
    private ArrayList<MenuItem> mSubItems;

    public enum MenuType {
        New_Customer,
        New_Report,
        Delete_Customer,
        Delete_Report,
        Search,
        Settings,
        About,
    }

    public MenuItem(Bitmap image,
                    String name,
                    String description,
                    int viewid,
                    MenuType menutype,
                    boolean visible) {

        mImage = image;
        mName = name;
        mDesciption = description;
        mMenuType = menutype;
        mVisible = visible;
        mViewID = viewid;
        mSubItems = new ArrayList<MenuItem>();
    }

    public Bitmap Image() {
        return mImage;
    }

    public String Name() {
        return mName;
    }

    public String Description() {
        return mDesciption;
    }

    public MenuType MenuType() {
        return mMenuType;
    }

    public int ViewID(){
        return mViewID;
    }

    public boolean Visible(){
        return mVisible;
    }

    public ArrayList<MenuItem> SubItems(){
        return mSubItems;
    }

}
