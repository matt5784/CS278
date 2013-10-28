package edu.vu.task;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.magnum.soda.Soda;
import org.magnum.soda.android.AndroidSoda;
import org.magnum.soda.android.AndroidSodaConnectionException;
import org.magnum.soda.android.AndroidSodaListener;
import org.magnum.soda.android.SodaInvokeInUi;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends ListActivity implements AndroidSodaListener{

    private static final String TAG = "TaskListMainActivity";

    private final ArrayList<TaskItem> mTasks = new ArrayList<TaskItem>();
    private Soda mSoda;
    private TaskAdapter mAdapter;

    public static String mHost;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Properties props = new Properties();

        try {
            final InputStream rawResource = getResources().openRawResource(R.raw.connection);
            props.load(rawResource);

            mHost=props.getProperty("host");
        }
        catch(final IOException e) {
            Log.e("Property File not found",e.getLocalizedMessage());
        }


        final TaskItem v = new TaskItem().setChecked(true).setDescription("Describe me!").setDue("TOMORROW").setId(5);
        mTasks.add(v);

        updateListSize(mTasks.size());

        final Button add = (Button) findViewById(R.id.button1);
        add.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                final EditText desc = (EditText) findViewById(R.id.textView1);

                final TaskItem temp = new TaskItem();

                temp.setDescription(desc.getText().toString());
                //desc.clearComposingText();
                desc.setText("");
                temp.setChecked(false);
                temp.setDue("Tomorrow");

                mTasks.add(temp);

                updateListSize(mTasks.size());
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private void onUpdate() {
        AndroidSoda.async(new Runnable() {
            @Override
            public void run() {
                final TaskItemCollection reportHandle = mSoda.get(TaskItemCollection.class, TaskItemCollection.NAME);
                reportHandle.addListener(new TaskListener() {
                    @SodaInvokeInUi
                    public void itemAdded(final TaskItem r) {
                        reportHandle.addItem(r);
                        mAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void itemChanged(final TaskItem r) {
                        reportHandle.addItem(r);
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }


    class TaskAdapter extends ArrayAdapter<TaskItem> {
        Activity context;
        TaskItem[] data;

        TaskAdapter(final Activity context, final TaskItem[] adapterInput) {
            super(context, R.layout.row, adapterInput);
            this.context=context;
            data = adapterInput;
        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {
            if (data[position] != null) {
                final View ret = data[position].getView(context, convertView, parent);

                final CheckBox cbox1 = (CheckBox) ret.findViewById(R.id.cbox1);
                cbox1.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(final View v) {
                        onUpdate();
                    }
                });

                return ret;
            } else {
                return null;
            }
        }
    }


    private void updateListSize(final int size) {
        final TaskItem[] temp = new TaskItem[size];
        mTasks.toArray(temp);
        mAdapter = new TaskAdapter(this, temp);
        setListAdapter(mAdapter);
    }

    @Override
    public void connected(final AndroidSoda soda) {
        this.mSoda = soda;

        AndroidSoda.init(this, mHost, 8081, this);
    }

    @Override
    public void connectionFailure(final AndroidSoda arg0, final AndroidSodaConnectionException arg1) {
        Log.e(TAG, "Connection failed.");
    }
}
