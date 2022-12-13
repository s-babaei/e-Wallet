package com.digipay.ewallet.models.user.entity;

import com.digipay.ewallet.models.BaseEntity;
import com.digipay.ewallet.models.wallet.entity.WalletEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @NotBlank(message ="userName is necessary")
    @Size(min =3,max = 20)
    private String username;

    @NotBlank(message ="name is necessary")
    @Size(min =3,max = 20)
    private String firstName;



    @NotBlank(message ="lastName is necessary")
    @Size(min =3,max = 20)
    @Column(name = "LAST_NAME")
    private String lastName;



    @OneToMany(mappedBy = "user")
    private List<WalletEntity> wallet = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public UserEntity(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<WalletEntity> getWallet() {
        return wallet;
    }

    public void setWallet(List<WalletEntity> wallet) {
        this.wallet = wallet;
    }

    public static class UserBuilder {

        private Long id;
        private String firstName;

        private String lastName;
        @OneToMany(mappedBy = "user")
        private List<WalletEntity> wallet;


        public UserBuilder(Long id, String firstName, String lastName, List<WalletEntity> wallet) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.wallet = wallet;
        }

        public UserBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setWallet(List<WalletEntity> wallet) {
            this.wallet = wallet;
            return this;
        }

        public UserEntity build() {
            return new UserEntity();
        }
    }

}
