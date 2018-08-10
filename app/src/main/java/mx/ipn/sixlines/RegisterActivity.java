package mx.ipn.sixlines;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    Spinner states,school;
    EditText name,lname,email,id,pass,cpass,bday,career;
    RadioGroup rg;

    TextView helper1, helper2;

    DBHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;

    int type;


    private static  final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);


        states = findViewById(R.id.spinner_State);
        school = findViewById(R.id.spinner_school);
        name = findViewById(R.id.txt_Name);
        lname = findViewById(R.id.txt_Lname);
        email = findViewById(R.id.txt_email);
        id = findViewById(R.id.txt_id);
        bday = findViewById(R.id.txt_bDay);
        career =  findViewById(R.id.spinner_career);
        rg = findViewById(R.id.radioGrp);



        String[] States = {"State","Aguascalientes","Baja California","Baja California Sur","Campeche","Chiapas","Chihuahua","Ciudad de Mexico","Coahuila","Colima","Durango","Guanajuato","Guerrero","Hidalgo","Jalisco","Edo. Mex.","Michoacan","Morelos","Nayarit","Nuevo Leon","Oaxaca","Puebla","Queretaro","Quintana Roo","San Luis Potosi","Sinaloa","Sonora","Tabasco","Tamaulipas","Tlaxcala","Veracruz","Yucatan","Zacatecas"};
        states.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, States));
        String[] Cecyts = {"School","Cecyt 1","Cecyt 2","Cecyt 3","Cecyt 4","Cecyt 5","Cecyt 6","Cecyt 7","Cecyt 8","Cecyt 9","Cecyt 10","Cecyt 11","Cecyt 12","Cecyt 13","Cecyt 14","Cecyt 15"};
        school.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Cecyts));

        dbHelper = new DBHelper(this,"SixLines.db",null,1);


    }

    public void signup(View view){

        pass = findViewById(R.id.txt_password);
        cpass = findViewById(R.id.txt_cpassword);




        if (name.getText().toString().length() == 0){
            Toast unameToast = Toast.makeText(getApplicationContext(),"Name is required",Toast.LENGTH_SHORT);
            unameToast.show();
        }else{
            if (lname.getText().toString().length() == 0){
                Toast Lnamet = Toast.makeText(getApplicationContext(),"Last name is required",Toast.LENGTH_SHORT);
                Lnamet.show();
            }else{
                if (id.getText().toString().length() == 0){
                    Toast unameToast = Toast.makeText(getApplicationContext(),"ID is required",Toast.LENGTH_SHORT);
                    unameToast.show();
                }
                else{
                    if (bday.getText().toString().length() == 0){
                        Toast unameToast = Toast.makeText(getApplicationContext(),"Birth day is required",Toast.LENGTH_SHORT);
                        unameToast.show();
                    }
                    else {
                        if (email.getText().toString().length() == 0){
                            Toast unameToast = Toast.makeText(getApplicationContext(),"Email is required",Toast.LENGTH_SHORT);
                            unameToast.show();
                        }else {
                            if (validateEmail(email.getText().toString())){
                                if (career.getText().toString().length() == 0){
                                    Toast unameToast = Toast.makeText(getApplicationContext(),"Career is required",Toast.LENGTH_SHORT);
                                    unameToast.show();
                                }else{
                                    if (pass.getText().toString().length() == 0){
                                        Toast unameToast = Toast.makeText(getApplicationContext(),"Password is required",Toast.LENGTH_SHORT);
                                        unameToast.show();
                                    }else{
                                        if (cpass.getText().toString().length() == 0){
                                            Toast unameToast = Toast.makeText(getApplicationContext(),"Confirm password is required",Toast.LENGTH_SHORT);
                                            unameToast.show();
                                        }else{
                                            insertaDatos();
                                        }
                                    }
                                }
                            }else {
                                Toast unameToast = Toast.makeText(getApplicationContext(),"Invalid email",Toast.LENGTH_SHORT);
                                unameToast.show();
                            }
                        }
                    }
                }
            }
        }


    }

    private void insertaDatos() {
        String username,Name,Lname,Email,Id,password,Bday,Career,City,HighS,typeS;
        String Utype = "";


        Name = name.getText().toString();
        Lname = lname.getText().toString();
        Email = email.getText().toString();
        Id = id.getText().toString();
        Bday = bday.getText().toString();
        Career = career.getText().toString();
        password = pass.getText().toString();

        helper1 = (TextView) states.getSelectedView();
        helper2 = (TextView) school.getSelectedView();

        City = helper1.getText().toString();
        HighS = helper2.getText().toString();






        typeS = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();

        switch (typeS){
            case "Teacher":
                type = 1;
                break;
            case "Group Leader":
                type = 2;
                break;
            case "Group Mortal":
                type = 3;
                break;
        }

        switch(type){
            case 1:
                Utype = "teacher";
                break;
            case 2:
                Utype = "GLeader";
                break;
            case 3:
                Utype = "Gmate";
                break;
        }



        username = Lname + Id.charAt(Id.length() - 4)+ Id.charAt(Id.length() - 3)+ Id.charAt(Id.length() - 2)+ Id.charAt(Id.length() - 1);

        db = dbHelper.getWritableDatabase();
        if (this.validarRegistro(Id)){
            Toast toast = Toast.makeText(getApplicationContext(),"ID is already register to another account",Toast.LENGTH_SHORT);
            toast.show();
        }else{
            this.insertarchido(Id,Utype,Name,Lname,Email,password,Bday,City,HighS,Career,username);

            db.close();

            Toast toast = Toast.makeText(getApplicationContext(),"Register was successful",Toast.LENGTH_SHORT);
            toast.show();


            if (Utype == "GLeader"){
                Intent projectIntent = new Intent(this, ProjectsActivity.class);
                projectIntent.putExtra("LeaderID",Id);
                startActivity(projectIntent);
            }else{
                Intent logIntent = new Intent(this,LoginActivity.class);
                startActivity(logIntent);
            }
        }

    }

    private void insertarchido(String id, String type, String name, String lname, String email, String password, String bday, String state, String school, String career, String username) {
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("type",type);
        values.put("name",name);
        values.put("lname",lname);
        values.put("email",email);
        values.put("password",password);
        values.put("bday",bday);
        values.put("state",state);
        values.put("school",school);
        values.put("career",career);
        values.put("username",username);
        db.insert("users",null,values);
    }

    private boolean validateEmail(String s) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }


    public boolean validarRegistro(String ID){
        boolean exists = false;
        /*int id;

        String table = "agenda";
        String[] columns = {"id","type","name","lname","email","password","bday","state","school","career","username"};
        String selection = ID;
        try{
            cursor = db.query(table,columns,selection,null,null,null,null);
            while (cursor.moveToNext()){
                id = cursor.getInt(cursor.getColumnIndex("id"));
            }
        }catch (SQLException ex){
            exists = false;
        }
        */
        return exists;
    }



}
