package com.be.member.dto.res;

import com.be.member.domain.Member;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberDefaultResDto {

    private int memberNum;
    private String memberID;
    private String Name;
    private String Email;
    private String Birth;
    private String Gender;
    private int Preference;
    private int InvestScore;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String RegDate;

    public MemberDefaultResDto(Member member) {

    }
}
