package com.example.sodacloudsmsexampleclient.test;

import edu.vu.task.TaskItem;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

public class TaskItemTest extends AndroidTestCase {

    private static final String JUNK = "147821949214hjdfkasnkjc7r4hujkas";

    @SmallTest
    public void testSetGetChecked() {
        final TaskItem test = new TaskItem();
        test.setChecked(true);

        assertEquals("State should match", test.getChecked(), true);
    }

    @SmallTest
    public void testSetGetId() {
        final TaskItem test = new TaskItem();
        test.setId(77);

        assertEquals("State should match", test.getId(), 77);
    }

    @SmallTest
    public void testSetGetDesc() {
        final TaskItem test = new TaskItem();
        test.setDescription(JUNK);

        assertEquals("State should match", test.getDescription(), JUNK);
    }

    @SmallTest
    public void testSetGetDue() {
        final TaskItem test = new TaskItem();
        test.setDue(JUNK);

        assertEquals("State should match", test.getDue(), JUNK);
    }

}
