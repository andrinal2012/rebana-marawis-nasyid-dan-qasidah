package congas.dangdut.bongos;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private int ss1,ss2,ss3,ss4,ss5,ss6,ss7,ss8,ss9,ss10,ss11,ss12,ss13,ss14;
    SoundPool sp;
    private ImageView dek,dong,dum,dum2,dut,kecrekkiri,kecrekkanan;
    private ImageView taktis,tang,tek,tom,tung,uo1,uo2;


    private com.facebook.ads.AdView adView;
    private String[] permissionsRequired2 = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
    private String[] permissionsRequired = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int PERMISSION_CALLBACK_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;

    private SharedPreferences permissionStatus2;
    private boolean sentToSettings2 = false;

    //List Lagu
    //Song from sdcard

    private final String[] listContent = {"Sebatang Pohon","Peristiwa Subuh","Matahari Dunia","Kota Santri","Iktiraf","Bila Izrail"};

    private final int[] resID = {R.raw.sebatang_pohon,R.raw.peristiwa_subuh,R.raw.matahari_dunia,R.raw.kota_santri,R.raw.iktiraf,R.raw.bila_izrail};
    private ListView songRecyclerView;

    ContentResolver contentResolver;
    String[] ListElements = new String[] { };
    String[] ListEl = new String[] { };
    List<String> ListElementsArrayList ;
    List<String> listData;
    List<String> lagu;
    ArrayAdapter<String> adapter ;
    Cursor cursor;
    MediaPlayer mp;

    private Button pilihlagu;
    private Button btnCancle;
    private RelativeLayout layoutSong;
    private RelativeLayout llboard;
    private boolean opensong = false;
    private Context context;
    private Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        permissionStatus2 = getSharedPreferences("permissionStorage2", MODE_PRIVATE);
        mp = new MediaPlayer();
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        insertSong();

        pilihlagu  = findViewById(R.id.btn_pilih_lagu);
        btnCancle = findViewById(R.id.btn_cancle);
        layoutSong = findViewById(R.id.layout_song);
        llboard    = findViewById(R.id.ll_board);
        stop      = findViewById(R.id.stop);


        songRecyclerView       = findViewById(R.id.songRecyclerView);

        ListElementsArrayList  = new ArrayList<>(Arrays.asList(ListElements));
        listData               = new ArrayList<>(Arrays.asList(ListEl));
        // lagu                   = new ArrayList<>(Arrays.asList(listSongUri));

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ListElementsArrayList);

        pilihlagu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permission2();
                layoutSong.setVisibility(View.VISIBLE);
                pilihlagu.setVisibility(View.GONE);
                btnCancle.setVisibility(View.VISIBLE);
                opensong = true;
                llboard.setVisibility(View.GONE);
                songRecyclerView.setAdapter(adapter);
            }
        });

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihlagu.setVisibility(View.VISIBLE);
                btnCancle.setVisibility(View.GONE);
                opensong = true;
                llboard.setVisibility(View.VISIBLE);
                layoutSong.setVisibility(View.GONE);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.setVisibility(View.GONE);
                mp.stop();
            }
        });



        dek = findViewById(R.id.dek);
        dong = findViewById(R.id.dong);
        dum = findViewById(R.id.dum);
        dum2 = findViewById(R.id.dum2);
        dut = findViewById(R.id.dut);
        kecrekkiri = findViewById(R.id.kecrekkiri);
        kecrekkanan = findViewById(R.id.kecrekkanan);
        taktis= findViewById(R.id.taktis);
        tang = findViewById(R.id.tang);
        tek = findViewById(R.id.tek);
        tom = findViewById(R.id.tom);
        tung = findViewById(R.id.tung);
        uo1 = findViewById(R.id.uo1);
        uo2 = findViewById(R.id.uo2);

        dek.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent == null || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.button);
                    dek.startAnimation(myAnim);
                    sp.play(ss1, 1, 1, 0, 0, 0);

                }
                return true;
            }
        });

        dong.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent == null || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.button);
                    dong.startAnimation(myAnim);
                    sp.play(ss2, 1, 1, 0, 0, 0);

                }
                return true;
            }
        });

        dum.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent == null || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.button);
                    dum.startAnimation(myAnim);
                    sp.play(ss3, 1, 1, 0, 0, 0);

                }
                return true;
            }
        });

        dum2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent == null || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.button);
                    dum2.startAnimation(myAnim);
                    sp.play(ss4, 1, 1, 0, 0, 0);

                }
                return true;
            }
        });

        dut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent == null || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.button);
                    dut.startAnimation(myAnim);
                    sp.play(ss5, 1, 1, 0, 0, 0);

                }
                return true;
            }
        });

        kecrekkiri.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent == null || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.button);
                    kecrekkiri.startAnimation(myAnim);
                    sp.play(ss6, 1, 1, 0, 0, 0);

                }
                return true;
            }
        });

        kecrekkanan.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent == null || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.button);
                    kecrekkanan.startAnimation(myAnim);
                    sp.play(ss7, 1, 1, 0, 0, 0);

                }
                return true;
            }
        });

        taktis.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent == null || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.button);
                    taktis.startAnimation(myAnim);
                    sp.play(ss8, 1, 1, 0, 0, 0);

                }
                return true;
            }
        });


        tang.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent == null || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.button);
                    tang.startAnimation(myAnim);
                    sp.play(ss9, 1, 1, 0, 0, 0);

                }
                return true;
            }
        });

        tek.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent == null || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.button);
                    tek.startAnimation(myAnim);
                    sp.play(ss10, 1, 1, 0, 0, 0);

                }
                return true;
            }
        });

        tom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent == null || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.button);
                    tom.startAnimation(myAnim);
                    sp.play(ss11, 1, 1, 0, 0, 0);

                }
                return true;
            }
        });

        tung.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent == null || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.button);
                    tung.startAnimation(myAnim);
                    sp.play(ss12, 1, 1, 0, 0, 0);

                }
                return true;
            }
        });

        uo1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent == null || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.button);
                    uo1.startAnimation(myAnim);
                    sp.play(ss13, 1, 1, 0, 0, 0);

                }
                return true;
            }
        });

        uo2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent == null || motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.button);
                    uo2.startAnimation(myAnim);
                    sp.play(ss14, 1, 1, 0, 0, 0);

                }
                return true;
            }
        });

    }


    public void insertSong(){
        ss1 = sp.load(this,R.raw.dek,1);
        ss2 = sp.load(this, R.raw.dong, 1);
        ss3 = sp.load(this, R.raw.dum, 1);
        ss4 = sp.load(this, R.raw.dum2, 2);

        ss5 = sp.load(this, R.raw.dut, 1);
        ss6 = sp.load(this,R.raw.kecrekkiri,1);

        ss7 = sp.load(this,R.raw.kecrekkanan,1);
        ss8 = sp.load(this,R.raw.taktis,1);
        ss9 = sp.load(this, R.raw.tang, 2);

        ss10 = sp.load(this, R.raw.tek, 1);
        ss11 = sp.load(this,R.raw.tom,1);

        ss12 = sp.load(this,R.raw.tung,1);
        ss13 = sp.load(this,R.raw.uo1,1);
        ss14 = sp.load(this,R.raw.uo2,1);


    }


    public void permission2() {

        if (ActivityCompat.checkSelfPermission(MainActivity.this, permissionsRequired2[0]) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permissionsRequired[0])
                    ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle(Html.fromHtml("<font color='#000000'>" + getString(R.string.text_permission_storage) + "</font>"));
                builder.setMessage(Html.fromHtml("<font color='#000000'>" + getString(R.string.text_permission_storage_song) + "</font>"));

                builder.setPositiveButton(getString(R.string.text_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(MainActivity.this, permissionsRequired2, PERMISSION_CALLBACK_CONSTANT);
                    }
                });
                builder.setNegativeButton(getString(R.string.text_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        layoutSong.setVisibility(View.GONE);
                        llboard.setVisibility(View.VISIBLE);

                    }
                });
                builder.show();
            } else if (permissionStatus2.getBoolean(permissionsRequired2[0], false)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle(Html.fromHtml("<font color='#000000'>" + getString(R.string.text_permission_storage) + "</font>"));
                builder.setMessage(Html.fromHtml("<font color='#000000'>" + getString(R.string.text_permission_storage_song2) + "</font>"));

                builder.setPositiveButton(getString(R.string.text_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        sentToSettings2 = true;
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                        Toast.makeText(getBaseContext(), "Go to Permissions to Allow Storage File", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton(getString(R.string.text_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        layoutSong.setVisibility(View.GONE);
                        llboard.setVisibility(View.VISIBLE);
                    }
                });
                builder.show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, permissionsRequired2, PERMISSION_CALLBACK_CONSTANT);
            }

            SharedPreferences.Editor editor = permissionStatus2.edit();
            editor.putBoolean(permissionsRequired2[0], true);
            editor.commit();
        } else {
            GetAllMediaMp3Files();
        }
    }



    public void GetAllMediaMp3Files(){

        contentResolver = context.getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        cursor = contentResolver.query(
                uri, // Uri
                null, null,
                null,
                null
        );

        if (cursor == null) {

            Toast.makeText(context,"Something Went Wrong.", Toast.LENGTH_LONG);

        } else if (!cursor.moveToFirst()) {

            Toast.makeText(context,"No Music Found on SD Card.", Toast.LENGTH_LONG);

        }
        else {

            int Title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int song  = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);


            //Convert String[] to List<String> For Join title song
            List<String> wordList = Arrays.asList(listContent);
            // List<String> position = Arrays.asList(listSongUri);


            ListElementsArrayList.addAll(wordList);
            //  listData.addAll(position);
            do {

                // You can also get the Song ID using cursor.getLong(id).
                //long SongID = cursor.getLong(id);

                String SongTitle = cursor.getString(Title);
                String location = cursor.getString(song);

                // Adding Media File Names to ListElementsArrayList.
                ListElementsArrayList.add(SongTitle);

                listData.add(location);

            } while (cursor.moveToNext());

        }

        songRecyclerView.setAdapter(adapter);
        songRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pilihlagu.setVisibility(View.VISIBLE);
                btnCancle.setVisibility(View.GONE);
                opensong = false;
                llboard.setVisibility(View.VISIBLE);
                layoutSong.setVisibility(View.GONE);
                playSong2(listData.get(i),i);
                stop.setVisibility(View.VISIBLE);

            }

        });

    }

    public void playSong2(String data,int position) {
        // Play song
        mp.reset();// stops any current playing song

        if(position ==1|| position ==2|| position ==3|| position ==4|| position ==5|| position ==6){

            mp = MediaPlayer.create(getApplicationContext(), resID[position]);

        }else {
            mp= MediaPlayer.create(getApplicationContext(), Uri.parse(data));

        }

        mp.start(); // starting mediaplayer


    }


    public void onBackPressed(){

        if(opensong == true){
            pilihlagu.setVisibility(View.VISIBLE);
            btnCancle.setVisibility(View.GONE);
            opensong = false;
            llboard.setVisibility(View.VISIBLE);
            layoutSong.setVisibility(View.GONE);


        }else {
            //mInterstitialAd.show();
            sp.stop(1);
            sp.release();
            mp.stop();
            mp.release();
            Intent intent = new Intent();
            setResult(RESULT_OK,intent);
            finish();
        }
    }

    public void onResume(){
        super.onResume();
        permission2();
        insertSong();

    }

}