package manager;


import java.sql.Connection;
import java.util.Scanner;

public class Login {
    public static void managerlogin() {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n[관리자 로그인 메뉴]");
            System.out.print("아이디 입력>> ");
            String id = sc.next().toLowerCase();

            System.out.print("비번 입력>> ");
            String pw = sc.next().toLowerCase();

            LoginSVC loginSVC = new LoginSVC();

            User user = loginSVC.login(id, pw);
            System.out.println("User:::::::" + user);
            if (user == null) {
                System.out.println("로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다. 다시 시도하세요.\n");
            } else {
                System.out.println("로그인 성공! 관리자 화면으로 이동합니다.\n");

                break;
            }
        }

    }
}