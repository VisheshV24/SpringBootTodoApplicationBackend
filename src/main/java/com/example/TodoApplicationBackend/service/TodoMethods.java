package com.example.TodoApplicationBackend.service;


import com.example.TodoApplicationBackend.todoDto.TodoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoMethods {


    public List<TodoDto> findAllTodos();

    public TodoDto findTodoById(Long id);

    public int save(TodoDto todo);

    public int updateTodoById(TodoDto todo);


    public int deleteTodoById(Long id);



}
