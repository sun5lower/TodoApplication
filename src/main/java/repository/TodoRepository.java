package repository;

import entity.Todo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import types.Status;

public class TodoRepository {
    private DBHandler dbHandler = new DBHandler();

    public void addTodoToDB(Todo todo) throws SQLException {
        Connection connection = dbHandler.getConnection();
        String query = "INSERT INTO todos (description, dueDate, dueTime, status)"
                + "VALUES(?, ?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, todo.getDescription());
        preparedStatement.setDate(2, todo.getDueDate());
        preparedStatement.setTime(3, todo.getDueTime());
        preparedStatement.setString(4, todo.getStatus().toString());

        preparedStatement.execute();
    }

    public ArrayList<Todo> getAllTodoFromDB() throws SQLException{
        ArrayList<Todo> todoItems = new ArrayList<>();
        String query = "SELECT * FROM todos";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        ResultSet result = preparedStatement.executeQuery();

        while (result.next()){
            Todo todo = new Todo(
                    result.getInt("id"),
                    result.getString("description"),
                    result.getDate("dueDate"),
                    result.getTime("dueTime"),
                    Status.valueOf(result.getString("status"))
            );
            todoItems.add(todo);
        }
        return todoItems;
    }
}
