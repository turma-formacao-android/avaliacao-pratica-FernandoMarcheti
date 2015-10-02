package br.com.turmaformacaocast.avaliacao_pratica.controller.synchronizeds;

import android.os.AsyncTask;

import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Contact;
import br.com.turmaformacaocast.avaliacao_pratica.model.services.ContactBusinessService;
import br.com.turmaformacaocast.avaliacao_pratica.model.services.PhoneBusinessService;

public class FindContacts extends AsyncTask<Void, Void, List<Contact>>{
    @Override
    protected List<Contact> doInBackground(Void... params) {
        List<Contact> contacts = ContactBusinessService.getAll();
        for (Contact contact : contacts){
            contact.setPhones(PhoneBusinessService.getByContactId(contact.getId()));
        }
        return contacts;
    }
}
