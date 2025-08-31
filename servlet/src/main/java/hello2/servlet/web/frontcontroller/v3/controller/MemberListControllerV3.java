package hello2.servlet.web.frontcontroller.v3.controller;

import hello2.servlet.domain.member.Member;
import hello2.servlet.domain.member.MemberRepository;
import hello2.servlet.web.frontcontroller.ModelView;
import hello2.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberrepository = MemberRepository.getinstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberrepository.findAll();
        ModelView mv = new ModelView("members");
        mv.getModel().put("members",members);

        return mv;
    }
}
