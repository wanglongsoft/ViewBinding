package soft.wl.viewmodel;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import soft.wl.model.ModelDataAPI;

public class MyViewModel extends AndroidViewModel {

    public MutableLiveData<String> ButtonText = new MutableLiveData<>();//首字母大写，默认绑定函数，小写默认绑定变量
    public MutableLiveData<String> ViewText = new MutableLiveData<>();
    public MutableLiveData<Bitmap> BitmapData = new MutableLiveData<>();

    private ModelDataAPI modelDataAPI = ModelDataAPI.getInstance();

    public MyViewModel(Application application) {
        super(application);
        initLiveData();
    }

    private void initLiveData() {
        ButtonText.setValue("切换图片");
        ViewText.setValue("TTTT");
    }

    public MutableLiveData<String> getButtonText() {
        return modelDataAPI.getTestButtonText();
    }

    public MutableLiveData<String> getViewText() {
        return modelDataAPI.getTestViewText();
    }

    public MutableLiveData<Bitmap> getBitmapData() {
        return modelDataAPI.getTestBitmap();
    }

    public void switchImage() {
        modelDataAPI.updateChangeText();
    }
}
