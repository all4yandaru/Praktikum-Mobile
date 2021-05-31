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

public class PenjualanUpdateActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_PENJUALAN = "extra_barang";
    public static final int REQUEST_EDIT = 200;
    public static final int RESULT_EDIT = 210;

    private Penjualan penjualan;
    private PenjualanDao penjualanDao;

    TextView tvTgl;
    EditText etPemasukanKotor, etPengeluaran;
    Button btnUpdate;

    String tanggal;
    String pemasukanKotor;
    String pengeluaran;
    String pemasukanBersih;

    Calendar c;
    DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjualan_update);

        if (getActionBar() != null) {
            getActionBar().setTitle("Edit");
        }

        penjualan = getIntent().getParcelableExtra(EXTRA_PENJUALAN);
        penjualanDao = PenjualanDatabase.getInstance(this).penjualanDao();

        tvTgl = findViewById(R.id.tv_tanggal);
        etPemasukanKotor = findViewById(R.id.et_pemasukan_kotor);
        etPengeluaran = findViewById(R.id.et_pengeluaran);
        btnUpdate = findViewById(R.id.btn_update);

        tvTgl.setText(penjualan.getTanggal());

        String pKotor = String.valueOf(penjualan.getPemasukan_kotor());
        String pKeluar = String.valueOf(penjualan.getPengeluaran());
        etPemasukanKotor.setText(pKotor);
        etPengeluaran.setText(pKeluar);

        btnUpdate.setOnClickListener(this);
        tvTgl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                pemasukanKotor = etPemasukanKotor.getText().toString();
                pengeluaran = etPengeluaran.getText().toString();

                if (TextUtils.isEmpty(tanggal) || TextUtils.isEmpty(pemasukanKotor) || TextUtils.isEmpty(pengeluaran)) {
                    Toast.makeText(this, "Lengkapi Data Terlebih Dahulu!", Toast.LENGTH_SHORT).show();
                } else {
                    double kotor = Double.parseDouble(pemasukanKotor);
                    double bersih = Double.parseDouble(pemasukanKotor) - Double.parseDouble(pengeluaran);
                    double keluar = Double.parseDouble(pengeluaran);
                    pemasukanBersih = String.valueOf(bersih);

                    Penjualan penjualanUpdate = new Penjualan(penjualan.getId(), tanggal, kotor, bersih, keluar);
                    this.penjualanDao.updateData(penjualanUpdate);

                    setResult(RESULT_EDIT);
                    finish();
                }
                break;

            case R.id.tv_tanggal:
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(PenjualanUpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        tvTgl.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                        tanggal = mDay + "/" + (mMonth + 1) + "/" + mYear;
                    }
                }, day, month, year);
                dpd.show();
                break;
        }
    }
}