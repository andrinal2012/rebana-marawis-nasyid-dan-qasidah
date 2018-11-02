package congas.dangdut.bongos;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;


public class SplashScreenActivity extends AppCompatActivity {

    //permission
    private static final int PERMISSION_CALLBACK_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    String[] permissionsRequired = new String[]{Manifest.permission.RECORD_AUDIO};
    private boolean sentToSettings = false;
    private SharedPreferences permissionStatus;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    /**
     * Called when the activity is first created.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_splash_screen);
        permissionStatus = getSharedPreferences("permissionStatus", MODE_PRIVATE);
        StartAnimations();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void StartAnimations() {

        final ImageView iv = (ImageView) findViewById(R.id.imageView);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.alpha);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);

        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(an2);
                Intent i = new Intent(SplashScreenActivity.this, HomeActivity.class);
                startActivity(i);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void permission() {

        if (ActivityCompat.checkSelfPermission(SplashScreenActivity.this, permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(SplashScreenActivity.this, permissionsRequired[0])
                    ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreenActivity.this);

                builder.setTitle(Html.fromHtml("<font color='#000000'>" + getString(R.string.text_permission_audio) + "</font>"));
                builder.setMessage(Html.fromHtml("<font color='#000000'>" + getString(R.string.text_permission_create_audio) + "</font>"));

                builder.setPositiveButton(getString(R.string.text_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(SplashScreenActivity.this, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                    }
                });
                builder.setNegativeButton(getString(R.string.text_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        finish();
                        permission();
                    }
                });
                builder.show();
            } else if (permissionStatus.getBoolean(permissionsRequired[0], false)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreenActivity.this);

                builder.setTitle(Html.fromHtml("<font color='#000000'>" + getString(R.string.text_permission_audio) + "</font>"));
                builder.setMessage(Html.fromHtml("<font color='#000000'>" + getString(R.string.text_permission_create_audio) + "</font>"));

                builder.setPositiveButton(getString(R.string.text_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        sentToSettings = true;
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                        Toast.makeText(getBaseContext(), "Go to Permissions to Allow Audio", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton(getString(R.string.text_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        permission();
                        finish();
                    }
                });
                builder.show();
            } else {
                ActivityCompat.requestPermissions(SplashScreenActivity.this, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
            }

            SharedPreferences.Editor editor = permissionStatus.edit();
            editor.putBoolean(permissionsRequired[0], true);
            editor.commit();
        } else {
            Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(i);
        }


    }
}
