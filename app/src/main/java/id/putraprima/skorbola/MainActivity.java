package id.putraprima.skorbola;

import androidx.annotation.Nullable;
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
import android.widget.Toast;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.io.IOException;

import id.putraprima.skorbola.model.DataTim;

public class MainActivity extends AppCompatActivity {
    public static String EXTRA_DATA = "extra";

    private static final int REQUEST_CODE_HOME = 1;
    private static final int REQUEST_CODE_AWAY = 2;
//    private static final String TAG = MainActivity.class.getCanonicalName();
//    private static final int GALLERY_REQUEST_CODE = 1;

    @NotEmpty
    private EditText homeNameInput;
    @NotEmpty
    private EditText awayNameInput;
    @NotEmpty
    private ImageView homeLogoPict;
    @NotEmpty
    private ImageView awayLogoPict;

    private Uri homeLogo;
    private Uri awayLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        homeNameInput = findViewById(R.id.home_team);
        //2. Validasi Input Away Team
        awayNameInput = findViewById(R.id.away_team);
        //3. Ganti Logo Home Team
        homeLogoPict = findViewById(R.id.home_logo);
        //4. Ganti Logo Away Team
        awayLogoPict = findViewById(R.id.away_logo);
        //5. Next Button Pindah Ke MatchActivity
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        switch (requestCode){
            case REQUEST_CODE_HOME:
                homeLogo = data.getData();
                setImage(homeLogoPict, homeLogo);
                break;
            case REQUEST_CODE_AWAY:
                awayLogo = data.getData();
                setImage(awayLogoPict, awayLogo);
                break;
        }
    }

    private void setImage(ImageView view, Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            view.setImageBitmap(bitmap);
        } catch (IOException e) {
            Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
        }
    }

    public void homeLogo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_HOME);
    }

    public void awayLogo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_AWAY);
    }

    public void handleNext(View view) {
        String homeName = homeNameInput.getText().toString();
        String awayName = awayNameInput.getText().toString();

        Intent intent = new Intent(this, MatchActivity.class);
        intent.putExtra(EXTRA_DATA, new DataTim(homeName, awayName, homeLogo, awayLogo));

        startActivity(intent);
    }
}
