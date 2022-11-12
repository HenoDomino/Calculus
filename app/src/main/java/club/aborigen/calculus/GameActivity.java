package club.aborigen.calculus;

import static club.aborigen.calculus.Constants.KEY_ERRORS;
import static club.aborigen.calculus.Constants.KEY_TIME;
import static club.aborigen.calculus.Constants.NUMBER_COUNT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;

public class GameActivity extends AppCompatActivity {
    private long startMs = System.currentTimeMillis();
    private int errors = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        TextView currentSumView = findViewById(R.id.current_sum);
        TextView nextNumberView = findViewById(R.id.next_number);
        EditText userInputView = findViewById(R.id.user_input);

        Queue<Integer> numbers = new LinkedList<>();
        for(int i = 0; i < NUMBER_COUNT; i++) {
            double nn = Math.random() * 89 + 10;
            numbers.add((int)nn);
        }

        nextNumberView.setText(numbers.poll().toString());
        userInputView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int action, KeyEvent keyEvent) {
//                if(action==)
                Integer currentSum = Integer.parseInt(currentSumView.getText().toString());
                Integer nextNumber = Integer.parseInt(nextNumberView.getText().toString());
                Integer userInput = Integer.parseInt(userInputView.getText().toString());

                if(userInput == currentSum + nextNumber) {
                    currentSumView.setText(String.valueOf(currentSum + nextNumber));
                    userInputView.setTextColor(Color.WHITE);
                    if ((nextNumber = numbers.poll()) != null) {
                        nextNumberView.setText(String.valueOf(nextNumber));
                        userInputView.setText("");
                    } else {
                        Intent in = new Intent(GameActivity.this, ResultActivity.class);
                        in.putExtra(KEY_ERRORS, errors);
                        in.putExtra(KEY_TIME, (System.currentTimeMillis() - startMs)/1000);
                        startActivity(in);
                        finish();
                    }
                } else {
                    userInputView.setTextColor(Color.RED);
                    errors++;
                }
                return false;
            }
        });
    }
}