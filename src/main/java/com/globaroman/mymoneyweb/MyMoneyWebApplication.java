package com.globaroman.mymoneyweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

@SpringBootApplication
public class MyMoneyWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyMoneyWebApplication.class, args);
        openChrome("http://localhost:8088/main");
    }

    private static void openChrome(String url) {
        String os = System.getProperty("os.name").toLowerCase();
        String chromePath = "";

            // Путь к исполняемому файлу Chrome на Windows
            chromePath = "\"C:\\Program Files\\Google\\Chrome Beta\\Application\\chrome.exe\"";

        try {
            if (!chromePath.isEmpty()) {
                // Запуск Chrome с указанным URL
                Runtime.getRuntime().exec(new String[]{chromePath, url});
            } else {
                // Браузер Chrome не найден на данной платформе
                System.out.println("Браузер Chrome не найден на данной платформе.");
            }
        } catch (IOException e) {
            // Обработка ошибок открытия Chrome
            e.printStackTrace();
        }
    }
}
