package Adapters;

import Entities.Report;
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
public class Adapter_Report extends BaseAdapter {

    private ArrayList<Report> mReports = null;
    private Context mContext;
    private int mLayoutResourceId;

    public Adapter_Report(Context context, ArrayList<Report> Reports) {
        mContext = context;
        mReports = Reports;
        mLayoutResourceId = R.layout.reports_report;
    }

    @Override
    public int getCount() {
        return mReports.size();
    }

    @Override
    public Object getItem(int i) {
        return mReports.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        MenuItemHolder holder = null;

        final Report report = mReports.get(i);
        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, viewGroup, false);

            holder = new MenuItemHolder();

            holder.Name = (TextView) row.findViewById(R.id.reports_reportname);

            row.setTag(holder);
        } else {
            holder = (MenuItemHolder) row.getTag();
        }

        holder.Name.setText(report.Name());

        return row;
    }

    static class MenuItemHolder {

        public TextView Name;

    }

}
