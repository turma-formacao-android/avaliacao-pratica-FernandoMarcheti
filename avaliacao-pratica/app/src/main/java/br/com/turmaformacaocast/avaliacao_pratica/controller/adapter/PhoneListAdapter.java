package br.com.turmaformacaocast.avaliacao_pratica.controller.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.R;
import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Phone;

public class PhoneListAdapter extends BaseAdapter{

    private Activity context;
    private List<Phone> phones;

    public PhoneListAdapter(Activity context, List<Phone> phones) {
        this.context = context;
        this.phones = phones;
    }

    public void setValues(List<Phone> values){
        this.phones.clear();
        this.phones.addAll(values);
    }

    @Override
    public int getCount() {
        return phones.size();
    }

    @Override
    public Phone getItem(int position) {
        return phones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Phone phone = getItem(position);
        View view = context.getLayoutInflater().inflate(R.layout.list_item_phone, parent, false);

        TextView textViewPhone = (TextView) view.findViewById(R.id.textViewP);
        textViewPhone.setText(phone.getPhone().toString());

        return textViewPhone;
    }
}
