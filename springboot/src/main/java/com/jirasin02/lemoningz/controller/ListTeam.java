package com.jirasin02.lemoningz.controller;

import com.jirasin02.lemoningz.database.MySQLConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lemoningz")

public class ListTeam {
    @GetMapping(path = "/teams")
    public Map<String, Object> getTeam(@RequestParam String pic) {
        Map<String, Object> res = new HashMap<>();
        try  {
            Connection connection = MySQLConnector.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM team WHERE id != 0");
            ArrayList<Object> team = new ArrayList<>();
            while (resultSet.next()){
                Map<String,Object> info = new HashMap<>();
                info.put("id", resultSet.getInt("id"));
                info.put("name", resultSet.getString("name"));
                if (pic.equalsIgnoreCase("true")){
                    info.put("pic", resultSet.getString("team_pic"));
                }
                team.add(info);
            }
            res.put("team", team);
            Statement statement = connection.createStatement();
            ResultSet resultSet1 = statement.executeQuery("SELECT team_id,COUNT(team_id) FROM employee WHERE team_id != 0 GROUP BY team_id");
            ArrayList<Object> noEmp = new ArrayList<>();
            while (resultSet1.next()){
                Map<String,Object> info2 = new HashMap<>();
                info2.put("id", resultSet1.getInt("team_id"));
                info2.put("noOfEmp", resultSet1.getInt(2));
                noEmp.add(info2);
            }
            res.put("noEmp", noEmp);
        } catch (SQLException e) {
            res.put("success", false);
        }
        return res;
    }
}
