package com.be.member.repository;

import com.be.member.dto.res.MemberRegisterResDto;
import com.be.member.mapper.MemberMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public UserRepository(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public int insertUser(MemberRegisterResDto userRegisterResDto) {
        return sqlSessionTemplate.getMapper(MemberMapper.class).insert(userRegisterResDto);
    }

}
