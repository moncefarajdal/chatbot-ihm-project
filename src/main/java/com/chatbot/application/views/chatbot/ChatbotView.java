package com.chatbot.application.views.chatbot;

import com.chatbot.application.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.scopes.VaadinUIScope;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.artur.Avataaar;

import javax.annotation.security.PermitAll;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Route(value = "Chatbot", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PageTitle("Bank Virtual Assistant")
@CssImport("styles/views/chat/chat-view.css")
@Component
@Scope(VaadinUIScope.VAADIN_UI_SCOPE_NAME)
@PermitAll
public class ChatbotView extends VerticalLayout {

    private final UI ui;
    private final MessageList messageList = new MessageList();
    private final TextField message = new TextField();
    private final Chat chatSession;
    private final ScheduledExecutorService executorService;
    private Bot bot;

    public ChatbotView(Bot bankAssistant, ScheduledExecutorService executorService) {
        this.executorService = executorService;
        ui = UI.getCurrent();
        chatSession = new Chat(bankAssistant);

        message.setPlaceholder("Enter a message...");
        message.setSizeFull();

        Button send = new Button(VaadinIcon.ENTER.create(), event -> {
            String text = message.getValue();
            String answer = chatSession.multisentenceRespond(text);

            message.clear();
            messageList.addMessage("You", text, true);
            messageList.addMessage("Alice", answer, false);
        });

        send.addClickShortcut(Key.ENTER);

        HorizontalLayout inputLayout = new HorizontalLayout(message, send);
        inputLayout.addClassName("inputLayout");

        add(messageList, inputLayout);
        expand(messageList);
        setSizeFull();
    }
}