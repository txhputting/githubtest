package yaoshun.com.AndroidTest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
    Intent it1;

    private View.OnClickListener button1Onclick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent it2 = new Intent();
            it2.putExtra("result", "WelcomeActivity Success");
            Log.d("yaoshun", "onClick: 1111111111111111");
            WelcomeActivity.this.setResult(0x002, it2);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("yaoshun", "onCreate: 22222222");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView content = findViewById(R.id.welcome_content);
        findViewById(R.id.welcome_button1).setOnClickListener(button1Onclick);
        it1 = getIntent();
        Bundle bd = it1.getExtras();
        String  name = bd.getString("name");
        Integer age = bd.getInt("Age");
        String tips = bd.getString("Tips");
        content.setText("Name: " + name + " Age: " + age + " Tips: " + tips);
    }
}