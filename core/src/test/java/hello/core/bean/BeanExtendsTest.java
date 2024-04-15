package hello.core.bean;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class BeanExtendsTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입 조회시, 자식이 둘 이상 존재하면 오류 발생")
    void findByParentTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입 조회시, 자식이 둘 이상 존재하면 bean 이름을 지정")
    void findByParentTypeWithName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);
    }

    @Test
    @DisplayName("자식의 특정 타입으로 조회")
    void findSubType(){
        RateDiscountPolicy rateDisconutPolicy = ac.getBean(RateDiscountPolicy.class);
        assertThat(rateDisconutPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void findAllByParentType(){
        Map<String, DiscountPolicy> discountPolicyMap = ac.getBeansOfType(DiscountPolicy.class);
        for (String key : discountPolicyMap.keySet()) {
            System.out.println("key = " + key + "," + "value = " + discountPolicyMap.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회(Object)")
    void findAllByParentTypeObject(){
        Map<String, Object> discountPolicyMap = ac.getBeansOfType(Object.class);
        for (String key : discountPolicyMap.keySet()) {
            System.out.println("key = " + key + "," + "value = " + discountPolicyMap.get(key));
        }
    }


    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
