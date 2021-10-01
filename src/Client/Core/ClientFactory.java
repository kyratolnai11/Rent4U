package Client.Core;

import Client.Networking.Client;
import Client.Networking.ClientImpl;

public class ClientFactory
{
    private static ClientFactory instance = new ClientFactory();
    private Client client;

    private ClientFactory() {}

    public static ClientFactory getInstance()
    {
        return instance;
    }

    public Client getClient()
    {
        if(client == null)
            client = new ClientImpl();
        return client;
    }
}
