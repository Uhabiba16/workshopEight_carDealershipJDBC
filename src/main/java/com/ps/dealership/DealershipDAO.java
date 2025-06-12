package com.ps.dealership;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealershipDAO {
    private final DataSource dataSource;

    public DealershipDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Dealership> getAll() {
        List<Dealership> dealerships = new ArrayList<>();

        String query = "select * from dealerships;";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
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
    }

    private Dealership dealershipParser(ResultSet resultSet) throws SQLException {
        int dealershipId = resultSet.getInt("dealership_id");
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        String phone = resultSet.getString("phone");

        return new Dealership(dealershipId, name, address, phone);
    }
}
