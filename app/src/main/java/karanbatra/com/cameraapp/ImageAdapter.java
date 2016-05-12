package karanbatra.com.cameraapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Karan Batra on 11-04-2016.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private File imagesFile;

    public ImageAdapter(File folderFile)
    {
        imagesFile=folderFile;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.galleryimages, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        File imageFile = imagesFile.listFiles()[position];
        Bitmap imageBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        holder.getImage().setImageBitmap(imageBitmap);


    }

    @Override
    public int getItemCount() {
        return imagesFile.listFiles().length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;


        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);


        }

        public ImageView getImage() {
            return imageView;
        }

    }
}
