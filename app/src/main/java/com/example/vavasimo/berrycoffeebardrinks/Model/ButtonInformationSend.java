package com.example.vavasimo.berrycoffeebardrinks.Model;

public class ButtonInformationSend {

    private String TestoNotifica;

    public ButtonInformationSend(){}

    public ButtonInformationSend(String testoNotifica){
        TestoNotifica=testoNotifica;
    }

    public String getTestoNotifica(){return TestoNotifica;}
    public void setTestoNotifica(String TestoNotifica){this.TestoNotifica= TestoNotifica;}
}
