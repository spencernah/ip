package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    @Test
    void add() {
        TaskList taskList = new TaskList();
        Task task = new Task("borrow book");
        taskList.add(task);
        assertEquals(task, taskList.get(0));
    }

    @Test
    void remove() {
        TaskList taskList = new TaskList();
        Task task1 = new Task("borrow book 1");
        Task task2 = new Task("borrow book 2");
        taskList.add(task1);
        taskList.add(task2);
        assertEquals(task1, taskList.remove(0));
        assertEquals(task2, taskList.get(0));
    }

    @Test
    void get() {
        ToDo toDo = new ToDo("todo");
        Deadline deadline = new Deadline("deadline", "24/1/2022 1400");
        Event event = new Event("event", "24/1/2022 1400");
        TaskList taskList = new TaskList();
        taskList.add(toDo);
        taskList.add(deadline);
        taskList.add(event);
        TaskList list = new TaskList(taskList.convertAsLines());
        assertEquals(toDo.toString(), list.get(0).toString());
        assertEquals(deadline.toString(), list.get(1).toString());
        assertEquals(event.toString(), list.get(2).toString());
    }

    @Test
    void size() {
        TaskList taskList = new TaskList();
        taskList.add(new Task("borrow book 1"));
        taskList.add(new Task("borrow book 2"));
        assertEquals(2, taskList.size());
    }
}