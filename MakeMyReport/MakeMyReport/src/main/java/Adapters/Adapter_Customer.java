package Adapters;

import DAL.DL;
import Entities.Customer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.makemyreport.Activity_EditReport;
import com.project.makemyreport.R;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;


/**
 * Created by Vitali on 14.07.13.
 */
public class Adapter_Customer extends BaseAdapter implements Customer.CustomerEventgenerator{

    private ArrayList<Customer> mCustomers = null;
    private Context mContext;
    private int mLayoutResourceId;

    private ArrayList<Customer.EditCustomerListener> EditCustomerListeners;
    private ArrayList<Customer.DeleteCustomerListener> DeleteCustomerListeners;

    public Adapter_Customer(Context context, ArrayList<Customer> Customers) {
        mContext = context;
        mCustomers = Customers;
        mLayoutResourceId = R.layout.home_customer;
        EditCustomerListeners = new ArrayList<Customer.EditCustomerListener>();
        DeleteCustomerListeners = new ArrayList<Customer.DeleteCustomerListener>();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View row = view;
        MenuItemHolder holder = null;

        final Customer customer = mCustomers.get(i);

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, viewGroup, false);

            holder = new MenuItemHolder();

            holder.Name = (TextView) row.findViewById(R.id.home_customer_name);
            holder.Description = (TextView) row.findViewById(R.id.home_customer_description);
            holder.Image = (ImageView) row.findViewById(R.id.home_customer_image);
            holder.ShowOptions = (ImageView) row.findViewById(R.id.home_customer_showoptions);


            final View EditCustomer = (View)row.findViewById(R.id.customer_button_edit);
            holder.EditButton = EditCustomer;
            EditCustomer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditCustomerListeners.get(0).CustomerEdit(new Customer.Adapter_Customer_EventArgs(this,customer.CustomerID()));
                }
            });

            final View DeleteButton = (View)row.findViewById(R.id.customer_button_delete);
            holder.DeleteButton = DeleteButton;
            DeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mCustomers.remove(i);
                    DeleteCustomerListeners.get(0).CustomerDelete(new Customer.Adapter_Customer_EventArgs(this,customer.CustomerID()));


                    notifyDataSetInvalidated();

                }
            });


            final LinearLayout Options = (LinearLayout) row.findViewById(R.id.home_customer_options);
            holder.Options = Options;
            holder.ShowOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (Options.getVisibility() == View.GONE){
                        Options.setVisibility(View.VISIBLE);
                    } else{
                        Options.setVisibility(View.GONE);
                    }

                }
            });

            row.setTag(holder);

        } else {
            holder = (MenuItemHolder) row.getTag();
        }
        holder.Description.setText(customer.Description());
        holder.Name.setText(customer.Name());
        if (customer.Logo() != null) {
            holder.Image.setImageBitmap(customer.Logo());
        }


        holder.Options.setVisibility(View.GONE);
        return row;
    }

    @Override
    public void addEditCustomerListener(Customer.EditCustomerListener listener) {
        EditCustomerListeners.add(listener);
    }

    @Override
    public void addDeleteCustomerListener(Customer.DeleteCustomerListener listener) {
        DeleteCustomerListeners.add(listener);
    }


    static class MenuItemHolder {

        public TextView Name;
        public TextView Description;
        public ImageView Image;
        public ImageView ShowOptions;
        public LinearLayout Options;

        public View DeleteButton;
        public View EditButton;

    }

}
