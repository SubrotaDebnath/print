package subrota.shuveo.printing_test;

import android.content.Context;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MyPrintDocumentAdapter extends PrintDocumentAdapter {
    private final Context context;
    private final String filePath;
    private final String fileName;


    public MyPrintDocumentAdapter(Context context, String fileName, String filePath) {

        this.context = context;
        this.fileName = fileName;
        this.filePath = filePath;

    }

    @Override
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, LayoutResultCallback callback, Bundle extras) {

        if (cancellationSignal.isCanceled()) {
            callback.onLayoutCancelled();
            return;
        }

        PrintDocumentInfo pdi = new PrintDocumentInfo.Builder(fileName).setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT).build();

        callback.onLayoutFinished(pdi, true);
    }

    @Override
    public void onWrite(PageRange[] pages, ParcelFileDescriptor destination, CancellationSignal cancellationSignal, WriteResultCallback callback) {

        InputStream input = null;
        OutputStream output = null;

        try {

            input = new FileInputStream(filePath);
            output = new FileOutputStream(destination.getFileDescriptor());

            byte[] buf = new byte[1024];
            int bytesRead;

            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }

            callback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});

        } catch (Exception ee) {
            //Catch exception
        } finally {
            try {
                if (null != input) {
                    input.close();
                }
                if (null != output) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onFinish() {
        super.onFinish();


        Toast.makeText(context, "Printing is completed", Toast.LENGTH_SHORT).show();
    }
}
