package mx.ipn.sixlines;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassActivity extends AppCompatActivity {

    EditText NewPass;

    DBHelper dbHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        NewPass = (EditText) findViewById(R.id.editText);
        dbHelper = new DBHelper(this,"SixLines.db",null,1);

    }

    public void changePass(View view){
        String pass = "", cpass = "";
        pass = NewPass.getText().toString();
        db = dbHelper.getWritableDatabase();
        String table = "users";
        String email = "";
        Intent getEmail = this.getIntent();
        Bundle extra = getEmail.getExtras();
        email = extra.getString("email");

        ContentValues values = new ContentValues();
        values.put("password",pass);
        String whereClause = "email = ?";
        String[] args = {email};
        db.update(table,values,whereClause,args);

        Toast noemail = Toast.makeText(getApplicationContext(),"Password updated",Toast.LENGTH_SHORT);
        noemail.show();
        db.close();

    }
}
