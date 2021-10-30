package capstone2021.smartGym_backend.DTO.AllowedUser;

public class AllowedUserLoginDTO {
    private String userID;
    private String userPW;

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
}
