package hello.servlet.domain.member.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.domain.member.web.frontcontroller.ModelView;
import hello.servlet.domain.member.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {


    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);

        ModelView mv = new ModelView("save-reuslt");
        mv.getModel().put("memger",member);
        return mv;
    }
}
