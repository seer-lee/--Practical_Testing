package com.inflearn.lecture.spring.client.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MailSendClient {
    public boolean sendEmail(String fromEmail, String toEamil, String subject, String content) {
        log.info("메일 전송");
        return true;
    }
    public void a() {
        log.info("a");
    }
    public void b() {
        log.info("b");
    }
    public void c() {
        log.info("c");
    }
    public void d() {
        log.info("d");
    }
}
