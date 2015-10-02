package br.com.turmaformacaocast.avaliacao_pratica.controller.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.R;
import br.com.turmaformacaocast.avaliacao_pratica.controller.adapter.ContactsListAdapter;
import br.com.turmaformacaocast.avaliacao_pratica.controller.synchronizeds.FindContacts;
import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Contact;


public class ContactsNote extends Activity {

    private ListView listViewContacts;
    private Contact selectedContact;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_note);

        bindContacts();
        bindFab();
    }

    @Override
    protected void onResume() {
        new UpdateListContacts().execute();
        super.onResume();
    }

    private void bindContacts() {
        listViewContacts = (ListView) findViewById(R.id.listViewContacts);
        registerForContextMenu(listViewContacts);
        listViewContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ContactsListAdapter adapter = (ContactsListAdapter) listViewContacts.getAdapter();
                selectedContact = adapter.getItem(position);
                return false;
            }
        });
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

    private void updateContacts(List<Contact> contacts) {
        ContactsListAdapter adapter = (ContactsListAdapter) listViewContacts.getAdapter();
        if(adapter == null){
            adapter = new ContactsListAdapter(this);
            listViewContacts.setAdapter(adapter);
        }
        adapter.setValues(contacts);
        adapter.notifyDataSetChanged();
    }

    private class UpdateListContacts extends FindContacts{

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(ContactsNote.this);
            progressDialog.setMessage(getString(R.string.Loading));
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(List<Contact> contacts) {
            updateContacts(contacts);
            progressDialog.dismiss();
        }
    }
}
