package marlon.com.libiki;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

/**
 * Created by marlon on 05/12/17.
 */

public class ChargerClients extends CursorLoader {
    private ClientManager db;
    private ClientManager dab ;
    public ChargerClients(Context context,ClientManager db) {
        super(context);
        dab = new ClientManager(context);
//        this.db = db;
    }

    @Override
    protected Cursor onLoadInBackground() {
        return dab.viewData();
    }
}
