package ru.atom.chat.client;

import okhttp3.Response;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.atom.chat.client.ChatClient;
import ru.atom.chat.server.ChatApplication;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ChatApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ChatClientTest {
    private static String MY_NAME_IN_CHAT = "I_AM_STUPID";
    private static String MY_MESSAGE_TO_CHAT = "SOMEONE_KILL_ME";

    @Test
    public void login() throws IOException {
        Response response = ChatClient.login(MY_NAME_IN_CHAT);
        System.out.println("[" + response + "]");
        String body = response.body().string();
        System.out.println();
        Assert.assertTrue(response.code() == 200 || body.equals("Already logged in:("));
    }

    @Test
    public void logout() throws IOException {
        Response login = ChatClient.login(MY_NAME_IN_CHAT);
        System.out.println("[" + login + "]");

        Response logout = ChatClient.logout(MY_NAME_IN_CHAT);
        System.out.println("[" + logout + "]");
        Assert.assertEquals(200, logout.code());
    }

    @Test
    public void viewChat() throws IOException {
        Response response = ChatClient.viewChat();
        System.out.println("[" + response + "]");
        System.out.println(response.body().string());
        Assert.assertEquals(200, response.code());
    }

    @Test
    public void viewOnline() throws IOException {
        Response response = ChatClient.viewOnline();
        System.out.println("[" + response + "]");
        System.out.println(response.body().string());
        Assert.assertEquals(200, response.code());
    }

    @Test
    public void say() throws IOException {
        Response login = ChatClient.login(MY_NAME_IN_CHAT);
        System.out.println("[" + login + "]");
        System.out.println();

        Response response = ChatClient.say(MY_NAME_IN_CHAT, MY_MESSAGE_TO_CHAT);
        System.out.println("[" + response + "]");
        System.out.println(response.body().string());
        Assert.assertEquals(200, response.code());
    }

    @Test
    public void sayWithoutUser() throws IOException {
        Response login = ChatClient.login(MY_NAME_IN_CHAT);
        System.out.println("[" + login + "]");
        System.out.println();

        Response logout = ChatClient.logout(MY_NAME_IN_CHAT);
        System.out.println("[" + logout + "]");

        Response response = ChatClient.say(MY_NAME_IN_CHAT, MY_MESSAGE_TO_CHAT);
        System.out.println("[" + response + "]");
        System.out.println(response.body().string());
        Assert.assertEquals(400, response.code());
    }

    @Test
    public void getDate() throws IOException {
        Response login = ChatClient.login(MY_NAME_IN_CHAT);
        System.out.println("[" + login + "]");

        Response response = ChatClient.getTime(MY_NAME_IN_CHAT);
        System.out.println("[" + response + "]");
        System.out.println(response.body().string());
        Assert.assertEquals(200, response.code());
    }

    @Test
    public void clearChat() throws IOException {
        Response login = ChatClient.login(MY_NAME_IN_CHAT);
        System.out.println("[" + login + "]");

        Response response = ChatClient.getTime(MY_NAME_IN_CHAT);
        System.out.println("[" + response + "]");

        Response say1 = ChatClient.say(MY_NAME_IN_CHAT, MY_MESSAGE_TO_CHAT);
        System.out.println("[" + say1 + "]");

        Response say2 = ChatClient.say(MY_NAME_IN_CHAT, MY_MESSAGE_TO_CHAT + "!!!");
        System.out.println("[" + say2 + "]");

        Response clearChat = ChatClient.clearChat(MY_NAME_IN_CHAT);
        System.out.println("[" + clearChat + "]");
        String body = clearChat.body().string();
        System.out.println(body);
        System.out.println();
        Assert.assertTrue(body.equals(MY_NAME_IN_CHAT + " cleared the chat!"));
    }

}
