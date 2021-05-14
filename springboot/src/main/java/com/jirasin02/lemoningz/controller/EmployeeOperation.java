package com.jirasin02.lemoningz.controller;

import com.jirasin02.lemoningz.database.MySQLConnector;
import com.jirasin02.lemoningz.dto.Employee;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lemoningz")
public class EmployeeOperation {
    @PostMapping(path = "/employee/insert")
    public Map<String, Object> addEmployee(@RequestBody Employee employee){
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `db63130500202`.`employee` (`username`, `password`, `first_name`, `last_name`, `position`, `email`, `status`, `hire_date`, `address`, `phone`, `bio`, `about`, `profile_url`, `team_id`) VALUES ( ? , NULL , ? , ? , ? , ? , ?, ?, ?, ?, NULL, NULL, 'https://i.pinimg.com/originals/0c/3b/3a/0c3b3adb1a7530892e55ef36d3be6cb8.png', ?);");
            preparedStatement.setString(1, employee.getUsername());
            preparedStatement.setString(2, employee.getFirstname());
            preparedStatement.setString(3, employee.getLastname());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setString(6, employee.getStatus());
            preparedStatement.setString(7, employee.getHire_date());
            preparedStatement.setString(8, employee.getAddress());
            preparedStatement.setString(9, employee.getPhone());
            preparedStatement.setInt(10, employee.getTeam_id());
            preparedStatement.execute();
            res.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            res.put("success", false);
        }
        return res;
    }
    @PostMapping(path = "/employee/edit")
    public Map<String, Object> editEmployee(@RequestBody Employee employee, @RequestParam int id){
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET first_name = ?, last_name = ?, position = ?, email = ?, status = ?, hire_date = ?, address = ?, phone = ?, team_id = ? WHERE id = ?");
            preparedStatement.setString(1, employee.getFirstname());
            preparedStatement.setString(2, employee.getLastname());
            preparedStatement.setString(3, employee.getPosition());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setString(5, employee.getStatus());
            preparedStatement.setString(6, employee.getHire_date());
            preparedStatement.setString(7, employee.getAddress());
            preparedStatement.setString(8, employee.getPhone());
            preparedStatement.setInt(9, employee.getTeam_id());
            preparedStatement.setInt(10, id);
            preparedStatement.executeUpdate();
            res.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            res.put("success", false);
        }
        return res;
    }
    @PostMapping(path = "/employee/delete")
    public Map<String, Object> deleteEmployee(@RequestParam int id) {
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM event WHERE employee_id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            res.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            res.put("success", false);
        }
        return res;
    }
}
