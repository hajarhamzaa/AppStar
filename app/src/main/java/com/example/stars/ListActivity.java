package com.example.stars;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stars.adapter.StarAdapter;
import com.example.stars.beans.Star;
import com.example.stars.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private static final String TAG = "StarAdapter";

    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        starAdapter = new StarAdapter(this, service.finAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void init() {
        service.create(new Star("LeBron James", "https://upload.wikimedia.org/wikipedia/commons/7/7a/LeBron_James_%2851959977144%29_%28cropped2%29.jpg", 3.5f));
        service.create(new Star("Luka Dončić", "https://upload.wikimedia.org/wikipedia/commons/b/b2/Luka_Don%C4%8Di%C4%87_2021.jpg", 3));
        service.create(new Star("Shai Gilgeous-Alexander", "https://upload.wikimedia.org/wikipedia/commons/c/c6/Shai_Gilgeous-Alexander_-_Thunder_vs._Wizards.png", 5));
        service.create(new Star("Russell Westbrook", "https://upload.wikimedia.org/wikipedia/commons/b/be/Russell_Westbrook_%28March_21%2C_2022%29_%28cropped%29.jpg", 1));
        service.create(new Star("Chris Paul", "https://upload.wikimedia.org/wikipedia/commons/a/ad/Chris_Paul_%282022_All-Star_Weekend%29_%28cropped%29.jpg", 5));
        service.create(new Star("Klay Thompson", "https://upload.wikimedia.org/wikipedia/commons/1/10/Klay_Thompson_in_2015.jpg", 1));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null) {
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }

        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder.from(this).setType(mimeType).setChooserTitle("Stars").setText(txt).startChooser();
        }
        return super.onOptionsItemSelected(item);
    }
}
