package BuilderDP;

public class Main {
    public static void main(String args[]) {

        Builder clientreceiver = new ClientReceiver();
        clientreceiver.build();
        System.out.println(clientreceiver.getResult());
    }
}