package marlon.com.libiki;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class CommandeActivity extends AppCompatActivity {
    private EditText article, prix;
    private ClientManager db;
    private Button commander;
    private long id_client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);
        db = new ClientManager(this);

        article = (EditText)findViewById(R.id.comnd_input);
        prix = (EditText)findViewById(R.id.prix_input);

        commander = (Button )findViewById(R.id.btn_commande);
        commander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!article.getText().toString().isEmpty() & !prix.getText().toString().isEmpty()) {
                    long prixVa = Long.valueOf(prix.getText().toString());
                    commander = ( Button ) findViewById(R.id.btn_commande);
                    id_client = getIntent().getExtras().getLong("ID");

                    Commande commande = new Commande(article.getText().toString(), prixVa, id_client);
                    boolean result =db.inserCommande(commande);
                    if(result){
                        Toast.makeText(CommandeActivity.this, "Reussi", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(CommandeActivity.this, "Reussi", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }else{
                    Toast.makeText(CommandeActivity.this, "Fields must not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
