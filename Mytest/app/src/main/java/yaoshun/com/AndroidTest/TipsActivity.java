package yaoshun.com.AndroidTest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TipsActivity extends Activity {
    private static final String TAG = "TipsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: xxxxxx");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        Intent it2 = getIntent();
        String Language = it2.getStringExtra("language");
        TextView content = findViewById(R.id.content);
        content.setText(Language);
        Button btnClose =  findViewById(R.id.tips_button1);
        btnClose.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent it1 = new Intent();
                it1.putExtra("result", "TipsActivity Success");
                TipsActivity.this.setResult(0x001, it1);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}