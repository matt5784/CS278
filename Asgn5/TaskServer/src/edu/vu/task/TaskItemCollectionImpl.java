package edu.vu.task;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.magnum.soda.Soda;
import org.magnum.soda.ctx.SodaQR;

public class TaskItemCollectionImpl implements TaskItemCollection {

    private final List<TaskListener> listeners_ = new LinkedList<TaskListener>();
    private final List<TaskItem> reports_ = new LinkedList<TaskItem>();

    @Override
    public void addItem(final TaskItem r) {

        /*System.out.println("content :" + r.getContents() + " :"
                + r.getCreatorId());*/
        reports_.add(r);

        for (final TaskListener l : listeners_) {
            //l.reportAdded(r);
        }
    }

    @Override
    public void deleteItem(final int id) {

        final Iterator<TaskItem> it = reports_.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {

                reports_.remove(it.next());
                break;
            }
        }
    }

    @Override
    public List<TaskItem> getItems() {
        return reports_;
    }

    @Override
    public void addListener(final TaskListener l) {
        listeners_.add(l);
    }

    @Override
    public void removeListener(final TaskListener l) {
        listeners_.remove(l);
    }

    @Override
    public void modifyItem(final TaskItem r) {
        // TODO Auto-generated method stub
        boolean success = false;
        /*System.out.println("content :" + r.getContents() + " :"
                + r.getCreatorId());*/

        final Iterator<TaskItem> it = reports_.iterator();
        while (it.hasNext()) {
            final TaskItem temp = it.next();
            if (temp.getId() == r.getId()) {
                //temp.setImageData(r.getImageData());
                //temp.setContents(r.getContents());
                success = true;
                break;
            }
        }
    }
}
