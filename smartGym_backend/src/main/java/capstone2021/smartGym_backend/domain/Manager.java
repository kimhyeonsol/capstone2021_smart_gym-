package capstone2021.smartGym_backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Manager {
    @Id //식별자
    @Column(name="manager_password", length = 20) //크기
    private String managerPassword;
}
