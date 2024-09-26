package com.be.user.dto.res;

import com.be.user.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserDefaultResDto {

    private int userNum;
    private String userID;
    private String userName;
    private String userEmail;
    private String userBirth;
    private String userGender;
    private int userPreference;
    private int userInvestScore;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String userRegDate;

    public UserDefaultResDto(User user) {
        this.userNum = user.getUserNum();
        this.userID = user.getUserID();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userBirth = user.getUserBirth();
        this.userGender = user.getUserGender();
        this.userPreference = user.getUserPreference();
        this.userInvestScore = user.getUserInvestScore();
        this.userRegDate = user.getUserRegDate();
    }
}
