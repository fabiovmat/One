package onex.com.br.onex2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import BDHelper.MateriaisDb;
import model.Materiais;

public class CadastraProdutos extends AppCompatActivity {

    ListView lista;
    MateriaisDb bdHelper;
    ArrayList<Materiais> listview_Materiais;
    Materiais material;
    ArrayAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Button btnCadastrar = findViewById(R.id.btn_Cadastra);
        btnCadastrar.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(CadastraProdutos.this, FormularioMateriais.class);
                startActivity(intent);
            }

        });


        lista = findViewById(R.id.listview_Materiais);
        registerForContextMenu(lista);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Materiais materialEscolhido = (Materiais) adapter.getItemAtPosition(position);

                Intent i = new Intent(CadastraProdutos.this, FormularioMateriais.class);
                i.putExtra("material-escolhido", materialEscolhido);
                startActivity(i);

            }
        });


    }





            @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar este Material");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

            bdHelper = new MateriaisDb(CadastraProdutos.this);
            bdHelper.deletarMaterial(material);
            bdHelper.close();

            carregarMaterial();
            return true;
            }
        });





    }
    @Override
    protected void onResume(){
        super.onResume();
        carregarMaterial();

    }

    public void carregarMaterial(){
        bdHelper = new MateriaisDb(CadastraProdutos.this);
        listview_Materiais = bdHelper.getLista();
        bdHelper.close();

        if(listview_Materiais != null){
            adapter = new ArrayAdapter<Materiais>(CadastraProdutos.this, android.R.layout.simple_list_item_1,listview_Materiais);
            lista.setAdapter(adapter);


        }
        //finish();

    }






}
