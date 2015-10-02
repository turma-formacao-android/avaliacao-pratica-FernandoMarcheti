package br.com.turmaformacaocast.avaliacao_pratica.model.services;

import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Contact;
import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Phone;
import br.com.turmaformacaocast.avaliacao_pratica.model.persistence.repositories.ContactRepository;
import br.com.turmaformacaocast.avaliacao_pratica.model.persistence.repositories.PhoneRepository;

public final class PhoneBusinessService {

    public PhoneBusinessService() {
        super();
    }

    public static List<Phone> getAll() {
        return PhoneRepository.getAll();
    }

    public static List<Phone> getByContactId(int contactId){
        return PhoneRepository.getByContactId(contactId);
    }

    public static void save(List<Phone> phones, long contactId) {
        PhoneRepository.save(phones, contactId);
    }

    public static void delete(Phone phone) {
        //PhoneRepository.delete(contact.getId());
    }
}
