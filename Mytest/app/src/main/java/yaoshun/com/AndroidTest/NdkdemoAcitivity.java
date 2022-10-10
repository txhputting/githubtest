package yaoshun.com.AndroidTest;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import yaoshun.com.AndroidTest.databinding.ActivityNdkdemoAcitivityBinding;

public class NdkdemoAcitivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndktools);
//        String text = "test test";
        String text = ndkTools.getStringFromNDK();
        Log.i("gebilaolitou","text="+text);
        ((TextView)findViewById(R.id.tv)).setText(text);
    }
}