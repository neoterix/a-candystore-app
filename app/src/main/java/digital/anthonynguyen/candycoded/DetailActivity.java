package digital.anthonynguyen.candycoded;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = DetailActivity.this.getIntent();
        String candyName = "";

//        The conditional checks to see if the Intent has the candy name
        if (intent.hasExtra("candy_name")) {
            candyName = intent.getStringExtra("candy_name");
        }

        TextView textView = (TextView) this.findViewById(R.id.text_view_name);
        textView.setText(candyName);

//        Getting the rest of the candy data in section 2.9
//        Creating an IF statement to check if the INTENT has an extra "candy_blahblah" value using the
//        hasExtra() method. Then uses the getStringExtra() method to get the value and set it equal
//        to the given VARIABLE

        String candyImage = "";
        if (intent.hasExtra("candy_image")) {
            candyImage = intent.getStringExtra("candy_image");
        }
        String candyPrice = "";
        if (intent.hasExtra("candy_price")) {
            candyPrice = intent.getStringExtra("candy_price");
        }
        String candyDesc = "";
        if (intent.hasExtra("candy_desc")) {
            candyDesc = intent.getStringExtra("candy_desc");
        }

//        Concatenate a comma and the String to the Log statement
        Log.d("DetailActivity", "Intent data: " + candyImage + ", " + candyPrice + ", " + candyDesc);
    }
}