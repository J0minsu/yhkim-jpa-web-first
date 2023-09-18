package jpa.web.part.first.controller;

import jpa.web.part.first.domain.entity.Member;
import jpa.web.part.first.domain.req.MemberFormReq;
import jpa.web.part.first.domain.res.MemberListRes;
import jpa.web.part.first.domain.values.Address;
import jpa.web.part.first.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : jpa.web.part.first.controller
 * fileName       : MemberController
 * author         : ms.jo
 * date           : 2023/09/18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/18        ms.jo       최초 생성
 */

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public String list(Model model) {
        List<MemberListRes> members = memberService.findMembers().stream().map(item -> new MemberListRes(item.getId()
                                                            ,item.getName(),
                                                            item.getAddress()))
                                .collect(Collectors.toList());
        model.addAttribute("members", members);

        return "page/members/memberList";
    }

    @GetMapping("/new")
    public String registPage(Model model) {

        model.addAttribute("memberFormReq", new MemberFormReq());

        return "page/members/createMemberForm";
    }

    @PostMapping("/new")
    public String regist(@Valid MemberFormReq memberFormReq, BindingResult result) {

        if(result.hasErrors()) {
            return "page/members/createMemberForm";
        }

        Address address = new Address(memberFormReq.getCity(), memberFormReq.getStreet(), memberFormReq.getZipcode());

        Member member = new Member();
        member.setName(memberFormReq.getName());
        member.setAddress(address);

        memberService.regist(member);

        return "redirect:/";

    }

}
