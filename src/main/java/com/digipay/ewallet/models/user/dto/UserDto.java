package com.digipay.ewallet.models.user.dto;

import com.digipay.ewallet.models.wallet.entity.WalletEntity;
import lombok.Data;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Data
public class UserDto {



    private Long id;


    @NotBlank(message ="userName is necessary")
    @Size(min =3,max = 20)
    private String username;

    @NotBlank(message ="name is necessary")
    @Size(min =3,max = 20)
    private String firstName;

    @NotBlank(message ="lastName is necessary")
    @Size(min =3,max = 20)
    private String lastName;

    @OneToMany(mappedBy ="user")
    private List<WalletEntity> wallet;

}
