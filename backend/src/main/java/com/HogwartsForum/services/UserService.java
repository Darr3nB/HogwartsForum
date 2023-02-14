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
            HogwartsUser admin = new HogwartsUser();

            admin.setName("admin");
            admin.setPassword("$2a$10$aggKLhBPm7ke/CfXkiSnAOzpHXdIXqm9j5MxFobGjr.O38gnngBsK");
            admin.setHouse(HogwartsHouses.getHouseByStringEquivalent("Gryffindor"));
            admin.setPet(PetTypes.OWL);
            admin.setProfilePicture("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEBLAEsAAD/4QBWRXhpZgAATU0AKgAAAAgABAEaAAUAAAABAAAAPgEbAAUAAAABAAAARgEoAAMAAAABAAIAAAITAAMAAAABAAEAAAAAAAAAAAEsAAAAAQAAASwAAAAB/+0ALFBob3Rvc2hvcCAzLjAAOEJJTQQEAAAAAAAPHAFaAAMbJUccAQAAAgAEAP/hDIFodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvADw/eHBhY2tldCBiZWdpbj0n77u/JyBpZD0nVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkJz8+Cjx4OnhtcG1ldGEgeG1sbnM6eD0nYWRvYmU6bnM6bWV0YS8nIHg6eG1wdGs9J0ltYWdlOjpFeGlmVG9vbCAxMC4xMCc+CjxyZGY6UkRGIHhtbG5zOnJkZj0naHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyc+CgogPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9JycKICB4bWxuczp0aWZmPSdodHRwOi8vbnMuYWRvYmUuY29tL3RpZmYvMS4wLyc+CiAgPHRpZmY6UmVzb2x1dGlvblVuaXQ+MjwvdGlmZjpSZXNvbHV0aW9uVW5pdD4KICA8dGlmZjpYUmVzb2x1dGlvbj4zMDAvMTwvdGlmZjpYUmVzb2x1dGlvbj4KICA8dGlmZjpZUmVzb2x1dGlvbj4zMDAvMTwvdGlmZjpZUmVzb2x1dGlvbj4KIDwvcmRmOkRlc2NyaXB0aW9uPgoKIDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PScnCiAgeG1sbnM6eG1wTU09J2h0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8nPgogIDx4bXBNTTpEb2N1bWVudElEPmFkb2JlOmRvY2lkOnN0b2NrOjdjNDg5NGVjLWU3MWEtNGVlNy05YzQ1LTkzNTk0YjU4MWY3YzwveG1wTU06RG9jdW1lbnRJRD4KICA8eG1wTU06SW5zdGFuY2VJRD54bXAuaWlkOjdmNTYxMWQzLWM2ZWItNDhhYS1hMGZiLTBkMjdjZjY2YTZjZDwveG1wTU06SW5zdGFuY2VJRD4KIDwvcmRmOkRlc2NyaXB0aW9uPgo8L3JkZjpSREY+CjwveDp4bXBtZXRhPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAo8P3hwYWNrZXQgZW5kPSd3Jz8+/9sAQwAFAwQEBAMFBAQEBQUFBgcMCAcHBwcPCwsJDBEPEhIRDxERExYcFxMUGhURERghGBodHR8fHxMXIiQiHiQcHh8e/8AACwgBaAH4AQERAP/EAB0AAQACAgMBAQAAAAAAAAAAAAAHCQYIAQMEBQL/xABKEAABAwMCAwQFBwkGAwkAAAAAAQIDBAUGBxEIITESE0FRIjJhcYEUQlJygpGhCRUXI2KSorHSFiQzV5XBJWXRQ2NzdYOFsrPC/9oACAEBAAA/ANywAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA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");

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

    public boolean addReputationToUserById(int userId, int reputation) {
        try {
            HogwartsUser foundUser = getUserById(userId);

            foundUser.setReputation(foundUser.getReputation() + reputation);
            hogwartsUserDatabaseDao.save(foundUser);

            return true;
        }catch (UsernameNotFoundException e){
            System.out.println("I did not found the user: " + e);
            return false;
        }

    }

    public boolean substractReputationToUserById(Integer userId, int reputation) {
        try {
            HogwartsUser foundUser = getUserById(userId);

            foundUser.setReputation(foundUser.getReputation() - reputation);
            hogwartsUserDatabaseDao.save(foundUser);

            return true;
        }catch (UsernameNotFoundException e){
            System.out.println("I did not found the user: " + e);
            return false;
        }
    }
}
