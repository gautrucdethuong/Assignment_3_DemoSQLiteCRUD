package com.example.democrudsqlite.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.democrudsqlite.R;
import com.example.democrudsqlite.model.Customer;
import com.example.democrudsqlite.repository.layout_activity.CustomerActivity;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Author by HUYNH NHAT MINH (ミン).
 * Email: minhhnce140197@fpt.edu.vn.
 * Date on 6/1/2021.
 * Company: FPT大学.
 */

public class AdapterCustomer extends BaseAdapter {
    //Declare variable
    private CustomerActivity context;
    private ArrayList<Customer> customerArrayList;

    // Declare constructor
    public AdapterCustomer(CustomerActivity context, ArrayList<Customer> arrayListCustomer) {
        this.context = context;
        customerArrayList = arrayListCustomer;
    }


    @Override
    public int getCount() {
        return customerArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // inflate the layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.activity_item_add, null);

        // Call id component in Layout
        TextView txtCustomerName =(TextView) convertView.findViewById(R.id.txtName);
        TextView txtCustomerCode =(TextView) convertView.findViewById(R.id.editText_note_content);
        ImageView imageUrlView = (ImageView) convertView.findViewById(R.id.imageViewUrl);
        ImageButton imageBtnUpdate = (ImageButton) convertView.findViewById(R.id.ibtnUpdate);
        ImageButton imageBtnDelete = (ImageButton) convertView.findViewById(R.id.ibtnDelete);

        // Get positive customer from List
        Customer customer = customerArrayList.get(position);

        txtCustomerName.setText(customer.getCustomerName());
        txtCustomerCode.setText("Code: " + customer.getCustomerCode());

        //Load image from internet
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(customerArrayList.get(position).getCustomerUrl()).getContent());
            imageUrlView.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get id customer
        int id = customer.getCustomerId();

        // Handle event when user clicks on
        imageBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.delete(id);
            }
        });

        //Handle event when user clicks on
        imageBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.update(id);
            }
        });

        return convertView;
    }
}
