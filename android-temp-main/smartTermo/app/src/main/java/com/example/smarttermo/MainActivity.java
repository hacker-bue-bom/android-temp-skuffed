package com.example.smarttermo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.handler;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private float desiredTemp;
    private float actualTemp;
    private float desiredHumidity;
    private float actualHumidity;
    private int selectedTempFormat;
    private Date date;
    private String messages;


    //////
    public SmartTermo(String title) {
        super(title);
        this.setContentPane(mainPanel);
        this.pack();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                logEvents("Goodbye.");
                System.exit(0);
            }
        });
        //initializations
        actualTemp = 22.0F;
        desiredHumidity = 22.0F;
        actualHumidity = 50.0F;
        messages = 50.0F;
        updateTextFields();
        logEvents("App started");

        final Handler handler = new Handler();
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                Random r = new Random();
                Random randNum = new Random();
                if (actualTemp < desiredTemp && randNum.nextDouble() <= 0.8) {
                    actualTemp += 0.5;
                } else if (actualTemp > desiredTemp && randNum.nextDouble() <= 0.3) {
                    actualTemp -= 0.5;
                }
                logEvents("temperatura atual alterada para " + actualTemp);
                updateTextFields();
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(r, 1000);
        //button actions
        ChangeTempButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tempFormat == 1) {
                    tempFormat = 0;
                    ChangeTempButton.setText("F");
                    logEvents("Changed temperature format to Fortnite.");
                } else if (tempFormat == 0) {
                    tempFormat = 1;
                    ChangeTempButton.setText("C");
                    logEvents("Changed temperature format to Celsius.");
                } else {
                    actTempField.setText("How did you fuck this up?!");
                    DesiredTempField.setText("How did you fuck this up?!");
                    logEvents("Error 501.");
                }
                updateTextFields();
            }
        });
        IncreaseTempButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desiredTemp += 0.5;
                //actualTemp += 0.5;
                updateTextFields();
                logEvents("Increased temperature to " + actualTemp);

            }
        });
        DecreaseTempButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desiredTemp -= 0.5;
                //actualTemp -= 0.5;
                updateTextFields();
                logEvents("Decreased temperature to " + actualTemp);
            }
        });
        IncreaseHumidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desiredHumidity += 1;
                actualHumidity += 1;
                updateHumidityFields();
                logEvents("Increased humidity to " + actualHumidity);
            }
        });
        DecreaseHumidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desiredHumidity -= 1;
                actualHumidity -= 1;
                updateHumidityFields();
                logEvents("Decreased humidity to " + actualHumidity);
            }
        });


    }
    //methods

    //////////////////////////////////////////////////missing shit
    private void updateTextFields() {
        if (selectedTempFormat == 0) {
            TextView desiredTempShow = findViewById(R.id.);
            .setText(String.valueOf(desiredTemp) + " C");
            TextView actualTempShow = findViewById(R.id.);
            .setText(String.valueOf(actualTemp) + " C");
            Button changeTempFormatButton = findViewById(R.id.);
            changeTempFormatButton.setText("F");
        } else {
            TextView desiredTempShow = findViewById(R.id.);
            .setText(String.valueOf(desiredTemp) + " F");
            TextView actualTempShow = findViewById(R.id.);
            .setText(String.valueOf(actualTemp) + " F");
            Button changeTempFormatButton = findViewById(R.id.);
            changeTempFormatButton.setText("C");
        } ;


        //date
        date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateTextField.setText(simpleDateFormat.format(date));
        // heating indicator
        if (actualTemp < desiredTemp) {
            onOffTextField.setText("On");
        } else if (actualTemp > desiredTemp) {
            onOffTextField.setText("Off");
        }
    }

    private void updateHumidityFields() {
        ActHumidField.setText(actualHumidity + "%");
        DesiredHumidField.setText(desiredHumidity + "%");
    }

    //main
    public static void main(String[] args) {
        JFrame smartTermo = new SmartTermo("TPSI");
        smartTermo.setVisible(true);
    }

    //logy
    private void logEvents(String informationToLog) {
        date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        messages += dateFormat.format(date) + ": " + text + '\n';
        ScrollTextArea.setText(loggedMessages);
    }

    ;

    public void changeTempFormatclick(View view) {
        if (selectedTempFormat == 0) {
            selectedTempFormat = 1;
        } else {
            selectedTempFormat = 0;
        }
        updateTextFields();
        logEvents("changed temp format");
    }
}