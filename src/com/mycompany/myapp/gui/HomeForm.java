/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;

/**
 *
 * @author Aziz Sayeb
 */
public class HomeForm extends Form {
    Form current;
    Form previous;
    Resources theme;
    

    public HomeForm() {
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        Container cnt1=new Container(new FlowLayout(Container.CENTER));
        ImageViewer Logo=new ImageViewer(MyApplication.theme.getImage("img.png"));
        
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Créer un nouveau Compte");
        Button btnListTasks = new Button("Se connecter");
        
        btnAddTask.addActionListener(e-> new AddForm(current).show());
        btnListTasks.addActionListener(e-> new LoginForm(theme).show());
        addAll(btnAddTask,btnListTasks);
        
        getToolbar().addCommandToOverflowMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});
    }

   
    
}
