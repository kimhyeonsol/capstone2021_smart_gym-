package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class AllowedUser extends User{
    @Column(name="allowed_user_reservation_authority", length = 8) //크기
    @NotNull //널 허용 X
    private String allowedUserReservationAuthority;

    @Column(name="allowed_user_approval_date", length = 8) //크기
    @NotNull //널 허용 X
    private LocalDateTime allowedUserApprovalDate;

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
