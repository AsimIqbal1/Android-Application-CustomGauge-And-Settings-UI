package com.example.bluetoothappui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {


    RadioGroup rgTemperatureUnit;
    RadioButton rbSelectedRadio;

    EditText etNewDeviceName;
    ImageView btnAddNewDevice;

    RecyclerView rvRegisteredDevices;

    ArrayList<Device> deviceList = new ArrayList<>();

    MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        rgTemperatureUnit = findViewById(R.id.rg_temp_units);
        rbSelectedRadio = findViewById(rgTemperatureUnit.getCheckedRadioButtonId());
        //do your operations with "rbSelectedRadio" now

        etNewDeviceName = findViewById(R.id.et_new_device_name);
        btnAddNewDevice = findViewById(R.id.iv_add_new_device);

        rvRegisteredDevices = findViewById(R.id.rv_registered_devices);

        adapter = new MyListAdapter(deviceList);
        rvRegisteredDevices.setHasFixedSize(true);
        rvRegisteredDevices.setLayoutManager(new LinearLayoutManager(this));
        rvRegisteredDevices.setAdapter(adapter);

        btnAddNewDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update the list view here
                String devicename = etNewDeviceName.getText().toString();
                if(!devicename.isEmpty()) {
                    deviceList.add(new Device(devicename));
                    adapter.notifyChange();
                    etNewDeviceName.setText("");
                    Toast.makeText(SettingActivity.this, devicename + " Added.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

        ArrayList<Device> devices;

        MyListAdapter(ArrayList<Device> devices) {
            this.devices = devices;
        }

        void notifyChange(){
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem = layoutInflater.inflate(R.layout.registered_divices_row, parent, false);
            return new ViewHolder(listItem);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final Device device = devices.get(position);
            holder.textViewDeviceName.setText(device.deviceName);
            holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SettingActivity.this, deviceList.get(position).deviceName + " Deleted.", Toast.LENGTH_SHORT).show();
                    deviceList.remove(position);
                    notifyChange();

                }
            });

        }


        @Override
        public int getItemCount() {
            if(devices!=null) {
                return devices.size();
            }else{
                return 0;
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textViewDeviceName;
            ImageView imageViewDelete;
            ConstraintLayout cl;
            ViewHolder(View itemView) {
                super(itemView);
                this.textViewDeviceName =  itemView.findViewById(R.id.tv_device_name);
                this.imageViewDelete = itemView.findViewById(R.id.iv_delete_device);
                this.cl = itemView.findViewById(R.id.constraint_layout_rv);
            }
        }
    }
}
