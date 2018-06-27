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

// Getting the View SeekBar and assigning the seekBarListener
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
                // Do stuff with the SeekBar
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
## GUI Keyboard
The GUI keyboard was built using Android widget buttons. In order to capture the values they hold 
when the user presses the button an `onClick()` listener had to be assigned to each button. There
are two ways to approach this, either hardcode each button or dynamically cycle through each child
View of the Layout ViewGroup and assign the onClick() listener to each one. For this implementation 
the latter option was chosen.  

```Java
// Other important imports
import android.widget.Button;
import android.view.View.OnClickListener;
import java.util.ArrayList;

// Getting buttons from the main Layout and adding the onClick listener.
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // [...]
        private ArrayList<Button> buttons = new ArrayList<Button>();
        
        for (int i = 0; i < mainView.getChildCount(); i++) {
                    if (mainView.getChildAt(i) instanceof Button) {
                        buttons.add((Button) mainView.getChildAt(i));
                    }
                }
        
        for (Button button : buttons) {
            button.setOnClickListener(buttonPressed);
        }
    }
    
    // [..]
    
    // Anonymous inner class
    private final OnClickListener buttonPressed = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Button b = (Button) view;
            // Do stuff with the button
        }
    };
}
```

## User amount validation.
The user amount was validated using regex to make sure that the input had no more than a single 
`"."` and was a maximum of 2 decimal places. 

`userAmountInput.toString().matches("(\\d*)(\\.?)(\\d{0,2})")` 
