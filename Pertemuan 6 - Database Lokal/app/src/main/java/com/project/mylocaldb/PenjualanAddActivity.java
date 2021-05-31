package com.project.mylocaldb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.mylocaldb.database.PenjualanDao;
import com.project.mylocaldb.database.PenjualanDatabase;
import com.project.mylocaldb.model.Penjualan;

import java.util.Calendar;

public class PenjualanAddActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_ADD = 100;
    public static final int RESULT_ADD = 110;
    public static final String EXTRA_ADD = "extra_add";

    TextView tvTgl;
    EditText etPemasukanKotor, etPengeluaran;
    Button btnAdd;
    Calendar c;
    DatePickerDialog dpd;

    private PenjualanDao penjualanDao;

    String tanggal;
    String pemasukanKotor;
    String pengeluaran;
    String pemasukanBersih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjualan_add);

        penjualanDao = PenjualanDatabase.getInstance(this).penjualanDao();

        tvTgl = findViewById(R.id.tv_tanggal);
        etPemasukanKotor = findViewById(R.id.et_pemasukan_kotor);
        etPengeluaran = findViewById(R.id.et_pengeluaran);
        btnAdd = findViewById(R.id.btn_add);

        tvTgl.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tanggal:
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(PenjualanAddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        tvTgl.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                        tanggal = mDay + "/" + (mMonth + 1) + "/" + mYear;
                    }
                }, day, month, year);
                dpd.show();
                break;

            case R.id.btn_add:
                pemasukanKotor = etPemasukanKotor.getText().toString();
                pengeluaran = etPengeluaran.getText().toString();

                if (TextUtils.isEmpty(tanggal) || TextUtils.isEmpty(pemasukanKotor) || TextUtils.isEmpty(pengeluaran)){
                    Toast.makeText(this, "Lengkapi Data Terlebih Dahulu!", Toast.LENGTH_SHORT).show();
                } else {
                    double kotor = Double.parseDouble(pemasukanKotor);
                    double bersih = Double.parseDouble(pemasukanKotor) - Double.parseDouble(pengeluaran);
                    double keluar = Double.parseDouble(pengeluaran);
                    pemasukanBersih = String.valueOf(bersih);

                    Log.d("testAdd", tanggal);
                    Log.d("testAdd", String.valueOf(bersih));
                    Log.d("testAdd", String.valueOf(kotor));
                    Log.d("testAdd", String.valueOf(keluar));

                    Penjualan penjualan = new Penjualan(tanggal, kotor, bersih, keluar);
                    this.penjualanDao.insertData(penjualan);

                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_ADD, penjualan);
                    setResult(RESULT_ADD);
                    finish();
                }
                break;
        }
    }
}