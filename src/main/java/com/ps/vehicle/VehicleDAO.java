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

    public List<Vehicle> getByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "select * from vehicles where make=? and model=?;";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);
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
    }//Done

    public List<Vehicle> getByMileage(String startMileage, String endMileage) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "select * from vehicles where odometer between ? and ?;";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, startMileage);
            preparedStatement.setString(2, endMileage);
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

    public void createVehicle(Vehicle vehicle) {
        String query = "insert into vehicles( vin, year, make, model, type, color, odometer,price, sold)" +
                " values(?,?,?,?,?,?,?,?,?);";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setInt(2, vehicle.getYear());
            preparedStatement.setString(3, vehicle.getMake());
            preparedStatement.setString(4, vehicle.getModel());
            preparedStatement.setString(5, vehicle.getType());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setInt(7, vehicle.getOdometer());
            preparedStatement.setInt(8, vehicle.getPrice());
            preparedStatement.setBoolean(9, vehicle.isSold());

            int rows = preparedStatement.executeUpdate();
            if (rows == 1) {
                System.out.println("Vehicle added to Vehicle List");
            } else {
                System.out.println("Vehicle Creation Failed!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//Done

    public void updateVehicle(String vin, Vehicle vehicle) {
        String query = "update vehicles set year=?, make=?, model=?, type=?, color=?, odometer=?, price=?, sold=? where vin=?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, vehicle.getYear());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setString(4, vehicle.getType());
            preparedStatement.setString(5, vehicle.getColor());
            preparedStatement.setInt(6, vehicle.getOdometer());
            preparedStatement.setInt(7, vehicle.getPrice());
            preparedStatement.setBoolean(8, vehicle.isSold());
            preparedStatement.setString(9, vin);

            int rows = preparedStatement.executeUpdate();

            if(rows == 1){
                System.out.println("Vehicle successfully updated");
            } else {
                System.out.println("Vehicle update failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//Done

    public void deleteVehicle(String vin){
        String query= "delete from vehicles where vin=?;";

        try(
                Connection connection= dataSource.getConnection();
                PreparedStatement preparedStatement= connection.prepareStatement(query);
                ){
            preparedStatement.setString(1,vin);

            int rows = preparedStatement.executeUpdate();

            if(rows == 1){
                System.out.println("Vehicle successfully deleted");
            } else {
                System.out.println("Vehicle deletion failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Vehicle vehicleParser(ResultSet resultSet) throws SQLException {
        String vin = resultSet.getString("vin");
        int year = resultSet.getInt("year");
        String make = resultSet.getString("make");
        String model = resultSet.getString("model");
        String type = resultSet.getString("type");
        String color = resultSet.getString("color");
        int odometer = resultSet.getInt("odometer");
        int price = resultSet.getInt("price");
        boolean sold = resultSet.getBoolean("sold");

        return new Vehicle(vin, year, make, model, type, color, odometer, price, sold);
    }//Done
}