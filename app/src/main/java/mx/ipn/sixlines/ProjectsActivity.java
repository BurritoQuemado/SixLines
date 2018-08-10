package mx.ipn.sixlines;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProjectsActivity extends AppCompatActivity {
    EditText name,teacher,bdate,edate;

    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        name = (EditText) this.findViewById(R.id.txtProjectName);
        teacher =(EditText) this.findViewById(R.id.txtProjectTeacher);
        bdate =(EditText) this.findViewById(R.id.txtProjectBegin);
        edate =(EditText) this.findViewById(R.id.txtProjectEnd);

        dbHelper = new DBHelper(this,"SixLines.db",null,1);

    }

    public void addProject(View view) {
        db = dbHelper.getWritableDatabase();
        String pname = name.getText().toString();
        String pteacher = teacher.getText().toString();
        String begin = bdate.getText().toString();
        String end = edate.getText().toString();
        String pleader;

        Intent getLeader = this.getIntent();
        Bundle extra = getLeader.getExtras();

        pleader = extra.getString("LeaderID");

        ContentValues values = new ContentValues();
        values.put("name",pname);
        values.put("leader",pleader);
        values.put("teacher",pteacher);
        values.put("begin",begin);
        values.put("end",end);
        db.insert("proyectos",null,values);

        Toast toast = Toast.makeText(getApplicationContext(),"Adding project was successful",Toast.LENGTH_SHORT);
        toast.show();
        db.close();

        Intent logIntent = new Intent(this,LoginActivity.class);
        startActivity(logIntent);
    }
}
