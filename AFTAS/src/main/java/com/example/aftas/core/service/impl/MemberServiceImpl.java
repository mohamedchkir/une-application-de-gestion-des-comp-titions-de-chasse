package com.example.aftas.core.service.impl;

import com.example.aftas.core.dao.model.dto.Get.GetMemberDto;
import com.example.aftas.core.dao.model.dto.Store.MemberDto;
import com.example.aftas.core.dao.model.entity.Member;
import com.example.aftas.core.dao.repository.MemberRepository;
import com.example.aftas.core.service.MemberService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Override
    public MemberDto addMember(MemberDto storeMemberDto) {
        // Check if member with the same num already exists
        Optional<Member> optional = memberRepository.findById(storeMemberDto.getNum());
        if (optional.isPresent()) {
            throw new IllegalArgumentException("Member with the same num already exists");
        }

        // Map DTO to entity
        Member member = modelMapper.map(storeMemberDto, Member.class);
        member.setAccessionDate(LocalDate.now());
        Member savedMember = memberRepository.save(member);

        return modelMapper.map(savedMember, MemberDto.class);
    }

    @Override
    public List<GetMemberDto> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(member -> modelMapper.map(member, GetMemberDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetMemberDto getMemberByNum(Integer num) {
        Optional<Member> optionalMember = memberRepository.findByNum(num);

        return optionalMember.map(member -> modelMapper.map(member, GetMemberDto.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
    }
}
