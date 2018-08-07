package app.contact.com.contactapp;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import app.contact.com.contactapp.ContactAdapter.ContactAdapter;
import app.contact.com.contactapp.model.Contact;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    private final int CODE_ADD = 1;

    private ContactAdapter adaptercustom;
    private List<Contact> arrayContact;
    private Button btn_Add1;
    private EditText edtname;
    private EditText edtnumber;
    private RadioButton rb_male;
    private RadioButton rb_female;
    private ListView lv_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();

        checkandrequestPermission();
        //khoi tao cac arraycontact adapter
        arrayContact = new ArrayList<>();
        adaptercustom = new ContactAdapter(this, R.layout.row_listview_layout, arrayContact);
        lv_contact.setAdapter(adaptercustom);
        lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                showDialogComfirm(position);
            }
        });
    }


    //code xin permission tu android 6.0 tro len
    public void checkandrequestPermission() {
        String[] permissions = new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }
    }

    //hien cuoc goi va message dialog
    public void showDialogComfirm(final int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        Button btnCall = (Button) dialog.findViewById(R.id.btnCall);
        Button btnMessage = (Button) dialog.findViewById(R.id.btnMessage);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"Call",Toast.LENGTH_SHORT).show();
                intentCall(position);
            }
        });
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Message", Toast.LENGTH_SHORT).show();
                intentMessagesend(position);
            }
        });

        dialog.show();
    }
    //index bat dau tu 0 , size bat dau tu 1
    public void initWidget() {
        btn_Add1 = (Button) findViewById(R.id.btnAdd1);
        lv_contact = (ListView) findViewById(R.id.lv_contact);
        btn_Add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContact();
            }
        });
    }

    //chuyen man activity
    public void addContact() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent, CODE_ADD);
    }

    //truyen nhan du lieu tu activity khac
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.wtf(TAG, "onActivityResult " + requestCode + " " + resultCode);
        if (requestCode == CODE_ADD) {
            Log.wtf(TAG, "onActivityResult " + requestCode);
            if (resultCode == RESULT_OK) {
                Log.wtf(TAG, "onActivityResult " + requestCode + " " + resultCode);
                String name = data.getStringExtra(AddActivity.NAME);
                String number = data.getStringExtra(AddActivity.PHONE);
                boolean idMale = data.getBooleanExtra(AddActivity.MALE, true);

                Contact contact = new Contact(idMale, name, number);
                arrayContact.add(contact);
                adaptercustom.notifyDataSetChanged();
            }
        }
    }

    //hanh dong goi dien thoai
    private void intentCall(final int position) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + arrayContact.get(position).getmPhone()));
        startActivity(intent);
    }

    //hanh dong nhan tin
    private void intentMessagesend(final int position) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + arrayContact.get(position).getmPhone()));
        startActivity(intent);
    }
}
