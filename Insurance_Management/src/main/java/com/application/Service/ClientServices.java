package com.application.Service;

import java.util.List;
import com.application.Exception.ClientNotFoundException;
import com.application.Model.Client;

public interface ClientServices {
    public List<Client> getClients() throws ClientNotFoundException;
    public Client getClient(Integer Id) throws ClientNotFoundException;
    public Client newClient(Client c);
    public Client updateClient(Integer Id, Client c) throws ClientNotFoundException;
    public void deleteClient(Integer Id) throws ClientNotFoundException;
    public Client findByName(String name); // Add this method
	boolean authenticate(Client client);
}