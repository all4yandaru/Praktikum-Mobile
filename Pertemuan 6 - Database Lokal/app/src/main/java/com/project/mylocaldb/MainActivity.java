package com.project.mylocaldb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.project.mylocaldb.adapter.PenjualanAdapter;
import com.project.mylocaldb.database.PenjualanDao;
import com.project.mylocaldb.database.PenjualanDatabase;
import com.project.mylocaldb.model.Penjualan;

import java.util.ArrayList;
import java.util.List;

// CALLBACK
public class MainActivity extends AppCompatActivity implements PenjualanAdapter.OnDeleteListener {

    private FloatingActionButton fabAdd;

    private RecyclerView rvPenjualan;
    private PenjualanAdapter penjualanAdapter;
    private ArrayList<Penjualan> listPenjualan = new ArrayList<>();
    private PenjualanDao penjualanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPenjualan = findViewById(R.id.rv_penjualan);
        penjualanDao = PenjualanDatabase.getInstance(this).penjualanDao();

        penjualanAdapter = new PenjualanAdapter(this);
        penjualanAdapter.onDeleteClickedListener(this);

        rvPenjualan.setHasFixedSize(true);
        rvPenjualan.setLayoutManager(new LinearLayoutManager(this));
        rvPenjualan.setAdapter(penjualanAdapter);
        loadData();

        fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PenjualanAddActivity.class);
                startActivityForResult(intent, PenjualanAddActivity.REQUEST_ADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PenjualanAddActivity.REQUEST_ADD) {
            if (resultCode == PenjualanAddActivity.RESULT_ADD) {
                loadData();
                showSnackbar("Data berhasil ditambahkan!");
            }
        }
        else if (requestCode == PenjualanUpdateActivity.REQUEST_EDIT){
            if (resultCode == PenjualanUpdateActivity.RESULT_EDIT){
                loadData();
                showSnackbar("Data berhasil diupdate!");
            }
        }
    }

    void loadData() {
        List<Penjualan> data = penjualanDao.getAllData();
        if (listPenjualan.size() >= 0) {
            listPenjualan.clear();
        }
        Log.d("showData", data.toString());
        listPenjualan.addAll(data);
        penjualanAdapter.setListPenjualan(listPenjualan);

        if (data.size() == 0) {
            showSnackbar("Tidak ada data!");
        }
    }

    private void showSnackbar(String message) {
        Snackbar.make(rvPenjualan, message, Snackbar.LENGTH_SHORT).show();
    }

    // CALLBACK
    @Override
    public void deleteClicked() {
        loadData();
    }
}