package com.example.TodoApplicationBackend.controller;


import com.example.TodoApplicationBackend.service.TodoMethods;
import com.example.TodoApplicationBackend.todoDto.TodoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {


    TodoMethods todoMethods;

    public TodoController(TodoMethods todoMethods) {
        this.todoMethods = todoMethods;
    }


    /* Get All Todos */

    @GetMapping("/todos")
    public ResponseEntity<List<TodoDto>> getAllTodos() {

        List<TodoDto> response = todoMethods.findAllTodos();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /* Get Todo with given iD */

    @GetMapping("/todo/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id) {

        TodoDto response = todoMethods.findTodoById(id);

        if(response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }


    /* Create Todo */

    @PostMapping("/todo")
    public ResponseEntity<String> createTodo(@RequestBody TodoDto todo) {

        try {
            todoMethods.save(todo);
            return new ResponseEntity<>("Todo has been Successfully added", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /* Update Status of Todo */

    @PutMapping("/updateTodo")
    public ResponseEntity<String> updateStatus(@RequestParam Long id, @RequestParam boolean flag) {
        try {

            TodoDto todoDto = todoMethods.findTodoById(id);

            if(todoDto == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            todoDto.setIsCompleted(flag);

            todoMethods.updateTodoById(todoDto);
            return new ResponseEntity<>("Status has been Successfully changed", HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*  Delete Todo with given Id */
    @DeleteMapping("/deleteTodo/{id}")
    public ResponseEntity<String>  deleteTodo(@PathVariable Long id) {


        try {

            int response = todoMethods.deleteTodoById(id);
            if(response == 0) {
                return new ResponseEntity<>("No Todo found with this given Id " + id, HttpStatus.OK);
            }

            return new ResponseEntity<>("Todo has been successfully deleted", HttpStatus.OK);


        }catch(Exception e) {
            return new ResponseEntity<>("Cannot delete todo", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    
}
