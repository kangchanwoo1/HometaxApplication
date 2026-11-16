package connector.test;

import java.sql.Timestamp;

public class MemberDTO {
	private int id;
    private String username;  // 회원 아이디
    private String password;  // 비밀번호
    private String name;      // 사용자 이름
    private Timestamp regDate; // 가입일

    // --- Getter & Setter ---
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getRegDate() {
        return regDate;
    }
    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }
}


