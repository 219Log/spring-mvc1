package hello2.servlet.web.frontcontroller.v4.controller;

import hello2.servlet.domain.member.Member;
import hello2.servlet.domain.member.MemberRepository;
import hello2.servlet.web.frontcontroller.ModelView;
import hello2.servlet.web.frontcontroller.v3.ControllerV3;
import hello2.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private MemberRepository memberrepository = MemberRepository.getinstance();



    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberrepository.findAll();
        model.put("members",members);

        return "members";
    }
}
