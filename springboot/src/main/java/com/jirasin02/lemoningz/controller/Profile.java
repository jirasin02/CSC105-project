package com.jirasin02.lemoningz.controller;

import com.jirasin02.lemoningz.database.MySQLConnector;
import com.jirasin02.lemoningz.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lemoningz")
public class Profile {
    @PostMapping(path = "/employee/profile")
    public Map<String, Object> profile(@CookieValue String jwt) {
        Map<String, Object> res = new HashMap<>();
        String userid = JwtUtil.parseToken(jwt);
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT employee.*, team.name AS team_name FROM employee INNER JOIN team ON employee.team_id = team.id WHERE employee.id = ?");
            preparedStatement.setString(1,userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                res.put("id", resultSet.getInt("id"));
                res.put("first_name", resultSet.getString("first_name"));
                res.put("last_name", resultSet.getString("last_name"));
                res.put("position", resultSet.getString("position"));
                res.put("email", resultSet.getString("email"));
                res.put("status", resultSet.getString("status"));
                res.put("bio", resultSet.getString("bio"));
                res.put("about", resultSet.getString("about"));
                res.put("profile_url", resultSet.getString("profile_url"));
                res.put("team_name", resultSet.getString("team_name"));
                res.put("team_id", resultSet.getInt("team_id"));
            }
            res.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            res.put("success",false);
        }
        return res;
    }
    @PostMapping(path = "/employee/isAdmin")
    public Map<String, Object> isAdmin(@CookieValue String jwt) {
        Map<String, Object> res = new HashMap<>();
        String userid = JwtUtil.parseToken(jwt);
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE id = ?");
            preparedStatement.setString(1,userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                if (resultSet.getInt("is_admin") == 1){
                    res.put("isAdmin", true);
                }else{
                    res.put("isAdmin", false);
                }
            }
            res.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            res.put("success",false);
        }
        return res;
    }
}
