package ro.pub.cs.systems.eim.practicaltest02v4.model;

import android.support.annotation.NonNull;

public class HttpData {

    private final String data;

    public HttpData(String data) {
        this.data = data;
    }

    public String getData() { return data; }

    @NonNull
    @Override
    public String toString() {
        return "Data{" +
                "data='" + data + '}';
    }

}
