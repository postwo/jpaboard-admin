package com.example.JpaBoard;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test") //yml에 있는 testdb를 사용하겠다는 뜻이다 이렇게 설정함으로써 test실패를 예방 할 수 있다 ==Configuration Processor
@SpringBootTest
class JpaBoardApplicationTests {

	@Test
	void contextLoads() {
	}

}
