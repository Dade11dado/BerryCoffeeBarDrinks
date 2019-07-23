package com.example.vavasimo.berrycoffeebardrinks.Model;

public class ButtonInformation {
    private boolean Button1=false;
    private boolean Button2=false;
    private boolean Button3=false;
    private boolean Button4=false;
    private boolean Button5=false;
    private boolean Button6=false;
    private boolean Button7=false;
    private boolean Button8=false;
    private boolean Button9=false;
    private int ApeOmaggio=0;
    private boolean SendNotification = false;


    public ButtonInformation(){
    }

    public ButtonInformation(boolean button1, boolean button2, boolean button3, boolean button4, boolean button5,
                             boolean button6, boolean button7, boolean button8, boolean button9, int apeOmaggio, boolean sendNotification) {
        Button1 = button1;
        Button2 = button2;
        Button3 = button3;
        Button4 = button4;
        Button5 = button5;
        Button6 = button6;
        Button7 = button7;
        Button8 = button8;
        Button9 = button9;
        ApeOmaggio = apeOmaggio;
        SendNotification = sendNotification;

    }

    public boolean getButton1(){
        return Button1;
    }

    public void setButton1(boolean Button1){
        this.Button1 = Button1;
    }

    public boolean getButton2(){
        return Button2;
    }

    public void setButton2(boolean Button2){
        this.Button2 = Button2;
    }

    public boolean getButton3(){
        return Button3;
    }

    public void setButton3(boolean Button3){
        this.Button3 = Button3;
    }

    public boolean getButton4(){
        return Button4;
    }

    public void setButton4(boolean Button4){
        this.Button4 = Button4;
    }

    public boolean getButton5(){
        return Button5;
    }

    public void setButton5(boolean Button5){
        this.Button5 = Button5;
    }

    public boolean getButton6(){
        return Button6;
    }

    public void setButton6(boolean Button6){
        this.Button6 = Button6;
    }

    public boolean getButton7(){
        return Button7;
    }

    public void setButton7(boolean Button7){
        this.Button7 = Button7;
    }

    public boolean getButton8(){
        return Button8;
    }

    public void setButton8(boolean Button8){
        this.Button8 = Button8;
    }

    public boolean getButton9(){
        return Button9;
    }

    public void setButton9(boolean Button9){
        this.Button9 = Button9;
    }

    public int getApeOmaggio(){
        return ApeOmaggio;
    }

    public void setApeOmaggio(int ApeOmaggio){
        this.ApeOmaggio = ApeOmaggio;
    }

    public boolean getSendNotification(){return SendNotification;}
    public void setSendNotification(boolean SendNotification){this.SendNotification=SendNotification;}
}
