package subrota.shuveo.printing_test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintJob;
import android.print.PrintManager;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;

import java.io.File;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int PICK_FILE_RESULT_CODE = 1;

    private PrintManager printManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);

    }

    private String getFilepath(String filename) {

        Log.i(TAG, "File PAth::::::::: " + new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/Downloads/" + filename).getPath());

        return new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/Downloads/" + filename).getPath();

    }

    public void print(View view) {

//        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("*/*");
//        intent.setType("application/pdf");
//       // intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri);
//        //intent.setAction(Intent.ACTION_GET_CONTENT);
//       // intent = Intent.createChooser(intent, "Choose a file");
//        startActivityForResult(intent, PICK_FILE_RESULT_CODE);
//       // startActivityForResult(intent,PICK_FILE_RESULT_CODE);
        String jobName = getString(R.string.app_name) + " Document";
        PrintJob printJob = printManager.print(jobName, new MyPrintDocumentAdapter(MainActivity.this, "df", getFilepath("df.pdf")), null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == requestCode && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Log.i(TAG, "Data is null");
                return;
            }
            Uri uri = data.getData();
            Log.i(TAG, "PAth :::: " + uri.getPath());
        }
    }
}