package hello.core.bean;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SameBeanTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시, 같은 타입이 둘 이상 있으면 오류가 발생")
    void findByType(){
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }

    @Test
    @DisplayName("타입으로 조회시, 같은 타입이 둘 이상 있으면 빈 이름도 함께 조회")
    void findByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository1",MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입 모두 조회")
    void findAllByType(){
        Map<String, MemberRepository> memberRepository = ac.getBeansOfType(MemberRepository.class);
        for (String key : memberRepository.keySet()) {
            System.out.println("key = " + key +","+ "value = " + memberRepository.get(key));
        }
        System.out.println(memberRepository);
        assertThat(memberRepository.size()).isEqualTo(2);
    }


}
