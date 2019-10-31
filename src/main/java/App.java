import action.UserAction;

import java.util.Scanner;

public class App {
    public static void main (String[] args){
        App app = new App();
        app.run();
    }
    private void run (){
        welcome();
        while(true){
            menu();
            String op = (new Scanner(System.in)).nextLine();
            if (op.equals("0")){
                return;
            }
            Integer opStr = new Integer(op);
            int opNum = opStr.intValue();
            action(opNum);
        }
    }

    public void welcome(){
        System.out.println("Welcome");
        System.out.println("Version 1.0.0");
        System.out.println("=======信息管理=======");

    }
    public void menu(){
        System.out.println("[1]:用户管理");
        System.out.println("[2]:订单管理");
        System.out.println("[0]:退出");
    }
    public void action(int opNum){
        System.out.println(opNum);
        switch(opNum){
            case 1: (new UserAction()).run();break;
            case 2 : System.out.println("...............");break;
            case 0 : System.exit(0);

        }
    }
    public void exit(){
        System.out.println("已退出...");
    }
}
