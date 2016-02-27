package android.com.effectiverisk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

/**
 * Created by srinu on 2/11/2016.
 */
public class ResultActivity extends Activity {
    ImageView left_arrow,right_arrow;
    TextView tv_appname,tv_totalpermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_result);

        Intent intent=getIntent();

        tv_appname=(TextView)findViewById(R.id.tv_applicationName);
        tv_totalpermissions=(TextView)findViewById(R.id.tv_totalpermissions);
        left_arrow=(ImageView)findViewById(R.id.left_arrow);
        right_arrow=(ImageView)findViewById(R.id.right_arrow);

        tv_appname.setText(intent.getStringExtra("ApplicationName"));

        left_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this,MainActivity.class));
            }
        });

        right_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChart();
            }
        });

    }

    private void openChart() {

        // Pie Chart Section Names
        String[] code = new String[]{"RISK", "MODERATE", "NORMAL "};

        // Pie Chart Section Value
        double[] distribution = {42, 15, 19};

        // Color of each Pie Chart Sections
        int[] colors = {Color.RED, Color.YELLOW, Color.GREEN};

        // Instantiating CategorySeries to plot Pie Chart
        CategorySeries distributionSeries = new CategorySeries("Cellular Traffic Reduction");
        for (int i = 0; i < distribution.length; i++) {

            // Adding a slice with its values and name to the Pie Chart
            distributionSeries.add(code[i], distribution[i]);
        }

        // Instantiating a renderer for the Pie Chart
        DefaultRenderer defaultRenderer = new DefaultRenderer();
        for (int i = 0; i < distribution.length; i++) {

            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(colors[i]);
            //seriesRenderer.setDisplayChartValues(true);
            // Adding a renderer for a slice
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }


        defaultRenderer.setChartTitle("Cellular Traffic Reduction");
        defaultRenderer.setChartTitleTextSize(30);
        defaultRenderer.setDisplayValues(true);
        defaultRenderer.setZoomButtonsVisible(true);
        defaultRenderer.setApplyBackgroundColor(true);
        defaultRenderer.setBackgroundColor(Color.BLUE);

        // Creating an intent to plot bar chart using dataset and multipleRenderer
        Intent intent = ChartFactory.getPieChartIntent(getBaseContext(), distributionSeries, defaultRenderer, "AChartEnginePieChartDemo");

        // Start Activity
        startActivity(intent);

    }


}
