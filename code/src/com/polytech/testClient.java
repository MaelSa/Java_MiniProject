package com.polytech;

public class testClient {
    private testSocketClient socketClient;
    public testClient(){
        this.socketClient = new testSocketClient();
    }
    public void askForList(){
        String list = this.socketClient.sendMessage("list");
        System.out.println(list);
    }
}
