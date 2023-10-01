package com.kichutov.openai;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    private static final String BOT_USERNAME = "kichutov_openai_bot";
    private static final String BOT_TOKEN = "5843175251:AAGpWQnD17Bl1nr7qz45YWcFZSc0XnOCTMg";

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {


        // Ваш API-ключ от OpenAI
//        String apiKey = "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";
        String apiKey = "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";

        // Текст, который вы хотите отправить на обработку ChatGPT
        System.out.println("Запрос к ChatGPT: " + update.getMessage().getText());
        String inputText = update.getMessage().getText();

        // Создаем объект RestTemplate для выполнения HTTP-запросов
        RestTemplate restTemplate = new RestTemplate();

        // Устанавливаем заголовки запроса, включая авторизацию с использованием API-ключа
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<ChatGPTMessage> messages = new ArrayList<>();
        messages.add(new ChatGPTMessage(inputText));

        // Создаем объект запроса с входным текстом
        ChatGPTRequest request = new ChatGPTRequest(messages, "gpt-3.5-turbo");

        // Создаем объект HTTP-сущности для запроса
        HttpEntity<ChatGPTRequest> entity = new HttpEntity<>(request, headers);

        // Указываем URL API ChatGPT
        String apiUrl = "https://api.openai.com/v1/chat/completions";

        // Выполняем POST-запрос к API ChatGPT
        ResponseEntity<ChatGPTResponse> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                entity,
                ChatGPTResponse.class
        );

        // Получаем ответ от API ChatGPT
        ChatGPTResponse response = responseEntity.getBody();


        // Выводим ответ в консоль
        System.out.println("Ответ от ChatGPT: " + response);

        SendMessage sm = SendMessage.builder()
                .chatId(update.getMessage().getFrom().getId().toString()) //Who are we sending a message to
                .text(response.getChoices().get(0).message.content).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }


}
