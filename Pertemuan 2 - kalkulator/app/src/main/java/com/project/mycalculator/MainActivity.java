package com.project.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBalok, btnTabung, btnBola, btnHitung;
    EditText etParameter1, etParameter2, etParameter3;
    TextView tvVolume, tvLuasPermukaan, tvJudul;
    private int choose = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBalok = findViewById(R.id.btn_balok);
        btnTabung = findViewById(R.id.btn_tabung);
        btnBola = findViewById(R.id.btn_bola);
        btnHitung = findViewById(R.id.btn_hitung);

        etParameter1 = findViewById(R.id.et_parameter1);
        etParameter2 = findViewById(R.id.et_parameter2);
        etParameter3 = findViewById(R.id.et_parameter3);

        tvVolume = findViewById(R.id.tv_volume);
        tvLuasPermukaan = findViewById(R.id.tv_luas_permukaan);
        tvJudul = findViewById(R.id.tv_judul);

        firstRun();

        btnBalok.setOnClickListener(this);
        btnTabung.setOnClickListener(this);
        btnBola.setOnClickListener(this);
        btnHitung.setOnClickListener(this);
    }

    void firstRun() {
        choose = 1;
        setParameter();
    }

    void setParameter() {
        switch (choose) {
            case 1:
                tvJudul.setText("Balok");
                tvVolume.setText("");
                tvLuasPermukaan.setText("");

                etParameter1.setText("");
                etParameter2.setText("");
                etParameter3.setText("");

                etParameter1.setVisibility(View.VISIBLE);
                etParameter2.setVisibility(View.VISIBLE);
                etParameter3.setVisibility(View.VISIBLE);

                etParameter1.setHint("Panjang");
                etParameter2.setHint("Lebar");
                etParameter3.setHint("Tinggi");
                break;

            case 2:
                tvJudul.setText("Tabung");
                tvVolume.setText("");
                tvLuasPermukaan.setText("");

                etParameter1.setText("");
                etParameter2.setText("");
                etParameter3.setText("");

                etParameter1.setVisibility(View.VISIBLE);
                etParameter2.setVisibility(View.VISIBLE);
                etParameter3.setVisibility(View.GONE);

                etParameter1.setHint("Jari - Jari");
                etParameter2.setHint("Tinggi");
                break;

            case 3:
                tvJudul.setText("Bola");
                tvVolume.setText("");
                tvLuasPermukaan.setText("");

                etParameter1.setText("");
                etParameter2.setText("");
                etParameter3.setText("");

                etParameter1.setVisibility(View.VISIBLE);
                etParameter2.setVisibility(View.GONE);
                etParameter3.setVisibility(View.GONE);

                etParameter1.setHint("Jari - Jari");
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_balok:
                choose = 1;
                setParameter();
                break;

            case R.id.btn_tabung:
                choose = 2;
                setParameter();
                break;

            case R.id.btn_bola:
                choose = 3;
                setParameter();
                break;

            case R.id.btn_hitung:
                String parameter1 = String.valueOf(etParameter1.getText());
                String parameter2 = String.valueOf(etParameter2.getText());
                String parameter3 = String.valueOf(etParameter3.getText());
                if (choose == 1){
                    balok(parameter1, parameter2, parameter3);
                } else if (choose == 2){
                    tabung(parameter1, parameter2);
                } else if (choose == 3){
                    bola(parameter1);
                }
                break;
        }
    }

    void balok(String p, String l, String t){
        try {
            int panjang = Integer.parseInt(p);
            int lebar = Integer.parseInt(l);
            int tinggi = Integer.parseInt(t);

            double volume = panjang * lebar * tinggi * 1.0;
            double luasPermukaan = 2.0*(panjang*lebar) + 2.0*(panjang*tinggi) + 2.0*(lebar*tinggi);

            tvVolume.setText(String.valueOf(volume));
            tvLuasPermukaan.setText(String.valueOf(luasPermukaan));
        } catch (Exception e) {
            Toast.makeText(this, "Isi kolom dengan benar!", Toast.LENGTH_SHORT).show();
        }
    }

    void tabung(String r, String t){
        try {
            int jari = Integer.parseInt(r);
            int tinggi = Integer.parseInt(t);

            double volume = Math.PI * Math.pow(jari,2) * tinggi;
            double luasPermukaan = Math.PI * Math.pow(jari,2) + 2 * Math.PI * jari * tinggi;

            tvVolume.setText(String.valueOf(volume));
            tvLuasPermukaan.setText(String.valueOf(luasPermukaan));
        } catch (Exception e) {
            Toast.makeText(this, "Isi kolom dengan benar!", Toast.LENGTH_SHORT).show();
        }
    }

    void bola(String r){
        try {
            int jari = Integer.parseInt(r);

            double volume = 4.0 / 3 * Math.PI * Math.pow(jari,3);
            double luasPermukaan = 4 * Math.PI * Math.pow(jari,2);

            tvVolume.setText(String.valueOf(volume));
            tvLuasPermukaan.setText(String.valueOf(luasPermukaan));
        } catch (Exception e) {
            Toast.makeText(this, "Isi kolom dengan benar!", Toast.LENGTH_SHORT).show();
        }
    }
}