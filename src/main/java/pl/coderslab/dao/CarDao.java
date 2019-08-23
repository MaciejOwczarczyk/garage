package pl.coderslab.dao;

import pl.coderslab.model.Car;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarDao {
    private static final String CREATE_CAR_QUERY = "insert into car (client_id, model, brand, year_of_manufacture, data_plate, technical_review) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String READ_CAR_QUERY = "select * from car where id = ?;";
    private static final String UPDATE_CAR_QUERY = "update car set client_id = ?, model = ?, brand = ?, year_of_manufacture = ?, data_plate = ?, technical_review = ? where id = ?;";
    private static final String DELETE_CAR_QUERY = "delete from car where id = ?;";
    private static final String READ_ALL_CAR_QUERY = "select * from car;";


    public Car create(Car car) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_CAR_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, car.getClientId());
            statement.setString(2, car.getModel());
            statement.setString(3, car.getBrand());
            statement.setString(4, car.getYearOfManufacture().toString());
            statement.setString(5, car.getDataPlate());
            statement.setString(6, car.getTechnicalReview().toString());

            int result = statement.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    car.setId(generatedKeys.getInt(1));
                    return car;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Car read(int carId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(READ_CAR_QUERY);
            statement.setInt(1, carId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getInt(1));
                car.setClientId(resultSet.getInt(2));
                car.setModel(resultSet.getString(3));
                car.setBrand(resultSet.getString(4));
                car.setYearOfManufacture(resultSet.getObject(5, LocalDate.class));
                car.setDataPlate(resultSet.getString(6));
                car.setTechnicalReview(resultSet.getObject(7, LocalDate.class));
                return car;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Car car) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_CAR_QUERY);
            statement.setInt(1, car.getClientId());
            statement.setString(2, car.getModel());
            statement.setString(3, car.getBrand());
            statement.setString(4, car.getYearOfManufacture().toString());
            statement.setString(5, car.getDataPlate());
            statement.setString(6, car.getTechnicalReview().toString());
            statement.setInt(7, car.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int carId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_CAR_QUERY);
            statement.setInt(1, carId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> readAll() {
        try (Connection connection = DbUtil.getConnection()) {
            List<Car> carList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(READ_ALL_CAR_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getInt(1));
                car.setClientId(resultSet.getInt(2));
                car.setModel(resultSet.getString(3));
                car.setBrand(resultSet.getString(4));
                car.setYearOfManufacture(resultSet.getObject(5, LocalDate.class));
                car.setDataPlate(resultSet.getString(6));
                car.setTechnicalReview(resultSet.getObject(7, LocalDate.class));
                carList.add(car);
            }
            return carList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
