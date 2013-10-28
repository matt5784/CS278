package edu.vu.task;

import java.util.List;

public interface TaskItemCollection {

        public static final String NAME = "task";

        public void addItem(TaskItem r);
        public void modifyItem(TaskItem r);
        public void deleteItem(int id);
        public List<TaskItem> getItems();
        public void addListener(TaskListener l);
        public void removeListener(TaskListener l);
}
