package com.example.aftas.core.service;

import com.example.aftas.core.dao.model.entity.Member;

import java.util.List;

public interface MemberService {
    Member addMember(Member member);

    List<Member> getAllMembers();

    Member getMemberByNum(Integer num);

}
