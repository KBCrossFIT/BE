package com.be.member.service;

import com.be.exception.CustomException;
import com.be.member.domain.Member;
import com.be.member.dto.req.MemberLoginReqDto;
import com.be.member.dto.req.MemberRegisterReqDto;
import com.be.member.dto.res.MemberRegisterResDto;
import com.be.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.be.common.code.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberMapper memberMapper;

    @Bean
    private PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder();}

    public int registerMember(MemberRegisterReqDto reqDto) {

        validateMemberID(reqDto.getMemberID());
        validateMemberEmail(reqDto.getEmail());
        checkPasswordMatching(reqDto.getPassword(), reqDto.getReEnteredPassword());
        String encodedPassword = encodePassword(reqDto.getPassword());
        MemberRegisterResDto resDto = reqDto.toMemberResDto(encodedPassword);

        try {
            memberMapper.insert(resDto);
            log.info("Member registration successful");

            return 1;
        } catch (Exception e) {
            e.getStackTrace();
            log.error("Error registering user: {}", e.getMessage());

            return 0;
        }
    }

    public Member login(MemberLoginReqDto memberLoginReqDto) {
        Member member = fineOneMember(memberLoginReqDto.getMemberID());

        boolean isVerified = verifyPassword(member, memberLoginReqDto.getPassword());
        if (!isVerified) {
            throw new CustomException(LOGIN_UNAUTHENTICATED);
        }

        return member;
    }

    private Member fineOneMember(String userID) {
        Optional<Member> member = Optional.ofNullable(memberMapper.selectOneByMemberID(userID));

        if (member.isEmpty()) {
            log.info("사용자가 존재하지 않습니다.");
            throw new CustomException(LOGIN_UNAUTHENTICATED);
        }

        return member.get();
    }

    private boolean verifyPassword(Member user, String requestPassword) {
        // 로그인 시 비밀번호 일치여부 확인
        return passwordEncoder().matches(requestPassword, user.getPassword());

    }

    public void validateMemberID(String memberID) {

        isExistID(memberID);
    }

    public void validateMemberEmail(String Email) {

        isExistMemberEmail(Email);
    }

    public void checkPasswordMatching(String password, String reEnteredPassword) {
        // 회원가입 시 비밀번호 일치 여부 확인
        if (!password.equals(reEnteredPassword))
            throw new CustomException(PASSWORD_MATCH_INVALID);
    }


    public String encodePassword(String password) {
        return passwordEncoder().encode(password);
    }

    public void isExistID(String memberID) {
        Optional<Member> member = Optional.ofNullable(memberMapper.selectOneByMemberID(memberID));

        if (member.isPresent()) {
            log.info("ID already exists: {}", memberID);
            throw new CustomException(EXISTING_MEMBER_ID);
        }
    }

    public void isExistMemberEmail(String email) {
        Optional<Member> member = Optional.ofNullable(memberMapper.selectOneByMemberEmail(email));

        if (member.isPresent()) {
            throw new CustomException(EXISTING_EMAIL);
        }
    }

}
