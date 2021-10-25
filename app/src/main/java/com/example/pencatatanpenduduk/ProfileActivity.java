package com.example.pencatatanpenduduk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pencatatanpenduduk.Helpers.DBRoomHelper;
import com.example.pencatatanpenduduk.model.Penduduk;

import java.text.NumberFormat;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {

    private TextView nama, alamat, tanggalLahir, noTelp, gaji, agama, hobi, shortName, tglTercatat,jenisKelamin;
    private ImageView imageView;
    private int id;
    private DBRoomHelper db;
    private  Bundle bundle;
    private Locale localeID = new Locale("in", "ID");
    private NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initiate pemanggilan Room database
        db = Room.databaseBuilder(getApplicationContext(),
                DBRoomHelper.class, "tbPenduduk").allowMainThreadQueries().build();

        setContentView(R.layout.activity_profile);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(null);

        imageView = (ImageView) findViewById(R.id.profilePicture);
        nama = (TextView) findViewById(R.id.detailsNama);
        alamat = (TextView) findViewById(R.id.detailAlamat);
        tanggalLahir = (TextView) findViewById(R.id.detailsTglLahir);
        noTelp = (TextView) findViewById(R.id.detailsNoTelp);
        gaji = (TextView) findViewById(R.id.detailsGaji);
        agama = (TextView) findViewById(R.id.detailsAgama);
        hobi = (TextView) findViewById(R.id.detailsHobi);
        shortName = (TextView) findViewById(R.id.namaDepan);
        tglTercatat = (TextView) findViewById(R.id.tglTercatat);
        jenisKelamin = (TextView) findViewById(R.id.jk);



        //detail penduduk terbaru
        if (getIntent().getStringExtra("isNew") != null){

            String [] namaDepan = getIntent().getStringExtra("name").split(" ");

            shortName.setText( namaDepan.length > 1 ? namaDepan[0]+" "+namaDepan[1] : namaDepan[0]);
            nama.setText( getIntent().getStringExtra("name"));
            alamat.setText(getIntent().getStringExtra("alamat"));
            tanggalLahir.setText(getIntent().getStringExtra("tanggalLahir"));
            noTelp.setText(getIntent().getStringExtra("nomor_telepon"));
            gaji.setText(String.valueOf(formatRupiah.format((double) Integer.parseInt(getIntent().getStringExtra("gaji")))));
            agama.setText(getIntent().getStringExtra("agama"));
            hobi.setText(getIntent().getStringExtra("hobi"));
            tglTercatat.setText("Tanggal Tercatat : "+ getIntent().getStringExtra("tanggal_tercatat"));
            jenisKelamin.setText(getIntent().getStringExtra("jenisKelamin"));

            if (getIntent().getStringExtra("avatar") != null){
                Uri avatarUri = Uri.parse(getIntent().getStringExtra("avatar"));
                imageView.setImageURI(avatarUri);
            }

        }else{
            bundle = getIntent().getBundleExtra("userData");
            id = bundle.getInt("id");
            //detail Penduduk dengan select single
            Penduduk penduduk = db.pendudukDAO().selectPendudukDetail(id);

            if(penduduk != null) {

                @SuppressLint("Range")
                String [] namaDepan = penduduk.getnamaLengkap().split(" ");
                shortName.setText( namaDepan.length > 1 ? namaDepan[0]+" "+namaDepan[1] : namaDepan[0]);
                nama.setText(penduduk.getnamaLengkap());
//
                tanggalLahir.setText(penduduk.gettanggalLahir());
                alamat.setText(penduduk.getAlamat());
                gaji.setText(formatRupiah.format((double) Integer.parseInt(penduduk.getGaji())));
                hobi.setText(penduduk.getHobi());
                jenisKelamin.setText(penduduk.getjenisKelamin());
                tglTercatat.setText("Tanggal Tercatat : "+ penduduk.gettanggalTercatat());
                if (penduduk.getFoto() != null){
                    Uri avatarUri = Uri.parse(penduduk.getFoto());
                    imageView.setImageURI(avatarUri);
                }

            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getIntent().getStringExtra("isNew") != null){
            openDialogInfo();
        }
    }

    protected void openDialogInfo() {
        final Dialog dialog = new Dialog(ProfileActivity.this);
        dialog.setContentView(R.layout.info_layout_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setWindowAnimations(R.style.animationDialog);
        ImageView imageViewClose = dialog.findViewById(R.id.imageViewClose);
        TextView namaPendudukDialog = (TextView) dialog.findViewById(R.id.namaPenduduk);
        namaPendudukDialog.setText( "Penduduk dengan nama " + getIntent().getStringExtra("name") + " Berhasil di catat");
        dialog.show();


        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem menuItem3 = menu.findItem(R.id.menu_add);
        menuItem3.setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
//                todo: goto back activity from here

                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            case R.id.about:
                new AlertDialog.Builder(this)
                        .setTitle("About")
                        .setMessage("Nama : I Komang Wahyu Hadi Permana \n"+"Nim : 1905551010 \n"+"Judul Aplikasi : Pencatatan Penduduk")
                        .setPositiveButton("Tutup", null)
                        .setIcon(R.drawable.ic_baseline_info_24)
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}