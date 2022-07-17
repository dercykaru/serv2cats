package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final String SERVER_URL = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public static void main(String[] args) throws IOException, ParseException {
        //создание http-клиента:
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("Im agent007")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5_000) //макс время ожидания подключения к серверу
                        .setSocketTimeout(30_000) //макс время ожидания получения данных
                        .setRedirectsEnabled(false) //возможность перенаправления в ответе
                        .build())
                .build();

        //создание объекта запроса для передачи в http-клиент:
        HttpGet request = new HttpGet(SERVER_URL);

        //отправка запроса:
        CloseableHttpResponse response = httpClient.execute(request);

        //чтение тела ответа:
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
        System.out.println("Необработанный ответ от сервера: ");
        System.out.println(body);
        System.out.println();

        //преобразование json-ответа в java-объект:
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(body);
        JSONArray jsonObject = (JSONArray) obj;
        System.out.println();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        //формирование списка из объектов:
        List<Cats2Json> CATSSS = new ArrayList<>();
        for (Object next : jsonObject) {
            Cats2Json catInfo = gson.fromJson(next.toString(), Cats2Json.class);
            CATSSS.add(catInfo);
        }

        //сортировка и вывод на экран полученных объектов:
        System.out.println("Вот те сообщения о кошках, за которые проголосовали люди: ");
        CATSSS.stream().filter(x -> x.getUpvotes() != null && Integer.parseInt(x.getUpvotes()) > 0)
                .forEach(System.out::println);
    }

}