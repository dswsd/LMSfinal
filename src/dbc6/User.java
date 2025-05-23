package dbc6;
public class User {
	private String mid;
	private String mpw;
	private String mnm;
	private String mph;
	private String mzip;
	private String maddr;
	
	public User(String mid, String mpw, String mnm, String mph, String mzip, String maddr) {
		this.mid = mid;
		this.mpw = mpw;
		this.mnm = mnm;
		this.mph = mph;
		this.mzip = mzip;
		this.maddr = maddr;
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

	public String getMph() {
		return mph;
	}

	public void setMph(String mph) {
		this.mph = mph;
	}

	public String getMzip() {
		return mzip;
	}

	public void setMzip(String mzip) {
		this.mzip = mzip;
	}

	public String getMaddr() {
		return maddr;
	}

	public void setMaddr(String maddr) {
		this.maddr = maddr;
	}

	@Override
	public String toString() {
		return "회원 정보{" +
				"아이디 ='" + mid + '\'' +
				", 비밀번호 ='" + mpw + '\'' +
				", 이름 ='" + mnm + '\'' +
				", 연락처 ='" + mph + '\'' +
				", 우편번호 ='" + mzip + '\'' +
				", 주소 ='" + maddr + '\'' +
				'}';
	}
}