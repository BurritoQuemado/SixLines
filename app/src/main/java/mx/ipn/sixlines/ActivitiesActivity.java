package mx.ipn.sixlines;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
    }
    public void verActividades(android.view.View view) {
        String name = "";
        Intent getName = this.getIntent();
        Bundle ex = getName.getExtras();
        name = ex.getString("name");
        Intent addIntent = new Intent(this,SeeActsActivity.class);
        addIntent.putExtra("name",name);
        this.startActivity(addIntent);
    }
    public void agregarAct(android.view.View view) {
        String name = "";
        Intent getName = this.getIntent();
        Bundle ex = getName.getExtras();
        name = ex.getString("name");
        Intent addIntent = new Intent(this,AddActivitiesActivity.class);
        addIntent.putExtra("name",name);
        this.startActivity(addIntent);
    }

}
