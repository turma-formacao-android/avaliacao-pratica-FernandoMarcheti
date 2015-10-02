package br.com.turmaformacaocast.avaliacao_pratica.model.services;

import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Email;
import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Phone;
import br.com.turmaformacaocast.avaliacao_pratica.model.persistence.repositories.EmailRepository;
import br.com.turmaformacaocast.avaliacao_pratica.model.persistence.repositories.PhoneRepository;

public final class EmailBusinessService {

    public EmailBusinessService() {
        super();
    }

    public static List<Email> getAll() {
        return EmailRepository.getAll();
    }

    public static List<Email> getByContactId(int contactId){
        return EmailRepository.getByContactId(contactId);
    }

    public static void save(List<Email> emails, long contactId) {
        EmailRepository.save(emails, contactId);
    }

    public static void delete(Phone phone) {
        //PhoneRepository.delete(contact.getId());
    }
}
