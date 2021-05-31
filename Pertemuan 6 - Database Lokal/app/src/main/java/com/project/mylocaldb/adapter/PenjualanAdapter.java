package com.project.mylocaldb.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mylocaldb.MainActivity;
import com.project.mylocaldb.PenjualanUpdateActivity;
import com.project.mylocaldb.R;
import com.project.mylocaldb.database.PenjualanDao;
import com.project.mylocaldb.database.PenjualanDatabase;
import com.project.mylocaldb.model.Penjualan;

import java.text.NumberFormat;
import java.util.ArrayList;

public class PenjualanAdapter extends RecyclerView.Adapter<PenjualanAdapter.VH> {
    private ArrayList<Penjualan> listPenjualan = new ArrayList<>();
    private Activity activity;

    // CALLBACK
    OnDeleteListener mCallback;

    public void setListPenjualan(ArrayList<Penjualan> listPenjualan) {
        if (this.listPenjualan.size() >= 0) {
            this.listPenjualan.clear();
        }
        this.listPenjualan.addAll(listPenjualan);
        notifyDataSetChanged();
    }

    public PenjualanAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.penjualan_item, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(listPenjualan.get(position));
    }

    @Override
    public int getItemCount() {
        return listPenjualan.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView tvTanggal, tvPemasukanKotor, tvPemasukanBersih, tvPengeluaran;
        ImageView ivDelete;

        Penjualan penjualan;
        PenjualanDao penjualanDao;

        public VH(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvPemasukanBersih = itemView.findViewById(R.id.tv_pemasukan_bersih);
            tvPemasukanKotor = itemView.findViewById(R.id.tv_pemasukan_kotor);
            tvPengeluaran = itemView.findViewById(R.id.tv_pengeluaran);

            ivDelete = itemView.findViewById(R.id.iv_delete);
        }

        void bind(final Penjualan data) {
            NumberFormat format = NumberFormat.getInstance();
            String bersih = "Pemasukan Bersih : Rp " + format.format(data.getPemasukan_bersih());
            String kotor = "Pemasukan Kotor : Rp " + format.format(data.getPemasukan_kotor());
            String keluar = "Pengeluaran : Rp " + format.format(data.getPengeluaran());

            tvTanggal.setText(data.getTanggal());
            tvPengeluaran.setText(keluar);
            tvPemasukanKotor.setText(kotor);
            tvPemasukanBersih.setText(bersih);

            penjualanDao = PenjualanDatabase.getInstance(activity).penjualanDao();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, PenjualanUpdateActivity.class);
                    intent.putExtra(PenjualanUpdateActivity.EXTRA_PENJUALAN, data);
                    activity.startActivityForResult(intent, PenjualanUpdateActivity.REQUEST_EDIT);
                }
            });

            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(activity.getApplicationContext(), "Memproses Delete Data", Toast.LENGTH_SHORT).show();
                    penjualanDao.deleteData(data);
                    //Intent i = new Intent(activity, MainActivity.class);
                    // CALLBACK
                    mCallback.deleteClicked();
                    //activity.startActivity(i);
                    //activity.finish();
                }
            });
        }
    }

    // CALLBACK
    public void onDeleteClickedListener(OnDeleteListener mCallback){
        this.mCallback = mCallback;
    }

    public interface OnDeleteListener {
        public void deleteClicked();
    }
}
