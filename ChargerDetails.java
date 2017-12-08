package marlon.com.libiki;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

/**
 * Created by marlon on 05/12/17.
 */

public class ChargerDetails extends CursorLoader {
    private long id;
    private ClientManager db;
    public ChargerDetails(Context context,ClientManager db, long id) {
        super(context);
        this.db = db;
        this.id = id;
    }

    @Override
    protected Cursor onLoadInBackground() {
        return db.viewCommande(id);
    }
}
