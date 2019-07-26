package com.example.vavasimo.berrycoffeebardrinks.Model;

public class ButtonInformationSend {
    private String NotificaAvviso;

    public ButtonInformationSend(){}

    public ButtonInformationSend(String testoNotifica){
        NotificaAvviso=testoNotifica;
    }

    public String getTestoNotifica(){return NotificaAvviso;}
    public void setTestoNotifica(String notificaAvviso){this.NotificaAvviso= notificaAvviso;}
}
