package com.polytech;

public class testServer {
    private String[] artificialList = {"asda","adjioc","fewurei","sdfuhwjkndf"};
    private int canBeAnything;

    //Here we use synchronized to avoid injection attack from client side
    public synchronized void handleList(){
        //do something on the list
        System.out.println("enter synchronized handleList method");
        try {
            Thread.sleep(1000); //For testing if injection attack is rejected by using synchronized, which turns out it did works!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("exit synchronized handleList method");
        return;
    }

    //Here we use synchronized to avoid injection attack from client side
    public synchronized void handle_canBeAnything(){
        //do something on the int canBeAnything
        System.out.println("enter synchronized handle_canBeAnything method");
        try {
            Thread.sleep(1000);//For testing if injection attack is rejected by using synchronized, which turns out it did works!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("exit synchronized handle_canBeAnything method");
        return;
    }

    public static void main(String args[]){
        testServer Server= new testServer();
        testSocketServerThread thread = new testSocketServerThread(Server);
        thread.start();

    }

}
