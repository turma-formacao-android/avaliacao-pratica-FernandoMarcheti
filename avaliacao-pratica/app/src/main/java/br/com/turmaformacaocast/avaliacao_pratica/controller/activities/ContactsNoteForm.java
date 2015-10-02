package br.com.turmaformacaocast.avaliacao_pratica.controller.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.turmaformacaocast.avaliacao_pratica.R;
import br.com.turmaformacaocast.avaliacao_pratica.controller.adapter.EmailListAdapter;
import br.com.turmaformacaocast.avaliacao_pratica.controller.adapter.PhoneListAdapter;
import br.com.turmaformacaocast.avaliacao_pratica.controller.synchronizeds.SaveContact;
import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Contact;
import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Email;
import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Phone;
import br.com.turmaformacaocast.avaliacao_pratica.util.FormHelper;


public class ContactsNoteForm extends Activity {

    public static final String PARAM_CONTACT = "PARAM_CONTACT";
    private EditText editTextName;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private Button buttonAddPhone;
    private Button buttonAddEmail;
    private Button buttonSave;
    private Spinner spinnerPhone;
    private Spinner spinnerEmail;

    private ListView listViewPhone;
    private ListView listViewEmail;

    private Contact contact;
    private Phone phone;
    private List<Phone> phones;
    private Email email;
    private List<Email> emails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_note_form);

        init();
        bindFields();
        bindButtonAddPhone();
        bindButtonAddEmail();
        bindButtonSave();
    }

    @Override
    protected void onResume() {
        bindFields();
        super.onResume();
    }

    private void init() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.contact = (Contact) extras.getParcelable(PARAM_CONTACT);
            this.phone = (Phone) extras.getParcelable(PARAM_CONTACT);
            this.email = (Email) extras.getParcelable(PARAM_CONTACT);
        }
        this.contact = this.contact == null ? new Contact() : this.contact;
        this.phone = this.phone == null ? new Phone() : this.phone;
        this.email = this.email == null ? new Email() : this.email;
        if (phones == null){
            phones = new ArrayList();
        }
        if (emails == null){
            emails = new ArrayList();
        }
    }

    private void bindFields() {
        bindEditTextName();
        binndEditTextPhone();
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setText(contact.getName() == null ? "" : contact.getName());
    }

    private void binndEditTextPhone() {
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextPhone.setText(phone.getPhone() == null ? "" : phone.getPhone());
    }

    private void bindButtonAddEmail() {
        buttonAddEmail = (Button) findViewById(R.id.buttonAddEmail);
        buttonAddEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextEmail = (EditText) findViewById(R.id.editTextEmail);
                if (!("".equals(editTextEmail.getText().toString()))) {
                    //listViewEmail = (ListView) findViewById(R.id.listViewEmaisForm);
                    email.setEmail(editTextEmail.getText().toString());
                    emails.add(email);
                    bindEmailList(emails);
                    email = new Email();
                }
            }
        });
    }
    private void bindButtonAddPhone() {
        buttonAddPhone = (Button) findViewById(R.id.buttonAddPhone);
        buttonAddPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextPhone = (EditText) findViewById(R.id.editTextPhone);
                if (!("".equals(editTextPhone.getText().toString()))) {
                    phone.setPhone(editTextPhone.getText().toString());
                    phones.add(phone);
                    bindPhoneList(phones);
                    phone = new Phone();
                }
            }
        });
    }

    private void bindButtonSave() {
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String requiredMessage = ContactsNoteForm.this.getString(R.string.msg_required);
                if (validarCamposObrigatorios(requiredMessage)) {
                    bindContact();
                    bindPhones();
                    bindEmails();
                    new SetContact().execute(contact);
                }
            }
        });
    }

    private boolean validarCamposObrigatorios(String requiredMessage) {
        return (!FormHelper.checkRequireFields(requiredMessage, editTextName));
    }

    private void bindContact() {
        contact.setName(editTextName.getText().toString());
    }

    private void bindPhones(){
        contact.setPhones(phones);
    }

    private void bindEmails(){
        contact.setEmails(emails);
    }

    private void bindPhoneList(List<Phone> phones) {
        spinnerPhone = (Spinner) findViewById(R.id.spinnerItemPhone);
        spinnerPhone.setAdapter(new PhoneListAdapter(this, phones));
        PhoneListAdapter adapter = (PhoneListAdapter) spinnerPhone.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void bindEmailList(List<Email> emails) {
        spinnerEmail = (Spinner) findViewById(R.id.spinnerItemEmail);
        spinnerEmail.setAdapter(new EmailListAdapter(this, emails));
        EmailListAdapter adapter = (EmailListAdapter) spinnerEmail.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void updatePhones(List<Phone> phones) {
        PhoneListAdapter adapter = (PhoneListAdapter) listViewPhone.getAdapter();
        if(adapter == null){
            adapter = new PhoneListAdapter(this, phones);
            listViewPhone.setAdapter(adapter);
        }
        adapter.setValues(phones);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contacts_note_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class SetContact extends SaveContact {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(ContactsNoteForm.this);
            progressDialog.setMessage(getString(R.string.Loading));
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void product) {
            progressDialog.dismiss();
            Toast.makeText(ContactsNoteForm.this, R.string.msg_save_success, Toast.LENGTH_LONG).show();
            ContactsNoteForm.this.finish();
        }
    }
}
