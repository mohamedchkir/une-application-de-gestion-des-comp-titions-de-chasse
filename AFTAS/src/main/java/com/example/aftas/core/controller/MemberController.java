package com.example.aftas.core.controller;


import com.example.aftas.core.dao.model.dto.Get.GetMemberDto;
import com.example.aftas.core.dao.model.dto.Store.MemberDto;
import com.example.aftas.core.service.MemberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))

@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberDto> addMember(@RequestBody @Valid MemberDto memberDto) {
        MemberDto addedMember = memberService.addMember(memberDto);
        return new ResponseEntity<>(addedMember, HttpStatus.CREATED);
    }


}
