package io.dojogeek.modifiablelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mName;
    private EditText mNameInput;
    private Button mSubmitName;

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            case R.id.submitName:
                processAction();
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        loadViews();

        setClickListenerToViews(this);

    }

    private void loadViews() {
        mName = (TextView) findViewById(R.id.name);
        mNameInput = (EditText) findViewById(R.id.nameInput);
        mSubmitName = (Button) findViewById(R.id.submitName);
    }

    private void setClickListenerToViews(View.OnClickListener listener) {
        mSubmitName.setOnClickListener(listener);
    }

    private void processAction() {
        String value = mNameInput.getText().toString();
        mName.setText(value);
    }

}
