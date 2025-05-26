package LMS;

import java.util.List;
import java.util.Scanner;

public class Examq {

    public void takeExam(Scanner scanner, int sno, int subno) {
        // 0. 이미 시험 봤는지 체크
        ScoreSVC scoreSVC = new ScoreSVC();
        if (scoreSVC.hasTakenTest(sno, subno)) {
            System.out.println("이미 이 시험을 응시했습니다. 다시 응시할 수 없습니다.");
            return;
        }

        // 1. 과목명 출력
        SubjectSVC subjectSVC = new SubjectSVC();
        String subjectTitle = subjectSVC.getSubjectTitle(subno);

        TestSVC testSVC = new TestSVC();
        List<Question> questions = testSVC.getQuestionsWithAnswer(subno); // 정답 포함
        String[] answers = new String[questions.size()];

        System.out.println("\n시험응시 시작");
        System.out.println(subjectTitle + " 시험\n");

        // 2. 문제 응시
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println((i + 1) + "번. " + q.getEmun());
            System.out.println(q.getEjimun());
            System.out.println(q.getEsamp());
            System.out.print("답안을 입력해주세요: ");
            answers[i] = scanner.nextLine();
            System.out.println();
        }

        // 3. 제출/수정 루프
        while (true) {
            System.out.println("\n1. 시험 종료 및 제출");
            System.out.println("2. 답안 수정");
            System.out.print("선택 >> ");
            String input = scanner.nextLine();

            if (input.equals("1")) {
                // 채점
                int score = 0;
                for (int i = 0; i < questions.size(); i++) {
                    if (answers[i].equals(questions.get(i).getEans())) {
                        score += 20;
                    }
                }

                // 점수 저장
                int tno = scoreSVC.createTestInfo(sno, subno, score);

                // 답안 저장
                TestDescSVC testDescSVC = new TestDescSVC();
                for (int i = 0; i < questions.size(); i++) {
                    testDescSVC.insertAnswer(tno, questions.get(i).getEno(), answers[i]);
                }

                System.out.println("시험이 종료되었습니다. 점수: " + score + "점");
                return; // 학생 메뉴로 복귀

            } else if (input.equals("2")) {
                System.out.print("수정할 문제 번호 입력 >> ");
                try {
                    int idx = Integer.parseInt(scanner.nextLine()) - 1;
                    if (idx < 0 || idx >= questions.size()) {
                        System.out.println("잘못된 번호입니다.\n");
                        continue;
                    }
                    Question q = questions.get(idx);
                    System.out.println((idx + 1) + "번. " + q.getEmun());
                    System.out.println(q.getEjimun());
                    System.out.println(q.getEsamp());
                    System.out.print("답안 >> ");
                    answers[idx] = scanner.nextLine();

                } catch (NumberFormatException e) {
                    System.out.println("숫자를 입력해주세요.");
                }

            } else {
                System.out.println("1 또는 2 중에 입력해주세요.\n");
            }
        }
    }
}



