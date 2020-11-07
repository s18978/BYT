package BuilderDP;

public class ClientReceiver implements Builder<Client>{

    private final Client client = new Client();

    @Override
    public void build() {
        int i = (int)(Math.random()*2);
        if(i == 0)
            client.setId(1).setName("John Green").setNumber(44576658);
        else
            client.setId(2).setName("Ann Johnson").setNumber(58950395);
    }

    @Override
    public Client getResult() {
        return client;
    }
}