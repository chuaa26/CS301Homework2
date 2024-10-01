// @author Aaron Chu
// @version/date Final Version 30 Sep 2024

package edu.up.cs301homework;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView selectedElementTextView;
    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    private Chess customSurfaceView;
    private DrawableElement currentElement;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the above private variables to the ids in the xml file activity_main
        selectedElementTextView = findViewById(R.id.selectedElementTextView);
        redSeekBar = findViewById(R.id.redSeekBar);
        greenSeekBar = findViewById(R.id.greenSeekBar);
        blueSeekBar = findViewById(R.id.blueSeekBar);
        customSurfaceView = findViewById(R.id.customSurfaceView);

        // Set listeners on SeekBars
        // Found "enum" and Listeners in java after google searches/Youtube
        // https://www.geeksforgeeks.org/event-handling-in-java/
        // https://stackoverflow.com/questions/31625962/what-is-the-purpose-of-a-listener-in-java
        setSeekBarListeners();
        };

    public void onElementSelected(DrawableElement element) {
        // Use the getName function from DrawableElement to retrieve the string of the selected element
        currentElement = element;
        selectedElementTextView.setText(element.getName());

        // Use the getColor function from DrawableElement to retrieve the color of the element
        int color = element.getColor();
        redSeekBar.setProgress(Color.red(color));
        greenSeekBar.setProgress(Color.green(color));
        blueSeekBar.setProgress(Color.blue(color));
    }

    private void setSeekBarListeners() {
        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (currentElement != null) {
                    // Retrieve the progress of the seek bar and set the Color to it
                    int newColor = Color.rgb(redSeekBar.getProgress(),
                            greenSeekBar.getProgress(),
                            blueSeekBar.getProgress());
                    currentElement.setColor(newColor);
                    // https://stackoverflow.com/questions/14676741/how-to-redraw-surfaceview-using-invalidate-metod
                    // This was the only way I could find to draw the view
                    customSurfaceView.invalidate();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        };
        redSeekBar.setOnSeekBarChangeListener(listener);
        greenSeekBar.setOnSeekBarChangeListener(listener);
        blueSeekBar.setOnSeekBarChangeListener(listener);
    }
    }
