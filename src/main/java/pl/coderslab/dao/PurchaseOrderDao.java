package pl.coderslab.dao;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import pl.coderslab.model.PurchaseOrder;
import pl.coderslab.utils.DbUtil;

import java.io.CharArrayReader;
import java.lang.ref.PhantomReference;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseOrderDao {
    private static final String CREATE_PURCHASE_ORDER_QUERY = "insert into purchase_order (employee_id, car_id, status_id, date_of_entry, planned_repair, date_of_start_repair, problem_description, fixing_description, cost_for_client, cost_of_spare, cost_of_employee, working_hour) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String READ_PURCHASE_ORDER_QUERY = "select * from purchase_order where id = ?";
    private static final String READ_ALL_PURCHASE_ORDER_QUERY = "select * from purchase_order;";
    private static final String UPDATE_PURCHASE_ORDER_QUERY = "update purchase_order set employee_id = ?, car_id = ?, status_id =?, date_of_entry = ?, planned_repair = ?, date_of_start_repair = ?, problem_description = ?, fixing_description = ?, cost_for_client = ?, cost_of_spare = ?, cost_of_employee = ?, working_hour = ? where id = ?;";
    private static final String DELETE_PURCHASE_ORDER_QUERY = "delete from purchase_order where id = ?";

    public PurchaseOrder create(PurchaseOrder purchaseOrder) {
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement(CREATE_PURCHASE_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, purchaseOrder.getEmployeeId());
            statement.setInt(2, purchaseOrder.getCarId());
            statement.setInt(3, purchaseOrder.getStatusId());
            statement.setString(4, purchaseOrder.getDateOfEntry().toString());
            statement.setString(5, purchaseOrder.getPlannedRepair().toString());
            statement.setString(6, purchaseOrder.getDateOfStartRepair().toString());
            statement.setString(7, purchaseOrder.getProblemDescription());
            statement.setString(8, purchaseOrder.getFixingDescription());
            statement.setDouble(9, purchaseOrder.getCostForClient());
            statement.setDouble(10, purchaseOrder.getCostOfSpare());
            statement.setDouble(11, purchaseOrder.getCostOfEmployee());
            statement.setDouble(12, purchaseOrder.getWorkingHour());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                purchaseOrder.setId(resultSet.getInt(1));
            }
            return purchaseOrder;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PurchaseOrder read(int purchaseOrderId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(READ_PURCHASE_ORDER_QUERY);
            statement.setInt(1, purchaseOrderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PurchaseOrder purchaseOrder = new PurchaseOrder();
                purchaseOrder.setId(resultSet.getInt(1));
                purchaseOrder.setEmployeeId(resultSet.getInt(2));
                purchaseOrder.setCarId(resultSet.getInt(3));
                purchaseOrder.setStatusId(resultSet.getInt(4));
                purchaseOrder.setDateOfEntry(resultSet.getObject(5, LocalDate.class));
                purchaseOrder.setPlannedRepair(resultSet.getObject(6, LocalDate.class));
                purchaseOrder.setDateOfStartRepair(resultSet.getObject(7, LocalDate.class));
                purchaseOrder.setProblemDescription(resultSet.getString(8));
                purchaseOrder.setFixingDescription(resultSet.getString(9));
                purchaseOrder.setCostForClient(resultSet.getDouble(10));
                purchaseOrder.setCostOfSpare(resultSet.getDouble(11));
                purchaseOrder.setCostOfEmployee(resultSet.getDouble(12));
                purchaseOrder.setWorkingHour(resultSet.getDouble(13));
                return purchaseOrder;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PurchaseOrder> readAll() {
        try (Connection connection = DbUtil.getConnection()){
            List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(READ_ALL_PURCHASE_ORDER_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PurchaseOrder purchaseOrder = new PurchaseOrder();
                purchaseOrder.setId(resultSet.getInt(1));
                purchaseOrder.setEmployeeId(resultSet.getInt(2));
                purchaseOrder.setCarId(resultSet.getInt(3));
                purchaseOrder.setStatusId(resultSet.getInt(4));
                purchaseOrder.setDateOfEntry(resultSet.getObject(5, LocalDate.class));
                purchaseOrder.setPlannedRepair(resultSet.getObject(6, LocalDate.class));
                purchaseOrder.setDateOfStartRepair(resultSet.getObject(7, LocalDate.class));
                purchaseOrder.setProblemDescription(resultSet.getString(8));
                purchaseOrder.setFixingDescription(resultSet.getString(9));
                purchaseOrder.setCostForClient(resultSet.getDouble(10));
                purchaseOrder.setCostOfSpare(resultSet.getDouble(11));
                purchaseOrder.setCostOfEmployee(resultSet.getDouble(12));
                purchaseOrder.setWorkingHour(resultSet.getDouble(13));
                purchaseOrderList.add(purchaseOrder);
            }
            return purchaseOrderList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(PurchaseOrder purchaseOrder) {
        try (Connection connection = DbUtil.getConnection()){
           PreparedStatement statement = connection.prepareStatement(UPDATE_PURCHASE_ORDER_QUERY);
           statement.setInt(1, purchaseOrder.getEmployeeId());
           statement.setInt(2, purchaseOrder.getCarId());
           statement.setInt(3, purchaseOrder.getStatusId());
           statement.setString(4, purchaseOrder.getDateOfEntry().toString());
           statement.setString(5, purchaseOrder.getPlannedRepair().toString());
           statement.setString(6, purchaseOrder.getDateOfStartRepair().toString());
           statement.setString(7, purchaseOrder.getProblemDescription());
           statement.setString(8, purchaseOrder.getFixingDescription());
           statement.setDouble(9, purchaseOrder.getCostForClient());
           statement.setDouble(10, purchaseOrder.getCostOfSpare());
           statement.setDouble(11, purchaseOrder.getCostOfEmployee());
           statement.setDouble(12, purchaseOrder.getWorkingHour());
           statement.setInt(13, purchaseOrder.getId());
           statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int purchaseOrderId) {
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement(DELETE_PURCHASE_ORDER_QUERY);
            statement.setInt(1, purchaseOrderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
