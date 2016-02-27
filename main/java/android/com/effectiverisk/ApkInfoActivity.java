package android.com.effectiverisk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by srinu on 2/10/2016.
 */
public class ApkInfoActivity extends Activity {

    ImageView arrow3_image;
    Bundle b;
    TextView tv_appName,tv_packageName,tv_version,tv_permissions,tv_pathInfo,tv_installed,tv_lastModified,tv_targetSdkVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_apkinfo);

        b=getIntent().getExtras();

        tv_appName=(TextView)findViewById(R.id.tv_appName);
        tv_packageName=(TextView)findViewById(R.id.tv_packageName);
        tv_version=(TextView)findViewById(R.id.tv_version);
        tv_permissions=(TextView)findViewById(R.id.tv_requiredPermissions);
        tv_pathInfo=(TextView)findViewById(R.id.tv_pathinfo);
        tv_installed=(TextView)findViewById(R.id.tv_installed);
        tv_targetSdkVersion=(TextView)findViewById(R.id.tv_targetsdkVersion);
        tv_lastModified=(TextView)findViewById(R.id.tv_lastModified);
        arrow3_image=(ImageView)findViewById(R.id.arrow3_image);

        tv_appName.setText(b.getString("AppName"));
        tv_packageName.setText(b.getString("PackageName"));
        tv_permissions.setText(b.getString("Permission"));
        tv_targetSdkVersion.setText(b.getString("TargetSdkVersion"));
        tv_version.setText(b.getString("Version"));
        arrow3_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ApkInfoActivity.this,ResultActivity.class);
                intent.putExtra("ApplicationName",b.getString("AppName"));
              startActivity(intent);
            }
        });
    }
}
