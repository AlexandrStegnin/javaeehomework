package ru.stegnin.javaee.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "AppUser")
public class User extends AbstractEntity implements Serializable {

    @NotNull
    @Column(unique = true, nullable = false)
    private String login = "";

    @NotNull
    @Column(unique = true, nullable = false)
    private String email = "";

    @NotNull
    @JsonIgnore
    @Column(nullable = false)
    private String password = "";

    public static class Builder {
        private User newUser;

        public Builder() {
            newUser = new User();
        }

        public Builder withLogin(String login) {
            newUser.login = login;
            return this;
        }

        public Builder withEmail(String email) {
            newUser.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            newUser.password = password;
            return this;
        }

        public User build() {
            return newUser;
        }
    }
}
