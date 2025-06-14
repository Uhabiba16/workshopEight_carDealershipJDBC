package com.ps.vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    private static DataSource dataSource;

    public VehicleDAO(DataSource dataSource) {
        VehicleDAO.dataSource = dataSource;
    }

    public List<Vehicle> getAll() {
        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles;";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            if (resultSet.next()) {
                do {
                    Vehicle vehicle = vehicleParser(resultSet);
                    vehicles.add(vehicle);
                } while (resultSet.next());
            } else {
                System.out.println("No Vehicles Found!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }// Done

    public List<Vehicle> getByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "select * from vehicles where color=?;";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, color);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                if (resultSet.next()) {
                    do {
                        Vehicle vehicle = vehicleParser(resultSet);
                        vehicles.add(vehicle);
                    } while (resultSet.next());
                } else {
                    System.out.println("No Vehicles Found!");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }//Done

    public List<Vehicle> getByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "select * from vehicles where type=?;";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, type);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                if (resultSet.next()) {
                    do {
                        Vehicle vehicle = vehicleParser(resultSet);
                        vehicles.add(vehicle);
                    } while (resultSet.next());
                } else {
                    System.out.println("No Vehicles Found!");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }//Done

    public List<Vehicle> getByYear(int fromYear, int toYear) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "select * from vehicles where Year between ? and ?;";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, fromYear);
            preparedStatement.setInt(2, toYear);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                if (resultSet.next()) {
                    do {
                        Vehicle vehicle = vehicleParser(resultSet);
                        vehicles.add(vehicle);
                    } while (resultSet.next());
                } else {
                    System.out.println("No Vehicles Found!");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }//Done

    public List<Vehicle> getByPrice(int min, int max) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "select * from vehicles where price between ? and ?;";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                if (resultSet.next()) {
                    do {
                        Vehicle vehicle = vehicleParser(resultSet);
                        vehicles.add(vehicle);
                    } while (resultSet.next());
                } else {
                    System.out.println("No Vehicles Found!");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    private Vehicle vehicleParser(ResultSet resultSet) throws SQLException {
        String vin = resultSet.getString("vin");
        int year = resultSet.getInt("year");
        String make = resultSet.getString("make");
        String model = resultSet.getString("model");
        String type = resultSet.getString("type");
        String color = resultSet.getString("color");
        String odometer = resultSet.getString("odometer");
        int price= resultSet.getInt("price");
        boolean sold = resultSet.getBoolean("sold");

        return new Vehicle(vin, year, make, model, type, color, odometer,price, sold);
    }//Done
}