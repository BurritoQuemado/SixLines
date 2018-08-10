package mx.ipn.sixlines;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class CalendarActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        toolbar=(Toolbar)findViewById(R.id.id_tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.str_menu_calendar));
        toolbar.setTitleTextColor(0xFFFFFFFF);
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
                Intent settingsIntent = new Intent(this,SettingsActivity.class);
                this.startActivity(settingsIntent);
                break;


        }
        return true;
    }
    public void addactivity(View view) {
        Intent addIntent = new Intent(this,AddActivitiesActivity.class);
        this.startActivity(addIntent);
    }
}
