package com.example.costtrack.Handlers;

import com.example.costtrack.Entity.CostEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CostHandler {
    public static boolean Insert(CostEntity en){
        PreparedStatement preparedStatement = null;
        try {
            DatabaseHandler h = new DatabaseHandler();
            Connection con=h.conclass();
            String sql = "INSERT INTO Cost (Type, Amount, Name,Category) VALUES (?, ?, ?,?)";

            assert con != null;
            preparedStatement = con.prepareStatement(sql);


            preparedStatement.setString(1, en.getType());
            preparedStatement.setInt(2, en.getAmount());
            preparedStatement.setString(3, en.getName());
            preparedStatement.setInt(4,en.getCat());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                preparedStatement.close();
                con.close();
            }
            return true;

        } catch (Exception e) {
            return false;
        }

    }
    public static boolean Update(CostEntity en){
        PreparedStatement preparedStatement = null;
        try {
            DatabaseHandler h = new DatabaseHandler();
            Connection con = h.conclass();
            String sql = "UPDATE Cost SET Type = ?, Amount = ?, Name = ?, Category = ? WHERE id = ?";

            assert con != null;
            preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, en.getType());
            preparedStatement.setInt(2, en.getAmount());
            preparedStatement.setString(3, en.getName());
            preparedStatement.setInt(4, en.getCat());
            preparedStatement.setInt(5, en.getID());


            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                preparedStatement.close();
                con.close();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean Delete(int id){
        PreparedStatement preparedStatement = null;
        try {
            DatabaseHandler h = new DatabaseHandler();
            Connection con = h.conclass();

            String sql = "DELETE FROM Cost WHERE id = ?";

            assert con != null;
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                preparedStatement.close();
                con.close();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static CostEntity[] getCosts() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CostEntity> costList = new ArrayList<>();
        Connection con;
        try {
            DatabaseHandler h = new DatabaseHandler();
            con = h.conclass();
            String sql = "SELECT ID,Type, Amount, Name, Category FROM Cost";

            assert con != null;
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CostEntity cost = new CostEntity(resultSet.getInt("ID"),resultSet.getString("Type"),resultSet.getInt("Amount"),resultSet.getString("Name"),resultSet.getInt("Category"));
                costList.add(cost);
            }

            CostEntity[] costsArray = new CostEntity[costList.size()];
            resultSet.close();
            costsArray = costList.toArray(costsArray);
            preparedStatement.close();
            con.close();
            return costsArray;
        } catch (Exception e) {
            e.printStackTrace();
            return new CostEntity[0];
        }

    }

    public static CostEntity getCost(int id) {
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                Connection con;
                CostEntity cost =new CostEntity(0,"",0,"",0);
                try {
                    DatabaseHandler h = new DatabaseHandler();
                    con = h.conclass();
                    String sql = "SELECT ID,Type, Amount, Name,Category FROM Cost WHERE ID = ?";
            assert con != null;
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cost = new CostEntity(resultSet.getInt("ID"),resultSet.getString("Type"),resultSet.getInt("Amount"),resultSet.getString("Name"),resultSet.getInt("Category"));
            }

            resultSet.close();
            preparedStatement.close();
            con.close();
            return cost;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
