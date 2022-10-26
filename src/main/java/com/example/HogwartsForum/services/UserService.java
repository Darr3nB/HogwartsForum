package com.example.HogwartsForum.services;

import com.example.HogwartsForum.daos.HogwartsUserDao;
import com.example.HogwartsForum.model.HogwartsUser;
import com.example.HogwartsForum.security.PasswordAgent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    HogwartsUserDao hogwartsUserDatabaseDao;
    PasswordAgent passwordAgent;

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

    public Boolean existByName(String name) {
        return hogwartsUserDatabaseDao.existsByName(name);
    }

    public void updateUserById(Integer id, HogwartsUser hogwartsUser) {
        HogwartsUser hogwartsUserToUpdate = hogwartsUserDatabaseDao.getById(id);
        hogwartsUserToUpdate.setName(hogwartsUser.getName());
        hogwartsUserToUpdate.setPassword(hogwartsUser.getPassword());
        hogwartsUserToUpdate.setPet(hogwartsUser.getPet());
        hogwartsUserDatabaseDao.save(hogwartsUserToUpdate);
    }

    public void deleteUserById(Integer id) {
        HogwartsUser foundUser = getUserById(id);
        foundUser.setName("DELETED_USER");
        foundUser.setPassword("----");
        foundUser.setPet(null);
        foundUser.setHouse(null);
        hogwartsUserDatabaseDao.save(foundUser);
    }

    public Boolean validateLogin(String username, String plainPassword) {
        try {
            HogwartsUser userFromDB = getUserByUsername(username);
            return username.equals(userFromDB.getName()) &&
                    passwordAgent.passwordMatches(userFromDB.getPassword(), plainPassword);
        } catch (NullPointerException e) {
            return false;
        } catch (Exception e) {
            System.out.println("An error has occurred: " + e);
            return false;
        }
    }
}
