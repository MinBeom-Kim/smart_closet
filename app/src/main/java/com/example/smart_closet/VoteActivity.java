package com.example.smart_closet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VoteActivity extends AppCompatActivity {



    ImageView ivPoster;
    Button btnResult, btnReturn;
    RatingBar ratingbar;
    float voteCount[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    String[] voteList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote);

        btnReturn = (Button) findViewById(R.id.btnReturn);
        btnResult = (Button) findViewById(R.id.btnResult);
        ivPoster = (ImageView) findViewById(R.id.ivCloset);
        Gallery gallery = (Gallery) findViewById(R.id.gallery1);

        final String imgName[] = { "네이비색 셔츠", "슬림 셔츠", "U넥 셔츠",
                "남자 바지", "슬림형 바지", "체크무늬 바지", "3선 신발", "런닝화",
                "아디다스 신발" };


        MyGalleryAdapter galAdapter = new MyGalleryAdapter(this);
        gallery.setAdapter(galAdapter);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("VoteCount", voteCount);
                intent.putExtra("ImageName", imgName);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        final String[] imageName = intent.getStringArrayExtra("imageName");

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ClosetActivity.class);
                intent.putExtra("imageName", imageName);
//                setResult(RESULT_OK, intent);
                startActivity(intent);
//                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (resultCode == RESULT_OK) {
            // Make sure the request was successful
            try {
                voteList = data.getStringArrayExtra("voteList");
                Toast.makeText(getApplicationContext(), voteList[0], Toast.LENGTH_SHORT).show();

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
                R.drawable.iv9 };



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

        public View getView(int position, View convertView, ViewGroup parent) {
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
                    ratingbar = findViewById(R.id.Rating1);

                    ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                            Toast.makeText(getApplicationContext(),
                                    "설정값"+rating ,Toast.LENGTH_SHORT).show();
                            voteCount[pos] = rating;

                        }
                    });
//
//
//                class Listener implements RatingBar.OnRatingBarChangeListener
//                {
//                    @Override
//                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//
//                    }
//                }
                    return false;
                }
            });

            return imageview;
        }
    }

}
