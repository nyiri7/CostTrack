package com.example.costtrack.Handlers;

import com.example.costtrack.CostCategory;
import com.example.costtrack.Entity.CostCategoryEntity;
import com.example.costtrack.Entity.CostEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CostCategoryHandler {
    public static boolean Insert(CostCategoryEntity en){
        PreparedStatement preparedStatement = null;
        try {
            DatabaseHandler h = new DatabaseHandler();
            Connection con=h.conclass();
            String sql = "INSERT INTO CostCategory (Name) VALUES (?)";

            assert con != null;
            preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, en.getName());

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
    public static boolean Update(CostCategoryEntity en){
        PreparedStatement preparedStatement = null;
        try {
            DatabaseHandler h = new DatabaseHandler();
            Connection con = h.conclass();
            String sql = "UPDATE CostCategory SET Name = ? WHERE id = ?";

            assert con != null;
            preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, en.getName());
            preparedStatement.setInt(2, en.getId());

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

            String sql = "DELETE FROM CostCategory WHERE id = ?";

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

    public static CostCategoryEntity[] getAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CostCategoryEntity> catList = new ArrayList<>();
        Connection con;
        try {
            DatabaseHandler h = new DatabaseHandler();
            con = h.conclass();
            String sql = "SELECT Id, Name FROM CostCategory";

            assert con != null;
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CostCategoryEntity cost = new CostCategoryEntity(resultSet.getString("Name"),resultSet.getInt("ID"));
                catList.add(cost);
            }

            CostCategoryEntity[] costsArray = new CostCategoryEntity[catList.size()];
            resultSet.close();
            costsArray = catList.toArray(costsArray);
            preparedStatement.close();
            con.close();
            return costsArray;
        } catch (Exception e) {
            e.printStackTrace();
            return new CostCategoryEntity[0];
        }

    }
    public static CostCategoryEntity getOne(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection con;
        CostCategoryEntity cost =new CostCategoryEntity("",0);
        try {
            DatabaseHandler h = new DatabaseHandler();
            con = h.conclass();
            String sql = "SELECT ID, Name FROM CostCategory WHERE ID = ?";
            assert con != null;
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cost = new CostCategoryEntity(resultSet.getString("Name"),resultSet.getInt("ID"));
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
