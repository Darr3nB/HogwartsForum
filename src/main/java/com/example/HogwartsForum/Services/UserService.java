package com.example.HogwartsForum.Services;

import com.example.HogwartsForum.Model.HogwartsUser;
import com.example.HogwartsForum.Repositories.HogwartsUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    HogwartsUserRepository hogwartsUserDatabaseDao;

    public List<HogwartsUser> getAllUsers() {
        return hogwartsUserDatabaseDao.findAll();
    }

    public void addUser(HogwartsUser hogwartsUser) {
        hogwartsUserDatabaseDao.save(hogwartsUser);
    }

    public HogwartsUser getUserById(Integer id) {
        return hogwartsUserDatabaseDao.getById(id);
    }

    public HogwartsUser getUserByUsername(String username) {
        return hogwartsUserDatabaseDao.getHogwartsUserByName(username);
    }

    public Boolean countUsersByName(String name) {
        return hogwartsUserDatabaseDao.countByName(name) == 1;
    }

    public void updateUserById(Integer id, HogwartsUser hogwartsUser) {
        HogwartsUser hogwartsUserToUpdate = hogwartsUserDatabaseDao.getById(id);
        hogwartsUserToUpdate.setName(hogwartsUser.getName());
        hogwartsUserToUpdate.setPassword(hogwartsUser.getPassword());
        hogwartsUserToUpdate.setPet(hogwartsUser.getPet());
        hogwartsUserDatabaseDao.save(hogwartsUserToUpdate);
    }

    public void deleteUserById(Integer id) {
        hogwartsUserDatabaseDao.deleteById(id);
    }

    public Boolean validateLogin(String username){
        HogwartsUser userFromDB = getUserByUsername(username);
        

        return true;
    }
}
