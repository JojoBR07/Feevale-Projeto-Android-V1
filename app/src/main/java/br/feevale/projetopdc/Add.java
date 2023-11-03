package br.feevale.projetopdc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Add extends AppCompatActivity {
    ThreatSQLiteDatabase database;
    EditText txtAdress, txtDate, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        txtAdress = findViewById(R.id.campoEndereco);
        txtDate = findViewById(R.id.campoData);
        txtDescription = findViewById(R.id.campoDescricao);
        
        String date_n = new SimpleDateFormat( "dd/mm/yyyy" , Locale. getDefault ()).format( new Date());
        txtDate.setText(date_n);

        database = new ThreatSQLiteDatabase(getBaseContext());
    }

    public void addThreat(View view){
        Threat threat = new Threat();
        threat.setAdress(txtAdress.getText().toString());
        threat.setDate(txtDate.getText().toString());
        threat.setDescription(txtDescription.getText().toString());

        database.addThreat(threat);
        finish();
    }
}