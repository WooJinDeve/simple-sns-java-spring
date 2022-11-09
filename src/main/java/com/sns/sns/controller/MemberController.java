package com.sns.sns.controller;

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
    public Member register(@RequestBody RegisterMemberCommand command){
        return memberWriteService.create(command);
    }

    @GetMapping("/members/{id}")
    public Member getMember(@PathVariable Long id) {
        return memberReadService.getMember(id);
    }
}
