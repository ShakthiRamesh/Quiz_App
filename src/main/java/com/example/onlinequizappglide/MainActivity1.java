package com.example.onlinequizappglide;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class MainActivity1 extends AppCompatActivity {

    ImageView ivShowImage;
    ArrayList<String> cartNames = new ArrayList<>();
    ArrayList<String> newCartNames = new ArrayList<>();
    HashMap<String, String> map = new HashMap<>();
    int index;
    Random random;
    String[] answers = new String[4];
    Button btn1, btn2, btn3, btn4, btnRestart;
    int points = 0;
    TextView tvPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ivShowImage = findViewById(R.id.ivShowImage);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btnRestart = findViewById(R.id.btnRestart);
        tvPoints = findViewById(R.id.tvPoints);
        cartNames.add("Dora");
        cartNames.add("Chota Bheem");
        cartNames.add("Jackie Chan");
        cartNames.add("Shin Chan");
        cartNames.add("Doremon");
        cartNames.add("Tom and Jerry");
        cartNames.add("Power puff girls");
        index = 0;
        map.put(cartNames.get(0), "https://w7.pngwing.com/pngs/222/293/png-transparent-dora-the-explorer-humour-girlfriend-meme-9gag-dora-love-child-hand-thumbnail.png");
        map.put(cartNames.get(1), "https://i.pinimg.com/originals/4f/82/bd/4f82bd416fd55b8924607d378918cf54.png");
        map.put(cartNames.get(2), "https://www.nicepng.com/png/detail/80-806068_jackie-1-jackie-chan-cartoon-png.png");
        map.put(cartNames.get(3), "https://w7.pngwing.com/pngs/216/879/png-transparent-crayon-shin-chan-comedy-film-anime-youtube-crayon-cartoon-fictional-character-film.png");
        map.put(cartNames.get(4), "https://freepngimg.com/thumb/doraemon/71299-nobi-youtube-nobita-television-doraemon-free-photo-png-thumb.png");
        map.put(cartNames.get(5), "https://toppng.com//public/uploads/preview/tom-and-jerry-11530965341x4f6dpjznr.png");
        map.put(cartNames.get(6), "https://e1.pngegg.com/pngimages/371/549/png-clipart-cartoons-s-powerpuff-girls-illustration-thumbnail.png");
        Collections.shuffle(cartNames);
        random = new Random();
        generateQuestions(index);
    }

    private void generateQuestions(int index) {
        Glide.with(this)
                .asBitmap()
                .load(map.get(cartNames.get(index)))
                .error(R.drawable.not_found)
                .into(ivShowImage);
        newCartNames = (ArrayList<String>) cartNames.clone();
        newCartNames.remove(index);
        Collections.shuffle(newCartNames);
        int correctAnswerPosition = random.nextInt(4);
        for(int i=0; i < 4; i++){
            if(i == correctAnswerPosition){
                answers[i] = cartNames.get(index);
            } else{
                answers[i] = newCartNames.get(i);
            }
        }
        btn1.setText(answers[0]);
        btn2.setText(answers[1]);
        btn3.setText(answers[2]);
        btn4.setText(answers[3]);
        btnRestart.setVisibility(View.GONE);
    }

    public void answerSelected(View view) {
        String answer = ((Button) view).getText().toString();
        if(answer.equals(cartNames.get(index))){
            points++;
            tvPoints.setText(points + "/7");
        }
        index++;
        if(index > cartNames.size() - 1){
            ivShowImage.setVisibility(View.GONE);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            btnRestart.setVisibility(View.VISIBLE);
        } else{
            generateQuestions(index);
        }
    }

    public void restart(View view) {
        if(index > 6){
            index = 0;
            points = 0;
            ivShowImage.setVisibility(View.VISIBLE);
            btn1.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
            btn3.setVisibility(View.VISIBLE);
            btn4.setVisibility(View.VISIBLE);
            tvPoints.setText(points + "/7");
            Collections.shuffle(cartNames);
        }
        generateQuestions(index);
    }
}