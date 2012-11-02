package pl.axxxon.regexptester;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity {
    private static final String APP_TAG = "RegexpTester";
    private static final String PRE_MATCHES = "Found %d matches,\n";
    private static final String MATCH_TEMPLATE = "%s - from %d to %d\n";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.extra);
        Log.d(APP_TAG, "Started");

        bindActions();
    }

    private void bindActions() {
        View clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickClear();
            }
        });

        View search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runTest();
            }
        });
        Log.d(APP_TAG, "Binded");
    }

    public void clickClear() {
        EditText regexp = (EditText) findViewById(R.id.edit1);
        EditText test = (EditText) findViewById(R.id.edit2);
        EditText results = (EditText) findViewById(R.id.results);
        regexp.setText("", TextView.BufferType.NORMAL);
        test.setText("", TextView.BufferType.NORMAL);
        results.setText("", TextView.BufferType.NORMAL);
        Log.d(APP_TAG, "Cleared");
    }

    public void runTest() {
        Log.d(APP_TAG, "runned");
        EditText regexp = (EditText) findViewById(R.id.edit1);
        EditText test = (EditText) findViewById(R.id.edit2);
        String expression = regexp.getText().toString();
        String testString = test.getText().toString();
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(testString);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (matcher.find()) {
            sb.append(String.format(MATCH_TEMPLATE, matcher.group(), matcher.start(), matcher.end()));
            count++;
        }

        String resultString = String.format(PRE_MATCHES, count);
        resultString += sb.toString();
        EditText results = (EditText) findViewById(R.id.results);
        results.setText(resultString, TextView.BufferType.NORMAL);

    }
}
