package manager;

import java.util.Scanner;

public class ManagerMenu {
    public static void showManagerMenu(Scanner scanner){
        while (true) {
            System.out.println("관리자 메뉴 시작");
            System.out.print("1. 관리자 로그인\t");
            System.out.print("2. 관리자 가입\t");
            System.out.println("3. 홈으로");
            System.out.print("선택: ");
            int input = scanner.nextInt();

            switch (input) {
                case 1:
                    Login.managerlogin();
                    break;
                case 2:
                    System.out.println("관리자 가입 기능은 아직 구현되지 않았습니다.\n");
                    break;
                case 3:
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.\n");
            }
        }
    }
}