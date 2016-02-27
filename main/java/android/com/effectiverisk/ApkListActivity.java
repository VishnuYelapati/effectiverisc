package android.com.effectiverisk;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srinu on 2/10/2016.
 */
public class ApkListActivity  extends ListActivity {

    private PackageManager packageManager = null;
    private List<ApplicationInfo> applist = null;
    private ApplicationAdapter listadaptor = null;

    private static  String APP_DETAILS_PACKAGE_NAME =""; // Here you need to define the  package name

    private static  String SCREEN_CLASS_NAME ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_apk_listview);

        packageManager = getPackageManager();
        new LoadApplications().execute();

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ApplicationInfo app = applist.get(position);
        APP_DETAILS_PACKAGE_NAME=app.packageName;
        SCREEN_CLASS_NAME=app.className;

            Toast.makeText(getApplicationContext(), app.targetSdkVersion+"", Toast.LENGTH_LONG).show();

        try {
            PackageInfo info=packageManager.getPackageInfo(app.packageName, PackageManager.GET_ACTIVITIES);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

       /* try {
            Intent intent = packageManager
                    .getLaunchIntentForPackage(app.packageName);

            if (null != intent) {
                startActivity(intent);
            }
        } catch (ActivityNotFoundException e) {
            Toast.makeText(ApkListActivity.this, e.getMessage(),
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(ApkListActivity.this, e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }*/

        /*Intent intent1 = new Intent();
        intent1.setAction(Intent.ACTION_VIEW);
        intent1.setClassName(APP_DETAILS_PACKAGE_NAME, SCREEN_CLASS_NAME);
        startActivity(intent1);*/

       /* ActivityManager localActivityManager = (ActivityManager) getSystemService(Activity.ACTIVITY_SERVICE);


        List RunningServiceInfo = localActivityManager .getRunningServices(100);
        Log.d("Name...",RunningServiceInfo.toString());*/



        Intent intent=new Intent(ApkListActivity.this, ApkInfoActivity.class);
        intent.putExtra("AppName",app.loadLabel(packageManager));
        intent.putExtra("PackageName",app.packageName);
        intent.putExtra("Permission",packageManager.getAllPermissionGroups(1).toString());
        intent.putExtra("MetaData",app.metaData);
        intent.putExtra("TargetSdkVersion", app.targetSdkVersion);
        try {
            intent.putExtra("Version",packageManager.getPackageInfo(app.packageName,0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //PackageInfo info=new PackageInfo();

        startActivity(intent);
    }

    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
        ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
        for (ApplicationInfo info : list) {
            try {
                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
                    applist.add(info);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return applist;
    }

    private class LoadApplications extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {
            applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
            listadaptor = new ApplicationAdapter(ApkListActivity.this,R.layout.layout_single_row, applist);

            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Void result) {
            setListAdapter(listadaptor);
            progress.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(ApkListActivity.this, null,
                    "Loading application info...");
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
