package Adapters;

import Entities.MenuItem;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.project.makemyreport.R;

/**
 * Created by Vitali on 26.05.13.
 */
public class Adapter_MainMenu extends BaseAdapter {

    private MenuItem mMenuItems[] = null;
    private Context mContext;
    private int mLayoutResourceId;

    public Adapter_MainMenu(Context context, MenuItem menuItems[]) {
        mContext = context;
        mMenuItems = menuItems;
        mLayoutResourceId = R.layout.home_menuitem;
    }

    @Override
    public int getCount() {
        return mMenuItems.length;
    }

    @Override
    public Object getItem(int i) {
        return mMenuItems[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        MenuItemHolder holder = null;

        final MenuItem menuitem = mMenuItems[i];
        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, viewGroup, false);

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

    static class MenuItemHolder {
        public ImageView Image;
        public TextView Name;
        public TextView Description;
    }

}
