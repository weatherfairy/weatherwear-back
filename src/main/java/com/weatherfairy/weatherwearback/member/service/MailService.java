package com.weatherfairy.weatherwearback.member.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final EmailCodeService emailCodeService;

//    private String setContext(String code) {
//        Context context = new Context();
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//
//        context.setVariable("code", code);
//
//        templateResolver.setPrefix("templates/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode(TemplateMode.HTML);
//        templateResolver.setCacheable(false);
//
//        templateEngine.setTemplateResolver(templateResolver);
//
//        return templateEngine.process("mail", context);
//
//    }

    private String setContext(String code) {
        Context context = new Context();

        context.setVariable("code", code);

        return "코드를 입력해주세요: " + code;
    }

    private MimeMessage createMessage(String code, String email) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, email);
        message.setSubject("WeatherWear 회원가입 본인인증 코드입니다.");
        message.setText(setContext(code), "utf-8", "html");
        message.setFrom("metamingle@naver.com");

        emailCodeService.setEmailCode(email, code);

        return message;
    }

    public String sendMail(String email) {

        String code = UUID.randomUUID().toString().substring(0, 8);

        System.out.println("code = " + code);

        try {
            MimeMessage message = createMessage(code, email);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return code;
    }

    public Boolean verifyCode(String email, String code) {

        String codeForEmail = emailCodeService.getCode(email);

        if (codeForEmail == null) {
            throw new IllegalArgumentException("인증요청 기록이 없는 이메일입니다.");
        }

        return codeForEmail.equals(code);

    }



}