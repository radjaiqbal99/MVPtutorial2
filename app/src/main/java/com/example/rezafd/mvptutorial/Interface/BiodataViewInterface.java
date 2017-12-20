package com.example.rezafd.mvptutorial.Interface;

import com.example.rezafd.mvptutorial.Model.ResponsModel;

/**
 * Created by REZAFD on 20/12/2017.
 */

public interface BiodataViewInterface {
    void showLoading();
    void hideLoading();
    void onFinishinsert(ResponsModel res);
    void onFailureinsert();

    void log(String t);
}
