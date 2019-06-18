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
import android.widget.TextView;
import java.io.InputStream;
import java.util.ArrayList;
import android.widget.Gallery;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imageView;
    String name, mail, profile;
    ImageView ivPoster;
    Button btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnResult = (Button) findViewById(R.id.btnResult);
        imageView = (ImageView) findViewById(R.id.iv1);
        ivPoster = (ImageView) findViewById(R.id.ivCloset);
        Gallery gallery = (Gallery) findViewById(R.id.gallery1);

        final String imgName[] = { "독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀",
                "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들",
                "해변에서" };


        final int voteCount[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};

        MyGalleryAdapter galAdapter = new MyGalleryAdapter(this);
        gallery.setAdapter(galAdapter);

        Intent intent = getIntent();
        mail = intent.getStringExtra("email");
        final ArrayList<String> userData = (ArrayList<String>) intent.getSerializableExtra("userData");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View nav_header_view = navigationView.getHeaderView(0);
        TextView nav_header_mail_text = (TextView) nav_header_view.findViewById(R.id.user_mail);
        TextView nav_header_name_text = (TextView) nav_header_view.findViewById(R.id.user_name);
        nav_header_mail_text.setText(userData.get(0));
        nav_header_name_text.setText(userData.get(1) + "님 안녕하세요");


        TextView userDataUpdate = (TextView) nav_header_view.findViewById(R.id.user_data_update);
        userDataUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserDataUpdate.class);
                intent.putExtra("userData", userData);
                startActivity(intent);
            }
        });


        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("VoteCount", voteCount);
                intent.putExtra("ImageName", imgName);
                startActivity(intent);
            }
        });


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
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Make sure the request was successful
            try {
//                ImageInsertActivity n_layout = new ImageInsertActivity(getApplicationContext());
//                LinearLayout con = (LinearLayout) findViewById(R.id.con);
//                con.addView(n_layout);
//                // 선택한 이미지에서 비트맵 생성
//                InputStream in = getContentResolver().openInputStream(data.getData());
//                Bitmap img = BitmapFactory.decodeStream(in);
//                in.close();
//                imageView.setImageBitmap(img);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class MyGalleryAdapter extends BaseAdapter {

        Context context;
        Integer[] DressID = { R.drawable.iv1, R.drawable.iv2,
                R.drawable.iv3, R.drawable.iv4, R.drawable.iv5,
                R.drawable.iv6, R.drawable.iv7, R.drawable.iv8,
                R.drawable.iv9, R.drawable.iv10 };



        public MyGalleryAdapter(Context c) {
            context = c;
        }

        public int getCount() {
            return DressID.length;
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("ClickableViewAccessibility") //

        public View getView(int     position, View convertView, ViewGroup parent) {
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new Gallery.LayoutParams(200, 300));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);
            imageview.setImageResource(DressID[position]);

            final int pos = position;
            imageview.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {

                    ivPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    ivPoster.setImageResource(DressID[pos]);

                    return false;
                }
            });

            return imageview;
        }
    }




}

