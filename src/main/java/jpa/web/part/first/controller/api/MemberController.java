package jpa.web.part.first.controller.api;

import jpa.web.part.first.domain.entity.Member;
import jpa.web.part.first.domain.req.CreateMemberReq;
import jpa.web.part.first.domain.req.ModifyMemberReq;
import jpa.web.part.first.domain.res.CreateMemberRes;
import jpa.web.part.first.domain.res.FindMemberRes;
import jpa.web.part.first.domain.res.ModifyMemberRes;
import jpa.web.part.first.domain.res.Result;
import jpa.web.part.first.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("memberApi")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/v1/members")
    public Result findV1() {

        List<Member> findMembers = memberService.findMembers();

        List<FindMemberRes> list = findMembers.stream()
                .map(member -> new FindMemberRes(member.getId(), member.getName(), member.getAddress()))
                .toList();

        return new Result(list, list.size());

    }

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

        Long id = memberService.regist(member);

        return new CreateMemberRes(id);
    }

    @PatchMapping("/api/v2/members/{id}")
    public ModifyMemberRes modifyV1(@PathVariable("id") Long id,
                                    @RequestBody @Valid ModifyMemberReq req) {

        return memberService.modify(id, req);
    }

}
