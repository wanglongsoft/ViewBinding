package soft.wl.control;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatImageView;

public class ImageBase extends AppCompatImageView {

    private static final String TAG = "ImageBase";

    public ImageBase(Context context) {
        super(context);
    }

    public ImageBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBitmap(Bitmap bitmap) {

        Log.d(TAG, "setBitmap: " + bitmap);

        if(bitmap == null) {
            Log.d(TAG, "setBitmap: bitmap == null, return");
            return;
        }

        this.setImageBitmap(bitmap);
    }
}
