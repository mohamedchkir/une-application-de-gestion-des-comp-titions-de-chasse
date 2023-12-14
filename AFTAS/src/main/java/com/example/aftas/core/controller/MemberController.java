package com.example.aftas.core.controller;


import com.example.aftas.core.dao.model.dto.Get.GetMemberDto;
import com.example.aftas.core.dao.model.dto.Store.MemberDto;
import com.example.aftas.core.service.MemberService;
import com.example.aftas.shared.Const.AppEndpoints;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))

@RequestMapping(AppEndpoints.MEMBER_ENDPOINT)
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberDto> addMember(@RequestBody @Valid MemberDto memberDto) {
        MemberDto addedMember = memberService.addMember(memberDto);
        return new ResponseEntity<>(addedMember, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GetMemberDto>> getAllMembers() {
        List<GetMemberDto> allMembers = memberService.getAllMembers();
        return new ResponseEntity<>(allMembers, HttpStatus.OK);
    }

    @GetMapping("/{num}")
    public ResponseEntity<GetMemberDto> getMemberByNum(@PathVariable Integer num) {
        GetMemberDto member = memberService.getMemberByNum(num);
        if (member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
