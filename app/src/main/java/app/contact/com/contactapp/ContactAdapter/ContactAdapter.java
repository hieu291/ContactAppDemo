package app.contact.com.contactapp.ContactAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.contact.com.contactapp.R;
import app.contact.com.contactapp.model.Contact;

import static app.contact.com.contactapp.R.drawable.masculine;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private int resource;
    private List<Contact> arrayContact;

    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrayContact = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.row_listview_layout, parent, false);
            viewHolder.imgAvatar = (ImageView) convertView.findViewById(R.id.image_avatar);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvPhone = (TextView) convertView.findViewById(R.id.tv_phone);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Contact contact = arrayContact.get(position);

        viewHolder.tvName.setText(contact.getmName());
        viewHolder.tvPhone.setText(contact.getmPhone());

        if(contact.isMale()){
            viewHolder.imgAvatar.setBackgroundResource(R.drawable.masculine);
        } else {
            viewHolder.imgAvatar.setBackgroundResource(R.drawable.femenine);
        }
        return convertView;
    }

    }

    class ViewHolder {
        ImageView imgAvatar;
        TextView tvName;
        TextView tvPhone;
    }
