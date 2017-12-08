package marlon.com.libiki;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {
    private static long id_client;
    private TextView total;
    private ClientManager db ;
    private TableLayout table ;
    private TableRow row ;
    private TextView tv1,tv2,tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        db = new ClientManager(this);

        table = (TableLayout )findViewById(R.id.tableau);
        total = (TextView )findViewById(R.id.totale);

        enableBackButton();

         showNameAsTitle();


        iterateCommande();

        iteratePrixToatal();
    }

    @NonNull
    private Cursor showNameAsTitle() {
        id_client = getIntent().getExtras().getLong("id");
        Cursor cursor1 = db.viewNameNum(id_client);
        while (cursor1.moveToNext()){
             this.setTitle(cursor1.getString(0));
        }
        cursor1.close();
        return cursor1;
    }

    private void enableBackButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//active le button retour
    }

    private void iteratePrixToatal() {
        Cursor cursor3 = db.getTotal(id_client);
        if (cursor3.getCount() >=0){
            while (cursor3.moveToNext()){
                    total.setText(cursor3.getString(0)+" FCFA");
                }
        }else{
                total.setText("0 FCFA");
        }
        cursor3.close();
    }

    private void iterateCommande() {
        Cursor cursor2 = db.viewCommande(id_client);
        while (cursor2.moveToNext()){
            row = new TableRow(this);

            tv1 = new TextView(this);
            tv1.setText("Aucun");
            tv2 = new TextView(this);
            tv2.setText("0");
            tv3 = new TextView(this);
            tv3.setText("vide");

            tv1.setText(cursor2.getString(0));
            tv1.setGravity(Gravity.CENTER);
            tv1.setPadding(2,2,2,2);
            tv1.setLayoutParams(new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT));

            tv2.setPadding(2,2,2,2);

            tv2.setLayoutParams(new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT));
            tv3.setPadding(2,2,2,2);

            tv2.setText(cursor2.getString(1));
            tv2.setGravity(Gravity.CENTER);
            tv3.setText(cursor2.getString(2));
            tv3.setGravity(Gravity.CENTER);
            tv3.setLayoutParams(new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT));


            row.addView(tv1);
            row.addView(tv2);
            row.addView(tv3);

            table.addView(row);
        }

        cursor2.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.commande:
                Intent intent = new Intent(DetailsActivity.this,CommandeActivity.class);
                intent.putExtra("ID",id_client);
                startActivity(intent);
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
