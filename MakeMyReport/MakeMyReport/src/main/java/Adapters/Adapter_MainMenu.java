package Adapters;

import Entities.MenuItem;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.project.makemyreport.R;

import java.util.ArrayList;

/**
 * Created by Vitali on 26.05.13.
 */
public class Adapter_MainMenu extends BaseExpandableListAdapter{

    ArrayList<MenuItem> mMenuItems;
    private Context mContext;

    public Adapter_MainMenu(Context context, ArrayList<MenuItem> menuItems) {
        mContext = context;
        mMenuItems = menuItems;
    }

    @Override
    public int getGroupCount() {
        return mMenuItems.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mMenuItems.get(i).SubItems().size();
    }

    @Override
    public Object getGroup(int i) {
        return mMenuItems.get(i);
    }

    @Override
    public Object getChild(int i, int i2) {
        return mMenuItems.get(i).SubItems().get(i2);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i2) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        View row = view;
        MenuItemHolder holder = null;

        final MenuItem menuitem = mMenuItems.get(i);
        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(menuitem.ViewID(), viewGroup, false);

            holder = new MenuItemHolder();

            holder.Image = (ImageView) row.findViewById(R.id.home_menuitem_image);
            holder.Name = (TextView) row.findViewById(R.id.home_menuitem_Name);
            holder.Description = (TextView) row.findViewById(R.id.home_menuitem_Description);

            row.setTag(holder);
        } else {
            holder = (MenuItemHolder) row.getTag();
        }

        holder.Image.setImageBitmap(menuitem.Image());
        holder.Name.setText(menuitem.Name());
        holder.Description.setText(menuitem.Description());

        return row;
    }

    @Override
    public View getChildView(int i, int i2, boolean b, View view, ViewGroup viewGroup) {
        View row = view;
        MenuItemHolder holder = null;

        final MenuItem menuitem = mMenuItems.get(i).SubItems().get(i2);
        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(menuitem.ViewID(), viewGroup, false);

            holder = new MenuItemHolder();

            holder.Image = (ImageView) row.findViewById(R.id.home_menuitem_image);
            holder.Name = (TextView) row.findViewById(R.id.home_menuitem_Name);
            holder.Description = (TextView) row.findViewById(R.id.home_menuitem_Description);

            row.setTag(holder);
        } else {
            holder = (MenuItemHolder) row.getTag();
        }

        holder.Image.setImageBitmap(menuitem.Image());
        holder.Name.setText(menuitem.Name());
        holder.Description.setText(menuitem.Description());

        return row;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    static class MenuItemHolder {
        public ImageView Image;
        public TextView Name;
        public TextView Description;
    }

}
