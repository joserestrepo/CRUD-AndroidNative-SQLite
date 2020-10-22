package co.edu.unimagdalena.apmoviles.universidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarActivity extends AppCompatActivity implements View.OnClickListener {
    Estudiante e;
    EstudianteController ec;
    EditText codigo, nombre, programa;
    Button agregar, cancelar, mostrar, editar, eliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        cancelar = findViewById(R.id.btncancelar);
        agregar = findViewById(R.id.btnagregar);
        codigo = findViewById(R.id.edtcodigo);
        nombre = findViewById(R.id.edtnombre);
        programa = findViewById(R.id.edtprograma);
        editar = findViewById(R.id.btneditar);
        eliminar = findViewById(R.id.btneliminar);

        editar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
        agregar.setOnClickListener(this);
        ec = new EstudianteController(this);

        String cod = "";
        String nom = "";
        String prog = "";

        Intent recdData = getIntent();
        cod = recdData.getStringExtra("codigoItem");
        nom = recdData.getStringExtra("nombreItem");
        prog = recdData.getStringExtra("programaItem");
        codigo.setText(cod);
        nombre.setText(nom);
        programa.setText(prog);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btneliminar:
                if(TextUtils.isEmpty(codigo.getText().toString()) || TextUtils.isEmpty(nombre.getText().toString()) ||
                        TextUtils.isEmpty(programa.getText().toString())){
                    Toast.makeText(this,"Los datos no pueden ser vacíos", Toast.LENGTH_LONG).show();
                }
                else{
                    e = new Estudiante(codigo.getText().toString(),nombre.getText().toString(),programa.getText().toString());
                    if (ec.buscarEstudiante(e.getCodigo())){
                        ec.eliminarEstudiante(e);
                    }else{
                        Toast.makeText(this,"Estudiante No encontrado", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.btneditar:
                if(TextUtils.isEmpty(codigo.getText().toString()) || TextUtils.isEmpty(nombre.getText().toString()) ||
                        TextUtils.isEmpty(programa.getText().toString())){
                    Toast.makeText(this,"Los datos no pueden ser vacíos", Toast.LENGTH_LONG).show();
                }
                else{
                    e = new Estudiante(codigo.getText().toString(),nombre.getText().toString(),programa.getText().toString());
                    if (ec.buscarEstudiante(e.getCodigo())){
                        ec.editarEstudiante(e);
                    }else{
                        Toast.makeText(this,"Estudiante No encontrado", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.btncancelar:
                Intent i = new Intent(this, ListadoActivity.class);
                startActivity(i);
                break;
            case R.id.btnagregar:
                Intent im = new Intent(this, MainActivity.class);
                startActivity(im);
                break;
        }
    }
}