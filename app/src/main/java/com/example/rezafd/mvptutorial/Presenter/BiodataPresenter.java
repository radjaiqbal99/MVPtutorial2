package com.example.rezafd.mvptutorial.Presenter;

import android.util.Log;

import com.example.rezafd.mvptutorial.Interface.BiodataPresenterInterface;
import com.example.rezafd.mvptutorial.Interface.BiodataViewInterface;
import com.example.rezafd.mvptutorial.Model.ApiRequest;
import com.example.rezafd.mvptutorial.Model.ResponsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by REZAFD on 20/12/2017.
 */

public class BiodataPresenter implements BiodataPresenterInterface {
    private BiodataViewInterface view;
    private ApiRequest api;

    public BiodataPresenter(BiodataViewInterface view, ApiRequest api) {
        this.view = view;
        this.api = api;
    }

    @Override
    public void doBiodata(String namalengkap, String namapanggilan, String tmptlhir,
                          String tgllahir, String jenisk, String alamat, String nomerhp, String email) {
        view.showLoading();
        view.log("init");

        Call<ResponsModel> send = api.sendBiodata(namalengkap,namapanggilan,tmptlhir,tgllahir,jenisk,
                alamat,nomerhp,email);
        send.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                ResponsModel res = response.body();
                if (res.getKode()==1){
                    view.onFinishinsert(res);
                } else {
                    view.onFailureinsert();
                }
            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                view.hideLoading();
                Log.d("Base","Failure: "+"Gagal Mengirim Request");
            }
        });

    }
}
