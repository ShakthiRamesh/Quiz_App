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

public class MainActivity2 extends AppCompatActivity {

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
        cartNames.add("Watermelon");
        cartNames.add("Pineapple");
        cartNames.add("Grapes");
        cartNames.add("Strawberru");
        cartNames.add("BlueBerry");
        cartNames.add("Kiwi");
        cartNames.add("");
        index = 0;
        map.put(cartNames.get(0), "https://w7.pngwing.com/pngs/871/532/png-transparent-watermelon-watermelon-natural-foods-food-melon-thumbnail.png");
        map.put(cartNames.get(1), "https://w7.pngwing.com/pngs/734/792/png-transparent-juice-pineapple-smoothie-fruit-vegetable-cut-pineapple-pineapple-fruit-natural-foods-food-grape-thumbnail.png");
        map.put(cartNames.get(2), "https://www.pngitem.com/pimgs/m/113-1138805_currant-green-grapes-pngs-transparent-png-download.png");
        map.put(cartNames.get(3), "https://clipartix.com/wp-content/uploads/2017/11/Strawberry-clip-art-free-clipart-images-3-clipartpost.png");
        map.put(cartNames.get(4), "https://pngimg.com/uploads/blueberries/blueberries_PNG5.png");
        map.put(cartNames.get(5), "https://e7.pngegg.com/pngimages/432/514/png-clipart-kiwifruit-kiwi-cartoon-miscellaneous-food.png");
        map.put(cartNames.get(6), "https://e7.pngegg.com/pngimages/1001/506/png-clipart-slices-of-oranges-orange-juice-flavor-fruit-nutritious-orange-natural-foods-food.png");
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
