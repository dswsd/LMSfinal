package LMS;

public class User {
    private String id;
    private String password;
    private String name;
    private String role;
    private int state;

    public User(String id, String password, String name, String role, int state) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.role = role;
        this.state = state;
    }

    // Getter
    public String getId() { return id; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public int getState() { return state; }

    @Override
    public String toString() {
        return "ID: " + id + ", 이름: " + name + ", 역할: " + role + ", 상태: " + state;

    }
}
