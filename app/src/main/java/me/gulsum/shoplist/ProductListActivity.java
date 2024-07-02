package me.gulsum.shoplist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import me.gulsum.shoplist.Model.Item;

public class ProductListActivity extends AppCompatActivity {

    private ListView productListListView;
    private ArrayList<Item> itemList;
    private ArrayAdapter<Item> itemAdapter;
    private Button finishShoppingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        itemList = getIntent().getParcelableArrayListExtra("itemList");

        productListListView = findViewById(R.id.productListListView);
        itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        productListListView.setAdapter(itemAdapter);

        productListListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setPaintFlags(textView.getPaintFlags() | android.graphics.Paint.STRIKE_THRU_TEXT_FLAG);

                Item selectedItem = itemList.get(position);
                selectedItem.setCompleted(true);
                itemAdapter.notifyDataSetChanged();

                Toast.makeText(ProductListActivity.this, "Ürün satın alındı: " + selectedItem.getItemName(), Toast.LENGTH_SHORT).show();
            }
        });


        finishShoppingButton = new Button(this);
        finishShoppingButton.setText("Alışverişi Bitir");
        productListListView.addFooterView(finishShoppingButton);

        finishShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductListActivity.this, "Alışveriş tamamlandı!", Toast.LENGTH_SHORT).show();
                clearItemList();
                navigateToHome();
            }
        });
    }

    private void clearItemList() {
        itemList.clear();
        itemAdapter.notifyDataSetChanged();
    }

    private void navigateToHome() {
        Intent intent = new Intent(ProductListActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
