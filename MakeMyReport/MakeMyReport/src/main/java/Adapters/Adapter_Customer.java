package Adapters;

import Entities.Customer;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.project.makemyreport.R;

import java.util.ArrayList;


/**
 * Created by Vitali on 14.07.13.
 */
public class Adapter_Customer extends BaseAdapter {

    private ArrayList<Customer> mCustomers = null;
    private Context mContext;
    private int mLayoutResourceId;

    public Adapter_Customer(Context context, ArrayList<Customer> Customers) {
        mContext = context;
        mCustomers = Customers;
        mLayoutResourceId = R.layout.home_customer;
    }

    @Override
    public int getCount() {
        return mCustomers.size();
    }

    @Override
    public Object getItem(int i) {
        return mCustomers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        MenuItemHolder holder = null;

        final Customer customer = mCustomers.get(i);
        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, viewGroup, false);

            holder = new MenuItemHolder();

            holder.Name = (TextView) row.findViewById(R.id.home_customer_name);
            holder.Image = (ImageView) row.findViewById(R.id.home_customer_image);

            row.setTag(holder);
        } else {
            holder = (MenuItemHolder) row.getTag();
        }

        holder.Name.setText(customer.Name());
        if (customer.Logo() != null) {
            holder.Image.setImageBitmap(customer.Logo());
        }

        return row;
    }

    static class MenuItemHolder {

        public TextView Name;
        public ImageView Image;

    }

}
