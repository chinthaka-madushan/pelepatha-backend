package org.wdn.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.wdn.dto.User;
import org.wdn.entity.UserEntity;
import org.wdn.repository.UserRepository;
import org.wdn.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.wdn.util.UserType.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    final UserRepository repository;
    final ObjectMapper objectMapper;
    final ModelMapper modelMapper;
    @Override
    public boolean add(@Valid User user) {
        try{
            List<UserEntity> allByEmail = repository.findAllByEmail(user.getEmail());
            if (allByEmail.isEmpty()){
                repository.save(modelMapper.map(user,UserEntity.class));
                log.info("successfully added user :( => ");
                return true;
            }
            return false;
        }catch (Exception e){
            log.error("Error add user :( => "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        try{
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            log.error("Error delete user :( => "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(@Valid User user) {
        try{
            UserEntity save = repository.save(objectMapper.convertValue(user, UserEntity.class));
            return user.equals(objectMapper.convertValue(save,User.class));
        }catch (Exception e){
            log.error("Error update user :( => "+e.getMessage());
            return false;
        }
    }

    @Override
    public User searchById(Long id) {
        return  modelMapper.map(repository.findById(id),User.class);
    }

    @Override
    public List<User> searchByName(String name) {
        ArrayList<User> users = new ArrayList<>();
        repository.findByName(name).forEach(user->{
            users.add(objectMapper.convertValue(user,User.class));
        });
        return users;
    }

    @Override
    public List<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        repository.findAll().forEach(user->{
            users.add(objectMapper.convertValue(user,User.class));
        });
        return users;
    }

    @Override
    public List<User> getAllPlanner() {
        ArrayList<User> users = new ArrayList<>();
        getAll().forEach(user->{
            if(PLANNER ==user.getUserType()){
                users.add(user);
            }
        });
        return users;
    }

    @Override
    public List<User> getAllCouple() {
        ArrayList<User> users = new ArrayList<>();
        getAll().forEach(user->{
            if(COUPLE ==user.getUserType()){
                users.add(user);
            }
        });
        return users;
    }

    @Override
    public List<User> getAllEmployee() {
        ArrayList<User> users = new ArrayList<>();
        getAll().forEach(user->{
            if(EMPLOYEE ==user.getUserType()){
                users.add(user);
            }
        });
        return users;
    }

    @Override
    public User getAdmin() {
        return objectMapper.convertValue(repository.findByUserType(ADMIN),User.class);
    }

    @Override
    public Long getLastId() {
        List<UserEntity> all = repository.findAll();
        Long lastId = 0L;
        for (int i=0 ; i< all.size()-1 ; i++){
            Long fI = all.get(i).getId();
            Long sI = all.get(i+1).getId();
            if (fI<sI){
                lastId = sI;
            }
        }
        return lastId;
    }

    @Override
    public Long count() {
        return repository.count();
    }
}
