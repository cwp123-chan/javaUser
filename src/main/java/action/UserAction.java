package action;

import dao.UserDao;
import entity.User;
import ui.UserView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserAction {
    public void run(){
        while(true){
            menu();
            String op = (new Scanner(System.in)).nextLine();
            Integer opS = new Integer(op);
            int opNum = opS.intValue();
            if(opNum == 0){
                return;
            }
            action(opNum);
        }
    }
    public void menu(){
        System.out.println("========用户管理系统========");
        System.out.println("(1):添加用户");
        System.out.println("(2):删除用户");
        System.out.println("(3):更新用户");
        System.out.println("(4):查询用户");
        System.out.println("(0):返回首页");


    }

    public void action(int opNum){
        switch(opNum){
            case 1 :addUser();break;
            case 2 :delUser();break;
            case 3 :editUser();break;
            case 4 :showUser();break;
            case 0 :menu();break;
            default:System.exit(0);

        }

    }
    public void addUser(){
        // 这边 定义一个map 因为 上面的List 会因为User 对象 的问题 导致 值覆盖 等到数据库连接时可以解决
        Scanner scan = new Scanner(System.in);
        List<User> list = new ArrayList<User>();
        UserView UserView = new UserView();
        String cmd = "next";
        while(cmd.equals("next")) {
            User user = new User();
            List ageList =  UserView.addUser();
            String str = (String)ageList.get(0);
            String ageInt = (String)ageList.get(1);
            if (str.equals("exit")){
//                输入exit 跳转 首页
                menu();
                return;
            }
            Pattern pattern = Pattern.compile("[0-9]*");
            boolean sta = pattern.matcher(ageInt).matches();
            if(!sta){
                UserView.einfo(1);
            }else{
                int i = str.length();
                int k = ageInt.length();
                if (str.equals("") || ageInt.equals("")){
                    UserView.einfo(2);
                    if(ageInt.equals("exit") || str.equals("exit")){
//                输入exit 跳转 首页
                        menu();
                    }
                }else if(ageInt.equals("exit") || str.equals("exit")){
//                输入exit 跳转 首页
                    menu();
                }else {
                    if (i != 0) {
                        user.setName(str);
                        String nameStr = user.getName();

                    } else {
                        UserView.einfo(3);
                    }

                    if (k != 0) {
                        // 将 string 转为 integer
                        Integer ageInteger = Integer.valueOf(ageInt);
                        user.setAge(ageInteger);
                        String userName = user.getName();
                        Integer userAge = user.getAge();
                        String userAges = Integer.toString(userAge);
//                    将数据放入 list 进行存储数据库
                        list.add(user);
//                    System.out.println(list);
                        UserDao Dao = new UserDao();
                        Dao.addUser("next", userName, userAges);

                    } else {
                        UserView.einfo(3);
                    }
                    String cmdStr = UserView.menuInfo();
                    cmd = cmdStr;
                }
            }
            if(cmd.equals("edit")){
                editUser();
                break;
            }else{
                System.exit(0);
            }

            int numbs = -1;
            String numbMark = "up";
//            进行分页 与 显示
            while(cmd.equals("show")) {
                if(numbMark.equals("up")){
                    numbs++;
                }else if(numbMark.equals("down")){
                    numbs--;

                    if(numbs < 0){
                        numbs = 0;
                    }
                }
                UserDao toDao = new UserDao();

                toDao.showUser(numbs);
                String cmdNext = UserView.limitMenu();
//                判断 分页按钮 键位
                if(cmdNext.equals("2")){
                    cmd = "show";
                    numbMark = "up";
                }else if(cmdNext.equals("1")){
                    cmd = "show";
                    numbMark = "down";
                }else if(cmdNext.equals("3")){
                    String cmdStr = UserView.menuInfo();
                    cmd = cmdStr;
                }
            }
            if (cmd.equals("del")) {
                delUser();
            }else if (cmd.equals("edit")) {
                editUser();
            }else{
                System.exit(0);
            }
        }
    }
    public void showUser(){
        UserDao Dao = new UserDao();
        UserView UserView = new UserView();
        Scanner scan = new Scanner(System.in);
        String cmd = "show";
        int numbs = -1;
        String numbMark = "up";
//            进行分页 与 显示
        while(cmd.equals("show")) {
            if(numbMark.equals("up")){
                numbs++;
            }else if(numbMark.equals("down")){
                numbs--;

                if(numbs < 0){
                    numbs = 0;
                }
            }
            Dao.showUser(numbs);
            String cmdNext = UserView.limitMenu();
//                判断 分页按钮 键位
            if(cmdNext.equals("2")){
                cmd = "show";
                numbMark = "up";
            }else if(cmdNext.equals("1")){
                cmd = "show";
                numbMark = "down";
            }else if(cmdNext.equals("3")){
                String cmdStr = UserView.showUser();
                if(cmdStr.equals("edit")){
                    editUser();
                    cmd = cmdStr;
                }else if(cmdStr.equals("add")){
                    addUser();
                    cmd = cmdStr;
                }else if(cmdStr.equals("del")){
                    delUser();
                    cmd = cmdStr;
                }else if(cmdStr.equals("0")){
                    menu();
                    cmd = cmdStr;
                }else{
                    System.exit(0);
                }
            }
        }
    }

    public void editUser(){
        Scanner scanNum = new Scanner(System.in);
        UserView UserView = new UserView();
        String editUserNub = UserView.editUser();
        UserDao UserDao = new UserDao();
        UserDao.editUser(editUserNub);
        String remark = UserView.menuInfo();
        if(remark.equals("0")){
            menu();
        }else if(remark.equals("show")){
            showUser();
        }else if(remark.equals("del")){
            delUser();
        }else if(remark.equals("edit")){
            editUser();
        }else if(remark.equals("add")){
            addUser();
        }else{
            System.exit(0);
        }


    }
    public void delUser(){
        UserView UserView = new UserView();
        Scanner scanNum = new Scanner(System.in);

        String editUserNub = UserView.delUser();
        UserDao Dao = new UserDao();

        Dao.delUser(editUserNub);
        String remark = UserView.menuInfo();
        if(remark.equals("0")){
            menu();
        }else if(remark.equals("show")){
            showUser();
        }else if(remark.equals("del")){
            delUser();
        }else if(remark.equals("edit")){
            editUser();
        }else if(remark.equals("add")){
            addUser();
        }else{
            System.exit(0);
        }
    }

}
