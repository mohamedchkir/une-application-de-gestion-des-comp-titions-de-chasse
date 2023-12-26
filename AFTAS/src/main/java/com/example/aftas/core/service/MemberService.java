package com.example.aftas.core.service;

import com.example.aftas.core.dao.model.dto.Get.GetMemberDto;
import com.example.aftas.core.dao.model.dto.Store.MemberDto;
import com.example.aftas.core.dao.model.entity.Member;

import java.util.List;

public interface MemberService {
    MemberDto addMember(MemberDto memberDto);

    List<GetMemberDto> getAllMembers();

    GetMemberDto getMemberByNum(Integer num);

}
