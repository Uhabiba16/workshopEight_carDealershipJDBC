package com.ps.dealership;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealershipDAO {
    private static DataSource dataSource;

    public DealershipDAO(DataSource dataSource) {
        DealershipDAO.dataSource = dataSource;
    }

    public List<Dealership> getAll() {
        List<Dealership> dealerships = new ArrayList<>();

        String query = "select * from dealerships;";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if (resultSet.next()) {
                do {
                    Dealership dealership = dealershipParser(resultSet);
                    dealerships.add(dealership);
                } while (resultSet.next());
            } else {
                System.out.println("No Dealerships Found!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dealerships;
    }//Done

    public Dealership getById(String dealershipId) {
        String query = "select * from dealerships where dealership_id=?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, dealershipId);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                if (resultSet.next()) {
                    return dealershipParser(resultSet);
                } else {
                    System.out.println("No Dealership Found");
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }//Done

    public static void create(Dealership dealership) {
        String query = "insert into dealerships(name, address, phone) values (?, ?, ?);";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, dealership.getName());
            preparedStatement.setString(2, dealership.getAddress());
            preparedStatement.setString(3, dealership.getPhone());

            int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                System.out.println("New Dealership Added Successfully!");
            } else {
                System.out.println("Unsuccessful, Try Again!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//Done

    public static void update(String dealershipName, Dealership dealership) {
        String query = "update dealerships set address=?,phone=? where name = ?;";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, dealership.getAddress());
            preparedStatement.setString(2, dealership.getPhone());
            preparedStatement.setString(3, dealershipName);

            int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                System.out.println("Dealership info updated successfully!");
            } else {
                System.out.println("Dealership info updated failed!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//Done

    public static void delete(String dealershipName) {
        String query = "delete from dealerships where name=?;";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, dealershipName);

            int rows = preparedStatement.executeUpdate();
            if (rows == 1) {
                System.out.println("Dealership info has been deleted successfully!");
            } else {
                System.out.println("Dealership Deletion Failed!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//Done

    private Dealership dealershipParser(ResultSet resultSet) throws SQLException {
        int dealershipId = resultSet.getInt("dealership_id");
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        String phone = resultSet.getString("phone");

        return new Dealership(dealershipId, name, address, phone);
    }
}
