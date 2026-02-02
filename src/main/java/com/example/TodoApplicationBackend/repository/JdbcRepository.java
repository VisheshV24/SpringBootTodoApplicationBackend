package com.example.TodoApplicationBackend.repository;

import com.example.TodoApplicationBackend.service.TodoMethods;
import com.example.TodoApplicationBackend.todoDto.TodoDto;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



class TodoRowMapper implements RowMapper<TodoDto> {


    @Override
    public TodoDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        TodoDto todo = new TodoDto();
        todo.setId(rs.getInt("id"));
        todo.setTitle(rs.getString("title"));
        todo.setDescription(rs.getString("description"));
        todo.setIsCompleted(rs.getBoolean("isCompleted"));
        return todo;
    }
}
@Repository
public class JdbcRepository implements TodoMethods {

    JdbcTemplate jdbcTemplate;

    public JdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<TodoDto> findAllTodos() {

        String sql = "SELECT * FROM todos";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(TodoDto.class));

    }

    @Override
    public TodoDto findTodoById(Long id) {
        try {
            String sql = "SELECT * FROM todos WHERE id=?";
            TodoDto todo = jdbcTemplate.queryForObject(sql, new TodoRowMapper() , id);
            return todo;
        }catch(IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int save(TodoDto todo) {

        String sql = "INSERT INTO todos(id, title, description, isCompleted) VALUES(?,?,?,?)";
        return jdbcTemplate.update(sql, new Object[] {todo.getId(), todo.getTitle(), todo.getDescription(), todo.getIsCompleted()});
    }

    @Override
    public int updateTodoById(TodoDto todo) {
        String sql = "UPDATE todos SET title=?,description=?,isCompleted=? WHERE id=?";
        return jdbcTemplate.update(sql, new Object[]{todo.getTitle(), todo.getDescription(), todo.getIsCompleted(), todo.getId()});
    }

    @Override
    public int deleteTodoById(Long id) {
        String sql = "DELETE FROM todos WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }
}
