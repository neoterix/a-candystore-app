package digital.anthonynguyen.candycoded;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    // Allows us to use the Candy's array throughout the entire class, by declaring it here
    // it's possible we also need to remove Candy[] from line ~146
    private Candy[] candies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Setting the text box here
        TextView textView = (TextView)this.findViewById(R.id.text_view_title);
        textView.setText(R.string.products_title_2);

//        This is where the list is built

//        candy_list is the actual list data, it is an ArrayList
        final ArrayList<String> candy_list = new ArrayList<String>();

        candy_list.add("Loading Candies...");
//        Removed all hardcoded candies so to validate that app is pulling from online API

//        The ArrayAdapter named "adapter" allows feeding in list items dynamically so that the memory isn't huge
//        This is "creating an instance of the ArrayAdapter class called 'adapter'"
//        "new" is a "keyword"
//        We are passing "this" and the other layouts AND the data candy_list to the "ArrayAdapter() CONSTRUCTOR"
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, // this Activity
                R.layout.list_item_candy,  // overall list layout?
                R.id.text_view_candy,  // individual list item layout, from within list_item_candy
                candy_list // list data
        );

//        Finally the ListView itself (like the TextView) that pulls the ListView into the Activity
//        Pull in the list view from activity_main.xml like TextView example
//        Here we are "finding" the ListView from the XML by its ID and saving it to a ListView variable called "listView"
        ListView listView = (ListView)this.findViewById(R.id.list_view_candy);

//        And then attach the adapter to the listview
//        setAdapter is a METHOD
        listView.setAdapter(adapter);

//        After connecting the ListVIew and Adapter
        Context context = this; //we can use "this" since we're in the MainActivity class
        String text = "Hello toast!!11!";
        int duration = Toast.LENGTH_SHORT;

//        Create a Toast with the Toast.makeText() method.
//        Remember this method takes 3 arguments: the context, a String to display, and the duration.

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

//        Event listener section
//        The setOnItemClickListener METHOD accepts one ARGUMENT of type "clicklistener" so we'll create that with
//        the "new" keywoard

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                To create an intent, we use the new keyword with the Intent constructor, and pass it the context
//                of where we are creating this intent which is inside this MainActivity class, and the component
//                we want to create, which is the DetailActivity class.
                Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);

//                Use the putExtra method to insert data into the new class, of which the arguments are a key/value pair
//                For the value we go back to the original ArrayList and get the position
//                ***Also modifying in Section 2.9 to remove because candy_name code below

//                detailIntent.putExtra("candy_name", candy_list.get(i));

//                Modifying in section 2.9 to pass all API data about candies
                detailIntent.putExtra("candy_name", candies[i].name);
                detailIntent.putExtra("candy_image", candies[i].image);
                detailIntent.putExtra("candy_price", candies[i].price);
                detailIntent.putExtra("candy_desc", candies[i].description);


//                Then the MainActivity can call another activity using the startActivity() METHOD, which takes
//                our intent as a PARAMETER.
                startActivity(detailIntent);
            }
        });


        //        Adding http request capability by creating an AsyncHttpClient OBJECT
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://go.codeschool.com/CandyAPI", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("AsyncHttpClient", "response = " + responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("AsyncHttpClient", "response = " + responseString);

//                Create a Gson VARIABLE named "gson", and assign it to the result of calling create()
//                on a new GsonBuilder() OBJECT using the EMPTY CONSTRUCTOR
                Gson gson = new GsonBuilder().create();

//                Create an ARRAY of Candy OBJECTS called candies by using the gson object and its
//                fromJson() METHOD. fromJson() takes the JSON response string from ^^^
//                Removed Candy[] from this line... section 2.7 - is it because it made the array of candy
//                objects "candies" a CLASS VARIABLE accessible throughout the MainActivity class?

                candies = gson.fromJson(responseString, Candy[].class);
//                Clearing the adapter... not sure why Android Studio not picking up "adapter"
                adapter.clear();
//                A for loop over the candies array that adds each Candy's name to the adapter
//                For each object of the class Candy, we're calling this "candy", within the array of "candies"
//                add each the name of each object "candy" to the adapter which is set above
                for(Candy candy : candies) {
                    adapter.add(candy.name);
                }

            }
        });


    }
}
