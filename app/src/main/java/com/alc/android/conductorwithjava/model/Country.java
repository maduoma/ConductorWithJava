package com.alc.android.conductorwithjava.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class Country implements Parcelable{
    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
    private final String name;
    private final String capital;
    private final long population;
    private final String flag;
    private final String language;
    private final String currency;
    private final String timezone;

    public Country(String name, String capital, long population, String flag, String language, String currency, String timezone) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.flag = flag;
        this.language = language;
        this.currency = currency;
        this.timezone = timezone;
    }

    private Country(Parcel in) {
        name = in.readString();
        capital = in.readString();
        population = in.readLong();
        flag = in.readString();
        language = in.readString();
        currency = in.readString();
        timezone = in.readString();
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public long getPopulation() {
        return population;
    }

    public String getFlag() {
        return flag;
    }

    public String getLanguage() {
        return language;
    }

    public String getCurrency() {
        return currency;
    }

    public String getTimezone() {
        return timezone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(capital);
        dest.writeLong(population);
        dest.writeString(flag);
        dest.writeString(language);
        dest.writeString(currency);
        dest.writeString(timezone);
    }
}