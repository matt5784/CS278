package edu.vu.task;

import org.magnum.soda.proxy.SodaByValue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import edu.vu.task.R;

@SodaByValue
public class TaskItem {

    private int mId;
    private String mDue;
    private String mDescription;
    private boolean mIsChecked;


    public String getDue() {
        return this.mDue;
    }

    public TaskItem setDue(final String due) {
        this.mDue = due;
        return this;
    }

    public String getDescription() {
        return mDescription;
    }

    public TaskItem setDescription(final String description) {
        this.mDescription = description;
        return this;
    }

    public boolean getChecked() {
        return mIsChecked;
    }

    public TaskItem setChecked(final boolean mIsChecked) {
        this.mIsChecked = mIsChecked;
        return this;
    }

    public int getId() {
        return mId;
    }

    public TaskItem setId(final int mId) {
        this.mId = mId;
        return this;
    }

    public View getView(final Context context, final View convertView, final ViewGroup parent) {
        final View ret;

        if (convertView == null || convertView.getId() != R.id.good_row_id) {
            final LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ret = li.inflate(R.layout.row, null);
        } else {
            ret = convertView;
        }

        ((CheckBox) ret.findViewById(R.id.cbox1)).setChecked(mIsChecked);
        ((TextView) ret.findViewById(R.id.tvid)).setText(mId+"");
        ((TextView) ret.findViewById(R.id.tvdesc)).setText(mDescription);
        ((TextView) ret.findViewById(R.id.tvdue)).setText(mDue);

        return ret;
    }
}
