package Controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.project.makemyreport.R;

import Adapters.Adapter_Customer;

/**
 * Created by Kristina on 28.07.13.
 */
public class MyGridView extends GridView {

    Context mContext;

    public MyGridView(Context context) {
        super(context);
        mContext = context;
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        mContext = context;
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

}
