package com.be.member.mapper;


import com.be.member.domain.Member;
import com.be.member.dto.res.MemberRegisterResDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int insert(MemberRegisterResDto userRegisterResDto);

    Member selectOneByMemberID(String memberID);

    Member selectOneByMemberEmail(String email);

}
