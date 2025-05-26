package LMS;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("메뉴선택: 1. 관리자  2. 학생  3. 시스템 종료");
            System.out.print("선택: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    //관리자 메뉴로 이동
                    ManagerMenu.showManagerMenu(scanner);
                    break;
                case "2":
                    //학생 메뉴로 이동
                    StudentMenu.showStudentMenu(scanner);
                    break;
                case "3":
                    System.out.println("시스템을 종료합니다.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.\n");
            }
        }
    }
}