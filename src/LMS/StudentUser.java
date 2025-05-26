// LMS/StudentUser.java
package LMS;

public class StudentUser {
    private String sno;
    private String snm;
    private String sid;
    private String spw;
    private String sdate;

    public StudentUser() {}

    public StudentUser(String sno, String snm) {
        this.sno = sno;
        this.snm = snm;
    }

    // Getter, Setter
    public String getSno() { return sno; }
    public String getSnm() { return snm; }
    public String getSid() { return sid; }
    public String getSpw() { return spw; }
    public String getSdate() { return sdate; }

    public void setSno(String sno) { this.sno = sno; }
    public void setSnm(String snm) { this.snm = snm; }
    public void setSid(String sid) { this.sid = sid; }
    public void setSpw(String spw) { this.spw = spw; }
    public void setSdate(String sdate) { this.sdate = sdate; }
}
