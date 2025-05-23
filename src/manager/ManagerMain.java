package manager;

import dbc6.User;

import java.util.Scanner;

public class ManagerMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoginSVC loginSVC = new LoginSVC();
        int selNum = 0;

        while (true) {
            if (selNum == 0) {
                System.out.println("메뉴선택: 1.관리자 2.시스템종료");
                selNum = sc.nextInt();
            } else if (selNum == 1) {
                //로그인
                System.out.println("관리자 로그인ap뉴");

                System.out.print("아이디 입력>> ");
                String id = sc.next().toLowerCase();

                System.out.print("비밀번호>> ");
                String passwd = sc.next().toLowerCase();

                User user = loginSVC.login(id, passwd);
                if (user == null) {
                    System.out.println("아이디나 비밀번호가 일치하지 않습니다.");
                    System.out.println("다시 입력은 1번, 회원가입은 2번, 취소는 3번, 프로그램 종료는 4번을 눌러주세요.");
                    int sel = sc.nextInt();
                    if (sel == 1) {
                        selNum = 1;
                    } else if (sel == 2) {
                        selNum = 2;
                    } else if (sel == 3) {
                        selNum = 0;
                    } else {
                        selNum = 4;
                    }

                } else {
                    System.out.println("로그인 완료!!");
                    System.out.println("로그인한 사용자 정보");
                    System.out.println(user);
                    selNum = 0;
                }
            } else if (selNum == 2) {
                //회원가입
                System.out.println("회원가입 메뉴");
                System.out.print("아이디 입력: ");
                String id = sc.next().toLowerCase();

                System.out.print("비번 입력: ");
                String pw = sc.next().toLowerCase();

                sc.nextLine();

                System.out.print("이름 입력: ");
                String nm = sc.nextLine().toLowerCase();

                System.out.println("입력완료 : 1번, 다시 입력: 2번, 취소는 3번, 프로그램 종료는 4번을 눌러주세요.");
                int sel = sc.nextInt();
                if (sel == 1) {
                    int c = loginSVC.insertMember(id, pw, nm);
                    if (c > 0) {
                        System.out.println("회원가입이 완료되었습니다");
                        selNum = 0;
                    } else {
                        System.out.println("다시 입력해주십시오.");
                        selNum = 2;
                    }
                } else if (sel == 2) {
                    selNum = 2;
                } else if (sel == 3) {
                    selNum = 0;
                } else {
                    selNum = 4;
                }

            } else if (selNum == 3) {
                //회원탈퇴메뉴
                System.out.println("회원 탈퇴 메뉴");
                System.out.println("회원탈퇴를 하시려면 1번, 취소는 2번, 프로그램 종료는 3번을 눌러주세요.");
                int sel = sc.nextInt();
                if (sel == 1) {
                    System.out.print("탈퇴할 아이디 입력: ");
                    String id = sc.next().toLowerCase();
                    int c = loginSVC.deleteMember(id);
                    if (c > 0) {
                        System.out.println("회원탈퇴 완료");
                        selNum = 0;
                    } else {
                        System.out.println("탈퇴가 되지 않았습니다.");
                        continue;
                    }
                } else if (sel == 2) {
                    selNum = 0;
                } else {
                    selNum = 4;
                }
            } else {
                return;


            }
        }
    }
}
