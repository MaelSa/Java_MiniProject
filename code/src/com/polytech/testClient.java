package com.polytech;

public class testClient {

    public void askForList(){
//        String list = this.socketClient.sendMessage("list");
//        System.out.println(list);
    }
    public static void main(String[] args) {
        testSocketClient testsocketclient = new testSocketClient();
        String reply = testsocketclient.sendMessage("anything");
        System.out.println("reply: "+reply);

        testSocketClient testsocketclient2 = new testSocketClient();
        String reply2 = testsocketclient2.sendMessage("list");
        System.out.println("reply: "+reply2);

//        testSocketClient testsocketclient3 = new testSocketClient();
//        testsocketclient3.getFile("");



    }

}
