/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dtos.UserDTO;
import java.security.NoSuchAlgorithmException;
import utils.DBUtils;

/**
 *
 * @author minhv
 */
public class UserDAO {
    public UserDTO checkLogin(String email, String password) throws SQLException {
        UserDTO result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select email,fullName,password,phone,address,createDate,roleID\n" +
                                "from tblUser\n" +
                                "where email=? and password=? and status=1";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String phone=rs.getString("phone");
                    String address=rs.getString("address");
                    String createDate=rs.getString("createDate");
                    String roleID = rs.getString("roleID");
                    result=new UserDTO(email, fullName, "", phone, address, createDate, roleID, true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    

    
    public void createNew(UserDTO user) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException{
        System.out.println("U"+user);
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="insert into tblUser(email,fullName,password,phone,address,createDate,roleID,status)\n" +
                            "values(?,?,?,?,?,?,?,?)";
                stm=conn.prepareStatement(sql);
                stm.setString(1, user.getEmail());
                stm.setString(2, user.getFullName());
                stm.setString(3, user.getPassword());
                stm.setString(4, user.getPhone());
                stm.setString(5, user.getAddress());
                stm.setString(6, user.getCreateDate());
                stm.setString(7, user.getRoleID());
                stm.setBoolean(8,user.isStatus());
                stm.executeUpdate();
            }  
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    public boolean checkNumber(String phone){
        int count=0;
        char[] nums=phone.toCharArray();
        for (char num : nums) {
            if(Character.isDigit(num)){
                count++;
            }else{
                return false;
            }
        }
        if(count==phone.length())
            return true;
        return false;
    }
    public void updateStatusUser(String email) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="update tblUser\n" +
                        "set status=1\n" +
                        "where email=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, email);
                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
}
