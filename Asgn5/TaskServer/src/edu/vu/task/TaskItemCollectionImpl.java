package edu.vu.task;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.magnum.soda.Soda;
import org.magnum.soda.ctx.SodaQR;

public class TaskItemCollectionImpl implements TaskItemCollection {

    private final List<TaskItem> reports_ = new LinkedList<TaskItem>();

    @Override
    public void addItem(final TaskItem r) {
        reports_.add(r);
    }

    @Override
    public void deleteItem(final int id) {

        final Iterator<TaskItem> it = reports_.iterator();
        while (it.hasNext()) {
	    Report r = it.next();
            if (r.getId() == id) {
                it.remove();
                break;
            }
        }
    }

    @Override
    public List<TaskItem> getItems() {
        return reports_;
    }

    @Override
    public void modifyItem(final TaskItem r) {
        final Iterator<TaskItem> it = reports_.iterator();
        while (it.hasNext()) {
            final TaskItem temp = it.next();
            if (temp.getId() == r.getId()) {
                temp.setDue(r.getDue);
                temp.setDescription(r.getDescription);
                temp.setChecked(r.getChecked);
                break;
            }
        }
        
    }
}
