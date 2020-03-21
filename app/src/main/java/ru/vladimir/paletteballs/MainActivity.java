package ru.vladimir.paletteballs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.vladimir.paletteballs.views.Playground;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Playground(this));
    }
}
