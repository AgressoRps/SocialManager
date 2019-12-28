package ru.starokozhev.SocialManager.service.bot;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.starokozhev.SocialManager.service.OrderProductService;

import java.util.regex.Pattern;

@Service
@Log4j2
public class SocialManagerBot extends TelegramLongPollingBot {

    private final static String METHOD_GET = "/get";
    private final static String MAIL_REGEX = "^(.+)@(.+)$";
    private final static Long CHAT_ID = -376015465L;

    private Long chatId = null;
    private OrderProductService orderProductService;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            log.info("NEW MESSAGE! {}", update.getMessage());
            if (update.getMessage().getFrom().getBot()) {
                chatId = update.getMessage().getChatId();
                String message = update.getMessage().getText();

                if (thisMessageIsEmail(message)){
                    log.info("MESSAGE IS EMAIL: {}", message);
                }
            }
        }
    }

    public void getTemporaryMail(OrderProductService orderProductService) {
        if (chatId != null && orderProductService != null) {
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(chatId)
                    .setText(METHOD_GET);

            this.orderProductService = orderProductService;

            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private Boolean thisMessageIsEmail(String message) {
        Pattern pattern = Pattern.compile(MAIL_REGEX);
        return pattern.matcher(message).matches();
    }

    @Override
    public String getBotUsername() {
        return "Social";
    }

    @Override
    public String getBotToken() {
        return "1045020659:AAHoPqqx4pAn8QdfqzKzby9dpUCVEDjqjjk";
    }
}
