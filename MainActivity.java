package marlon.com.libiki;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String[] from = new String[]{
            "name","num","date_ins"
    };
    private static final int[] to = new int[]{
            R.id.my_text_name,R.id.my_tex_num,R.id.my_text_date
    };
    private static ClientManager db;
    private static ListView listView;

    private  SimpleCursorAdapter simpleCursorAdapter;
    private static Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLoaderManager().initLoader(1,null,this);//initialisation du loader


        db = new ClientManager(this);//instanciation de la base donnee
        listView = (ListView )findViewById(R.id.my_list);//recuperation de la listView

        simpleCursorAdapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.my_views, null, from, to, 0);

        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        registerForContextMenu(listView);


    }

   

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_client_item:
                Intent intent = new Intent(MainActivity.this,AddClientActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_main ,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo infos = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.supp:
                db.deleteClients(infos.id);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new ChargerClients(getApplicationContext(),db);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        simpleCursorAdapter.changeCursor(data);
        getLoaderManager().restartLoader(1,null,this);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
