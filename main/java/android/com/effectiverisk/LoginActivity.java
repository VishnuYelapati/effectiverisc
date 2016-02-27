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
public class LoginActivity extends Activity {

    EditText uname,pwd;
    Button btn_login,btn_register;

    String userid,password;

    public static SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        uname=(EditText)findViewById(R.id.et_login_uname);
        pwd=(EditText)findViewById(R.id.et_login_pwd);
        btn_login=(Button)findViewById(R.id.btn_login1);
        btn_register=(Button)findViewById(R.id.btn_register1);

        loginPreferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        //loginPrefsEditor = loginPreferences.edit();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userid=loginPreferences.getString("userId","");
                password=loginPreferences.getString("Password","");
                String userName=uname.getText().toString();
                String Password=pwd.getText().toString();
                //Toast.makeText(getApplicationContext(),userName+"..."+Password,Toast.LENGTH_LONG).show();

                if(userid.equals(userName)&&password.equals(Password))
                {
                    startActivity(new Intent(LoginActivity.this,ApkListActivity.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"please enter correct username,password",Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }
}
