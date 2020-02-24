package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
    private ImageView homeLogo;
    private ImageView awayLogo;
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

        Intent extras = getIntent();
        if (extras != null) {
            tim = extras.getParcelableExtra(EXTRA_DATA);
            homeNameText.setText(tim.getHomeName());
            awayNameText.setText(tim.getAwayName());
            homeLogo = findViewById(R.id.home_logo);
            awayLogo = findViewById(R.id.away_logo);
            setImage(homeLogo, tim.getHomeLogo());
            setImage(awayLogo, tim.getAwayLogo());
        }
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }

    private void setImage(ImageView view, Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            view.setImageBitmap(bitmap);
        } catch (IOException e) {
            Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
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
