package pl.axxxon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Pattern;

public class MainActivity extends Activity {
    private final String APP_TAG = "RegexpTester";
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        bindActions();
    }

    private void bindActions(){
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
    }

    public void clickClear(){

    }

    public void runTest(){
        EditText regexp = (EditText) findViewById(R.id.edit1);
        EditText test = (EditText) findViewById(R.id.edit2);
        String expression = regexp.getText().toString();
        String testString = test.getText().toString();
        Pattern pattern = Pattern.compile(expression);
        boolean matches = pattern.matcher(testString).matches();

    }
}
