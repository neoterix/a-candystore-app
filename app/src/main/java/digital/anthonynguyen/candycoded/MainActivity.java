package digital.anthonynguyen.candycoded;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

        candy_list.add("Tropical Wave");
        candy_list.add("Berry Bouncer");
        candy_list.add("Grape Gummer");
        candy_list.add("Apple of My Eye");
        candy_list.add("Much Minty");
        candy_list.add("So Fresh");
        candy_list.add("Sassy Sandwich Cookie");
        candy_list.add("Uni-pop");
        candy_list.add("Strawberry Surprise");
        candy_list.add("Wish Upon a Star");
        candy_list.add("Planetary Pops");
        candy_list.add("Watermelon Like Whoa");
        candy_list.add("Twist 'n' Shout");
        candy_list.add("Bear    y Squad Goals");
        candy_list.add("ROYGBIV Spinner");

//        The ArrayAdapter named "adapter" allows feeding in list items dynamically so that the memory isn't huge
//        This is "creating an instance of the ArrayAdapter class called 'adapter'"
//        "new" is a "keyword"
//        We are passing "this" and the other layouts AND the data candy_list to the "ArrayAdapter() CONSTRUCTOR"
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
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
                detailIntent.putExtra("candy_name", candy_list.get(i));

//                Then the MainActivity can call another activity using the startActivity() METHOD, which takes
//                our intent as a PARAMETER.
                startActivity(detailIntent);
            }
        });


    }
}
