package com.jirasin02.lemoningz.controller;

import com.jirasin02.lemoningz.database.MySQLConnector;
import com.jirasin02.lemoningz.dto.Employee;
import com.jirasin02.lemoningz.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lemoningz")
public class EditProfile {
    @PostMapping(path = "/profile/editBio")
    public Map<String, Object> editBio(@CookieValue String jwt, @RequestBody Employee employee) {
        Map<String, Object> res = new HashMap<>();
        String userid = JwtUtil.parseToken(jwt);
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET bio = ? WHERE id = ?");
            preparedStatement.setString(1,employee.getBio());
            preparedStatement.setString(2,userid);
            preparedStatement.executeUpdate();
            res.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            res.put("success",false);
        }
        return res;
    }
    @PostMapping(path = "/profile/editAbout")
    public Map<String, Object> editAbout(@CookieValue String jwt, @RequestBody Employee employee) {
        Map<String, Object> res = new HashMap<>();
        String userid = JwtUtil.parseToken(jwt);
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET about = ? WHERE id = ?");
            preparedStatement.setString(1,employee.getAbout());
            preparedStatement.setString(2,userid);
            preparedStatement.executeUpdate();
            res.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            res.put("success",false);
        }
        return res;
    }
}
