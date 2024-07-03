package me.gulsum.shoplist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import me.gulsum.shoplist.Model.Item;

public class MainActivity extends AppCompatActivity {

    private EditText productEditText;
    private Button addButton;
    private Button viewListButton;
    private ListView shoppingItemsListView;
    private RadioButton radioButton;

    private ArrayList<Item> itemList;
    private ArrayAdapter<Item> itemAdapter;

    private void init() {
        productEditText = findViewById(R.id.edtext);
        addButton = findViewById(R.id.button);
        viewListButton = findViewById(R.id.button2);
        radioButton = findViewById(R.id.radiobutton);
        shoppingItemsListView = findViewById(R.id.shoppingItemsListView);

        itemList = new ArrayList<>();
        itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        shoppingItemsListView.setAdapter(itemAdapter);

        productEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && productEditText.getText().toString().equals("Ürün Adı Girin")) {
                    productEditText.setText("");
                }
            }
        });


        addButton.setOnClickListener(view -> addItem());

        viewListButton.setOnClickListener(view -> goToProduct());

        radioButton.setOnClickListener(view -> {
            boolean checked = ((RadioButton) view).isChecked();
            if (checked) {
                Toast.makeText(MainActivity.this, "öncelik seçildi", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void addItem() {
        String itemName = productEditText.getText().toString().trim();
        if (!itemName.isEmpty()) {
            Item item = new Item(itemName, radioButton.isChecked());
            itemList.add(item);
            itemAdapter.notifyDataSetChanged();
            productEditText.setText("");
            radioButton.setChecked(false);
        } else {
            Toast.makeText(MainActivity.this, "Lütfen bir ürün adı girin!", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToProduct() {
        Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
        intent.putParcelableArrayListExtra("itemList", itemList);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}
