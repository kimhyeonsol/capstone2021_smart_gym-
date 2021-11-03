package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UnAllowedUser extends User{

    @Column(name="un_allowed_user_approval_authority", length = 8) //크기
    @NotNull //널 허용 X
    private String unAllowedUserApprovalAuthority;

    public String getUnAllowedUserApprovalAuthority() {
        return unAllowedUserApprovalAuthority;
    }

    public void setUnAllowedUserApprovalAuthority(String unAllowedUserApprovalAuthority) {
        this.unAllowedUserApprovalAuthority = unAllowedUserApprovalAuthority;
    }


}
