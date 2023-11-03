package br.feevale.projetopdc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Edit extends AppCompatActivity {

    ThreatSQLiteDatabase database;
    EditText txtAdress, txtDate, txtDescription;
    Threat current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        txtAdress = findViewById(R.id.campoEndereco);
        txtDate = findViewById(R.id.campoData);
        txtDescription = findViewById(R.id.campoDescricao);

        database = new ThreatSQLiteDatabase(getBaseContext());
        Long id = getIntent().getLongExtra("ID",0);
        current = database.getThreat(id);

        txtAdress.setText(current.getAdress());
        txtDate.setText(current.getDate());
        txtDescription.setText(current.getDescription());
    }

    public void updateThreat(View view){
        current.setAdress(txtAdress.getText().toString());
        current.setDate(txtDate.getText().toString());
        current.setDescription(txtDescription.getText().toString());

        database.updateThreat(current);
        finish();
    }
}