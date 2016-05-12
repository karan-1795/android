package karanbatra.com.cameraapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final int Request_Code = 1;
    ImageView photoClickedView;
    private String mimageLocation = "";
    private String LOCATION_GALLERY = "image_gallery";
    private File mGalleryFolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createImageGallery();
        photoClickedView = (ImageView) findViewById(R.id.imageView);

        new ImageAdapter(mGalleryFolder);
    }

    public void takephoto(View view)

    {
        Intent callcameraApp = new Intent();
        callcameraApp.setAction(MediaStore.ACTION_IMAGE_CAPTURE);


        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException e) {
            e.printStackTrace();

        }

        callcameraApp.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
        startActivityForResult(callcameraApp, Request_Code);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Request_Code && resultCode == RESULT_OK) {
            //Toast.makeText(this,"picture has been clicked",Toast.LENGTH_SHORT).show();

            //Bundle extras = data.getExtras();
            //Bitmap photoClicked = (Bitmap) extras.get("data");

            Bitmap photoCaptured = BitmapFactory.decodeFile(mimageLocation);

            photoClickedView.setImageBitmap(photoCaptured);

        }
    }

    public void createImageGallery() {
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        mGalleryFolder = new File(storageDirectory, LOCATION_GALLERY);
        if (!mGalleryFolder.exists()) {
            mGalleryFolder.mkdirs();

        }


    }

    public File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String imageFileName = "EMOJIS_" + timeStamp + "_";


        File image = File.createTempFile(imageFileName, ".jpg", mGalleryFolder);

        mimageLocation = image.getAbsolutePath();


        return image;
    }
}


