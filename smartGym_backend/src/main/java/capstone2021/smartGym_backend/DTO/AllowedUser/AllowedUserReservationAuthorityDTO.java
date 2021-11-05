package capstone2021.smartGym_backend.DTO.AllowedUser;

public class AllowedUserReservationAuthorityDTO {
    private String userID;
    private String allowedUserReservationAuthority;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAllowedUserReservationAuthority() {
        return allowedUserReservationAuthority;
    }

    public void setAllowedUserReservationAuthority(String allowedUserReservationAuthority) {
        this.allowedUserReservationAuthority = allowedUserReservationAuthority;
    }
}
