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
import java.util.regex.PatternSyntaxException;

public class MainActivity extends Activity {
    private static final String APP_TAG = "RegexpTester";
    private static final String PRE_MATCHES = "Found %d matches,\n";
    private static final String MATCH_TEMPLATE = "%s - from %d to %d\n";
    private EditText mRegexp = null;
    private EditText mTest = null;
    private EditText mResult = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.extra);
        Log.d(APP_TAG, "Started");

        mRegexp = (EditText) findViewById(R.id.edit1);
        mTest = (EditText) findViewById(R.id.edit2);
        mResult = (EditText) findViewById(R.id.results);
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

                try {
                    runTest();
                } catch (Exception e) {
                    mResult.setText("Wrong regexp", TextView.BufferType.NORMAL);
                    Log.e(APP_TAG, e.getMessage());
                }
            }
        });
        Log.d(APP_TAG, "Binded");
    }

    public void clickClear() {
        mRegexp.setText("", TextView.BufferType.NORMAL);
        mTest.setText("", TextView.BufferType.NORMAL);
        mResult.setText("", TextView.BufferType.NORMAL);
        Log.d(APP_TAG, "Cleared");
    }

    public void runTest() throws Exception {
        Log.d(APP_TAG, "runned");
        String expression = mRegexp.getText().toString();
        String testString = mTest.getText().toString();
        Pattern pattern = null;
        try {
            pattern = Pattern.compile(expression);
        } catch (PatternSyntaxException e) {
            mResult.setText("Wrong regexp", TextView.BufferType.NORMAL);
        }
        Matcher matcher = pattern.matcher(testString);

        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (matcher.find()) {
            sb.append(String.format(MATCH_TEMPLATE, matcher.group(), matcher.start(), matcher.end()));
            count++;
        }

        String resultString = String.format(PRE_MATCHES, count);
        resultString += sb.toString();
        mResult.setText(resultString, TextView.BufferType.NORMAL);

    }
}
