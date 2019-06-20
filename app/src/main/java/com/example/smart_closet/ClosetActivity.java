package com.example.smart_closet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import java.io.InputStream;
import java.util.ArrayList;
import android.widget.Gallery;
import android.widget.Toast;

public class ClosetActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper db;
    ImageView imageView;
    String name, mail, profile;
    Button btnVote;
    Integer tvID[] = { R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5};
    TextView tv[] = new TextView[tvID.length];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);




        btnVote = (Button) findViewById(R.id.btnVote);

        btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VoteActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        String[] imageName = intent.getStringArrayExtra("imageName");

        for (int i = 0; i < tvID.length; i++) {
            tv[i] = (TextView) findViewById(tvID[i]);
        }

        for (int i = 0; i < tvID.length; i++) {
            tv[i].setText(imageName[i]);
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = null;

        if (id == R.id.nav_camera) {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        } else if (id == R.id.nav_gallery) {
            intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            startActivityForResult(intent, 1);
            //Glide.with(this).load(R.drawable.img).into(iv);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        startActivity(intent);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (resultCode == RESULT_OK) {
            // Make sure the request was successful
            try {
                String[] imageName = data.getStringArrayExtra("imageNmae");
//                    ArrayList<String> imgName = db.getCloset();
                for (int i = 0; i < tvID.length; i++) {
                    tv[i] = (TextView) findViewById(tvID[i]);
                }

                for (int i = 0; i < imageName.length; i++) {
                    tv[i].setText(imageName[i]);
                }


//                    if (userCloset.isEmpty() != true ) {
//                        userCloset = (ArrayList<String>) data.getSerializableExtra("voteList");
//
//                        Integer tvID[] = { R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5};
//                        TextView tv[] = new TextView[userCloset.size()];
//
//                        for (int i = 0; i < userCloset.size(); i++) {
//                            tv[i] = (TextView) findViewById(tvID[i]);
//                        }
//
//                        // 각 TextVeiw 및 RatingBar에 넘겨 받은 값을 반영.
//                        for (int i = 0; i < userCloset.size(); i++) {
//                            tv[i].setText(userCloset.get(i));
//                        }
//                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }






}

