package com.jirasin02.lemoningz.controller;

import com.jirasin02.lemoningz.database.MySQLConnector;
import com.jirasin02.lemoningz.dto.Employee;
import com.jirasin02.lemoningz.dto.Team;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lemoningz")
public class TeamOperation {
    @PostMapping(path = "/team/insert")
    public Map<String, Object> addTeam(@RequestBody Team team){
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `db63130500202`.`team` (`name`, `team_pic`) VALUES ( ? , NULL);");
            preparedStatement.setString(1, team.getName());
            preparedStatement.execute();
            res.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            res.put("success", false);
        }
        return res;
    }
    @PostMapping(path = "/team/edit")
    public Map<String, Object> editTeam(@RequestBody Team team, @RequestParam int id){
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE team SET name = ? WHERE id = ?");
            preparedStatement.setString(1,team.getName());
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
            res.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            res.put("success", false);
        }
        return res;
    }
    @PostMapping(path = "/team/delete")
    public Map<String, Object> deleteTeam(@RequestParam int id) {
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET team_id = 0 WHERE team_id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("DELETE FROM team WHERE id = ?");
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
