package jpa.web.part.first.controller.api;

import jpa.web.part.first.domain.entity.Member;
import jpa.web.part.first.domain.req.CreateMemberReq;
import jpa.web.part.first.domain.res.CreateMemberRes;
import jpa.web.part.first.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberRes registV1(@RequestBody @Valid Member member) {

        Long id = memberService.regist(member);

        return new CreateMemberRes(id);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberRes registV2(@RequestBody @Valid CreateMemberReq req) {

        Member member = new Member();

        member.setName(req.getUsername());
        member.setAddress(req.getAddress());

        memberService.regist(member);

    }

}
