package com.sns.sns.controller;

import com.sns.sns.domain.member.dto.MemberDto;
import com.sns.sns.domain.member.dto.RegisterMemberCommand;
import com.sns.sns.domain.member.entity.Member;
import com.sns.sns.domain.member.service.MemberReadService;
import com.sns.sns.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberWriteService memberWriteService;
    private final MemberReadService memberReadService;

    @PostMapping("/members")
    public MemberDto register(@RequestBody RegisterMemberCommand command){
        Member member = memberWriteService.create(command);
        return memberReadService.toDto(member);
    }

    @GetMapping("/members/{id}")
    public MemberDto getMember(@PathVariable Long id) {
        return memberReadService.getMember(id);
    }
}
