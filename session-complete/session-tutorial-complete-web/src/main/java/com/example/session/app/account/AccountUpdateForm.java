package com.example.session.app.account;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

public class AccountUpdateForm implements Serializable {  // (1)

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    // (2)
    @NotNull(groups = { Wizard1.class })
    @Size(min = 1, max = 255, groups = { Wizard1.class })
    private String name;

    @NotNull(groups = { Wizard1.class })
    @Size(min = 1, max = 255, groups = { Wizard1.class })
    @Email(groups = { Wizard1.class })
    private String email;

    @NotNull(groups = { Wizard1.class })
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthday;

    @NotNull(groups = { Wizard1.class })
    @Size(min = 7, max = 7, groups = { Wizard1.class })
    private String zip;

    @NotNull(groups = { Wizard1.class })
    @Size(min = 1, max = 255, groups = { Wizard1.class })
    private String address;

    @Size(min = 16, max = 16, groups = { Wizard2.class })
    private String cardNumber;

    @DateTimeFormat(pattern = "yyyy-MM")
    private Date cardExpirationDate;

    @Size(min = 1, max = 255, groups = { Wizard2.class })
    private String cardSecurityCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(Date cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }

    public String getCardSecurityCode() {
        return cardSecurityCode;
    }

    public void setCardSecurityCode(String cardSecurityCode) {
        this.cardSecurityCode = cardSecurityCode;
    }

    public String getLastFourOfCardNumber() {
        if (cardNumber == null) {
            return "";
        }
        return cardNumber.substring(cardNumber.length() - 4);
    }

    public static interface Wizard1 {

    }

    public static interface Wizard2 {

    }
}