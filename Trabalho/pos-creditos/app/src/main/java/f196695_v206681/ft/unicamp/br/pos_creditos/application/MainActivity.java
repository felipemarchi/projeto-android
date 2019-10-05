package f196695_v206681.ft.unicamp.br.pos_creditos.application;

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

import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.controllers.email.GmailSend;
import f196695_v206681.ft.unicamp.br.pos_creditos.viewController.outros.SobreFragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.viewController.outros.AutoresFragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.viewController.consultar.ConsultarFragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.viewController.outros.ContatoFragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.viewController.home.HomeFragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar.AvaliarFilmeFragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar.AvaliarFragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar.AvaliarBuscaFragment;

import android.view.Menu;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

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
            fragmentTransaction.add(R.id.frame, homeFragment, "home_fragment");
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
        getMenuInflater().inflate(R.menu.kebab_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.kebab_menu_sobre) {
            Fragment sobreFragment = fragmentManager.findFragmentByTag("sobre_fragment");
            if (sobreFragment == null) {
                sobreFragment = new SobreFragment();
            }
            ReplaceFragment(sobreFragment,"sobre_fragment");
        }

        else if (id == R.id.kebab_menu_autores) {
            Fragment autoresFragment = fragmentManager.findFragmentByTag("autores_fragment");
            if (autoresFragment == null) {
                autoresFragment = new AutoresFragment();
            }
            ReplaceFragment(autoresFragment,"autores_fragment");
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.navigation_drawer_home) {
            Fragment homeFragment = fragmentManager.findFragmentByTag("home_fragment");
            if (homeFragment == null) {
                homeFragment = new HomeFragment();
            }
            ReplaceFragment(homeFragment,"home_fragment");
        }

        else if (id == R.id.navigation_drawer_avaliar) {
            Fragment avaliarFragment = fragmentManager.findFragmentByTag("avaliar_fragment");
            avaliarFragment = new AvaliarFragment();
            ReplaceFragment(avaliarFragment,"avaliar_fragment");
        }

        else if (id == R.id.navigation_drawer_consultar) {
            Fragment consultarFragment = fragmentManager.findFragmentByTag("consultar_fragment");
            if (consultarFragment == null) {
                consultarFragment = new ConsultarFragment();
            }
            ReplaceFragment(consultarFragment,"consultar_fragment");
        }

        else if (id == R.id.navigation_drawer_contato) {
            Fragment contatoFragment = fragmentManager.findFragmentByTag("contato_fragment");
            if (contatoFragment == null) {
                contatoFragment = new ContatoFragment();
            }
            ReplaceFragment(contatoFragment,"contato_fragment");
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void mostrarAvaliarBuscaFragment(String titulo, String ano, int indexTipo) {
        AvaliarBuscaFragment avaliarBuscaFragment = (AvaliarBuscaFragment) fragmentManager.findFragmentByTag("avaliar_busca_fragment");
        if (avaliarBuscaFragment == null) {
            avaliarBuscaFragment = new AvaliarBuscaFragment();
        }
        avaliarBuscaFragment.carregarAtributos(titulo, ano, indexTipo);
        ReplaceFragment(avaliarBuscaFragment,"avaliar_busca_fragment");
    }

    public void mostrarAvaliarFilmeFragment() {
        Fragment avaliarFilmeFragment = fragmentManager.findFragmentByTag("avaliar_filme_fragment");
        if (avaliarFilmeFragment == null) {
            avaliarFilmeFragment = new AvaliarFilmeFragment();
        }
        ReplaceFragment(avaliarFilmeFragment,"avaliar_filme_fragment");
    }

    public void enviarContato(String assunto, String mensagem) {
        new GmailSend("contato.poscreditos@gmail.com", assunto, mensagem);
        Toast.makeText(getApplicationContext(), "Contato enviado", Toast.LENGTH_SHORT).show();
        Fragment homeFragment = fragmentManager.findFragmentByTag("home_fragment");
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        ReplaceFragment(homeFragment,"home_fragment");
    }

    public void hideKeyboard() {
        if(getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void ReplaceFragment(Fragment fragment, String tag) {
        hideKeyboard();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
