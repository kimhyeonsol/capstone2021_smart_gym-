package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
public class UnAllowedUser extends User{

    @Column(name="un_allowed_user_approval_date", length = 8) //크기
    @NotNull //널 허용 X
    private LocalDateTime unAllowedUserApprovalDate;
}
