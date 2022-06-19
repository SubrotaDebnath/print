package subrota.shuveo.printing_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Log;
import android.view.View;

import java.io.File;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private PrintManager printManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);

    }

    private String getFilepath(String filename) {

        Log.i(TAG,"File PAth::::::::: "+ new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/Downloads/" + filename).getPath());

        return new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/Downloads/" + filename).getPath();

    }


    public void print(View view) {
        String jobName = getString(R.string.app_name) + " Document";
        PrintJob printJob = printManager.print(jobName, new MyPrintDocumentAdapter(MainActivity.this, "df", getFilepath("df.pdf")), null);
    }


}