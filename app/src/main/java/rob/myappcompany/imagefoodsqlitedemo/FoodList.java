package rob.myappcompany.imagefoodsqlitedemo;

import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FoodList extends AppCompatActivity {
    GridView gridView;
    ArrayList<Food> list;
    FoodListAdapter adapter = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.food_list_activity);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new FoodListAdapter(this, R.layout.food_item, list);
        gridView.setAdapter(adapter);

        //get All data from sqlite
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM FOOD");
        list.clear();
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new Food(id, name, price, image));

        }
        adapter.notifyDataSetChanged();
    }
}
