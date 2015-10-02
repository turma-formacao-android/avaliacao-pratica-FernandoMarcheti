package br.com.turmaformacaocast.avaliacao_pratica.controller.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.R;
import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Contact;

public class ContactsListAdapter extends BaseAdapter {

    private Activity context;
    private List<Contact> contacts;

    public ContactsListAdapter(Activity context) {
        this.context = context;
        this.contacts = new ArrayList();
    }

    public void setValues(List<Contact> values){
        contacts.clear();
        contacts.addAll(values);
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Contact getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Contact contact = getItem(i);
        View contactsItemView = context.getLayoutInflater().inflate(R.layout.list_item_contacts, viewGroup, false);

        TextView textViewName = (TextView) contactsItemView.findViewById(R.id.textViewName);
        textViewName.setText(contact.getName().toString());

        TextView textViewPhone = (TextView) contactsItemView.findViewById(R.id.textViewPhone);
        textViewPhone.setText(contact.getPhones().get(0).getPhone().toString());

        if(contact.getEmails() != null && contact.getEmails().size() > 0) {
            TextView textViewEmail = (TextView) contactsItemView.findViewById(R.id.textViewEmail);
            textViewEmail.setText(contact.getEmails().get(0).getEmail().toString());
        }

        return contactsItemView;
    }
}
