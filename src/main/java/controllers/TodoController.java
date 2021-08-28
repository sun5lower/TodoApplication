package controllers;

import entity.Todo;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import repository.TodoRepository;
import types.Status;

public class TodoController {
    TodoRepository todoRepository = new TodoRepository();
    public void addTodo() {
        Todo todo = collectTodoInfo();
        try{
            todoRepository.addTodoToDB(todo);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateTodo(){ }
    public void viewTodo(){ }
    public void viewAllTodo(){
        ArrayList<Todo> todoList = new ArrayList<>();
        try {
            todoList = todoRepository.getAllTodoFromDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        displayTodoList(todoList);
    }
    public void removeTodo(){ }

    private Todo collectTodoInfo() {
        Todo todo = new Todo();
        todo.setDescription(getUserInput("Description"));
        todo.setDueDate(Date.valueOf(getUserInput("Due Date (2021-07-26)")));
        todo.setDueTime(Time.valueOf(getUserInput("Due Time (21:40)") + ":00"));
        todo.setStatus(Status.PENDING);
        return todo;
    }

    private String getUserInput(String info){
        return JOptionPane.showInputDialog("Enter " +info);
    }

    private void displayTodoList(ArrayList<Todo> todoList){
        System.out.println("id \tstatus \tdue date & time \tdescription");
        todoList.forEach(System.out::println);
    }
}
