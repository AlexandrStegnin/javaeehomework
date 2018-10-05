package ru.stegnin.javaee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
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
    @XmlTransient
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
