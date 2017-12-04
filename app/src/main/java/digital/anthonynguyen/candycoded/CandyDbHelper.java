package digital.anthonynguyen.candycoded;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 12/3/2017.
 */

public class CandyDbHelper extends SQLiteOpenHelper {
    public CandyDbHelper(Context context) {
        super(context, CandyContract.DB_NAME , null, CandyContract.DB_VERSION);
    }

//    Overriding the onCreate method with our own where we call the execSQL method on the db database
//    parameter that's passed in, and as an argument to this call we want to execute the
//    SQL_CREATE_ENTRIES statement we created earlier in the CandyContract class
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CandyContract.SQL_CREATE_ENTRIES);
    }

//    Overriding the onUpgrade method, executing the SQL command to delete the table which is the
//    SQL_DELETE_ENTRIES statement created in the RecipeContract class
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CandyContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }


    }
}
