package org.wdn.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.wdn.dto.User;
import org.wdn.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {
    final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@Valid @RequestBody User user){
        if (userService.add(user)){
            log.info("Received request to add user : {}",user);
            return ResponseEntity.ok("user added successfuly");
        }else {
            return ResponseEntity.ok("user can't added :(");
        }
    }
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<String> deleteUserById(@Valid @PathVariable Long id){
        if (userService.deleteUser(id)){
            log.info("Received request to delete user : {}",id);
            return ResponseEntity.ok("user deleted successfuly");
        }else {
            return ResponseEntity.ok("user can't delete");
        }
    }
    @PutMapping("/update")
    public ResponseEntity<String> deleteUserById(@Valid @RequestBody User user){
        if (userService.update(user)){
            log.info("Received request to update user : {}",user);
            return ResponseEntity.ok("user updated successfuly");
        }else {
            return ResponseEntity.ok("user can't update");
        }
    }
    @GetMapping("/search-by-id/{id}")
    public Map<String,User> searchUserById( @PathVariable Long id){
        User user = userService.searchById(id);
        Map<String,User> map = new HashMap<>();
        log.debug("Received request to search by id : {} -> {}",id,user);
        map.put("user",user);
        return map;

    }
    @GetMapping("/search-by-name/{name}")
    public Map<String,List<User>> searchUserByName( @PathVariable String name){
        List<User> users = userService.searchByName(name);
        Map<String,List<User>> map = new HashMap<>();
        log.debug("Received request to search by name : {} -> list size : {}",name,users.size());
        map.put("user",users);
        return map;
    }
    @GetMapping("/get-all")
    public Map<String,List<User>> getAllUser(){
        List<User> users = userService.getAll();
        Map<String,List<User>> map = new HashMap<>();
        log.debug("Received request to get all user : list size : {}",users.size());
        map.put("user",users);
        return map;
    }
    @GetMapping("/get-all-planner")
    public Map<String,List<User>> getAllPlanner(){
        List<User> planner = userService.getAllPlanner();
        Map<String,List<User>> map = new HashMap<>();
        log.debug("Received request to get all planner : list size : {}",planner.size());
        map.put("user",planner);
        return map;
    }
    @GetMapping("/get-all-couple")
    public Map<String,List<User>> getAllCouple(){
        List<User> couple = userService.getAllCouple();
        Map<String,List<User>> map = new HashMap<>();
        log.debug("Received request to get all couple : list size : {}",couple.size());
        map.put("user",couple);
        return map;
    }
    @GetMapping("/get-all-employee")
    public Map<String,List<User>> getAllEmployee(){
        List<User> employee = userService.getAllEmployee();
        Map<String,List<User>> map = new HashMap<>();
        log.debug("Received request to get all employee : list size : {}",employee.size());
        map.put("user",employee);
        return map;
    }
    @GetMapping("/get-admin")
    public Map<String,User> getAdmin(){
        User employee = userService.getAdmin();
        Map<String,User> map = new HashMap<>();
        log.debug("Received request to get all admin {}",employee);
        map.put("user",employee);
        return map;
    }
    @GetMapping("/get-next-id")
    public Long getNextId(){
        return  userService.getLastId();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error("Validation error: {}", errors);
        return errors;
    }
}
