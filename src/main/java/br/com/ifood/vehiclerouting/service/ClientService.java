package br.com.ifood.vehiclerouting.service;

import br.com.ifood.vehiclerouting.entity.Client;
import br.com.ifood.vehiclerouting.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void save(Client client) {
        clientRepository.save(client);
    }

    public Long update(Client client) {
        client = clientRepository.save(client);
        return client.getId();
    }

    public Client get(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
}
