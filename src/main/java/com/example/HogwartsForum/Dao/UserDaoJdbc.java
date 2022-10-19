package com.example.HogwartsForum.Dao;

import com.example.HogwartsForum.Model.HogwartsHouses;
import com.example.HogwartsForum.Model.RegisteredUsers;
import com.example.HogwartsForum.Model.UserDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    private DataSource dataSource;

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(RegisteredUsers registeredUsers) {
        try(Connection conn = dataSource.getConnection()){
            String query = "INSERT INTO users (name, password, house, pet) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, registeredUsers.getName());
            statement.setString(2, registeredUsers.getPassword());
            statement.setString(3, registeredUsers.getHouse().toString());
            statement.setString(4, registeredUsers.getPet());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

            registeredUsers.setId(resultSet.getInt(1));
        }catch (SQLException e){
            throw new RuntimeException("Error while adding new User.", e);
        }
    }

    @Override
    public void update(RegisteredUsers registeredUsers) {
        try (Connection conn = dataSource.getConnection()){
            String query = "UPDATE user SET name = ?, password = ?, pet = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, registeredUsers.getName());
            statement.setString(2, registeredUsers.getPassword());
            statement.setString(3, registeredUsers.getPet());
            statement.setInt(4, registeredUsers.getId());
        }catch (SQLException e){
            throw new RuntimeException("Error while trying to update a User.", e);
        }
    }

    @Override
    public RegisteredUsers get(int id) {
        try(Connection conn = dataSource.getConnection()){
            String query = "SELECT name, password, house, pet FROM user WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                return null;
            }

            RegisteredUsers registeredUsers = new RegisteredUsers(resultSet.getString(1), resultSet.getString(2),
                    HogwartsHouses.getHouseByStringEquivalent(resultSet.getString(3)), resultSet.getString(4));
            registeredUsers.setId(id);

            return registeredUsers;
        }catch (SQLException e){
            throw new RuntimeException("Error while trying to get a User.", e);
        }
    }

    @Override
    public List<RegisteredUsers> getAll() {
        return null;
    }
}
