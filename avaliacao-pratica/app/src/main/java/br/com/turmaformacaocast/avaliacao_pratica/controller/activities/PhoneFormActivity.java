package br.com.turmaformacaocast.avaliacao_pratica.controller.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.turmaformacaocast.avaliacao_pratica.R;
import br.com.turmaformacaocast.avaliacao_pratica.controller.synchronizeds.SavePhone;
import br.com.turmaformacaocast.avaliacao_pratica.model.entities.Phone;
import br.com.turmaformacaocast.avaliacao_pratica.util.FormHelper;

public class PhoneFormActivity extends Activity{

    private Phone phone;
    private EditText editTextPhone;
    private Button buttonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_form);

        init();
        bindeditTextPhone();
        bindButtonSave();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void init() {
        if(phone == null){
            phone = new Phone();
        }
    }

    private void bindeditTextPhone() {
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextPhone.setText(phone.getPhone());
    }

    private void bindButtonSave() {
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String requiredMessage = PhoneFormActivity.this.getString(R.string.msg_required);
                if (!FormHelper.checkRequireFields(requiredMessage, editTextPhone)) {
                    bindPhone();
                    new SetPhone().execute(phone);
                }
            }
        });
    }

    private void bindPhone() {
        phone.setPhone(editTextPhone.getText().toString());
    }

    private class SetPhone extends SavePhone {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(PhoneFormActivity.this);
            progressDialog.setMessage(getString(R.string.Loading));
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void product) {
            progressDialog.dismiss();
            Toast.makeText(PhoneFormActivity.this, R.string.msg_save_success, Toast.LENGTH_LONG).show();
            PhoneFormActivity.this.finish();
        }
    }
}
