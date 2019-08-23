package pl.coderslab.dao;

import pl.coderslab.model.Employee;
import pl.coderslab.utils.DbUtil;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

public class EmployeeDao {
    private static final String CREATE_EMPLOYEE_QUERY = "insert into employee (name, surname, address, phone, note, working_hour) values (?, ?, ?, ?, ?, ?);";
    private static final String READ_EMPLOYEE_QUERY = "select * from employee where id = ?;";
    private static final String READ_ALL_EMPLOYEE_QUERY = "select * from employee;";
    private static final String UPDATE_EMPLOYEE_QUERY = "update employee set name = ?, surname = ?, address = ?, phone = ?, note = ?, working_hour = ? where id = ?;";
    private static final String DELETE_EMPLOYEE_QUERY = "delete from employee where id = ?;";

    public Employee create(Employee employee) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_EMPLOYEE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getSurname());
            statement.setString(3, employee.getAddress());
            statement.setString(4, employee.getPhone());
            statement.setString(5, employee.getNote());
            statement.setDouble(6, employee.getWorkingHour());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                employee.setId(resultSet.getInt(1));
            }
            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Employee read(int employeeId) {
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement(READ_EMPLOYEE_QUERY);
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setSurname(resultSet.getString(3));
                employee.setAddress(resultSet.getString(4));
                employee.setPhone(resultSet.getString(5));
                employee.setNote(resultSet.getString(6));
                employee.setWorkingHour(resultSet.getDouble(7));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> readAll() {
        try (Connection connection = DbUtil.getConnection()){
            List<Employee> employees = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(READ_ALL_EMPLOYEE_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setSurname(resultSet.getString(3));
                employee.setAddress(resultSet.getString(4));
                employee.setPhone(resultSet.getString(5));
                employee.setNote(resultSet.getString(6));
                employee.setWorkingHour(resultSet.getDouble(7));
                employees.add(employee);
            }
            return employees;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(Employee employee) {
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_QUERY);
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getSurname());
            statement.setString(3, employee.getAddress());
            statement.setString(4, employee.getPhone());
            statement.setString(5, employee.getNote());
            statement.setDouble(6, employee.getWorkingHour());
            statement.setInt(7, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int employeeId) {
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_QUERY);
            statement.setInt(1, employeeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
