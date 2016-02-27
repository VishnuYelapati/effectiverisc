package android.com.effectiverisk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.security.AccessControlContext;

/**
 * Created by srinu on 2/10/2016.
 */
public class AboutActivity extends Activity {

    ImageView arrow_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_about);

       arrow_image=(ImageView)findViewById(R.id.arrow2_image);
        arrow_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutActivity.this,LoginActivity.class));
            }
        });
    }
}
