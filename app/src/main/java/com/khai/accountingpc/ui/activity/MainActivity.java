package com.khai.accountingpc.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.khai.accountingpc.R;
import com.khai.accountingpc.ui.PCmodel;

public class MainActivity extends AppCompatActivity {

    private Button btnQrData;
    private String mScanResult;

    private TextView tvName;
    private TextView tvOldId;
    private TextView tvId;
    private TextView tvMac;
    private TextView tvIp;
    private TextView tvNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScanResult = getIntent().getStringExtra("qrData");
        btnQrData = (Button) findViewById(R.id.btnScan);

        tvName = (TextView) findViewById(R.id.Name);
        tvOldId = (TextView) findViewById(R.id.Old_Id);
        tvId = (TextView) findViewById(R.id.Id);
        tvMac = (TextView) findViewById(R.id.Mac);
        tvIp = (TextView) findViewById(R.id.Ip);
        tvNotes = (TextView) findViewById(R.id.Notes);

        PCmodel model = new PCmodel(mScanResult);
        fillField(model);
        btnQrData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToQrReader();
            }
        });
    }

    @Override
    public void onBackPressed() {
        goToQrReader();
    }

    private void goToQrReader() {
        startActivity(new Intent(MainActivity.this, QrReaderActivity.class));
        finish();
    }

    private void fillField(PCmodel model) {
        tvName.setText(model.getName());
        tvOldId.setText(model.getOldId());
        tvId.setText(model.getId());
        tvMac.setText(model.getMac());
        tvIp.setText(model.getIp());
        tvNotes.setText(model.getNotes());
    }

}
