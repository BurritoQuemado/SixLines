package mx.ipn.sixlines;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    DBHelper dbHelper;
    Cursor cursor;
    SQLiteDatabase db;

    private static  final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = findViewById(R.id.txt_login_correo);
        password = findViewById(R.id.txt_login_password);

        dbHelper = new DBHelper(this,"SixLines.db",null,1);

        db =  dbHelper.getReadableDatabase();


    }
    public void login(View view){

        String emailS;
        if(email.getText().toString().length() == 0){
            Toast noemail = Toast.makeText(getApplicationContext(),"Write an email",Toast.LENGTH_SHORT);
            noemail.show();
        }else{
            if (password.getText().toString().length() == 0){
                Toast nopass = Toast.makeText(getApplicationContext(),"Write the password",Toast.LENGTH_SHORT);
                nopass.show();
            }else{
                if (validateEmail(email.getText().toString())){
                    if (mailExists(email.getText().toString())){
                        if (passExists(email.getText().toString(),password.getText().toString())){
                            emailS = email.getText().toString();
                            db.close();
                            Intent logintent = new Intent(this,MainActivity.class);
                            logintent.putExtra("email",emailS);
                            this.startActivity(logintent);
                        }else{
                            Toast noemail = Toast.makeText(getApplicationContext(),"Invalid password",Toast.LENGTH_SHORT);
                            noemail.show();
                        }
                    }else{
                        Toast noemail = Toast.makeText(getApplicationContext(),"Email not found",Toast.LENGTH_SHORT);
                        noemail.show();
                    }
                }
                else{
                    Toast noemail = Toast.makeText(getApplicationContext(),"Write a valid email",Toast.LENGTH_SHORT);
                    noemail.show();
                }
            }
        }


    }

    private boolean passExists(String mail,String pass) {
        boolean exists = true;

        return exists;
    }


    public void register(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        this.startActivity(intent);
    }

    private boolean validateEmail(String s) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    private boolean mailExists(String mail){
        boolean exists = true;

        return exists;
    }

}
