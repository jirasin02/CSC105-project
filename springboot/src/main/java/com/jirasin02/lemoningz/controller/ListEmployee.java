package com.jirasin02.lemoningz.controller;

import com.jirasin02.lemoningz.database.MySQLConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lemoningz")

public class ListEmployee {
    @GetMapping(path = "/employees")
    public Map<String, Object> getEmployee() {
        Map<String, Object> res = new HashMap<>();
        try  {
            Connection connection = MySQLConnector.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT employee.*, team.name AS team_name FROM employee INNER JOIN team ON employee.team_id = team.id");
            ArrayList<Object> emp = new ArrayList<>();
            while (resultSet.next()){
                Map<String,Object> info = new HashMap<>();
                info.put("id", resultSet.getInt("id"));
                info.put("firstname", resultSet.getString("first_name"));
                info.put("lastname",resultSet.getString("last_name"));
                info.put("position", resultSet.getString("position"));
                info.put("email", resultSet.getString("email"));
                info.put("status", resultSet.getString("status"));
                info.put("team", resultSet.getString("team_name"));
                info.put("phone", resultSet.getString("phone"));
                info.put("address", resultSet.getString("address"));
                SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd MMM yyyy");
                info.put("hireDate", sdf.format(resultSet.getDate("hire_date")));
                info.put("name", resultSet.getString("first_name")+" "+resultSet.getString("last_name"));
                emp.add(info);
            }
            res.put("employee", emp);
            res.put("success", true);
        } catch (SQLException e) {
            res.put("success", false);
            e.printStackTrace();
        }
        return res;
        }
}
