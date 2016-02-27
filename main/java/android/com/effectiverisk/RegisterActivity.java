package android.com.effectiverisk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by srinu on 2/10/2016.
 */
public class RegisterActivity extends Activity {

    Button btn_save,btn_reset;
    EditText et_register_uname,et_register_uid,et_register_pwd;

    String uname,userId,password;

    public static SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        et_register_uname=(EditText)findViewById(R.id.et_register_uname);
        et_register_uid=(EditText)findViewById(R.id.et_register_uid);
        et_register_pwd=(EditText)findViewById(R.id.et_register_pwd);
        btn_save=(Button)findViewById(R.id.btn_save);
        btn_reset=(Button)findViewById(R.id.btn_reset);

        loginPreferences = getSharedPreferences("loginPrefs",	Context.MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname=et_register_uname.getText().toString();
                userId=et_register_uid.getText().toString();
                password=et_register_pwd.getText().toString();

                loginPrefsEditor.putString("username", uname);
                loginPrefsEditor.putString("userId", userId);
                loginPrefsEditor.putString("Password", password);
                loginPrefsEditor.commit();

                //Toast.makeText(getApplicationContext(),userId+""+password,Toast.LENGTH_LONG).show();

                Toast.makeText(getApplicationContext(),"Registered sucessfully...",Toast.LENGTH_LONG).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_register_uname.setText("");
                et_register_uid.setText("");
                et_register_pwd.setText("");
            }
        });

    }
}
