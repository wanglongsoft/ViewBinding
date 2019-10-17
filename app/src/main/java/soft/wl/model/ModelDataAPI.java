package soft.wl.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.lifecycle.MutableLiveData;

public class ModelDataAPI {

    private static ModelDataAPI modelDataAPIInstance = null;

    public MutableLiveData<String> mButtonText = new MutableLiveData<>();
    public MutableLiveData<String> mViewText = new MutableLiveData<>();
    public MutableLiveData<Bitmap> mBitmap = new MutableLiveData<>();
    public MutableLiveData<String> mChangeText = new MutableLiveData<>();

    static public ModelDataAPI getInstance() {
        if(null == modelDataAPIInstance) {
            modelDataAPIInstance = new ModelDataAPI();
        }
        return modelDataAPIInstance;
    }

    public ModelDataAPI() {
        initLiveData();
    }

    private void initLiveData() {
        mButtonText.setValue("ModelData is TTTT");
        mViewText.setValue("ModelData is SSSS");
        mBitmap.setValue(getTextImage("123456", 500, 500));
        mChangeText.postValue("WWWWWW");
    }

    public MutableLiveData<String> getTestButtonText() {
        return mButtonText;
    }

    public MutableLiveData<String> getTestViewText() {
        return mViewText;
    }

    public MutableLiveData<Bitmap> getTestBitmap() {
        return mBitmap;
    }

    public MutableLiveData<String> getChangeText() {
        return mChangeText;
    }

    private static Bitmap getTextImage(String text, int width, int height) {
        final Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        final Canvas canvas = new Canvas(bmp);
        canvas.drawColor(Color.WHITE);
        drawTextNewLines(text, canvas);

        return bmp;
    }

    private static void drawTextNewLines(String text,  Canvas canvas){
        final Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(24.0f);

        String[] textArray = text.split("\n");
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int count = textArray.length;
        int lineSize = (int) (paint.descent() - paint.ascent());
        int maxLinesToPushUp = height / lineSize;
        maxLinesToPushUp = count < maxLinesToPushUp ? count : maxLinesToPushUp;
        int pixelsToPushUp = (maxLinesToPushUp - 1) / 2 * lineSize;

        int x = width / 2;
        int y = (height / 2) - pixelsToPushUp;

        for (String line : textArray){
            canvas.drawText(line, x, y, paint);
            y += lineSize;
        }
    }

    public void updateChangeText() {
        mChangeText.setValue("WWWWWW");
    }
}