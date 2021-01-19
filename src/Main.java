import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalStateException {
        LibraryDaoRunning LibraryMethod = new LibraryDaoRunning();
        System.out.println("---------------------欢迎来到 啥都有图书馆 -------------------\n");
        System.out.println("我是您的助手 西兔" + '\n' + "西兔真诚为您服务~~~~~~");
        System.out.println("亲~~~您需要登录您的账户呦~~~");
        Scanner scanner = new Scanner(System.in);
        System.out.println("亲~ 请您输入用户名~~~");
        String username = scanner.nextLine();
        System.out.println("亲~ 请您输入密码~~~");
        String password = scanner.nextLine();

        boolean flag = new Login().login(username, password);
        while (!flag) {
            System.out.println("亲~ 您输入的用户名或密码有误哦~~~");
            System.out.println("亲~ 请您输入用户名~~~");
            username = scanner.next();
            System.out.println("亲~ 请您输入密码~~~");
            password = scanner.nextLine();
            flag = new Login().login(username, password);
        }
        System.out.println("亲~ 登录成功了呦~~~");
        System.out.println("~亲~ 请选择你想选择的服务" + '\n' + "1.图书查询服务  2.借书服务  3.还书服务");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                System.out.println("亲~~ 您想按照书名查询还是按照作者查询呢？" + '\n' + "1.书名 2.作者");
                int choice1 = scanner.nextInt();
                switch (choice1) {
                    case 1: {
                        System.out.println("请输入您想查询的书名");
                        String book_name = scanner.next();
                        LibraryMethod.SearchBookByName(book_name);

                    }
                    break;

                    case 2 : {
                        System.out.println("请输入您想查询的作者名");
                        String author = scanner.next();
                        LibraryMethod.SearchBookByAuthor(author);

                    }
                    default : {
                        throw new IllegalStateException("Unexpected value: " + choice1);

                    }

                }


            }
            break;

            case 2: {
                System.out.println("亲~~~请输入所借图书的编号：");
                String book_id = scanner.next();
                System.out.println("亲~~~请您输入所借图书名：");
                String book_name = scanner.next();
                LibraryMethod.BorrowBook(book_id,username ,book_name);



            }
            break;

            case 3: {
                System.out.println("亲~~~请输入要归还的图书的编号：");
                String book_id = scanner.next();
                System.out.println("亲~~~请您输入所归还图书名：");
                String book_name = scanner.next();
                LibraryMethod.ReturnBook(book_id,username,book_name);
                System.out.println("还书成功！");
            }
            default:
                System.out.println("操作无效！");
        }


    }


}

//所有方法经测试均能正常运行！！！

//username： 032005101
//password ： 123456

//1. 图书查询  --> 图书查询服务 -->  1.书名查询 --> 斗罗大陆
//图书编号：111111   书名：斗罗大陆    作者：唐家三少   出借状态：true
//2.借书：
// 444444 --> 一个亿远远不够哦 --> 借书成功
//如果该书已被借阅 则借书失败！！！

