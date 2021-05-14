package com.jirasin02.lemoningz.controller;

import com.jirasin02.lemoningz.database.MySQLConnector;
import com.jirasin02.lemoningz.dto.LoginDto;
import com.jirasin02.lemoningz.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lemoningz")
public class Login {
    @PostMapping(path = "/login")
    public Map<String, Object> login(@RequestBody LoginDto userdata, HttpServletResponse response){
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE username = ? AND password = ?");
            preparedStatement.setString(1, userdata.getUsername());
            preparedStatement.setString(2, userdata.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                String jwt = JwtUtil.generateToken(resultSet.getInt("id") + "");
                res.put("isLoggedIn", true);
                res.put("is_admin",resultSet.getInt("is_admin"));
                res.put("token", jwt);
                Cookie cookie = new Cookie("jwt", jwt);
                cookie.setPath("/");
                response.addCookie(cookie);
            }else{
                res.put("isLoggedIn", false);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
    @PostMapping("/logout")
    public void logout(HttpServletResponse response){
        try {
            Cookie cookie = new Cookie("jwt",null);
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
