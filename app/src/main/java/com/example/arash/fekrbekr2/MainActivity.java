package com.example.arash.fekrbekr2;

    import android.graphics.Color;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.ProgressBar;
    import android.widget.TextView;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Objects;
    import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String BLUE = "#2161c6";
    private static final String YELLOW = "#ffee02";
    private static final String GREEN = "#13a021";
    private static final String RED = "#d82039";
    private static final String PURPLE = "#8a2be2";
    private static final String PINK = "#ffb6c1" ;
    
    // bc => Button Computer
    // bu => Button User
    Button bc1 , bc2 , bc3 , bc4 ;
    Button bu1 , bu2 , bu3 , bu4 ;

    Random r = new Random();

    Button playBtn;
    ProgressBar prg;
    List<String>DefaultColors;
    TextView stepsTxtView;
    String stepsTxt;
    int stepsCount;
    TextView successTxtView;

    // Set View Outlets
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bc1 = (Button)findViewById(R.id.button3);
        bc2 = (Button)findViewById(R.id.button4);
        bc3 = (Button)findViewById(R.id.button5);
        bc4 = (Button)findViewById(R.id.button6);
        bu1 = (Button)findViewById(R.id.button7);
        bu2 = (Button)findViewById(R.id.button8);
        bu3 = (Button)findViewById(R.id.button9);
        bu4 = (Button)findViewById(R.id.button10);
        prg = (ProgressBar)findViewById(R.id.progressBar);
        stepsTxtView = (TextView)findViewById(R.id.textView);
        stepsTxt = (String) stepsTxtView.getText();
        stepsCount = Integer.parseInt(stepsTxt);
        playBtn = (Button)findViewById(R.id.playbtn);
        successTxtView =(TextView)findViewById(R.id.tbrText);
        successTxtView.setVisibility(View.INVISIBLE);
        DefaultColors = new ArrayList<>();
        RandomizeColors();
    }

    public void RandomizeColors()
    {
        for (int i = 0; i < 4; i++)
        {
            // Can you explain below 4 lines of code? @arash
            Random r = new Random();
            int colorRand = r.nextInt(6 - 0);
            String color;
            color = BLUE;
            
            switch (colorRand)
            {
                case 0:
                    color = BLUE;
                    break;

                case 1:
                    color = YELLOW;
                    break;

                case 2:
                    color = GREEN;
                    break;
                case 3:
                    color = RED;
                    break;
                case 4:
                    color = PURPLE;
                    break;
                case 5:
                    color = PINK;
                    break;
            }

            switch (i)
            {
                case 0:
                    bc1.setBackgroundColor(Color.parseColor(color));
                    break;

                case 1:
                    bc2.setBackgroundColor(Color.parseColor(color));
                    break;

                case 2:
                    bc3.setBackgroundColor(Color.parseColor(color));
                    break;

                case 3:
                    bc4.setBackgroundColor(Color.parseColor(color));
                    break;
            }
        }
    }

    public void playBtn(View v)
    {
        stepsCount++;
        stepsTxt = String.valueOf(stepsCount);
        stepsTxtView.setText(stepsTxt);
    }
}
