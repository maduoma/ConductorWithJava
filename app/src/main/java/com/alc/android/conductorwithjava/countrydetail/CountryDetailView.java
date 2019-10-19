package com.alc.android.conductorwithjava.countrydetail;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.alc.android.conductorwithjava.R;
import com.alc.android.conductorwithjava.model.Country;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import android.support.design.widget.FloatingActionButton;

//import android.support.annotation.Nullable;

//import android.support.annotation.NonNull;

public class CountryDetailView extends LinearLayoutCompat {

    // Assign public visibility for the below views for quickly demo view change animation.
    // Production apps should have this done properly.
    @BindView(R.id.flag)
    public ImageView flagView;
    @BindView(R.id.favourite_fab)
    public FloatingActionButton favouriteFab;
    @BindView(R.id.detail_group)
    public ViewGroup detailGroup;
    @BindView(R.id.capital)
    TextView capitalView;
    @BindView(R.id.population)
    TextView populationView;
    @BindView(R.id.currency)
    TextView currencyView;
    @BindView(R.id.language)
    TextView languageView;
    @BindView(R.id.timezone)
    TextView timezoneView;
    // Note: Having the controller implementing an interface and pass its reference to this View to handle navigation
    // upon clicks for demo purposes.
    // A nicer way of doing this is using RxJava to turn view clicks into a stream which is then observed by a Presenter
    // declared in the Controller. The Presenter then determines what should be done to response to clicks.
    private DetailEventHandler eventHandler;

    public CountryDetailView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @OnClick(R.id.flag)
    void onFlagClicked() {
        eventHandler.onFlagClicked();
    }

    public void setEventHandler(DetailEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setData(@NonNull Country country) {
        Picasso.with(getContext())
                .load(country.getFlag())
                .into(flagView);

        capitalView.setText(country.getCapital());
        populationView.setText(String.valueOf(country.getPopulation()));
        languageView.setText(country.getLanguage());
        currencyView.setText(country.getCurrency());
        timezoneView.setText(country.getTimezone());
    }
}
