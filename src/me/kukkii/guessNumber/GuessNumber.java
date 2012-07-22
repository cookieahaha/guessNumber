package me.kukkii.guessNumber;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;

public class GuessNumber extends Activity{

  // private int MAX_NUM = 1000;
  private int MAX_NUM = 100;

  private int answer = -1;
  private int guess = -1;
  private int result = -1;
  private int time = 0;
  private String resultText = "";

  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    newAnswer();
  }

  public void newAnswer(){
    time = 0;
    runOnUiThread(new Runnable() {
      public void run() {
        answer = (int) (Math.random()*MAX_NUM)+1;
        TextView textView = (TextView) findViewById(R.id.text);
        resultText = "Guess my number from 1 to " + MAX_NUM  + "\n";
        textView.setText(resultText);

        EditText editText = (EditText) findViewById(R.id.guess);
        editText.setText("");
      }} );
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
      resultText += ("You win!\nYou tried " + time + " times.\n");
      textView.setText(resultText);

      new Thread() {
        public void run() {
          try {
            Thread.sleep(5000);
          }
          catch (InterruptedException e) {
          }
          newAnswer();
        }
      }.start();
      return;
    }
    else if(result == 1){
      resultText += (guess + " is too big. You haveve tried " + time + " times.\n");
    }
    else if(result == 2){
      resultText += (guess + " is too small. You have tried " + time + " times.\n");
    }
    textView.setText(resultText);

    EditText editText = (EditText) findViewById(R.id.guess);
    editText.setText("");
  }

  public void numberEntered(View view) {
    EditText editText = (EditText) findViewById(R.id.guess);
    String text = editText.getText().toString();
    int viewId = view.getId();
    switch (viewId) {
    case R.id.button1 :
      text = text + "1";
      break;
    case R.id.button2 :
      text = text + "2";
      break;
    case R.id.button3 :
      text = text + "3";
      break;
    case R.id.button4 :
      text = text + "4";
      break;
    case R.id.button5 :
      text = text + "5";
      break;
    case R.id.button6 :
      text = text + "6";
      break;
    case R.id.button7 :
      text = text + "7";
      break;
    case R.id.button8 :
      text = text + "8";
      break;
    case R.id.button9 :
      text = text + "9";
      break;
    case R.id.button0 :
      text = text + "0";
      break;
    case R.id.button_clear :
      text = "";
      break;
    case R.id.button_enter :
      guess(null);
      return;
    }
    editText.setText(text);
  }

}
