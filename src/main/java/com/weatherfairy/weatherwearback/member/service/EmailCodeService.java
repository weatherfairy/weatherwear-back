package com.weatherfairy.weatherwearback.member.service;

import com.weatherfairy.weatherwearback.common.auth.EmailCode;
import com.weatherfairy.weatherwearback.common.auth.EmailCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailCodeService {

    private final EmailCodeRepository emailCodeRepository;

    @Transactional
    public void setEmailCode(String email, String code) {

        Optional<EmailCode> optionalEmailCode = emailCodeRepository.findByEmail(email);

        if (optionalEmailCode.isPresent()) {
            EmailCode existingEmailCode = optionalEmailCode.get();
            existingEmailCode.update(code);
            emailCodeRepository.save(existingEmailCode);
        } else {
            EmailCode newEmailCode = EmailCode.builder()
                    .email(email)
                    .code(code)
                    .build();
            emailCodeRepository.save(newEmailCode);
        }

    }

    public String getCode(String email) {
        Optional<EmailCode> emailCode = emailCodeRepository.findByEmail(email);
        return emailCode.get().getCode();
    }

    public void deleteCode(String email) {
        Optional<EmailCode> emailCode = emailCodeRepository.findByEmail(email);
        emailCodeRepository.deleteById(emailCode.get().getCodeNo());
    }
}
