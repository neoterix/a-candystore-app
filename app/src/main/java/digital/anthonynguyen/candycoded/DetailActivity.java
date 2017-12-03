package digital.anthonynguyen.candycoded;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

        TextView textViewName = (TextView) this.findViewById(R.id.text_view_name);
        textViewName.setText(candyName);

//        Getting the rest of the candy data in section 2.9
//        Creating an IF statement to check if the INTENT has an extra "candy_blahblah" value using the
//        hasExtra() method. Then uses the getStringExtra() method to get the value and set it equal
//        to the given VARIABLE

        String candyImage = "";
        if (intent.hasExtra("candy_image")) {
            candyImage = intent.getStringExtra("candy_image");
        }
        ImageView imageView = (ImageView)this.findViewById(R.id.image_view_candy);
//        Using Picasso to load and cache the image from the URL and display in imageView,
//        Using Picasso and the methods with(), load(), and into() with the PARAMETERS, this as the Context
//        candyImage as the URL--a good example of multiple methods on a class (?)
        Picasso.with(this).load(candyImage).into(imageView);

//        Find the TextView with ID text_view_price and save it to a TextView variable named textViewPrice
        String candyPrice = "";
        if (intent.hasExtra("candy_price")) {
            candyPrice = intent.getStringExtra("candy_price");
        }
        TextView textViewPrice = (TextView) this.findViewById(R.id.text_view_price);
        textViewPrice.setText(candyPrice);

//        Should there be a (intent != null && intent.hasExtra("candy_desc"))??? should it check for null?
        String candyDesc = "";
        if (intent.hasExtra("candy_desc")) {
            candyDesc = intent.getStringExtra("candy_desc");
        }
        TextView textViewDesc = (TextView) this.findViewById(R.id.text_view_desc);
        textViewDesc.setText(candyDesc);

//        Concatenate a comma and the String to the Log statement
        Log.d("DetailActivity", "Intent data: " + candyImage + ", " + candyPrice + ", " + candyDesc);
    }
}