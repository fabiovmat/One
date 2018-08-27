package onex.com.br.onex2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import BDHelper.MateriaisDb;
import model.Materiais;

public class FormularioMateriais extends AppCompatActivity {

    EditText editText_Material, editText_Desc, editText_Quantidade;
    Button btn_Polimorf;
    Materiais editarMaterial, material;
    MateriaisDb bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        material = new Materiais();
        bdHelper = new MateriaisDb(FormularioMateriais.this);


        Intent intent = getIntent();
        editarMaterial = (Materiais) intent.getSerializableExtra("material-escolhido");

        editText_Material = findViewById(R.id.editText_Material);
        editText_Desc = findViewById(R.id.editText_Desc);
        editText_Quantidade = findViewById(R.id.editText_Quantidade);

        btn_Polimorf = findViewById(R.id.btn_Polimorf);

        if (editarMaterial != null) {
            btn_Polimorf.setText("Alterar");

            editText_Material.setText(editarMaterial.getNomeMaterial());
            editText_Desc.setText(editarMaterial.getDescMaterial());
            editText_Quantidade.setText(editarMaterial.getQuantidade()+"");

            material.setId(editarMaterial.getId());

        } else {
            btn_Polimorf.setText("Cadastrar");

        }

        btn_Polimorf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                material.setNomeMaterial(editText_Material.getText().toString());
                material.setDescMaterial(editText_Desc.getText().toString());
                material.setQuantidade(Integer.parseInt(editText_Quantidade.getText().toString()));

                if (btn_Polimorf.getText().toString().equals("Cadastrar")) {
                    bdHelper.salvarMaterial(material);
                    bdHelper.close();
                }else{

                    bdHelper.alterarMaterial(material);
                    bdHelper.close();





                }
            }
        });
    }
}