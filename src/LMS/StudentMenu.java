package LMS;

import java.util.List;
import java.util.Scanner;

public class StudentMenu {
    public static void showStudentMenu(Scanner scanner) {
        StudentSVC studentSVC = new StudentSVC();

        while (true) {
            System.out.println("[학생]메뉴선택: 1.로그인  2.회원가입  3.홈으로");
            int selNum;
            if (!scanner.hasNextInt()) {
                System.out.println("1~3 사이의 숫자를 입력해주세요.\n");
                scanner.nextLine();
                continue;
            }

            selNum = scanner.nextInt();
            scanner.nextLine();
            if (selNum == 1) {
                int num = 0;
                while (true) {
                    if (num == 0) {
                        System.out.println("[학생] 로그인 메뉴");
                        System.out.print("아이디 입력>>");
                        String id = scanner.next().toLowerCase();
                        System.out.print("비번 입력>>");
                        String pw = scanner.next().toLowerCase();
                        User user = studentSVC.login(id, pw);

                        if (user == null) {
                            System.out.println("등록된 정보가 없거나 일치하지 않습니다.");
                            while (true) {
                                int sel;
                                System.out.println("다시 입력은 1번, 회원가입은 2번, 취소는 3번, 메인메뉴로 돌아가려면 4번을 눌러주세요.");
                                if (!scanner.hasNextInt()) {
                                    System.out.println("1~4 사이의 숫자를 입력해주세요.\n");
                                    scanner.nextLine();
                                    continue;
                                }
                                sel = scanner.nextInt();
                                scanner.nextLine();
                                if (sel == 1) break;
                                else if (sel == 2) num = 1;
                                else if (sel == 3) num = 3;
                                else if (sel == 4) {
                                    selNum = 3;
                                    break;
                                } else System.out.println("1~4사이의 숫자를 입력 해주세요.\n");
                            }
                        } else {
                            System.out.println(user.getSnm() + "님 환영합니다.");
                            int sno = user.getSno();
                            int subno = 1;
                            while (true) {
                                System.out.println("학생 메뉴 선택: 1.시험응시 2.점수확인  3.로그아웃");
                                int number;
                                if (!scanner.hasNextInt()) {
                                    System.out.println("1~3 사이의 숫자를 입력해주세요.\n");
                                    scanner.nextLine();
                                    continue;
                                }
                                number = scanner.nextInt();
                                scanner.nextLine();
                                if (number == 1) {
                                    Examq examq = new Examq();
                                    examq.takeExam(scanner, sno, subno);
                                } else if (number == 2) {
                                    System.out.println("[점수확인 기능은 추후 구현 예정입니다]");
                                } else if (number == 3) {
                                    return;
                                } else {
                                    System.out.println("잘못된 입력입니다.\n");
                                }
                            }
                        }
                    } else if (num == 1) {
                        System.out.println("회원가입 기능은 아직 구현되지 않았습니다.\n");
                    } else if (num == 3) break;
                    else if (num == 4) selNum = 3;
                }
            } else if (selNum == 2) {
                System.out.println("회원가입 기능은 아직 구현되지 않았습니다.\n");
            } else if (selNum == 3) {
                break;
            } else {
                System.out.println("1~3 사이의 숫자를 입력해주세요.\n");
            }
        }
    }
}

