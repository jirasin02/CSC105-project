package com.jirasin02.lemoningz.controller;

import com.jirasin02.lemoningz.database.MySQLConnector;
import com.jirasin02.lemoningz.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/lemoningz")
public class Event {
    @PostMapping(path = "/calendar/events")
    public Map<String, Object> listEvent(@CookieValue String jwt) {
        Map<String, Object> res = new HashMap<>();
        String userid = JwtUtil.parseToken(jwt);
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM event WHERE employee_id = ?");
            preparedStatement.setString(1, userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Object> events = new ArrayList<>();
            while (resultSet.next()) {
                Map<String,Object> info = new HashMap<>();
                info.put("id", resultSet.getString("id"));
                info.put("name", resultSet.getString("name"));
                info.put("color", resultSet.getString("color"));
                info.put("start", resultSet.getString("start_date"));
                info.put("end", resultSet.getString("end_date"));
                events.add(info);
            }
            res.put("events",events);
            res.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("success", false);
        }
        return res;
    }

    @PostMapping(path = "/calendar/addEvent")
    public Map<String, Object> addEvent(@CookieValue String jwt, @RequestBody com.jirasin02.lemoningz.dto.Event event) {
        Map<String, Object> res = new HashMap<>();
        String userid = JwtUtil.parseToken(jwt);
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO event (name, color, start_date, end_date, employee_id) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, event.getName());
            preparedStatement.setString(2, event.getColor());
            preparedStatement.setString(3, event.getStart_date());
            preparedStatement.setString(4, event.getEnd_date());
            preparedStatement.setString(5, userid);
            preparedStatement.execute();
            res.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("success", false);
        }
        return res;
    }
    @PostMapping(path = "/calendar/editEvent")
    public Map<String, Object> editEvent(@CookieValue String jwt, @RequestBody com.jirasin02.lemoningz.dto.Event event) {
        Map<String, Object> res = new HashMap<>();
        String userid = JwtUtil.parseToken(jwt);
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE event SET name = ?, color = ?, start_date = ?, end_date = ? WHERE employee_id = ? AND id = ?");
            preparedStatement.setString(1, event.getName());
            preparedStatement.setString(2, event.getColor());
            preparedStatement.setString(3, event.getStart_date());
            preparedStatement.setString(4, event.getEnd_date());
            preparedStatement.setString(5, userid);
            preparedStatement.setInt(6, event.getId());
            preparedStatement.executeUpdate();
            res.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("success", false);
        }
        return res;
    }
    @PostMapping(path = "/calendar/deleteEvent")
    public Map<String, Object> deleteEvent(@CookieValue String jwt, @RequestBody com.jirasin02.lemoningz.dto.Event event) {
        Map<String, Object> res = new HashMap<>();
        String userid = JwtUtil.parseToken(jwt);
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM event WHERE employee_id = ? AND id = ?");
            preparedStatement.setString(1,userid);
            preparedStatement.setInt(2,event.getId());
            preparedStatement.execute();
            res.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            res.put("success", false);
        }
        return res;
    }
}