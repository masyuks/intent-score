package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import id.putraprima.skorbola.model.DataTim;

import static id.putraprima.skorbola.MainActivity.EXTRA_DATA;

public class MatchActivity extends AppCompatActivity {

    private static final String EXTRA_RESULT = "EXTRA_RESULT";

    private TextView homeNameText;
    private TextView awayNameText;
    private ImageView homeLogoPict;
    private ImageView awayLogoPict;
    private DataTim tim;
    private TextView homeScoreText;
    private TextView awayScoreText;
    private int homeScore = 0;
    private int awayScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        homeNameText = findViewById(R.id.txt_home);
        awayNameText = findViewById(R.id.txt_away);
        homeLogoPict = findViewById(R.id.home_logo);
        awayLogoPict = findViewById(R.id.away_logo);

        Intent extras = getIntent();
        if (extras != null) {
            tim = extras.getParcelableExtra(EXTRA_DATA);
            homeNameText.setText(tim.getHomeName());
            awayNameText.setText(tim.getAwayName());
            try {
                Log.d("Main", "onActivityResult home=: "+ tim.getHomeLogo().toString());
                Log.d("Main", "onActivityResult away=: "+ tim.getAwayLogo().toString());
                Bitmap homeLogo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(tim.getHomeLogo()));
                Bitmap awayLogo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(tim.getAwayLogo()));
                awayLogoPict.setImageBitmap(awayLogo);
                homeLogoPict.setImageBitmap(homeLogo);
            } catch (IOException e) {
                Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void handleScoreHome(View view) {
        homeScore+=1;
        homeScoreText = findViewById(R.id.score_home);
        homeScoreText.setText(String.valueOf(homeScore));
    }

    public void handleScoreAway(View view) {
        awayScore+=1;
        awayScoreText = findViewById(R.id.score_away);
        awayScoreText.setText(String.valueOf(awayScore));
    }

    public void cekResult(View view) {
        String result = (homeScore == awayScore) ? tim.getHomeName()+" Imbang dengan "+tim.getAwayName():
                (homeScore > awayScore) ? tim.getHomeName()+" Menang !! ": tim.getAwayName()+" Menang!! ";

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.EXTRA_RESULT, result);
        startActivity(intent);
    }
}
