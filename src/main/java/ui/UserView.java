package ui;

import java.util.*;

public class UserView {
    public List addUser(){
        Scanner scan = new Scanner(System.in);
        List addUserList = new ArrayList();
        System.out.println("请输入你的名字 [输入exit返回]:");
        String str = scan.next();
        System.out.println("请输入你的年龄 [输入exit返回]:");
        String ageInt = scan.next();

        addUserList.add(str);
        addUserList.add(ageInt);
        return addUserList;

    }
    public String showUser(){
        Scanner scan = new Scanner(System.in);
        System.out.println("[0]:返回首页 [add]:添加用户 [edit]:编辑用户 [del]:删除用户 \r\n\r\n");
        String userInt = scan.next();
        return userInt;
    }
    public String editUser(){
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入所需修改的用户编号");

        String idInt = scan.next();
        return idInt;
    }
    public String delUser(){
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入所需删除的用户编号");
        String idInt = scan.next();
        return idInt;
    }

    public void einfo(int Code){
        switch(Code){
            case 1: System.out.println("年龄格式不正确....\r\n \r\n");break;
            case 2: System.out.println("姓名或者年龄不能为空请重新输入....\r\n \r\n");break;
            case 3: System.out.println("暂无输入");break;

        }
    }
    public String menuInfo(){
        Scanner scan = new Scanner(System.in);
        System.out.println("[0]:返回首页 [next]:继续录入 [del]:删除用户 [edit]:编辑用户 [show]:查询用户 \r\n\r\n");
        String cmdStr = scan.next();
        return cmdStr;

    }
    public String limitMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("1:上一页"+"\t"+"2:下一页" +"\t"+ "3:菜单");
        String cmdNext = scan.next();
        return cmdNext;

    }
}
