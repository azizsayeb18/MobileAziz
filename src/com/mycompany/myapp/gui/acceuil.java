/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Command;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Aziz Sayeb
 */
class acceuil extends Form{
Label name = new Label();
 Toolbar tb1;
 Form previous;
 Form current;
 Resources theme;
    public acceuil(Form current,String username) {
        current=this;
         name.setText("Salut " + username);
        this.add(name);
        tb1 = this.getToolbar();
        tb1.addCommandToOverflowMenu(new Command("déconexion"){
            @Override
            public void actionPerformed(ActionEvent evt){
                LoginForm l = new LoginForm(theme);
                l.show();
            }
        });
       
        tb1.addCommandToOverflowMenu(new Command("voir profile"){
            @Override
            public void actionPerformed(ActionEvent evt){
                User t=ServiceUser.getInstance().getUser(username);
                ProfilForm l = new ProfilForm(username,previous);
                l.show();
            }
        });
    }
    

    

   
}
