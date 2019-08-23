package pl.coderslab.dao;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import pl.coderslab.model.Status;
import pl.coderslab.utils.DbUtil;

import java.lang.ref.PhantomReference;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusDao {

    private static final String CREATE_STATUS_QUERY = "insert into status (id, status) values (?, ?);";
    private static final String READ_STATUS_BY_ID_QUERY = "select * from status where id = ?;";
    private static final String READ_BY_STATUS_BY_STATUS_QUERY = "select * from status where status = ?;";
    private static final String READ_ALL_STATUS_QUERY = "select * from status";

    public Status create(Status status) {
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement(CREATE_STATUS_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, status.getId());
            statement.setString(2, status.getStatus());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                status.setId(resultSet.getInt(1));
            }
            return status;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Status readbyId(int statusId) {
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement(READ_STATUS_BY_ID_QUERY);
            statement.setInt(1, statusId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Status status = new Status();
                status.setId(resultSet.getInt(1));
                status.setStatus(resultSet.getString(2));
                return status;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Status readByStatus(String status) {
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement(READ_BY_STATUS_BY_STATUS_QUERY);
            statement.setString(1,status);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Status status1 = new Status();
                status1.setId(resultSet.getInt(1));
                status1.setStatus(resultSet.getString(2));
                return status1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Status> readAll() {
        try (Connection connection = DbUtil.getConnection()){
            List<Status> statusList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(READ_ALL_STATUS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Status status = new Status();
                status.setId(resultSet.getInt(1));
                status.setStatus(resultSet.getString(2));
                statusList.add(status);
            }
            return statusList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
