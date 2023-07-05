package com.hyundai.challenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiHyundaiApplicationTests {

    @Test
    void contextLoads() {
        Double dummy=2D;
        Assertions.assertNotNull(dummy);
    }

}
