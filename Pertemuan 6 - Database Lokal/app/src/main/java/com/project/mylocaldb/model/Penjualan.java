package com.project.mylocaldb.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "penjualan")
public class Penjualan implements Parcelable {

    @PrimaryKey(autoGenerate = true) // auto increment
    @ColumnInfo(name = "Id") // nama kolom
    private int id;

    @ColumnInfo(name = "Tanggal")
    private String tanggal;

    @ColumnInfo(name = "PemasukanKotor")
    private double pemasukan_kotor;

    @ColumnInfo(name = "PemasukanBersih")
    private double pemasukan_bersih;

    @ColumnInfo(name = "Pengeluaran")
    private double pengeluaran;

    @Ignore
    public Penjualan(String tanggal, double pemasukan_kotor, double pemasukan_bersih, double pengeluaran) {
        this.tanggal = tanggal;
        this.pemasukan_kotor = pemasukan_kotor;
        this.pemasukan_bersih = pemasukan_bersih;
        this.pengeluaran = pengeluaran;
    }

    public Penjualan(int id, String tanggal, double pemasukan_kotor, double pemasukan_bersih, double pengeluaran) {
        this.id = id;
        this.tanggal = tanggal;
        this.pemasukan_kotor = pemasukan_kotor;
        this.pemasukan_bersih = pemasukan_bersih;
        this.pengeluaran = pengeluaran;
    }

    @Ignore
    protected Penjualan(Parcel in) {
        id = in.readInt();
        tanggal = in.readString();
        pemasukan_kotor = in.readDouble();
        pemasukan_bersih = in.readDouble();
        pengeluaran = in.readDouble();
    }

    public static final Creator<Penjualan> CREATOR = new Creator<Penjualan>() {
        @Override
        public Penjualan createFromParcel(Parcel in) {
            return new Penjualan(in);
        }

        @Override
        public Penjualan[] newArray(int size) {
            return new Penjualan[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public double getPemasukan_kotor() {
        return pemasukan_kotor;
    }

    public void setPemasukan_kotor(double pemasukan_kotor) {
        this.pemasukan_kotor = pemasukan_kotor;
    }

    public double getPemasukan_bersih() {
        return pemasukan_bersih;
    }

    public void setPemasukan_bersih(double pemasukan_bersih) {
        this.pemasukan_bersih = pemasukan_bersih;
    }

    public double getPengeluaran() {
        return pengeluaran;
    }

    public void setPengeluaran(double pengeluaran) {
        this.pengeluaran = pengeluaran;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(tanggal);
        dest.writeDouble(pemasukan_kotor);
        dest.writeDouble(pemasukan_bersih);
        dest.writeDouble(pengeluaran);
    }
}
