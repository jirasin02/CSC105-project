package com.jirasin02.lemoningz.controller;

import com.jirasin02.lemoningz.database.MySQLConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lemoningz")

public class TeamDetail {
    @GetMapping(path = "/teamdetail")
    public Map<String, Object> getTeamDetail(@RequestParam int id) {
        Map<String, Object> res = new HashMap<>();
        try  {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM team WHERE id = ?");
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            Map<String,Object> info = new HashMap<>();
            info.put("id", resultSet.getInt("id"));
            info.put("name", resultSet.getString("name"));
            info.put("pic", resultSet.getString("team_pic"));
            res.put("teamdetail",info);
            PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM employee WHERE team_id = ?");
            stmt2.setInt(1,id);
            ResultSet resultSet2 = stmt2.executeQuery();
            ArrayList<Object> emp = new ArrayList<>();
            while (resultSet2.next()){
                Map<String,Object> empinfo = new HashMap<>();
                empinfo.put("id", resultSet2.getInt("id"));
                empinfo.put("name", resultSet2.getString("first_name") + " " + resultSet2.getString("last_name"));
                empinfo.put("position", resultSet2.getString("position"));
                empinfo.put("email", resultSet2.getString("email"));
                empinfo.put("status", resultSet2.getString("status"));
                emp.add(empinfo);
            }
            res.put("employee", emp);
        } catch (SQLException e) {
            res.put("success", false);
            e.printStackTrace();
        }
        return res;
    }
}

