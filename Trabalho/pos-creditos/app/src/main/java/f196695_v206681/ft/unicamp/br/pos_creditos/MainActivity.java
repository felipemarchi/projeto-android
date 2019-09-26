package f196695_v206681.ft.unicamp.br.pos_creditos;

import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HomeFragment homeFragment = new HomeFragment();
            fragmentTransaction.add(R.id.frame, homeFragment, "home");
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sobre) {
            Fragment sobreFragment = fragmentManager.findFragmentByTag("sobre");
            if (sobreFragment == null) {
                sobreFragment = new SobreFragment();
            }
            ReplaceFragment(sobreFragment,"sobre");
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Fragment homeFragment = fragmentManager.findFragmentByTag("home");
            if (homeFragment == null) {
                homeFragment = new HomeFragment();
            }
            ReplaceFragment(homeFragment,"home");
        } else if (id == R.id.nav_avaliar) {
            Fragment avaliarFragment = fragmentManager.findFragmentByTag("avaliar");
            if (avaliarFragment == null) {
                avaliarFragment = new AvaliarFragment();
            }
            ReplaceFragment(avaliarFragment,"avaliar");
        } else if (id == R.id.nav_consultar) {
            Fragment consultarFragment = fragmentManager.findFragmentByTag("consultar");
            if (consultarFragment == null) {
                consultarFragment = new ConsultarFragment();
            }
            ReplaceFragment(consultarFragment,"consultar");
        } else if (id == R.id.nav_autores) {
            Fragment autoresFragment = fragmentManager.findFragmentByTag("autores");
            if (autoresFragment == null) {
                autoresFragment = new AutoresFragment();
            }
            ReplaceFragment(autoresFragment,"autores");
        } else if (id == R.id.nav_contato) {
            Fragment contatoFragment = fragmentManager.findFragmentByTag("contato");
            if (contatoFragment == null) {
                contatoFragment = new ContatoFragment();
            }
            ReplaceFragment(contatoFragment,"contato");
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void ReplaceFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
