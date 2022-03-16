package com.chatbot.application.views.chatbot;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import org.vaadin.artur.Avataaar;

public class MessageList extends Div {
    private Div gap = new Div();

    public MessageList() {
        setClassName(getClass().getSimpleName());
        gap.addClassName(getClass().getSimpleName() + "-gap");
        add(gap);
    }

//    public void addMessage(String from, String text) {
//        Div line = new Div(new Text(from + ":" + text));
//        add(line);
//        line.getElement().callJsFunction("scrollIntoView");
//    }

    public void addMessage(String from, String text, boolean isCurrentUser) {
        Span fromContainer = new Span(new Text(from));
        fromContainer.addClassName(getClass().getSimpleName() + "-name");

        Div textContainer = new Div(new Html("<span>" + text + "</span>"));
        textContainer.addClassName(getClass().getSimpleName() + "-bubble");

//        Div textContainer = new Div(new Text(text));
//        textContainer.addClassName(getClass().getSimpleName() + "-bubble");

        Div line = new Div(textContainer);
        line.addClassName(getClass().getSimpleName() + "-row");
        add(line);

        if (isCurrentUser) {
            line.addClassName(getClass().getSimpleName() + "-row-currentUser");
            textContainer.addClassName(getClass().getSimpleName() + "-bubble-currentUser");
        } else {
            line.addClassName(getClass().getSimpleName() + "-row-otherUser");
            textContainer.addClassName(getClass().getSimpleName() + "-bubble-otherUser");
        }

        remove(gap);
        add(gap);
        line.getElement().callJsFunction("scrollIntoView");
    }

    public void addMessage(String from, Avataaar avatar, String text, boolean isCurrentUser) {
        Span fromContainer = new Span(new Text(from));
        fromContainer.addClassName(getClass().getSimpleName() + "-name");

        Div textContainer = new Div(new Text(text));
        textContainer.addClassName(getClass().getSimpleName() + "-bubble");

        Div avatarContainer = new Div(avatar, fromContainer);
        avatarContainer.addClassName(getClass().getSimpleName() + "-avatar");

        Div line = new Div(avatarContainer, textContainer);
        line.addClassName(getClass().getSimpleName() + "-row");
        add(line);

        if (isCurrentUser) {
            line.addClassName(getClass().getSimpleName() + "-row-currentUser");
            textContainer.addClassName(getClass().getSimpleName() + "-bubble-currentUser");
        }

        line.getElement().callJsFunction("scrollIntoView");
    }

    public void clear() {
        removeAll();
    }

}