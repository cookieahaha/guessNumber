package me.kukkii.guessNumber;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;

public class GuessNumber extends Activity{

  private int answer = -1;
  private int guess = -1;
  private int result = -1;
  private int time = 0;

  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    newAnswer();
  }

  public void newAnswer(){
    answer = (int) (Math.random()*1000)+1;
    TextView textView = (TextView) findViewById(R.id.text);
    textView.setText("guess my number from 1 to 1000");
  }

  public void guess(View view){
    EditText editText = (EditText) findViewById(R.id.guess);
    guess = Integer.parseInt(editText.getText().toString());
    compare(guess, answer); 
  }

  public void compare(int guess, int answer){
    if(guess == answer){
      result = 0;
    }
    else if(guess > answer){
      result = 1;
      time += 1;
    }
    else{
      result = 2;
      time += 1;
    }
    showResult(result, time);
  }

  public void showResult(int result, int time){
    TextView textView = (TextView) findViewById(R.id.text);
    if(result == 0){
      textView.setText("win! \n you tried " + time + " times");
      time = 0;
      newAnswer();
    }
    if(result == 1){
      textView.setText(guess + " is too big, \n u've tried " + time + " times");
    }
    if(result == 2){
      textView.setText(guess + " is too small, \n u've tried " + time + " times");
    }
  }

}
