package com.example.HogwartsForum.Services;

import com.example.HogwartsForum.Model.User;
import com.example.HogwartsForum.Repositories.UserRepository;

import java.util.List;

public class UsersService{
    UserRepository userDatabaseDao;

    public List<User> getAllUsers(){
        return userDatabaseDao.findAll();
    }

    public void addUser(User user){
        userDatabaseDao.save(user);
    }

    public User getUserById(Integer id){
        return userDatabaseDao.getById(id);
    }

    public void updateUserById(Integer id, User user){
        User userToUpdate = userDatabaseDao.getById(id);
        userToUpdate.setName(user.getName());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setPet(user.getPet());
        userDatabaseDao.save(userToUpdate);
    }

    public void deleteUserById(Integer id){
        userDatabaseDao.deleteById(id);
    }
}
