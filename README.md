# Android Tip Calculator

This is a simple tip calculator app built for Android. The app is built with Java and uses the 
native API.

<img 
    src="https://github.com/nadershamma/android-tip-calculator/blob/master/misc/android_tip_calc_app_1.png?raw=true" 
    width="300px" height="auto" alt="Android Tip Calculator App Screenshot" align="center" />
    
## Main feature

The app allows the user to input the amount owing on the bill and select how much they want to tip
as a percentage of the bill.

<img 
    src="https://github.com/nadershamma/android-tip-calculator/blob/master/misc/android_tip_calc_demo_1.gif?raw=true" 
    width="300px" height="auto" alt="Android Tip Calculator App Demo" align="center" />

### Summary of features:

    - A SeekBar which allows the user to choose the tip percenatge
    - A GUI keyboard replacing the Android keyboard*
    - Realtime update of the totals
    - Input validation
 
_*I could have simply used an EditText box which would have presented the inbuilt soft keyboard but 
where is the fun in that?_

## Implementation
### Layout GUI

The layout uses the ConstraintLayout to create a responsive layout. Within the ViewGroup there are:

    - A TextView to present the Amount 
    - A SeekBar to allow the user to set the percentage of the tip the want to pay between 0 and 30%   
    - TextViews for the row labels and total amount containers
    - Buttons for the GUI keyboard
    - Guidelines to hold everything together
  
## Handling the SeekBar Event

In order to handle the SeekBar change event an anonymous inner class which implements the 
OnSeekBarChangeListener interface was created and set to the app SeekBar.

```Java
// Other important imports
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

// Getting the View Seekbar and assigning the seekBarListener
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // [...]
        
        SeekBar percentSeekBar = findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);
    }
    
    // [..]
    
    // Anonymous inner class
    private final OnSeekBarChangeListener seekBarListener = new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                percent = progress / 100.0;
                calculateBill();
            }
    
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
    
            }
    
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
    
            }
    };
}
```
  


