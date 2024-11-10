package org.wdn.service;

import org.wdn.dto.User;

import java.util.List;
public interface UserService {
    boolean add(User user);
    boolean deleteUser(Long id);
    boolean update(User user);
    User searchById(Long id);
    List<User> searchByName(String name);
    List<User> getAll();
    List<User> getAllPlanner();
    List<User> getAllCouple();
    List<User> getAllEmployee();
    User getAdmin();
    Long getLastId();
    Long count();
}
