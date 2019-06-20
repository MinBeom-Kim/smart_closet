package com.example.smart_closet;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    DatabaseHelper db;
    String[] imageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        setTitle("투표 결과");

        db = new DatabaseHelper(this);


        Intent intent = getIntent();
        float[] voteResult = intent.getFloatArrayExtra("VoteCount");
        final String[] imageName = intent.getStringArrayExtra("ImageName");

        final ArrayList<String> voteList = new ArrayList<>();

        TextView tv[] = new TextView[imageName.length];
        RatingBar rbar[] = new RatingBar[imageName.length];


        Integer tvID[] = { R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5,
                R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9 };
        Integer rbarID[] = { R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4,
                R.id.rbar5, R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9 };


        // TextView, RatingBar 개체 찾기.
        for (int i = 0; i < voteResult.length; i++) {
            tv[i] = (TextView) findViewById(tvID[i]);
            rbar[i] = (RatingBar) findViewById(rbarID[i]);
        }

        // 각 TextVeiw 및 RatingBar에 넘겨 받은 값을 반영.
        for (int i = 0; i < voteResult.length; i++) {
            tv[i].setText(imageName[i]);
            rbar[i].setRating((float) voteResult[i]);
        }

        for (int i = 0; i < voteResult.length; i++) {
            if( voteResult[i] > 0 ){
                voteList.add(imageName[i]);
            }
        }

        for (int i = 0; i < voteList.size(); i++) {
            imageName[i] = voteList.get(i);
        }


        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Boolean insert = db.closet_insert(imageName);
                Toast.makeText(getApplicationContext(), imageName[0], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), VoteActivity.class);
                intent.putExtra("imageName", imageName);
                setResult(RESULT_OK, intent);
                startActivity(intent);
                finish();
//                if (insert == true) {
//                    Toast.makeText(getApplicationContext(), imageName[0], Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), VoteActivity.class);
//                    intent.putExtra("imageName", imageName);
//                    setResult(RESULT_OK, intent);
//                    startActivity(intent);
//                    finish();
//                }

            }
        });

    }
}