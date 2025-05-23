package manager;
public class User {
	private String mid;
	private String mpw;
	private String mnm;
	private String role;
	private int state;
	
	public User(String mid, String mpw, String mnm, String role, int state ) {
		this.mid = mid;
		this.mpw = mpw;
		this.mnm = mnm;
		this.role = role;
		this.state = state;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpw() {
		return mpw;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public String getMnm() {
		return mnm;
	}

	public void setMnm(String mnm) {
		this.mnm = mnm;
	}

	public String getrole() {
		return role;
	}

	public void setrole(String role) {
		this.role = role;
	}

	public int getstate() {
		return state;
	}

	public void setsteat(int state) {
		this.state = state;
	}

}