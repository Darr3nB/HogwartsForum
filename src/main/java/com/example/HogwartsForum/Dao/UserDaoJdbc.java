package com.example.HogwartsForum.Dao;

import com.example.HogwartsForum.Model.HogwartsHouses;
import com.example.HogwartsForum.Model.User;
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
    public void add(User user) {
        try(Connection conn = dataSource.getConnection()){
            String query = "INSERT INTO users (name, password, house, pet) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getHouse().toString());
            statement.setString(4, user.getPet());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

            user.setId(resultSet.getInt(1));
        }catch (SQLException e){
            throw new RuntimeException("Error while adding new User.", e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection conn = dataSource.getConnection()){
            String query = "UPDATE user SET name = ?, password = ?, pet = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPet());
            statement.setInt(4, user.getId());
        }catch (SQLException e){
            throw new RuntimeException("Error while trying to update a User.", e);
        }
    }

    @Override
    public User get(int id) {
        try(Connection conn = dataSource.getConnection()){
            String query = "SELECT name, password, house, pet FROM user WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                return null;
            }

            User user = new User(resultSet.getString(1), resultSet.getString(2),
                    HogwartsHouses.getHouseByStringEquivalent(resultSet.getString(3)), resultSet.getString(4));
            user.setId(id);

            return user;
        }catch (SQLException e){
            throw new RuntimeException("Error while trying to get a User.", e);
        }
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
