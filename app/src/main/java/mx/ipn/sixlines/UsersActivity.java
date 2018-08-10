package mx.ipn.sixlines;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class UsersActivity extends AppCompatActivity {
    DBHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        listView = (ListView) this.findViewById(R.id.listView);

        dbHelper = new DBHelper(this,"SixLines.db",null,1);

        db = dbHelper.getReadableDatabase();

        this.readUsers();

        db.close();
    }
    private void readUsers() {
        String table = "proyectos";
        String[] columns = {"_id","name","end"};
        String selection = "_id > ?";
        String[] sArgs = {"0"};
        cursor = db.query(table,columns,selection,sArgs,null,null,null);

        String[] from = {"_id","name","end"};
        int[] to = {R.id.textViewID,R.id.textViewType,R.id.textViewName};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.rows,cursor,from,to);

        listView.setAdapter(simpleCursorAdapter);
    }
}
