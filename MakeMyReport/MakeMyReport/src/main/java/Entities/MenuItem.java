package Entities;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Vitali on 26.05.13.
 */
public class MenuItem {

    private Bitmap mImage;
    private String mName;
    private String mDesciption;
    private MenuType mMenuType;
    private boolean mVisible;

    public enum MenuType {
        New_Customer,
        New_Report,
        Search,
        Settings,
        About,
    }

    public MenuItem(Bitmap image,
                    String name,
                    String description,
                    MenuType menutype) {

        mImage = image;
        mName = name;
        mDesciption = description;
        mMenuType = menutype;

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

}
