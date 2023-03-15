package com.cdp.Examen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cdp.Examen.db.DbContactos;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtCorreoElectronico;
    Button btnGuarda;
    private ListView lv1;
    private TextView tv1;
    private String[] paises={"Honduras","Costa Rica","El Salvador","Nicaragua"};

    private String[] codigo={"+504","+502","+501","+505"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
        tv1= (TextView) findViewById(R.id.tv1);
        lv1=(ListView)findViewById(R.id.lv1);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>( this,
                android.R.layout.simple_list_item_1,paises);

        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                tv1.setText("Codigo:"+lv1.getItemAtPosition(position)+" es:"+ codigo[position]);
            }
        });


        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")) {

                    DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                    long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreoElectronico.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");

    }
}