package practice.onye.stuff;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.textfield.TextField;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;


@ApplicationScoped
@Theme(value = Lumo.class, variant = Lumo.DARK)

public class StartingOutService extends VerticalLayout {

    public String randomExample(String name) {
        return "Random Example " + name;
    }

    private TodoRepo todoRepo;
    TextField taskField = new TextField();
    Button addButton = new Button("Add");
    VerticalLayout todosList = new VerticalLayout();
    Button clearButton = new Button("Clear Completed");

    public StartingOutService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;

        add(
                new H1("Random Stuff"),
                new HorizontalLayout(taskField, addButton),
                todosList,
                clearButton
        );



    }
}













