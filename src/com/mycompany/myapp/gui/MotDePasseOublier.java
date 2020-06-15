/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Aziz Sayeb
 */
public class MotDePasseOublier extends Form {
    Form current;
Form previous;
Resources theme;
private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]?";

    private static final String PASSWORD_ALLOW = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    // optional, make it more random
//    private static final String PASSWORD_ALLOW_BASE_SHUFFLE = shuffleString(PASSWORD_ALLOW_BASE);
//    private static final String PASSWORD_ALLOW = PASSWORD_ALLOW_BASE_SHUFFLE;

    private static final SecureRandom random = new SecureRandom();
    String pasw;
    public MotDePasseOublier() {
        setTitle("Mot de passe oublier");
        setLayout(BoxLayout.y());
        TextField tEmail = new TextField("","Entrez votre Email");
        Button btnPass = new Button("Envoyer");
        addAll(tEmail,btnPass);
        
        btnPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            User u=ServiceUser.getInstance().getUser1(tEmail.getText());
                for (int i = 0; i < 1; i++) {
                     pasw=generateRandomPassword(15);
            System.out.println("password : " + pasw);
            System.out.println("\n");
        }
                     User t = new User(u.getId(),pasw);
                        if( ServiceUser.getInstance().MotDePasseOublier(t)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        }
                        
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                
                 Message m = new Message("Votre nouveau mot de passe "+ pasw);
                 Display.getInstance().sendMessage(new String[] {tEmail.getText()}, "Baskel: Nouveau mot de passe ", m);
            new LoginForm(theme).show();
            }
        
        });
                 
        
    }
    
    public static String generateRandomPassword(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            int rndCharAt = random.nextInt(PASSWORD_ALLOW.length());
            char rndChar = PASSWORD_ALLOW.charAt(rndCharAt);

            sb.append(rndChar);

        }

        return sb.toString();

    }
//    public static String shuffleString(String a) {
//        List<String> letters = Arrays.asList(a.split(""));
//        Collections.shuffle(letters);
//        return letters.stream().collect(Collectors.joining());
//    }
//        
                       
                    
                
    }
