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
    private EditText mRegexp;
    private EditText mTest;
    private EditText mResult;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.extra);
        mRegexp = (EditText) findViewById(R.id.edit1);
        mTest = (EditText) findViewById(R.id.edit2);
        mResult = (EditText) findViewById(R.id.results);

        Log.d(APP_TAG, "Started");

    }

    public void clickClear(View v) {
        mRegexp.setText("", TextView.BufferType.NORMAL);
        mTest.setText("", TextView.BufferType.NORMAL);
        mResult.setText("", TextView.BufferType.NORMAL);
        Log.d(APP_TAG, "Cleared");
    }

    public void runTest(View v) throws Exception {
        Log.d(APP_TAG, "runned");
        String expression = mRegexp.getText().toString();
        String testString = mTest.getText().toString();
        Pattern pattern = null;
        try {
            pattern = Pattern.compile(expression);
        } catch (PatternSyntaxException e) {
            mResult.setText("Wrong regexp", TextView.BufferType.NORMAL);
            return;
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
