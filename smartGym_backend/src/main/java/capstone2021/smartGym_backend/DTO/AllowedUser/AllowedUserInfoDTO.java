package capstone2021.smartGym_backend.DTO.AllowedUser;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class AllowedUserInfoDTO {
    private String userID;
    private String userPW;
    private String userName;
    private String userSex;
    private String userPhone;
    private String userEmail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime userRegisterDate;
    private String allowedUserReservationAuthority;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime allowedUserApprovalDate;

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

    public LocalDateTime getUserRegisterDate() {
        return userRegisterDate;
    }

    public void setUserRegisterDate(LocalDateTime userRegisterDate) {
        this.userRegisterDate = userRegisterDate;
    }

    public String getAllowedUserReservationAuthority() {
        return allowedUserReservationAuthority;
    }

    public void setAllowedUserReservationAuthority(String allowedUserReservationAuthority) {
        this.allowedUserReservationAuthority = allowedUserReservationAuthority;
    }

    public LocalDateTime getAllowedUserApprovalDate() {
        return allowedUserApprovalDate;
    }

    public void setAllowedUserApprovalDate(LocalDateTime allowedUserApprovalDate) {
        this.allowedUserApprovalDate = allowedUserApprovalDate;
    }
}
