package LMS;

import java.util.List;
import java.util.Scanner;

public class StudentMenu {
    //학생 로그인, 회원가입 메뉴
    public static void showStudentMenu(Scanner scanner) {
        StudentSVC studentSVC = new StudentSVC();

        while (true) {
            System.out.println("[학생]메뉴선택: 1.로그인  2.회원가입  3.홈으로");
            int selNum;
            // 잘못된 입력 버림
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
                    //로그인 메뉴
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
                                    scanner.nextLine(); // 잘못된 입력 버림
                                    continue;
                                }
                                sel = scanner.nextInt();
                                scanner.nextLine();
                                if (sel == 1) {//로그인 메뉴로 다시 복귀
                                    break;
                                } else if (sel == 2) {//회원가입 메뉴로 전환
                                    num = 1;
                                    break;
                                } else if (sel == 3) {//학생 로그인, 회원가입 메뉴
                                    num = 3;
                                    break;
                                } else if (sel == 4) {//메인 메뉴로 복귀
                                    num = 4;
                                    break;
                                } else {//잘못된 값 입력시 출력
                                    System.out.println("1~4사이의 숫자를 입력 해주세요.\n");
                                }

                            }
                            //학생 로그인 메뉴
                        } else {
                            System.out.println(user.getSnm() + "님 환영합니다.");
                            while (true) {
                                System.out.println("학생 메뉴 선택: 1.시험응시 2.점수확인  3.로그아웃");
                                int number;
                                if (!scanner.hasNextInt()) {
                                    System.out.println("1~4 사이의 숫자를 입력해주세요.\n");
                                    scanner.nextLine(); // 잘못된 입력 버림
                                    continue;
                                }
                                number = scanner.nextInt();
                                scanner.nextLine();
                                //시험응시
                                if (number == 1) {
                                    Examq examq = new Examq();
                                    examq.takeExam(scanner);

                                    //점수확인
                                } else if (number == 2) {
                                    //로그아웃
                                } else if (number == 3) {
                                    return;
                                } else {

                                }

                            }


                        }
                        //회원가입 메뉴 구현 예정
                    } else if (num == 1) {
                        System.out.println("회원가입 기능은 아직 구현되지 않았습니다.\n");
                    } else if (num == 3) {//
                        break;
                    } else if (num == 4) {
                        selNum = 3;
                        break;
                    }

                }

            } else if (selNum == 2) {
                System.out.println("회원가입 기능은 아직 구현되지 않았습니다.\n");

            } else if (selNum == 3) {
                break; // 학생 메뉴 빠져나와 메인 메뉴로
            } else {
                System.out.println("1~3 사이의 숫자를 입력해주세요.\n");
            }
            if (selNum == 3) {
                return;
            }
        }


    }




}