package digital.anthonynguyen.candycoded;

import android.provider.BaseColumns;

/**
 * Created by User on 12/3/2017.
 */

public class CandyContract {
    public static final String DB_NAME = "candycoded.db";
    public static final int DB_VERSION = 1;

//    Used to create SQL table
    public static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " +
                CandyEntry.TABLE_NAME + " (" +
                CandyEntry._ID + " INTEGER PRIMARY KEY," +
                CandyEntry.COLUMN_NAME_NAME + " TEXT," +
                CandyEntry.COLUMN_NAME_PRICE + " TEXT," +
                CandyEntry.COLUMN_NAME_DESC + " TEXT," +
                CandyEntry.COLUMN_NAME_IMAGE + " TEXT)";

//    Used to delete SQL table. Adds a command to delete the Candy table by creating a public static
//    final String variable of the name SQL_DELETE_ENTRIES and setting it equal to a String that
//    delivers the SQL drop table command on the CandyEntry table
//    The space after DROP TABLE IF EXISTS is important!!!
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + CandyEntry.TABLE_NAME;

//    Inside of the CandyEntry class, we are defining column names by adding public static final
//    String variables, providing column names (in SQL style caps) and then setting them to the
//    candy attribute value given. We defined these before creating the SQL string creating SQL table
    public static class CandyEntry implements BaseColumns{
        public static final String TABLE_NAME = "candy";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_DESC = "description";
        public static final String COLUMN_NAME_IMAGE = "image";
    }
}
