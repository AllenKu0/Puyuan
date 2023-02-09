package com.example.demo1.my_utils.Email;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

//    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
//    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
//        try {
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//            helper.setText(email, true);
//            helper.setTo(to);
//            helper.setSubject("Confirm your email/驗證您的信箱");
//            helper.setFrom("ianLiu@example.com");
//        } catch (MessagingException e) {
////            LOGGER.error("failed to end email", e);
//            throw new IllegalStateException("failed to send email");
//        }
    }
}
