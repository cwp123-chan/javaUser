package dao;

import util.Config;

import java.sql.Connection;
import java.sql.DriverManager;

//此类 用于封装数据库连接 待完善 可不用
public class ConectMsq {
    {
        try{
            String userName = new Config().getStringByKey("username");
            String pwd = new Config().getStringByKey("password");
            String url = new Config().getStringByKey("url");
            String driver = new Config().getStringByKey("driver");
            //连接对象
            Connection conn = DriverManager.getConnection(url, userName, pwd);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
