package com.alc.android.conductorwithjava.countrydetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.alc.android.conductorwithjava.R;
import com.alc.android.conductorwithjava.controller.BaseController;
import com.alc.android.conductorwithjava.misc.BundleBuilder;
import com.alc.android.conductorwithjava.model.Country;

//import android.os.Bundle;

//import android.support.annotation.NonNull;

public class CountryDetailController extends BaseController implements DetailEventHandler {

    private static final String KEY_COUNTRY = "KEY_COUNTRY";

    private Country country = getArgs().getParcelable(KEY_COUNTRY);

    public CountryDetailController(Country country) {
        this(new BundleBuilder(new Bundle())
                .putParcelable(KEY_COUNTRY, country)
                .build());
    }

    public CountryDetailController(Bundle args) {
        super(args);
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        CountryDetailView view = (CountryDetailView) inflater.inflate(R.layout.country_detail, container, false);
        view.setEventHandler(this);
        view.setData(country);
        return view;
    }

    @Override
    public void onFlagClicked() {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("geo:0,0?q=%s", country.getName())));
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    @Override
    protected String getTitle() {
        return country.getName();
    }
}
