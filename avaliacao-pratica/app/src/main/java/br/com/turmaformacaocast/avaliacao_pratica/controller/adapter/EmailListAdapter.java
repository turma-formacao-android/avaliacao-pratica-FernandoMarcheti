package br.com.turmaformacaocast.avaliacao_pratica.controller.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.R;
import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Email;
import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Phone;


public class EmailListAdapter extends BaseAdapter{

    private Activity context;
    private List<Email> emails;

    public EmailListAdapter(Activity context, List<Email> emails) {
        this.context = context;
        this.emails = emails;
    }

    public void setValues(List<Email> values){
        this.emails.clear();
        this.emails.addAll(values);
    }

    @Override
    public int getCount() {
        return emails.size();
    }

    @Override
    public Email getItem(int position) {
        return emails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Email email = getItem(position);
        View view = context.getLayoutInflater().inflate(R.layout.list_item_email, parent, false);

        TextView textViewEmail = (TextView) view.findViewById(R.id.textViewEmail);
        textViewEmail.setText(email.getEmail().toString());

        return textViewEmail;
    }
}
