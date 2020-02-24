package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    public static String EXTRA_RESULT;

    public TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent extras = getIntent();
        String result = extras.getStringExtra(EXTRA_RESULT);

        resultText = findViewById(R.id.winnerText);
        resultText.setText(result);

    }
}
