package ru.starokozhev.SocialManager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.Mail;

@Data
@NoArgsConstructor
public class MailWrapper {

    private Long id;

    private String mail;

    private String password;

    public MailWrapper(Mail mail) {
        toWrapper(mail);
    }

    private void toWrapper(Mail mail) {
        if (mail != null) {
            id = mail.getId();
            this.mail = mail.getEmail();
            password = mail.getPassword();
        }
    }

    public void fromWrapper(Mail mail) {
        if (mail != null) {
            mail.setEmail(this.mail);
            mail.setPassword(password);
        }
    }

}
