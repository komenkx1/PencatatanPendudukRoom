package com.example.pencatatanpenduduk.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tbPenduduk")
public class Penduduk implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int _id;
    @ColumnInfo(name = "namaLengkap")
    public String namaLengkap;
    @ColumnInfo(name = "alamat")
    public String alamat;
    @ColumnInfo(name = "tanggalLahir")
    public String tanggalLahir;
    @ColumnInfo(name = "tanggalTercatat")
    public String tanggalTercatat;
    @ColumnInfo(name = "jenisKelamin")
    public String jenisKelamin;
    @ColumnInfo(name = "nomorTlp")
    public String nomorTlp;
    @ColumnInfo(name = "gaji")
    public String gaji;
    @ColumnInfo(name = "agama")
    public String agama;
    @ColumnInfo(name = "hobi")
    public String hobi;
    @ColumnInfo(name = "foto")
    public String foto;


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getnamaLengkap() {
        return namaLengkap;
    }

    public void setnamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String gettanggalLahir() {
        return tanggalLahir;
    }

    public void settanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String gettanggalTercatat() {
        return tanggalTercatat;
    }

    public void settanggalTercatat(String tanggalTercatat) {
        this.tanggalTercatat = tanggalTercatat;
    }

    public String getjenisKelamin() {
        return jenisKelamin;
    }

    public void setjenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNomorTlp() {
        return nomorTlp;
    }

    public void setNomorTlp(String nomorTlp) {
        this.nomorTlp = nomorTlp;
    }

    public String getGaji() {
        return gaji;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getHobi() {
        return hobi;
    }

    public void setHobi(String hobi) {
        this.hobi = hobi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
