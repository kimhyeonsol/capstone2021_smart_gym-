package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="DTYPE")
public class User {
    @Id //식별자
    @Column(name="user_id") //크기
    private String userID;

    @Column(name="user_pw", length = 200) //크기
    @NotNull //널 허용 X
    private String userPW;

    @Column(name="user_name", length = 200) //크기
    @NotNull //널 허용 X
    private String userName;

    @Column(name="user_sex", length = 20) //크기
    @NotNull //널 허용 X
    private String userSex;

    @Column(name="user_phone", length = 200) //크기
    @NotNull //널 허용 X
    private String userPhone;

    @Column(name="user_email", length = 200) //크기
    @NotNull //널 허용 X
    private String userEmail;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPW() {
        return userPW;
    }

    public void setUserPW(String userPW) {
        this.userPW = userPW;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
