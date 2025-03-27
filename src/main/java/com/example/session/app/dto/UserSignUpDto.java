package com.example.session.app.dto;

import com.example.session.core.model.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserSignUpDto {
    @NotEmpty(message = "Nombre y apellidos requeridos")
    private String names;
    @NotEmpty(message = "El correo electronico es requerido")
    private String email;
    @NotEmpty(message = "Asegurate de haber escogido una contraseña")
    private String password;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntity toUser(PasswordEncoder passwordEncoder) {
        UserEntity user = new UserEntity();
        user.setNames(names);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setIsEnable(true);
        return user;
    }

    @Override
    public String toString() {
        return "UserSignUpDto{" +
                "names='" + names + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
