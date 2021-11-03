package capstone2021.smartGym_backend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Manager {
    @Id //식별자
    @Column(name="manager_password", length = 20, columnDefinition = "VARCHAR(20) default '0000'")
    private String managerPassword = "0000";

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }
}
