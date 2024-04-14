package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        //  ApplicationContext를 스프링 컨테이너라고 함
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //  bean의 이름은 메서드명을 기본으로 함, (이름 ,타입)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "member", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("findMember = "+member.getName());
        System.out.println("findMember = "+findMember.getName());
    }
}
