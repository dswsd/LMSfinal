package LMS;

import java.util.List;
import java.util.Scanner;

public class Examq {

    public void takeExam(Scanner scanner) {
        TestSVC testSVC = new TestSVC();
        List<Question> questions = testSVC.getQuestions();

        String[] answers = new String[questions.size()];  // 사용자 답안 저장
        int total = questions.size();

        System.out.println("\n시험응시 시작");
        System.out.println("2025년 1학기 JAVA 초급 시험\n");

        // 1. 문제를 순서대로 전부 풀기
        for (int i = 0; i < total; i++) {
            Question q = questions.get(i);
            System.out.println((i + 1) + "번. " + q.getEmun());
            System.out.println("==============================");
            System.out.println(q.getEjimun());
            System.out.println("==============================");
            System.out.println(q.getEsamp());
            System.out.print("답안을 입력해주세요 (번호로 입력): ");
            answers[i] = scanner.nextLine();
            System.out.println();
        }

        // 2. 전부 푼 뒤 수정 or 제출 메뉴
        while (true) {
            System.out.println("\n모든 문제를 푸셨습니다. 다음 중 선택하세요:");
            System.out.println("1. 시험 종료 및 제출");
            System.out.println("2. 답안 수정");
            System.out.print("선택 >> ");
            String input = scanner.nextLine();

            if (input.equals("1")) {
                System.out.println("\n[시험 종료]");
                break;

            } else if (input.equals("2")) {
                System.out.print("수정할 문제 번호 입력>> ");
                try {
                    int idx = Integer.parseInt(scanner.nextLine()) - 1;
                    if (idx < 0 || idx >= total) {
                        System.out.println("잘못된 번호입니다.\n");
                        continue;
                    }

                    Question q = questions.get(idx);
                    System.out.println((idx + 1) + "번. " + q.getEmun());
                    System.out.println("==============================");
                    System.out.println(q.getEjimun());
                    System.out.println("==============================");
                    System.out.println(q.getEsamp());
                    System.out.println("답안 입력(번호로 입력해주세요)>>");
                    answers[idx] = scanner.nextLine();

                } catch (NumberFormatException e) {
                    System.out.println("숫자를 입력해주세요.\n");
                }

            } else {
                System.out.println("1 또는 2 중에 입력해주세요.\n");
            }
        }
    }
}
