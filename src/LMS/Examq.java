package LMS;

import java.util.List;
import java.util.Scanner;

public class Examq {


    public void takeExam(Scanner scanner, int sno, int subno) {
        ScoreSVC scoreSVC = new ScoreSVC();
        if (scoreSVC.hasTakenTest(sno, subno)) {
            System.out.println("이미 이 시험을 응시했습니다. 다시 응시할 수 없습니다.");
            return;
        }

        SubjectSVC subjectSVC = new SubjectSVC();
        String subjectTitle = subjectSVC.getSubjectTitle(subno);

        TestSVC testSVC = new TestSVC();
        List<Question> questions = testSVC.getQuestionsWithAnswer(subno);
        String[] answers = new String[questions.size()];

        System.out.println("\n시험응시 시작");
        System.out.println(subjectTitle + " 시험\n");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println((i + 1) + "번. " + q.getEmun());
            System.out.println(q.getEjimun());
            System.out.println(q.getEsamp());
            System.out.print("답안을 입력해주세요: ");
            answers[i] = scanner.nextLine();
            System.out.println();
        }

        while (true) {
            System.out.println("\n1. 시험 종료 및 제출");
            System.out.println("2. 답안 수정");
            System.out.print("선택 >> ");
            String input = scanner.nextLine();

            if (input.equals("1")) {
                // 점수 임시 저장
                int score = 0;
                int tno = scoreSVC.createTestInfo(sno, subno, 0); // total은 일단 0

                TestDescSVC testDescSVC = new TestDescSVC();


                for (int i = 0; i < questions.size(); i++) {
                    int eno = questions.get(i).getEno();
                    int studentAnswer = Integer.parseInt(answers[i]);

                    // DB에서 정답 조회
                    int correctAnswer = testDescSVC.getCorrectAnswer(eno);

                    // 점수 계산
                    if (studentAnswer == correctAnswer) {
                        score += 20;
                    }

                    // 답안 저장
                    testDescSVC.insertAnswer(tno, eno, studentAnswer);
                }

                // 점수 업데이트
                scoreSVC.updateScore(tno, score);

                System.out.println("시험이 종료되었습니다. 점수: " + score + "점");
                return;

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



