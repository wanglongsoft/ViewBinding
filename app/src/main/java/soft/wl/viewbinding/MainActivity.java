package soft.wl.viewbinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import soft.wl.model.ModelDataAPI;
import soft.wl.viewbinding.databinding.ActivityMainBinding;
import soft.wl.viewmodel.MyViewModel;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "ViewBinding";

    ActivityMainBinding mActivityMainBinding = null;
    MyViewModel mMyViewModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: ");

        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMyViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        if (null != mActivityMainBinding && null != mMyViewModel) {
            mActivityMainBinding.setVm(mMyViewModel);
            mActivityMainBinding.setLifecycleOwner(this);
        }else {
            Log.d(TAG, "onCreate: mainBinding == null");
        }

        ModelDataAPI.getInstance().getChangeText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG, "onChanged mChangeText : " + s);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public void switchImage(View view) {//反射获取该方法，View.java源码有实现
        Log.d(TAG, "switchImage: " + "Button is click");
        if(null != mMyViewModel) {
            mMyViewModel.switchImage();
        }
    }

}
