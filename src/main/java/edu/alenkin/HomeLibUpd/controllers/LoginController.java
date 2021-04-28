package edu.alenkin.HomeLibUpd.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
public class LoginController {
    public LoginController() {
    }

    public String login(){
        return "books";
    }
}
