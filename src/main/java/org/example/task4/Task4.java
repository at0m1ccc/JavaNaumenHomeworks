package org.example.task4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.UserInterface;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Task4 {
    private final UserInterface userInterface = new UserInterface();

    public void solveTask4() {
        userInterface.printMessage("Выполнение задания №4");
        CompletableFuture<HttpResponse<String>> response;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/headers"))
                    .build();
            response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        }

        response.thenAccept(resp -> {
            ObjectMapper mapper = new ObjectMapper();
            try {
                Map<String, Object> headers = mapper.readValue(resp.body(), Map.class);
                String headersList = headers.values()
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(","));
                userInterface.printMessage("Заголовки ответа: " + headersList);
            } catch (JsonProcessingException ex) {
                userInterface.printMessage(ex.getMessage());
            }
        });

        response.exceptionally(ex -> {
            userInterface.printMessage(ex.getMessage());
            return null;
        });

        userInterface.printMessage("-".repeat(50));
    }
}
