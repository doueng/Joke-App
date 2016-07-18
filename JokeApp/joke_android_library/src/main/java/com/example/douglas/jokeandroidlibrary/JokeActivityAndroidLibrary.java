package com.example.douglas.jokeandroidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by douglas on 22/05/2016.
 */
public class JokeActivityAndroidLibrary extends ActionBarActivity {

    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_android);

        Intent intent = getIntent();
        String joke = intent.getStringExtra("joke");
        textView = (TextView) this.findViewById(R.id.textview_joke_android);
        if (textView != null) {
            textView.setText(joke);
        }



    }

}
