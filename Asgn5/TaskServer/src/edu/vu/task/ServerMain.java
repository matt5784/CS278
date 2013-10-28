package edu.vu.task;

import java.util.Calendar;

import org.magnum.soda.Soda;
import org.magnum.soda.protocol.java.NativeJavaProtocol;
import org.magnum.soda.server.wamp.ServerSodaLauncher;
import org.magnum.soda.server.wamp.ServerSodaListener;

public class ServerMain implements ServerSodaListener {

    private Integer id = 0;

    public static void main(final String[] args) {
        final ServerSodaLauncher launcher = new ServerSodaLauncher();
        launcher.launch(new NativeJavaProtocol(), 8081, new ServerMain());
    }

    @Override
    public void started(final Soda soda) {
        final TaskItemCollection reports = new TaskItemCollectionImpl();

        final String time = Calendar.getInstance().getTime().toString();

        final TaskItem t1= new TaskItem();
        t1.setId(id++);
        t1.setDescription("Pick up milk at the store");
        t1.setDue(time);
        t1.setChecked(true);
        reports.addItem(t1);

        final TaskItem t2= new TaskItem();
        t2.setId(id++);
        t2.setDescription("Hand in project for CS271");
        t2.setDue(time);
        t2.setChecked(false);
        reports.addItem(t2);

        soda.bind(reports, TaskItemCollection.NAME);
    }
}