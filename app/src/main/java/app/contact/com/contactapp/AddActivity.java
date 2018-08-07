package app.contact.com.contactapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

import app.contact.com.contactapp.ContactAdapter.ContactAdapter;
import app.contact.com.contactapp.model.Contact;

public class AddActivity extends AppCompatActivity {
    public static final String NAME = "Name";
    public static final String PHONE = "Phone";
    public static final String BUNDLE = "Bundle";
    public static final String MALE = "Male";
    private EditText edtname;
    private EditText edtnumber;
    private RadioButton rb_male;
    private RadioButton rb_female;
    private Button btnAdd2;
    RadioGroup radioGroup;
    private ContactAdapter adaptercustom;
    private List<Contact> arrayContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initWidget();
    }

    private void initWidget() {
        edtname = (EditText) findViewById(R.id.edtname);
        edtnumber = (EditText) findViewById(R.id.edtnumber);
        rb_male = (RadioButton) findViewById(R.id.rb_male);
        rb_female = (RadioButton) findViewById(R.id.rb_female);
        btnAdd2 = (Button) findViewById(R.id.btnAdd2);
        radioGroup = findViewById(R.id.radio_group);

        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContactlist();
            }
        });

    }

    private void addContactlist() {
        String name = edtname.getText().toString().trim();
        String number = edtnumber.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number)) {
            Toast.makeText(this, "Pls input something ", Toast.LENGTH_LONG).show();
        } else {
            boolean isMale = (radioGroup.getCheckedRadioButtonId() == R.id.rb_male);
            Intent intent = new Intent();
            intent.putExtra(NAME, edtname.getText().toString());
            intent.putExtra(PHONE, edtnumber.getText().toString());
            intent.putExtra(MALE, isMale);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }

//        } else {
//            Contact contact = new Contact(isMale, name, number);
//            arrayContact.add(contact);
//            Toast.makeText(this, "Add succesfully", Toast.LENGTH_SHORT).show();
//        }
//        adaptercustom.notifyDataSetChanged();

    }
}

