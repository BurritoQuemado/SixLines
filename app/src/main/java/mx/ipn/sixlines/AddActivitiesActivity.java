package mx.ipn.sixlines;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivitiesActivity extends AppCompatActivity {

    EditText act,res,day;

    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activities);

        act = findViewById(R.id.editText8);
        res = findViewById(R.id.editText9);
        day = findViewById(R.id.editText10);

        dbHelper = new DBHelper(this,"SixLines.db",null,1);
    }

    public void addAct(View view) {
        db = dbHelper.getWritableDatabase();
        String project = "";
        Intent getP = this.getIntent();
        Bundle extra = getP.getExtras();
        project = extra.getString("name");

        ContentValues values = new ContentValues();
        values.put("name",act.getText().toString());
        values.put("project",project);
        values.put("user",res.getText().toString());
        values.put("end",day.getText().toString());
        db.insert("activitites",null,values);
        Toast toast = Toast.makeText(getApplicationContext(),"Adding activity was successful",Toast.LENGTH_SHORT);
        toast.show();
        db.close();
    }
}
