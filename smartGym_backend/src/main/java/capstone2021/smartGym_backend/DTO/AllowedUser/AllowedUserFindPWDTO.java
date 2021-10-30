package capstone2021.smartGym_backend.DTO.AllowedUser;

public class AllowedUserFindPWDTO {
    private String userID;
    private String userEmail;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
