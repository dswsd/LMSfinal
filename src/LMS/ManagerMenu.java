package LMS;

import java.util.Scanner;

import static LMS.StateSVC.stateSVC;

public class ManagerMenu {
    //관리자 로그인, 회원가입 메뉴
    public static void showManagerMenu(Scanner scanner){
        LoginSVC loginSVC = new LoginSVC();

        while (true) {
            System.out.println("[관리자 메뉴]");
            System.out.println("1. 관리자 로그인");
            System.out.println("2. 관리자 가입");
            System.out.println("3. 홈으로");
            System.out.print("선택: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("관리자 로그인 메뉴");
                    System.out.print("아이디 입력 >> ");
                    String loginId = scanner.nextLine();
                    System.out.print("비밀번호 입력 >> ");
                    String loginPw = scanner.nextLine();

                    User user = loginSVC.login(loginId, loginPw);
                    if (user == null) {
                        System.out.println("관리자가 아닙니다");
                        return;
                    } else {
                        System.out.println(user.getName() + "님 환영합니다");
                        showAdminMenu(scanner, user);
                        return;
                    }

                case "2":
                    System.out.println("관리자 가입 메뉴");
                    System.out.print("아이디 입력 >> ");
                    String id = scanner.nextLine();
                    System.out.print("비밀번호 입력 >> ");
                    String pw = scanner.nextLine();
                    System.out.print("이름 입력 >> ");
                    String name = scanner.nextLine();

                    int result = loginSVC.insertMember(id, pw, name);
                    if (result > 0) {
                        System.out.println("회원가입 성공!");
                    } else {
                        System.out.println("회원가입 실패. 다시 시도해주세요.");
                    }
                    break;

                case "3":
                    return;

                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.\n");
            }
        }
    }

    //관리자 전용 메뉴
    private static void showAdminMenu(Scanner scanner, User user){
        AdminSVC adminSVC = new AdminSVC();

        while (true) {
            System.out.println("[관리자 전용 메뉴]");
            System.out.println("1. 학생등록");
            System.out.println("2. 학생목록");
            System.out.println("3. 관리자 목록");
            System.out.println("4. 상태변경");
            System.out.println("5. 역할변경");
            System.out.println("6. 로그아웃");
            System.out.print("선택: ");
            String sel = scanner.nextLine();

            switch (sel) {
                //1. 학생 등록 메뉴
                case "1":
                    try {
                        System.out.println("학생등록 메뉴");
                        System.out.print("학번 입력: ");
                        int sno = Integer.parseInt(scanner.nextLine());

                        System.out.print("이름 입력: ");
                        String snm = scanner.nextLine();

                        System.out.print("아이디 입력: ");
                        String sid = scanner.nextLine();

                        System.out.print("비밀번호 입력: ");
                        String spw = scanner.nextLine();

                        int result = adminSVC.insertStudent(sno, snm, sid, spw);

                        if (result > 0) {
                            System.out.println("학생 등록 완료!");
                        } else {
                            System.out.println("학생 등록 실패!");
                        }
                    } catch (Exception e) {
                        System.out.println("입력 오류. 다시 시도해주세요.");
                    }
                    break;

                //2. 학생 목록 확인
                case "2":
                    adminSVC.printStudentList();
                    break;
                //3. 관리자 목록 확인
                case "3":
                    adminSVC.printManagerList();
                    break;
                case "4":
                    stateSVC = new StateSVC();
                    System.out.println("상태 변경 메뉴");
                    System.out.print("변경할 아이디 입력 >> ");
                    String targetIdForState = scanner.nextLine();

                    if (!user.getRole().equalsIgnoreCase("SUPER")) {
                        System.out.println("권한이 없습니다. SUPER 관리자만 상태 변경이 가능합니다.");
                        break;
                    }

                    System.out.print("변경할 상태 입력 (0.사용불가 또는 대기  1.사용가능) >> ");
                    String stateInput = scanner.nextLine();
                    int newState;

                    if (stateInput.equals("0")) {
                        newState = 0;
                    } else if (stateInput.equals("1")) {
                        newState = 1;
                    } else {
                        System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
                        break;
                    }

                    int stateChangeResult = stateSVC.updateState(targetIdForState, newState);
                    if (stateChangeResult > 0) {
                        System.out.println("상태변경됨");
                    } else {
                        System.out.println("변경 실패");
                    }
                    break;

                case "5":
                    RollSVC rollSVC = new RollSVC();
                    System.out.println("역할 변경 메뉴");
                    System.out.print("변경할 아이디 입력 >> ");
                    String targetId = scanner.nextLine();


                    if (!user.getRole().equalsIgnoreCase("SUPER")) {
                        System.out.println("권한이 없습니다. SUPER 관리자만 역할 변경이 가능합니다.");
                        break;
                    }

                    System.out.print("변경할 역할 입력 (1.SUPER  2.SUB) >> ");
                    String roleInput = scanner.nextLine();
                    String newRole = null;

                    if (roleInput.equals("1")) {
                        newRole = "SUPER";
                    } else if (roleInput.equals("2")) {
                        newRole = "SUB";
                    } else {
                        System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
                        break;
                    }

                    int changeResult = rollSVC.updateRole(targetId, newRole);
                    if (changeResult > 0) {
                        System.out.println("상태변경됨");
                    } else {
                        System.out.println("변경 실패");
                    }
                    break;

                case "6":
                    System.out.println("로그아웃되었습니다.\n");
                    return;  // 메인 메뉴로 복귀
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}