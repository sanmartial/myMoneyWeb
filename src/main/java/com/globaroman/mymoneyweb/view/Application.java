package com.globaroman.mymoneyweb.view;

import java.awt.Desktop;
import java.net.URI;

public class Application {

    public static void main(String[] args) {
        // Здесь идет код инициализации и запуска вашего Spring Boot приложения

        // Открываем браузер с указанным URL после запуска приложения
        openBrowser("http://localhost:8088/main");
    }

    private static void openBrowser(String url) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception e) {
                // Обработка ошибок открытия браузера
                e.printStackTrace();
            }
        } else {
            // Браузер не поддерживается на данной платформе
            System.out.println("Открытие браузера не поддерживается на данной платформе.");
        }
    }
}

