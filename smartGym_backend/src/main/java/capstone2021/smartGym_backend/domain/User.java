package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class User {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 올라가게 설정
    @Column(name="user_id") //크기
    private Long userID;

    @Column(name="user_pw", length = 200) //크기
    @NotNull //널 허용 X
    private String userPW;

    @Column(name="user_name", length = 200) //크기
    @NotNull //널 허용 X
    private String userNAME;

    @Column(name="user_sex", length = 20) //크기
    @NotNull //널 허용 X
    private String userSex;

    @Column(name="user_phone", length = 200) //크기
    @NotNull //널 허용 X
    private String userPhone;

    @Column(name="user_email", length = 200) //크기
    @NotNull //널 허용 X
    private String userEmail;

}
