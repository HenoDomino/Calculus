package club.aborigen.calculus;

import static club.aborigen.calculus.Constants.KEY_ERRORS;
import static club.aborigen.calculus.Constants.KEY_TIME;
import static club.aborigen.calculus.Constants.NUMBER_COUNT;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultErrors = findViewById(R.id.result_errors);
        TextView resultTime = findViewById(R.id.result_time);

        resultErrors.setText(getString(R.string.label_result_errors, getIntent().getIntExtra(KEY_ERRORS, 0), NUMBER_COUNT));
        resultTime.setText(getString(R.string.label_result_time, getIntent().getLongExtra(KEY_TIME, 0)));

        TextView closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}