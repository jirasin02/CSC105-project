package com.jirasin02.lemoningz.controller;

import com.jirasin02.lemoningz.database.MySQLConnector;
import com.jirasin02.lemoningz.dto.LoginDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lemoningz")
public class Setup {
    @PostMapping(path = "/setup")
    public Map<String, Object> setup(@RequestBody LoginDto userdata) {
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE username = ?");
            preparedStatement.setString(1,userdata.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                if (resultSet.getInt("is_create")==0){
                    preparedStatement = connection.prepareStatement("UPDATE employee SET password = ?, is_create = 1 WHERE username = ? ");
                    preparedStatement.setString(1, userdata.getPassword());
                    preparedStatement.setString(2, userdata.getUsername());
                    preparedStatement.executeUpdate();
                    res.put("success", true);
                }else{
                    res.put("success",false);
                }
            }else{
                res.put("notExisted",true);
            }
        }catch (Exception e){
            e.printStackTrace();
            res.put("success",false);
        }
        return res;
    }

}
