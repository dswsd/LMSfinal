package manager;

import java.util.Scanner;

public class ManagerMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("메뉴선택: 1. 관리자  2. 학생  3. 시스템 종료");
            System.out.print("선택: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    ManagerMenu.showManagerMenu(scanner);
                    break;
                case "2":
                    System.out.println("학생 메뉴는 아직 구현되지 않았습니다.\n");
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




//                if (user == null) {
//                    System.out.println("아이디나 비밀번호가 일치하지 않습니다.");
//                    System.out.println("다시 입력은 1번, 회원가입은 2번, 취소는 3번, 프로그램 종료는 4번을 눌러주세요.");
//
//                    if (sel == 1) {
//                        selNum = 1;
//                    }else{
//                        selNum = 0;
//                    }
//
//                } else {
//                    System.out.println("로그인 완료!!");
//                    System.out.println("로그인한 사용자 정보");
//                    System.out.println(user);
//                    selNum = 0;
//                }
//            } else if (selNum == 2) {
//                //회원가입
//                System.out.println("회원가입 메뉴");
//                System.out.print("아이디 입력: ");
//                String id = sc.next().toLowerCase();
//
//                System.out.print("비번 입력: ");
//                String pw = sc.next().toLowerCase();
//
//                sc.nextLine();
//
//                System.out.print("이름 입력: ");
//                String nm = sc.nextLine().toLowerCase();
//
//                System.out.println("입력완료 : 1번, 다시 입력: 2번, 취소는 3번, 프로그램 종료는 4번을 눌러주세요.");
//                int sel = sc.nextInt();
//                if (sel == 1) {
//                    int c = loginSVC.insertMember(id, pw, nm);
//                    if (c > 0) {
//                        System.out.println("회원가입이 완료되었습니다");
//                        selNum = 0;
//                    } else {
//                        System.out.println("다시 입력해주십시오.");
//                        selNum = 2;
//                    }
//                } else if (sel == 2) {
//                    selNum = 2;
//                } else if (sel == 3) {
//                    selNum = 0;
//                } else {
//                    selNum = 4;
//                }
//
//            } else if (selNum == 3) {
//                //회원탈퇴메뉴
//                System.out.println("회원 탈퇴 메뉴");
//                System.out.println("회원탈퇴를 하시려면 1번, 취소는 2번, 프로그램 종료는 3번을 눌러주세요.");
//                int sel = sc.nextInt();
//                if (sel == 1) {
//                    System.out.print("탈퇴할 아이디 입력: ");
//                    String id = sc.next().toLowerCase();
//                    int c = loginSVC.deleteMember(id);
//                    if (c > 0) {
//                        System.out.println("회원탈퇴 완료");
//                        selNum = 0;
//                    } else {
//                        System.out.println("탈퇴가 되지 않았습니다.");
//                        continue;
//                    }
//                } else if (sel == 2) {
//                    selNum = 0;
//                } else {
//                    selNum = 4;
//                }
//            } else {
//                return;


//            }
//        }
//    }
//}
