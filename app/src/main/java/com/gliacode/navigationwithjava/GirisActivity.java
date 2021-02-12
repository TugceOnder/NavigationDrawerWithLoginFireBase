package com.gliacode.navigationwithjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class GirisActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

//        if(firebaseAuth.getCurrentUser()!=null){
        //    startActivity(new Intent(getApplicationContext(),MainActivity.class));
     //       finish(); }

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new BlankFragment()).commit();

        }

      //  public void onBackPressed(){
         //   if(drawer.isDrawerOpen(GravityCompat.START)){
               // drawer.closeDrawer(GravityCompat.START);
           // }
          //  else {
            //    super.onBackPressed();
        //    }}


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;

            case R.id.nav_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MessageFragment()).commit();
                break;

            case R.id.nav_person:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PersonFragment()).commit();
                break;
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}