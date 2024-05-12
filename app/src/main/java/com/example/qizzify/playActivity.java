package com.example.qizzify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class playActivity extends AppCompatActivity {
    String[] question_list = {"What is the capital of Italy?",
    "What is the longest river in the world?",
    "What does WHO stand for?",
    "What is the name of the day after Thanksgiving?",
    "How many American colonies declared independence in 1776?",
    "Which nations make up the United Kingdom?",
    "Which British celebrity chef hosted Hell’s Kitchen?",
    "Which artist painted the ceiling of the Sistine Chapel?",
    "Which Game of Thrones house does Daenerys belong to?",
    "Which UK city is Banksy from?"};

    String[] choose_list = {"Rome","Milan","Florence","Venice",
            "Amazon River","Nile River","Yangtze River","Mississippi River",
            "World Health Organization","World Humanitarian Organization","World Heritage Organization","World Housing Organization",
            "Cyber Monday","Black Friday","Small Business Saturday","Giving Tuesday",
            "7","9","13","15",
            "England, Wales, Scotland, and Northern Ireland","England, Scotland, Ireland, and Wales","England, Scotland, and Wales","England, Scotland, and Northern Ireland",
            "Jamie Oliver","Nigella Lawson","Gordon Ramsay","Heston Blumenthal",
            "Leonardo da Vinci","Raphael","Michelangelo","Donatello",
            "Stark","Lannister","Targaryen","Baratheon",
            "London","Manchester","Bristol","Liverpool"

    };
    String[] correct_list={"Rome",
    "Nile River",
    "World Health Organization",
    "Black Friday",
    "13",
    "England, Wales, Scotland, and Northern Ireland",
    "Gordon Ramsay",
    "Michelangelo",
    "Targaryen",
    "Bristol"};


    TextView cpt_question , text_question;
    Button btn_choose1 , btn_choose2 , btn_choose3 , btn_choose4 , btn_next;

    int currentQuestion = 0;
    boolean isclickBtn = false;
    String valueChoose = "";
    Button btn_click;
    int scorePlayer = 0;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        cpt_question = findViewById(R.id.cpt_question);
        text_question = findViewById(R.id.text_question);

        btn_choose1 = findViewById(R.id.btn_choose1);
        btn_choose2 = findViewById(R.id.btn_choose2);
        btn_choose3 = findViewById(R.id.btn_choose3);
        btn_choose4 = findViewById(R.id.btn_choose4);
        btn_next = findViewById(R.id.btn_next);

        findViewById(R.id.image_back).setOnClickListener(
                a-> finish()

        );
        displayData();

        btn_next.setOnClickListener(
                view -> {
                    if(isclickBtn){
                        isclickBtn = false;
                        if(!valueChoose.equals(correct_list[currentQuestion])){
                            Toast.makeText(playActivity.this , "Wrong answer😥",Toast.LENGTH_LONG).show();
                            btn_click.setBackgroundResource(R.drawable.background_btn_error);
                        }else{
                            Toast.makeText(playActivity.this , "Correct answer😃",Toast.LENGTH_LONG).show();
                            btn_click.setBackgroundResource(R.drawable.background_btn_correct);

                            scorePlayer++;
                        }
                        new Handler().postDelayed(() -> {
                            if(currentQuestion != question_list.length-1){
                                currentQuestion = currentQuestion + 1;
                                displayData();
                                valueChoose = "";
                                btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
                                btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
                                btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
                                btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);
                            }else {

                                finish();
                                dbHandler = new DBHandler(playActivity.this);
                                String score = String.valueOf(scorePlayer);
                                dbHandler.addNewScore(score);
                                dbHandler.close();
                                Intent intent = new Intent(playActivity.this, endActivity.class);
                                startActivity(intent);
                                Toast.makeText(playActivity.this , "Score saved🎉🎉🎉",Toast.LENGTH_LONG).show();

                            }

                        },2000);

                    }else{
                        Toast.makeText(playActivity.this , "Choose an answer⚠️",Toast.LENGTH_LONG).show();
                    }
                }
        );


    }

    void displayData(){
        cpt_question.setText((currentQuestion+1) + "/" + question_list.length);
        text_question.setText(question_list[currentQuestion]);

        btn_choose1.setText(choose_list[4 * currentQuestion]);
        btn_choose2.setText(choose_list[4 * currentQuestion+1]);
        btn_choose3.setText(choose_list[4 * currentQuestion+2]);
        btn_choose4.setText(choose_list[4 * currentQuestion+3]);

    }

    public void ClickChoose(View view) {
        btn_click = (Button) view;

        if (isclickBtn) {
            btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);
        }
        choiceBtn();
    }



    void choiceBtn(){

        btn_click.setBackgroundResource(R.drawable.background_btn_choose_color);
        isclickBtn = true;
        valueChoose = btn_click.getText().toString();
    }
}