/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CarDTO;
import dtos.OrderDTO;
import dtos.OrderDetailDTO;
import dtos.TypeDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class CarDAO {
    public List<CarDTO> getListCar(String carNameForSearch,int num,String rentDate,String returnDate,String radio,int page) throws SQLException{
        List<CarDTO> result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        String rd;
        if (radio.equals("name")) {
            rd="carName";
        } else {
            rd="typeID";
        }
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select carID,carName,color,quantity,price,nsx,typeID,img\n" +
                            "from tblCar\n" +
                            " where "+rd+" like ? and quantity>=? and status=1 \n" +
                            " and carID not in(select carID from tblOrder O,tblOrderDetail OD\n" +
                            "					where O.orderID=OD.orderID \n" +
                            "					and rentDate>=? and returnDate<=?)\n" +
                            "order by nsx\n"
                        + " offset (? - 1)*20 rows fetch next 20 rows only";;
                stm=conn.prepareStatement(sql);
                stm.setString(1, "%"+carNameForSearch+"%");
                stm.setInt(2, num);
                stm.setString(3, rentDate);
                stm.setString(4, returnDate);
                stm.setInt(5, page);
                rs=stm.executeQuery();
                while(rs.next()){
                    String carID=rs.getString("carID");
                    String carName=rs.getString("carName").trim();
                    String color=rs.getString("color");
                    int quantity=rs.getInt("quantity");
                    float price=rs.getFloat("price");
                    String nsx=rs.getString("nsx");
                    float rating=getRatingOfCar(carID);
                    String typeID=rs.getString("typeID");
                    String img=rs.getString("img");
                    if(result==null){
                        result=new ArrayList<>();
                    }
                    result.add(new CarDTO(carID, carName, color, quantity, price, nsx, rating, typeID, true, img));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public int getCountCarForUser(String carNameForSearch,int num,String rentDate,String returnDate,String radio) throws SQLException {
        int result = -1;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String rd;
        if (radio.equals("name")) {
            rd="carName";
        } else {
            rd="typeID";
        }
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select COUNT(carID) as num\n" +
"                            from tblCar\n" +
"                             where "+rd+" like ? and quantity>=? and status=1 \n" +
"                             and carID not in(select carID from tblOrder O,tblOrderDetail OD\n" +
"                            					where O.orderID=OD.orderID \n" +
"                            					and rentDate>=? and returnDate<=?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%"+carNameForSearch+"%");
                stm.setInt(2, num);
                stm.setString(3, rentDate);
                stm.setString(4, returnDate);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("num");
                    if (result >= 0) {
                        return result;
                    }
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
    public List<TypeDTO> getListType() throws SQLException{
        List<TypeDTO> result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select typeID,typeName\n" +
                            "from tblTypeCar";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                while(rs.next()){
                    if(result==null){
                        result=new ArrayList<>();
                    }
                    String typeID=rs.getString("typeID");
                    String typeName=rs.getString("typeName");
                    result.add(new TypeDTO(typeID, typeName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public boolean checkDate(String rentDate,String returnDate){
        if(rentDate==null || returnDate==null){
            return false;
        }        
        rentDate=rentDate.replace('-', '/');
        returnDate=returnDate.replace('-', '/');
        Date dateRent=new Date(rentDate);
        Date dateReturn=new Date(returnDate);
        Date currentDate=new Date();
//        System.out.println("Current"+currentDate);
//        System.out.println("Rent"+dateRent);
//        System.out.println("Return"+dateReturn);
        if(dateRent.compareTo(currentDate)==-1){
            return false;
        }
        if(dateRent.compareTo(dateReturn)>0){
            return false;
        }
        return true;
    }
    public float getPriceOfCar(String carID) throws SQLException{
        float result = 0;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select price\n" +
                        "from tblCar\n" +
                        "where carID=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, carID);
                rs=stm.executeQuery();
                while(rs.next()){
                    result=rs.getFloat("price");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public long getDateBetween(String dateRent,String dateReturn) throws ParseException{
        long numDay = 1;
        if (!dateRent.isEmpty() && !dateReturn.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date in = sdf.parse(dateRent);
            Date out = sdf.parse(dateReturn);
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(in);
            c2.setTime(out);
            numDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
            return numDay+1;
        }
        return numDay;
    }
    public int getCountOrder() throws SQLException {
        int result = -1;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select COUNT(orderID) AS num\n"
                        + "from tblOrder ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("num");
                    if (result >= 0) {
                        return result + 1;
                    }
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
    public int getCountOrderDetail() throws SQLException{
        int result=-1;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select COUNT(detailID) AS num\n" +
                            "from tblOrderDetail ";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                if(rs.next()){
                    result=rs.getInt("num");
                    if(result>=0){
                        return result+1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public void insertOrder(OrderDTO dto) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="insert into tblOrder\n" +
                            "values (?,?,?,?,?)";
                stm=conn.prepareStatement(sql);
                stm.setString(1, dto.getOrderID());
                stm.setString(2, dto.getEmail());
                stm.setFloat(3, dto.getTotalPrice());
                stm.setString(4, dto.getOrderDate());
                stm.setBoolean(5, dto.isStatus());
                stm.executeUpdate();
            }  
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    public void insertOrderDetail(OrderDetailDTO dto) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="insert into tblOrderDetail(detailID,orderID,carID,amount,rentDate,returnDate,price,status)\n" +
                            "values (?,?,?,?,?,?,?,?)";
                stm=conn.prepareStatement(sql);
                stm.setString(1, dto.getDetailID());
                stm.setString(2, dto.getOrderID());
                stm.setString(3, dto.getCarID());
                stm.setInt(4, dto.getAmount());
                stm.setString(5, dto.getRentDate());
                stm.setString(6, dto.getReturnDate());
//                stm.setFloat(7, dto.getRate());
                stm.setFloat(7, dto.getPrice());
                stm.setBoolean(8, dto.isStatus());             
                stm.executeUpdate();
            }  
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    public List<OrderDetailDTO> getListOrderDetail(String email) throws SQLException{
        List<OrderDetailDTO> result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select OD.detailID,OD.orderID,OD.carID,C.carName,amount,OD.price,rentDate,returnDate,rate\n" +
                            "from tblOrderDetail OD,tblCar C,tblOrder O\n" +
                            "where OD.carID=C.carID and OD.status=1 and OD.orderID=O.orderID and O.email=?\n"
                        + " order by rentDate";
                stm=conn.prepareStatement(sql);
                stm.setString(1, email);
                rs=stm.executeQuery();
                while(rs.next()){
                    if(result==null){
                        result=new ArrayList<>();
                    }
                    String detailID=rs.getString("detailID");
                    String orderID=rs.getString("orderID");
                    String carID=rs.getString("carID");
                    String carName=rs.getString("carName");
                    int amount=rs.getInt("amount");
                    float price=rs.getFloat("price");
                    String rentDate=rs.getString("rentDate");
                    String returnDate=rs.getString("returnDate");
                    float rate=rs.getFloat("rate");
                    OrderDetailDTO ord=new OrderDetailDTO(detailID, orderID, carID,carName, amount, rentDate, returnDate, rate, price, true);
                    checkStatusCancelAndRating(rentDate, returnDate, ord);
                    result.add(ord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public List<OrderDetailDTO> getListOrderDetailForSearch(String email,String searchCarName,String orderDate) throws SQLException{
        List<OrderDetailDTO> result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select OD.detailID,OD.orderID,OD.carID,C.carName,amount,OD.price,rentDate,returnDate,rate\n" +
                            "from tblOrderDetail OD,tblCar C,tblOrder O\n" +
                            "where OD.carID=C.carID and OD.status=1 and OD.orderID=O.orderID and O.email=? and C.carName like ? and O.signDate=? \n"
                        + " order by rentDate";
                stm=conn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, "%"+searchCarName+"%");
                stm.setString(3, orderDate);
                rs=stm.executeQuery();
                while(rs.next()){
                    if(result==null){
                        result=new ArrayList<>();
                    }
                    String detailID=rs.getString("detailID");
                    String orderID=rs.getString("orderID");
                    String carID=rs.getString("carID");
                    String carName=rs.getString("carName");
                    int amount=rs.getInt("amount");
                    float price=rs.getFloat("price");
                    String rentDate=rs.getString("rentDate");
                    String returnDate=rs.getString("returnDate");
                    float rate=rs.getFloat("rate");
                    OrderDetailDTO ord=new OrderDetailDTO(detailID, orderID,carID, carName, amount, rentDate, returnDate, rate, price, true);
                    checkStatusCancelAndRating(rentDate, returnDate, ord);
                    result.add(ord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public static void checkStatusCancelAndRating(String rentDate,String returnDate,OrderDetailDTO ord){
        Date currentDate=new Date();
        rentDate=rentDate.replace("-", "/");
        returnDate=returnDate.replace("-", "/");
        Date DateRent=new Date(rentDate);
        Date DateReturn=new Date(returnDate);
        if(currentDate.compareTo(DateRent)<0){
            ord.setStatusCancel(true);
        }
        if(currentDate.compareTo(DateReturn)==1){
            ord.setStatusRate(true);
        }      
    }
    public void cancelDetail(String detailID) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="update tblOrderDetail\n" +
                            " set status=0\n" +
                            " where detailID=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, detailID);
                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    public void updatePriceOrder(String detailID,String orderID) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="update tblOrder\n" +
                            " set totalPrice=totalPrice - (select price from tblOrderDetail where detailID=?)\n" +
                            " where orderID=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, detailID);
                stm.setString(2, orderID);
                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    public void rateDetail(String detailID,float point) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            System.out.println(detailID+"????"+point);
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="update tblOrderDetail\n" +
                            " set rate=?\n" +
                            " where detailID=?";
                stm=conn.prepareStatement(sql);
                stm.setFloat(1, point);
                stm.setString(2, detailID);
                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    public void updateRatingPoint(String carID) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="update tblCar\n" +
                            "set rating=(select AVG(rate) as point\n" +
                            "				from tblOrderDetail\n" +
                            "				where rate >=1 and rate <=10 and carID=?)\n" +
                            "where carID=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, carID);
                stm.setString(2, carID);
                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    public static float getRatingOfCar(String carID) throws SQLException{
        float result=0;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select AVG(rate) as point\n" +
                        "from tblOrderDetail\n" +
                        "where rate >=1 and rate <=10 and carID=? and status=1";
                stm=conn.prepareStatement(sql);
                stm.setString(1, carID);
                rs=stm.executeQuery();
                if(rs.next()){
                    result=rs.getFloat("point");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public float getDiscount(String codeDiscount) throws SQLException{
        float result=0;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String currentDate=simpleDateFormat.format(date);       
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select phanTram\n" +
                    "from tblDiscount\n" +
                    "where code=? and status=1 and ?<=expiryDate";
                stm=conn.prepareStatement(sql);
                stm.setString(1, codeDiscount);
                stm.setString(2, currentDate);
                rs=stm.executeQuery();
                if(rs.next()){
                    result=rs.getFloat("phanTram");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public void removeDiscount(String codeDiscount) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="update tblDiscount\n" +
                                "set status=0\n" +
                                "where code=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, codeDiscount);
                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    public boolean checkDetail(String carID,int quantiry,String rentDate,String returnDate) throws SQLException{
        boolean result=false;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String currentDate=simpleDateFormat.format(date);       
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select carName,quantity\n" +
                        "from tblCar\n" +
                        "where carID=? and quantity>=? and status=1 \n" +
                        "and ? not in(select carID from tblOrder O,tblOrderDetail OD\n" +
                        "				where O.orderID=OD.orderID \n" +
                        "				and rentDate>=? and returnDate<=?)";
                stm=conn.prepareStatement(sql);
                stm.setString(1, carID);
                 stm.setInt(2, quantiry);
                stm.setString(3, carID);
                 stm.setString(4, rentDate);
                  stm.setString(5, returnDate);
                rs=stm.executeQuery();
                while(rs.next()){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public boolean checkQuanlity(String carID,int quantity) throws SQLException{
        boolean result=false;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String currentDate=simpleDateFormat.format(date);       
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select carName\n" +
                            "from tblCar\n" +
                            "where carID=? and quantity>=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, carID);
                stm.setInt(2, quantity);
                rs=stm.executeQuery();
                while(rs.next()){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
}
