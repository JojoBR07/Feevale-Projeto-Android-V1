package br.feevale.projetopdc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listThreat;
    Adapter adapterThreat;
    ThreatSQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new ThreatSQLiteDatabase(getBaseContext());
        listThreat = findViewById(R.id.listThreat);
        adapterThreat = new Adapter(getBaseContext(), database);
        listThreat.setAdapter(adapterThreat);

        listThreat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                changeToUpdate(id);
            }
        });

        listThreat.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                database.removeThreat((Threat) adapterThreat.getItem(position));
                adapterThreat.notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapterThreat.notifyDataSetChanged();
    }

    public void changeToAdd(View view){
        Intent it = new Intent(getBaseContext(), Add.class);
        startActivity(it);
    }

    public void changeToUpdate(Long id){
        Intent it = new Intent(getBaseContext(), Edit.class);
        it.putExtra("ID", id);
        startActivity(it);
    }
}