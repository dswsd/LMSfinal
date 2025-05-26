package LMS;

import java.util.Scanner;

public class StudentSU {
    private StudentSVC studentSVC = new StudentSVC();

    public void registerStudent(Scanner scanner) {
        System.out.println("[학생] 회원가입 메뉴");
        try {
            System.out.print("학번 입력 >> ");
            int sno = Integer.parseInt(scanner.nextLine());

            System.out.print("아이디 입력 >> ");
            String sid = scanner.nextLine();

            System.out.print("비밀번호 입력 >> ");
            String spw = scanner.nextLine();

            System.out.print("이름 입력 >> ");
            String snm = scanner.nextLine();

            int result = studentSVC.insertStudent(sno, snm, sid, spw);
            if (result > 0) {
                System.out.println("회원가입 성공!");
            } else {
                System.out.println("회원가입 실패! 다시 시도해주세요.");
            }
        } catch (NumberFormatException e) {
            System.out.println("학번은 숫자로 입력해주세요.");
        }
    }
}