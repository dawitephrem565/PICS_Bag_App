package com.filenber.pics_bag;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.filenber.pics_bag.TabAdapter.TabAdapter;

public class Pro_Tab extends  AppCompatActivity {
  private TabAdapter adapter;
  private TabLayout tabLayout;
  private ViewPager viewPager;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pro__tab);
    viewPager = (ViewPager) findViewById(R.id.view_pager);
    tabLayout = (TabLayout) findViewById(R.id.tabs);
    adapter = new TabAdapter(getSupportFragmentManager());
 //   adapter.addFragment(new MyProduct_Fragment(), "My Product");
  //  adapter.addFragment(new Pinned_Fragment(), "Pinned");

    viewPager.setAdapter(adapter);
    tabLayout.setupWithViewPager(viewPager);
  }
}

