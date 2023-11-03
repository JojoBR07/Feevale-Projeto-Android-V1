package br.feevale.projetopdc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter extends BaseAdapter {
    LayoutInflater inflater;
    ThreatSQLiteDatabase database;

    public Adapter(Context context, ThreatSQLiteDatabase database) {
        this.inflater = LayoutInflater.from(context);
        this.database = database;
    }

    @Override
    public int getCount() {
        return database.getThreats().size();
    }

    @Override
    public Object getItem(int position) {
        return database.getThreats().get(position);
    }

    @Override
    public long getItemId(int position) {
        return database.getThreats().get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.threat_list_item, null);
        TextView txtThreatName = view.findViewById(R.id.txtThreatName);
        TextView txtThreatDate = view.findViewById(R.id.txtThreatDate);
        txtThreatName.setText(database.getThreats().get(position).getDescription());
        txtThreatDate.setText(database.getThreats().get(position).getDate());
        return view;
    }
}
