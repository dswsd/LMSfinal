package LMS;

public class Question {
    private int eno;
    private String emun;     // 문제 제목
    private String ejimun;   // 코드/본문
    private String esamp;    // 보기들 (번호 포함된 문자열)
    private String eans;

    public Question(int eno, String emun, String ejimun, String esamp, String eans) {
        this.eno = eno;
        this.emun = emun;
        this.ejimun = ejimun;
        this.esamp = esamp;
    }

    public int getEno() { return eno; }
    public String getEmun() { return emun; }
    public String getEjimun() { return ejimun; }
    public String getEsamp() { return esamp; }
    public String getEans() { return eans; }
}
