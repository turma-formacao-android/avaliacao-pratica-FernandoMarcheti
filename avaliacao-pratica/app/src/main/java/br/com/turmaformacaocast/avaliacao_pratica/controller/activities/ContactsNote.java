package br.com.turmaformacaocast.avaliacao_pratica.controller.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import br.com.turmaformacaocast.avaliacao_pratica.R;
import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Contact;


public class ContactsNote extends Activity {

    private ListView listViewContacts;
    private Contact selectedContact;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_note);

        bindFab();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void bindFab() {
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMainActivity = new Intent(ContactsNote.this, ContactsNoteForm.class);
                startActivity(goToMainActivity);
            }
        });
    }
}
