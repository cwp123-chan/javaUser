package dao;
import util.Config;

import java.sql.*;
import java.util.Scanner;

public class UserDao {
    Connection conn;
    {
        try{
            String userName = new Config().getStringByKey("username");
            String pwd = new Config().getStringByKey("password");
            String url = new Config().getStringByKey("url");
            String driver = new Config().getStringByKey("driver");
            //连接对象
            this.conn = DriverManager.getConnection(url, userName, pwd);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(String func,String userName,String userAge){
        try {

            String sql = "INSERT INTO user (name,age) VALUES (?,?)";
            //      进行jdbc预处理sql语句 防止 sql注入
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userName);
            stmt.setString(2, userAge);
            //新增一条数据
            if(stmt.execute()){
                System.out.println("INSERT SUCCESS");
            }
            ResultSet res = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            int userId;
            if (res.next()) {
                userId = res.getInt(1);
                System.out.println("newUSerID:" + userId);
            }
            if (conn!=null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showUser(int numbs){
        System.out.println(numbs);
        Integer total = 0;

        try{
            int markName = 10;
            String sql = "SELECT * FROM user where id>? order by id limit "+numbs*markName+","+markName;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 0);
            ResultSet selectRes = stmt.executeQuery();
            System.out.println("编号"+"\0\0\0"+"姓名"+"\t"+"年龄");
            while (selectRes.next()) { //循环输出结果集，next是移动到下一条记录，如果后面没有记录则返回false
                total++;
                int id = selectRes.getInt("id");
                String name = selectRes.getString("name");
                String age = selectRes.getString("age");
                System.out.println(id + "\t" + name + "\t" + age);
            }
            if(total < markName){
                System.out.println("=========没有更多用户=========");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void delUser(String userNumber){

        Scanner scaner = new Scanner(System.in);
        int userNumInt =Integer.parseInt(userNumber);
        String isAs = "0";

        try{
            String sql = "SELECT * FROM user where id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userNumInt);
            ResultSet selectRes = stmt.executeQuery();
            System.out.println("编号"+"\t"+"姓名"+"\t"+"年龄");
            while (selectRes.next()) { //循环输出结果集，next是移动到下一条记录，如果后面没有记录则返回false
                int id = selectRes.getInt("id");
                String name = selectRes.getString("name");
                String age = selectRes.getString("age");
                System.out.println(id + "\t" + name + "\t" + age);
                if(selectRes.next() == false){
//              判断是否存在用户 存在 则进入循环 标志为1 否则不进入循环 标志默认为0

                    isAs = "1";
                }
            }

            if(isAs.equals("1")) {

                System.out.println("确认删除该条用户信息?[Y/N]");
                String isOk = scaner.next().toLowerCase();
                if (isOk.equals("y")) {
                    isDelData(isOk, userNumber);
                } else {
                    System.out.println("已取消操作");
                }
            }else if(isAs.equals("0")){
                System.out.println("该用户不存在");
            }


        }catch(Exception e){
            e.printStackTrace();
        }


    }
    //    确认删除用户
    public void isDelData(String isOkMark,String userNumber){

        // 如果 用户确认 则 删除 根据 isOkMark 判断
        if(isOkMark.equals("y")){
            try {
                String sqlDel = "DELETE FROM user WHERE id = ?";
                PreparedStatement stmtDel = conn.prepareStatement(sqlDel);
                stmtDel = conn.prepareStatement(sqlDel);
                stmtDel.setString(1, userNumber);
                int deleteRes = stmtDel.executeUpdate(); //返回删除行数
                if(deleteRes == 1){
                    System.out.println("删除成功");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
        }
    }

    // 编辑用户
    public void editUser(String userNumber){
        Scanner scaner = new Scanner(System.in);
        int userNumInt =Integer.parseInt(userNumber);
        String isAs = "0";
        try{
            String sql = "SELECT * FROM user where id = ?";
//            System.out.println(sql);


            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userNumInt);
            ResultSet selectRes = stmt.executeQuery();
            System.out.println("编号"+"\0\0\0"+"姓名"+"\t"+"年龄");
            while (selectRes.next()) { //循环输出结果集，next是移动到下一条记录，如果后面没有记录则返回false
                int id = selectRes.getInt("id");
                String name = selectRes.getString("name");
                String age = selectRes.getString("age");
                System.out.println(id + "\t" + name + "\t" + age);
//                        判断是否存在用户 存在 则进入循环 标志为1 否则不进入循环 标志默认为0
//
                if(selectRes.next() == false){
//                        System.out.println(idS);
                    isAs = "1";
                }
            }
            // 判断用户是否存在



        }catch(Exception e){
            e.printStackTrace();
        }
// * 数据库一个链接 不能同时 执行 多个sql语句
        if(isAs.equals("1")) {
            try {
                System.out.println("请输入新的用户名");
                String newEditUser = scaner.next();
                System.out.println("请输入新的年龄");
                String newEditAge = scaner.next();
                System.out.println(userNumInt);
                String sqlEdit = "UPDATE user SET name = ?,age = " + newEditAge + " WHERE id = " + userNumInt;
                PreparedStatement stmts = conn.prepareStatement(sqlEdit);
                stmts.setString(1, newEditUser);

                int updateResNum = stmts.executeUpdate();//返回列新行数
                System.out.println(updateResNum);
                if (updateResNum == 1) {
                    System.out.println("编辑成功!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(isAs.equals("0")){
            System.out.println("该用户不存在");
        }

    }

}
