package mx.ipn.sixlines;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class    SettingsActivity extends AppCompatActivity {
    private Toolbar toolbar;

    TextView Name,Username,Email,Password;

    DBHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Name = (TextView) findViewById(R.id.id_nombrePerfil);
        Username = (TextView) findViewById(R.id.id_usuarioPerfil);
        Email = (TextView) findViewById(R.id.id_correoPerfil);
        Password = (TextView) findViewById(R.id.id_passwordPerfil);


        toolbar=(Toolbar)findViewById(R.id.id_tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.str_menu_user));
        toolbar.setTitleTextColor(0xFFFFFFFF);

        dbHelper = new DBHelper(this,"SixLines.db",null,1);

        db = dbHelper.getReadableDatabase();

        this.getUser();

        db.close();
    }
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.id_icon_home:
                Intent getEmail = this.getIntent();
                Bundle extra = getEmail.getExtras();
                String email = extra.getString("email");
                Intent homeIntent = new Intent(this,MainActivity.class);
                homeIntent.putExtra("email",email);
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
                Intent settingsIntent = new Intent(this,SettingsActivity.class);
                this.startActivity(settingsIntent);
                break;


        }
        return true;
    }
    public void changeIntent(View view) {
        Intent getEmail = this.getIntent();
        Bundle extra = getEmail.getExtras();
        String email = extra.getString("email");

        Intent changeIntent = new Intent(this,ChangePassActivity.class);
        changeIntent.putExtra("email",email);
        this.startActivity(changeIntent);
    }
    public void calendarIntent(View view) {
        Intent calendarIntent = new Intent(this,CalendarActivity.class);
        this.startActivity(calendarIntent);
    }


    public void settingsIntent(View view) {
        Intent settingsIntent = new Intent(this,MainActivity.class);
        this.startActivity(settingsIntent);
    }

    public void projectsIntent(View view) {
        Intent projectsIntent = new Intent(this,CalendarActivity.class);
        this.startActivity(projectsIntent);
    }



    public void usersIntent(View view) {
        Intent projectsIntent = new Intent(this,UsersActivity.class);
        this.startActivity(projectsIntent);
    }

    public void filesIntent(View view) {
        Intent projectsIntent = new Intent(this,FilesActivity.class);
        this.startActivity(projectsIntent);
    }

    private void getUser(){
        String name = "",email = "",lastname = "",username = "",password = "";
        Intent getEmail = this.getIntent();
        Bundle extra = getEmail.getExtras();

        email = extra.getString("email");

        String table = "users";
        String[] columns = {"name","lname","email","password","username"};
        String selection = "email = ?";
        String[] sArgs = {email};
        cursor = db.query(table,columns,selection,sArgs,null,null,null);
        while (cursor.moveToNext()){
            name = cursor.getString(cursor.getColumnIndex("name"));
            lastname = cursor.getString(cursor.getColumnIndex("lname"));
            password = cursor.getString(cursor.getColumnIndex("password"));
            username = cursor.getString(cursor.getColumnIndex("username"));
        }

        Name.setText(name + " " + lastname);
        Username.setText(username);
        Email.setText(email);
        Password.setText(password);
    }
}
