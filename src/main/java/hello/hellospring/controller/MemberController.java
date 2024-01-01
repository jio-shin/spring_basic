package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    //MemberService()는 별 기능이 없어서 인스턴스로 생성할 필요가 없음
    //private final MemberService memberService = new MemberService();
    private final MemberService memberService;

    @Autowired
    //autowired사용하면 memberService를 찾아서 연결시켜줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    //회원 등록
    //get은 조회할 때 쓰이는 것
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    //post는 데이터를 form에 담아서 전달할 때 쓰는 것
    @PostMapping("/members/new")
    public String create(MemberForm form){
        //createMemberForm.html의 name이 MemberForm의 setName을 통해서 name에 담기게 됨

        Member member = new Member();
        member.setName(form.getName());
        //MemberForm form 에서 getName으로 꺼낸 것

        memberService.join(member);
        //회원가입이 끝나면 홈으로 돌려버리는 것
        return "redirect:/";
    }

    //회원 조회
    //서버 다시 키면 내용 다 날라감-> db에 저장해야 한다!
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        //model 에 members리스트 담아서 넘김

        return "members/memberList.html";
    }
}
