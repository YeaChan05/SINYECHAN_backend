package org.yechan.remittance;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import(
    MemberAutoConfiguration.class
)
@EnableAutoConfiguration
@SpringBootConfiguration
public class TestApplication {

}
