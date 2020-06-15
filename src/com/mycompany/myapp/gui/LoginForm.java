/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.notifications.LocalNotification;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.util.Base64;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import org.mindrot.jbcrypt.BCrypt;



/**
 *
 * @author Aziz Sayeb
 */
public class LoginForm extends Form{
   Form current;
    public  static int ID=0;
        

 public LoginForm(Resources theme){
     current=this;
        setTitle("Login");
        setLayout(BoxLayout.y());
        Container cnt1=new Container(new FlowLayout(Container.CENTER));
        Container cnt4=new Container(new FlowLayout(Container.CENTER));
        Container cnt5=new Container(new FlowLayout(Container.CENTER));
        Container cnt2=new Container(new FlowLayout(Container.CENTER));
        Container cnt3=new Container(new FlowLayout(Container.CENTER));
        Container cnt6=new Container(new FlowLayout(Container.CENTER));
        
        ImageViewer Logo=new ImageViewer(MyApplication.theme.getImage("img.png"));
        Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);
                cnt1.add(Logo);
  Label Seconnecter = new Label("Se connecter");
  Style connecterStyle=Seconnecter.getAllStyles();
  connecterStyle.setFont(largeBoldSystemFont);
  connecterStyle.setFgColor(0x4cc395);
  connecterStyle.setMargin(Component.TOP,170); 
  connecterStyle.setMargin(Component.BOTTOM,150);
  TextField Username = new TextField("", "saisir votre username");
  TextField tpassword = new TextField();
  tpassword.setHint("saisir votre mot de passe");
  tpassword.setConstraint(TextField.PASSWORD);
  Style validerStyle=Seconnecter.getAllStyles();
  cnt4.add(Seconnecter);
  cnt2.addAll(Username);
  cnt3.add(tpassword);
  Button btnval = new Button("valider");
  Style btnvalStyle=btnval.getAllStyles();
  btnvalStyle.setFgColor(0x000000);
  Button motOublier= new Button("Mot de passe oublié ?");
  Style motOublierStyle=motOublier.getAllStyles();
  motOublierStyle.setFgColor(0x000000);
  Button btnAddTask = new Button("Créer un nouveau Compte");
  Style btnAddTaskStyle=btnAddTask.getAllStyles();
  btnAddTaskStyle.setFgColor(0x000000);
  Button facebook = new Button("se connecter avec Facebook");
//  Style btnAddTaskStyle=btnAddTask.getAllStyles();
//  btnAddTaskStyle.setFgColor(0x000000);
  cnt6.addAll(motOublier);
   Style cnt6Style=cnt6.getAllStyles();
       cnt6Style.setFgColor(0x4cc395);
       cnt6Style.setMargin(Component.TOP,800); 
  addAll(cnt1,cnt4,cnt2,cnt3,btnval,cnt6,btnAddTask,facebook);    
/***********************************/
               btnval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Username.getText().length()==0)||(tpassword.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {               // pw = pw.replace("$2y$", "$2a$");

User t=ServiceUser.getInstance().getUser(Username.getText());
String pw = t.getPassword();
String pw1=pw.substring(4);
String pw11="$2a$"+pw1;
                System.out.println("pw1 = " +pw1);
//                pw = pw.replace("$2y$", "$2a$");
                
                        if(t.equals(null))
                        {
                            
                                            Dialog.show("ERROR", "User not found", new Command("OK"));
                                            }

                        else if(BCrypt.checkpw(tpassword.getText(),pw11)==false){
                                                                    Dialog.show("ERROR", "Mot de passe invalide", new Command("OK"));

                        }
                                
                                else{
                            
                            System.out.println(pw);
                       ID=t.getId();
                          new acceuil(current,Username.getText()).show();
                            
                        
                        }
                  
                    } catch (NumberFormatException e) {
                        System.out.println("1");
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
               
               facebook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            
            String clientId = "861902307638537";
                String redirectURI = "http://www.codenameone.com/";
                String clientSecret = "6cbc07589913ba6e6bb3b7191a8a8fc2";
                Login fb = FacebookConnect.getInstance();
                fb.setClientId(clientId);
                fb.setRedirectURI(redirectURI);
                fb.setClientSecret(clientSecret);
//                fb.setCallback(...);
                //trigger the login if not already logged in
                if(!fb.isUserLoggedIn()){
                    fb.doLogin();
                }else{
                    //get the token and now you can query the facebook API
                    String token = fb.getAccessToken().getToken();
                    
                }}});
               
        motOublier.addActionListener(e-> new MotDePasseOublier().show()); 
        btnAddTask.addActionListener(e-> new AddForm(current).show());
         getToolbar().addCommandToOverflowMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});

}

   
}
 
 
 
 
 