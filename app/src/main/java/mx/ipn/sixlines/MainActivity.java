package mx.ipn.sixlines;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import     android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {


    TextView proyecto, endDay, restDay;
    DBHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    String project;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.id_tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.str_menu_home));
        toolbar.setTitleTextColor(0xFFFFFFFF);
        proyecto = findViewById(R.id.id_proyectoMain);
        endDay = findViewById(R.id.id_fechaMain);
        restDay = findViewById(R.id.id_diasMain);

        dbHelper = new DBHelper(this,"SixLines.db",null,1);

        Intent getEmail = this.getIntent();
        Bundle extra = getEmail.getExtras();
        String email = extra.getString("email");

        db = dbHelper.getReadableDatabase();

        this.getID(email);

        db.close();

    }
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.id_icon_home:
                Intent homeIntent = new Intent(this,MainActivity.class);
                this.startActivity(homeIntent);
                break;
            case R.id.id_icon_files:
                Intent projectsIntent = new Intent(this,FilesActivity.class);
                this.startActivity(projectsIntent);
                break;
            case R.id.id_icon_calendar:
                Intent calendarIntent = new Intent(this,CalendarActivity.class);
                this.startActivity(calendarIntent);
                break;
            case R.id.id_icon_user:
                Intent getEmail = this.getIntent();
                Bundle extra = getEmail.getExtras();
                String email = extra.getString("email");

                Intent settingsIntent = new Intent(this,SettingsActivity.class);
                settingsIntent.putExtra("email",email);
                this.startActivity(settingsIntent);
                break;


        }
        return true;
    }

    private void getID(String email){
        String id = "";
        String table = "users";
        String[] columns = {"id","email"};
        String selection = "email = ?";
        String[] sArgs = {email};
        cursor = db.query(table,columns,selection,sArgs,null,null,null);
        while(cursor.moveToNext()){
            id  = cursor.getString(cursor.getColumnIndex("id"));
        }

        this.getProject(id);
    }

    private void getProject(String id){
            String projectName =  "";
            String endD = "";
            String table = "proyectos";
            String[] columns = {"name","leader","end"};
            String selection = "leader = ?";
            String[] sArgs = {id};
            cursor = db.query(table,columns,selection,sArgs,null,null,null);
            while(cursor.moveToNext()){
                projectName = cursor.getString(cursor.getColumnIndex("name"));
                endD = cursor.getString(cursor.getColumnIndex("end"));
            }
            project = projectName;
            proyecto.setText(projectName);
            endDay.setText(endD);
    }



    public void projectsIntent(View view) {
        Intent projectsIntent = new Intent(this,CalendarActivity.class);
        this.startActivity(projectsIntent);
    }


    public void usersIntent(View view) {
        Intent UserIntent = new Intent(this,UsersActivity.class);
        this.startActivity(UserIntent);
    }
    public void activitiesIntent(View view) {
        Intent ActivityIntent = new Intent(this,ActivitiesActivity.class);
        ActivityIntent.putExtra("name",project);
        this.startActivity(ActivityIntent);
    }
}

