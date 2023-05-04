package sg.edu.rp.c346.id22035357.billplease;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView tvDisplay1;
    TextView tvDisplay2;
    Button splitButton;
    Button resetButton;
    EditText amountInput;
    EditText paxInput;
    EditText discountInput;
    RadioGroup rgGroup;
    RadioButton rgButtonpaynow;
    ToggleButton svsButton;
    ToggleButton gstButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountInput = findViewById(R.id.amountId);
        paxInput = findViewById(R.id.paxId);
        discountInput = findViewById(R.id.DiscountId);
        svsButton  = findViewById(R.id.svsButton);
        gstButton  = findViewById(R.id.GstButton);
        rgGroup = findViewById(R.id.PaymentOption);
        rgButtonpaynow = findViewById(R.id.PaynowOption);
        splitButton = findViewById(R.id.SplitOption);
        resetButton = findViewById(R.id.ResetOption);
        tvDisplay1 = findViewById(R.id.Display);
        tvDisplay2 = findViewById(R.id.Display1);

        splitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String paxAmt = paxInput.getText().toString();
                double doublePax = Double.parseDouble(paxAmt);
                String strAmt = amountInput.getText().toString();
                double doubleAmt = Double.parseDouble(strAmt);
                String strDis = discountInput.getText().toString();
                double doubleDis = Double.parseDouble(strDis);
                double SVS = 1.10;
                double GST = 1.08;
                Double total = 0.0;
                String ph = "8786836";
                if (svsButton.isChecked()&gstButton.isChecked()){
                    total = doubleAmt*(1-doubleDis/100)*SVS*GST;
                }else if (gstButton.isChecked()) {
                    total = doubleAmt*(1-doubleDis/100)*GST;
                }else if (svsButton.isChecked()){
                    total = doubleAmt*(1-doubleDis/100)*SVS;
                }else{
                    total = doubleAmt*(1-doubleDis/100);
                }
                double each = total/doublePax;
                String printTotal = String.format("%.2f",total);
                String printEach = String.format("%.2f",each);
                tvDisplay1.setText("Total Bill: $" + printTotal);
                if (rgButtonpaynow.isChecked()){
                    tvDisplay2.setText("Each pays: $" + printEach + " to " + ph);
                }else{
                    tvDisplay2.setText("Each PayNow: $" + printEach + " in cash");
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountInput.setText("");
                paxInput.setText("");
                svsButton.setChecked(false);
                gstButton.setChecked(false);
                discountInput.setText("");
                rgGroup.clearCheck();
            }
        });




    }
}