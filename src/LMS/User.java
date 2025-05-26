package LMS;

public class User {
    private String id;
    private String password;
    private String name;
    private String role;
    private int state;
    private String sno;
    private String snm;
    private String sid;
    private String spw;
    private String sdate;

    public User(String id, String password, String name, String role, int state) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.role = role;
        this.state = state;
    }

    public User(String sno, String snm) {
        this.sno = sno;
        this.snm = snm;
        this.sid = sid;
        this.spw = spw;
        this.sdate = sdate;

    }

    // Getter
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public int getState() {
        return state;
    }

    public String getSno() {
        return sno;
    }

    public String getSnm() {
        return snm;
    }

    public String getSid() {
        return sid;
    }

    public String getSpw() {
        return spw;
    }

    public String getSdate() {
        return sdate;
    }

    // Setter
    public void setSno(String sno) {
        this.sno = sno;
    }

    public void setSnm(String snm) {
        this.snm = snm;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setSpw(String spw) {
        this.spw = spw;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }


    @Override
    public String toString() {
        return "ID: " + id + ", 이름: " + name + ", 역할: " + role + ", 상태: " + state;
    }
}