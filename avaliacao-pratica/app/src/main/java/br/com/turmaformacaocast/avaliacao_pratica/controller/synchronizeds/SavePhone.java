package br.com.turmaformacaocast.avaliacao_pratica.controller.synchronizeds;

import android.os.AsyncTask;

import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Phone;
import br.com.turmaformacaocast.avaliacao_pratica.model.services.PhoneBusinessService;

public class SavePhone  extends AsyncTask<Phone, Void, Void> {

    @Override
    protected Void doInBackground(Phone... params) {
        //PhoneBusinessService.save(params[0]);
        return null;
    }
}
