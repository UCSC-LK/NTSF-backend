package com.cops.ntsf.dao;

import com.cops.ntsf.model.People;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PeopleDAO {
    public void fetchCivilInfo(People people) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM people WHERE nic = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, people.getNic());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                people.setName(resultSet.getString("name"));
                people.setAddress(resultSet.getString("address"));
                people.setBod(resultSet.getDate("bod"));
                people.setGender(resultSet.getString("gender"));
                people.setBirthPlace(resultSet.getString("birth_place"));
                people.setNic(resultSet.getString("nic"));
                people.setIssueDate(resultSet.getDate("issue_date"));
                people.setJob(resultSet.getString("job"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
