package com.example.JpaBoard.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ThymeleafConfig { // 이걸 사용하는 이유는 th.xml 파일을 사용하여 템플릿 로직을 분리하고자 하는 것

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(
            SpringResourceTemplateResolver defaultTemplateResolver,
                Thymeleaf3Properties thymeleaf3Properties
    ) {
        defaultTemplateResolver.setUseDecoupledLogic(thymeleaf3Properties.decoupledLogic());
        return defaultTemplateResolver;
    }


    // 첫번째 방식
//    @RequiredArgsConstructor
//    @Getter
//    @ConfigurationProperties("spring.thymeleaf3")
//    /*ConfigurationProperties를 유저가 직접 만들었을 경우에 반드시 스캔을 해줘야 한다
//    그래서 application에서 ConfigurationPropertiesScan을 써주면 된다*/
//    public static class Thymeleaf3Properties {
//        /**
//         * Use Thymeleaf 3 Decoupled Logic
//         */
//        private final boolean decoupledLogic;
//
//        //이것들을 getterlombok으로 처리
//        // 생성자를 명시적으로 정의
////        public Thymeleaf3Properties(boolean decoupledLogic) {
////            this.decoupledLogic = decoupledLogic;
////        }
////
////        public boolean isDecoupledLogic() {
////            return decoupledLogic;
////        }
//    }

    // 두번째 방식
    @ConfigurationProperties("spring.thymeleaf3")
    public record Thymeleaf3Properties(boolean decoupledLogic) {}
}
