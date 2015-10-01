package br.com.turmaformacaocast.avaliacao_pratica;


import android.app.Application;

import br.com.turmaformacaocast.avaliacao_pratica.util.ApplicationUtil;

public class ContactsNoteManagerApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());
    }
}
