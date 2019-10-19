package com.alc.android.conductorwithjava.countrygrid;

//import android.support.annotation.NonNull;

import androidx.annotation.NonNull;

import com.alc.android.conductorwithjava.model.Country;

public interface GridEventHandler {
    void onCountryClicked(@NonNull Country country);
}
