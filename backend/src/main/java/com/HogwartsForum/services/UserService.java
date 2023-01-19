package com.HogwartsForum.services;

import com.HogwartsForum.dao.HogwartsUserDao;
import com.HogwartsForum.model.*;
import com.HogwartsForum.security.PasswordAgent;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService implements CommandLineRunner {
    HogwartsUserDao hogwartsUserDatabaseDao;
    PasswordAgent passwordAgent;

    public List<HogwartsUser> getAllUsers() {
        return hogwartsUserDatabaseDao.findAll();
    }

    public void addUser(HogwartsUser hogwartsUser) {
        if (hogwartsUser.getRole() == null) {
            hogwartsUser.setRole(Roles.USER);
        }
        if (hogwartsUser.getProfilePicture() == null) {
            hogwartsUser.setProfilePicture("../src/assets/default-profile-picture.jpg");
        }
        hogwartsUserDatabaseDao.save(hogwartsUser);
    }

    public HogwartsUser getUserById(Integer id) {
        return hogwartsUserDatabaseDao.findHogwartsUserById(id);
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
        foundUser.setRole(null);
        foundUser.setReputation(0);
        foundUser.setProfilePicture("../src/assets/default-profile-picture.jpg");
        hogwartsUserDatabaseDao.save(foundUser);
    }

    public Boolean validateProfile(String username, String plainPassword) {
        if (username.equals("DELETED_USER")) {
            return false;
        }

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

    public void saveQuestionToUserSet(Integer userId, Question question) {
        HogwartsUser userToAddQuestion = getUserById(userId);
        userToAddQuestion.getQuestionsList().add(question);
        hogwartsUserDatabaseDao.save(userToAddQuestion);
    }

    public void addCommentToUser(Integer userId, Comment comment) {
        HogwartsUser userToAddComment = getUserById(userId);
        userToAddComment.getCommentList().add(comment);
        hogwartsUserDatabaseDao.save(userToAddComment);
    }

    public void loadUserData() {
        if (hogwartsUserDatabaseDao.count() == 0) {
            HogwartsUser admin = new HogwartsUser("admin",
                    "$2a$10$aggKLhBPm7ke/CfXkiSnAOzpHXdIXqm9j5MxFobGjr.O38gnngBsK",
                    HogwartsHouses.GRYFFINDOR.name(), PetTypes.OWL.name());

            admin.setRole(Roles.ADMIN);

            hogwartsUserDatabaseDao.save(admin);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    public boolean removeCommentById(int userId, int commentId) {
        HogwartsUser foundUser = getUserById(userId);

        for (Comment comment : foundUser.getCommentList()) {
            if (comment.getId() == commentId) {
                foundUser.getCommentList().remove(comment);
                hogwartsUserDatabaseDao.save(foundUser);

                return true;
            }
        }

        return false;
    }

    public boolean thisUserPostedCommentWithThisId(int userId, int commentId) {
        HogwartsUser foundUser = getUserById(userId);

        if (foundUser.getCommentList().size() <= 0) {
            return false;
        }

        for (Comment comment : foundUser.getCommentList()) {
            if (comment.getId() == commentId) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserAdmin(int userId) {
        HogwartsUser foundUser = getUserById(userId);

        return foundUser.getRole() == Roles.ADMIN;
    }

    public HogwartsUser getUserOwnsThisComment(Integer commentId){
        List<HogwartsUser> allUsers = getAllUsers();

        for (HogwartsUser user : allUsers){
            for (Comment comment : user.getCommentList()){
                if (Objects.equals(comment.getId(), commentId)){
                    return user;
                }
            }
        }

        throw new UsernameNotFoundException("I did not found the requested user.");
    }
}
