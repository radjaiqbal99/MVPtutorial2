package com.example.rezafd.mvptutorial.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rezafd.mvptutorial.Interface.BiodataPresenterInterface;
import com.example.rezafd.mvptutorial.Interface.BiodataViewInterface;
import com.example.rezafd.mvptutorial.Model.BaseActivity;
import com.example.rezafd.mvptutorial.Model.ResponsModel;
import com.example.rezafd.mvptutorial.Presenter.BiodataPresenter;
import com.example.rezafd.mvptutorial.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements BiodataViewInterface{
    @BindView(R.id.input_namalengkap) EditText namalengkap;
    @BindView(R.id.input_namap) EditText namap;
    @BindView(R.id.input_tmptlahir) EditText tmptlahir;
    @BindView(R.id.input_tgllahir) EditText tgllahir;
    @BindView(R.id.input_alamat) EditText alamat;
    @BindView(R.id.checkjk) RadioGroup checkjk;
    @BindView(R.id.input_nohp) EditText nohp;
    @BindView(R.id.inputjk) TextView inputjk;
    @BindView(R.id.input_email) EditText email;
    ProgressDialog pg;

    public BiodataPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pg = new ProgressDialog(this);
        ButterKnife.bind(MainActivity.this);

        presenter = new BiodataPresenter(this,getApi());
    }
    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.checkjk_L:
                if (checked)
                    inputjk.setText("Laki-Laki");
                break;
            case R.id.checkjk_P:
                if (checked)
                    inputjk.setText("Perempuan");
        }
    }
    @OnClick(R.id.btnsave) void doBiodata(){
        pg.setMessage("Sending Data...");
        pg.setCancelable(false);
        pg.show();

        String snamalengkap=namalengkap.getText().toString();
        String snamap = namap.getText().toString();
        String stmptlahir = tmptlahir.getText().toString();
        String stgllahir = tgllahir.getText().toString();
        String sinputjk = inputjk.getText().toString();
        String salamat = alamat.getText().toString();
        String snohp = nohp.getText().toString();
        String semail = email.getText().toString();

        presenter.doBiodata(snamalengkap,snamap,stmptlahir,stgllahir,sinputjk,salamat,
                snohp,semail);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onFinishinsert(ResponsModel res) {
        pg.hide();
        Toast.makeText(MainActivity.this,"Insert Succes",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFailureinsert() {
        pg.hide();
        Toast.makeText(MainActivity.this,"Insert Failed", Toast.LENGTH_SHORT).show();

    }
}
