package com.sumit.learning.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Confirmation_Tokens")
public class ConfirmationTokenEntity {

    static int time_for_token_to_expire=60;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Column(name="expire_date")
    private Date expireDate;

    @ManyToOne
    @JoinColumn(nullable = false,name = "user_id" , referencedColumnName="id")
    private UserEntity userEntity;

    public ConfirmationTokenEntity()
    {

    }

    public ConfirmationTokenEntity(UserEntity user) {
        super();
        this.userEntity = user;
        expireDate = new Date(System.currentTimeMillis() + 1000 * 60 * time_for_token_to_expire);
        confirmationToken = UUID.randomUUID().toString();
    }

    public long getTokenid() {
        return tokenid;
    }

    public void setTokenid(long tokenid) {
        this.tokenid = tokenid;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    // getters and setters
}