package br.com.turmaformacaocast.avaliacao_pratica.model.services;

import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Contact;
import br.com.turmaformacaocast.avaliacao_pratica.model.persistence.repositories.ContactRepository;

public final class ContactBusinessService {

    public ContactBusinessService() {
        super();
    }

    public static List<Contact> getAll() {
        return ContactRepository.getAll();
    }

    public static long save(Contact contact) {
        return ContactRepository.save(contact);
    }

    public static void delete(Contact contact) {
        ContactRepository.delete(contact.getId());
    }
}
