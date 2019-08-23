package pl.coderslab.dao;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import pl.coderslab.model.Client;
import pl.coderslab.utils.DbUtil;

import javax.print.DocFlavor;
import java.io.PrintWriter;
import java.net.PortUnreachableException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientDao {
    private static final String CREATE_CLIENT_QUERY = "insert into client (name, surname, birth_date) values (?, ?, ?);";
    private static final String READ_CLIENT_QUERY = "select * from client where id = ?;";
    private static final String UPDATE_CLIENT_QUERY = "update client set name = ?, surname = ?, birth_date = ? where id = ?;";
    private static final String DELETE_CLIENT_QUERY = "delete from client where id = ?;";
    private static final String READ_ALL_CLIENT_QUERY = "select * from client";
    public Client create(Client client) {
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement(CREATE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setString(3, client.getBirthDate().toString());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                client.setId(resultSet.getInt(1));
            }
            return client;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Client read(int clientId) {
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement(READ_CLIENT_QUERY);
            statement.setInt(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt(1));
                client.setName(resultSet.getString(2));
                client.setSurname(resultSet.getString(3));
                client.setBirthDate(resultSet.getObject(4, LocalDate.class));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Client client) {
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENT_QUERY);
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setString(3, client.getBirthDate().toString());
            statement.setInt(4, client.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int clientId) {
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT_QUERY);
            statement.setInt(1, clientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> readAll() {
        try (Connection connection = DbUtil.getConnection()){
            List<Client> clientList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(READ_ALL_CLIENT_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt(1));
                client.setName(resultSet.getString(2));
                client.setSurname(resultSet.getString(3));
                client.setBirthDate(resultSet.getObject(4, LocalDate.class));
                clientList.add(client);
            }
            return clientList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
