package br.com.turmaformacaocast.avaliacao_pratica.controller.synchronizeds;

import android.os.AsyncTask;

import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Contact;
import br.com.turmaformacaocast.avaliacao_pratica.model.services.ContactBusinessService;
import br.com.turmaformacaocast.avaliacao_pratica.model.services.EmailBusinessService;
import br.com.turmaformacaocast.avaliacao_pratica.model.services.PhoneBusinessService;

public class SaveContact extends AsyncTask<Contact, Void, Void> {

    @Override
    protected Void doInBackground(Contact... params) {
        long id = ContactBusinessService.save(params[0]);
        PhoneBusinessService.save(params[0].getPhones(), id);
        EmailBusinessService.save(params[0].getEmails(), id);
        return null;
    }
}
