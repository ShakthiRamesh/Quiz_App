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

public class MainActivity4 extends AppCompatActivity {

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
        cartNames.add("Car");
        cartNames.add("Bike");
        cartNames.add("Cycle");
        cartNames.add("Aeroplane");
        cartNames.add("Bus");
        cartNames.add("Train");
        cartNames.add("Helicopter");
        index = 0;
        map.put(cartNames.get(0), "https://www.kindpng.com/picc/m/7-78945_car-cartoon-icon-png-transparent-png.png");
        map.put(cartNames.get(1), "https://www.pngitem.com/pimgs/m/97-970661_clip-art-cartoon-dirt-bike-cartoon-dirt-bike.png");
        map.put(cartNames.get(2), "https://www.nicepng.com/png/detail/6-63713_cartoon-bicycle-cartoon-picture-of-bicycle.png");
        map.put(cartNames.get(3), "https://toppng.com/uploads/preview/rodigy-undiscovered-airoplane-cartoon-aeroplane-11563433132nosqgixpoh.png");
        map.put(cartNames.get(4), "https://www.kindpng.com/picc/m/356-3565361_tour-bus-cartoon-png-transparent-png.png");
        map.put(cartNames.get(5), "https://img.lovepik.com/free-png/20210926/lovepik-cartoon-green-leather-train-png-image_401480306_wh1200.png");
        map.put(cartNames.get(6), "https://www.pngitem.com/pimgs/m/109-1091720_helicopter-png-image-cartoon-military-helicopter-png-transparent.png");
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
        for (int i = 0; i < 4; i++) {
            if (i == correctAnswerPosition) {
                answers[i] = cartNames.get(index);
            } else {
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
        if (answer.equals(cartNames.get(index))) {
            points++;
            tvPoints.setText(points + "/7");
        }
        index++;
        if (index > cartNames.size() - 1) {
            ivShowImage.setVisibility(View.GONE);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            btnRestart.setVisibility(View.VISIBLE);
        } else {
            generateQuestions(index);
        }
    }

    public void restart(View view) {
        if (index > 6) {
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