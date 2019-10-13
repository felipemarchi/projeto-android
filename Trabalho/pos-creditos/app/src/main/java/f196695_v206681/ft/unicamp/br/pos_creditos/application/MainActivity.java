package f196695_v206681.ft.unicamp.br.pos_creditos.application;

import android.content.Context;
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
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;
import f196695_v206681.ft.unicamp.br.pos_creditos.viewController.outros.SobreFragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.viewController.outros.AutoresFragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.viewController.consultar.ConsultarFragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.viewController.outros.ContatoFragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.viewController.home.HomeFragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar.AvaliarFilmeFragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar.AvaliarFragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar.AvaliarResultadosFragment;

import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.navigation_drawer_home);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HomeFragment homeFragment = new HomeFragment();
            fragmentTransaction.add(R.id.frame, homeFragment, "home_fragment");
            fragmentTransaction.commit();
        }
        hideKeyboard();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

            Fragment currentFragment = this.getSupportFragmentManager().findFragmentById(R.id.frame);
            if (currentFragment instanceof HomeFragment) {
                navigationView.setCheckedItem(R.id.navigation_drawer_home);
            } else if (currentFragment instanceof AvaliarFragment
                    || currentFragment instanceof AvaliarResultadosFragment
                    || currentFragment instanceof AvaliarFilmeFragment) {
                navigationView.setCheckedItem(R.id.navigation_drawer_avaliar);
            } else if (currentFragment instanceof ConsultarFragment) {
                navigationView.setCheckedItem(R.id.navigation_drawer_consultar);
            } else if (currentFragment instanceof ConsultarFragment) {
                navigationView.setCheckedItem(R.id.navigation_drawer_contato);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.kebab_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        navigationView.getCheckedItem().setCheckable(false);

        int id = item.getItemId();
        if (id == R.id.kebab_menu_sobre) {
            Fragment sobreFragment = fragmentManager.findFragmentByTag("sobre_fragment");
            if (sobreFragment == null) {
                sobreFragment = new SobreFragment();
            }
            replaceFragment(sobreFragment, "sobre_fragment");
        } else if (id == R.id.kebab_menu_autores) {
            Fragment autoresFragment = fragmentManager.findFragmentByTag("autores_fragment");
            if (autoresFragment == null) {
                autoresFragment = new AutoresFragment();
            }
            replaceFragment(autoresFragment, "autores_fragment");
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
            replaceFragment(homeFragment, "home_fragment");
        } else if (id == R.id.navigation_drawer_avaliar) {
            Fragment avaliarFragment = new AvaliarFragment();
            replaceFragment(avaliarFragment, "avaliar_fragment");
        } else if (id == R.id.navigation_drawer_consultar) {
            Fragment consultarFragment = fragmentManager.findFragmentByTag("consultar_fragment");
            if (consultarFragment == null) {
                consultarFragment = new ConsultarFragment();
            }
            replaceFragment(consultarFragment, "consultar_fragment");
        } else if (id == R.id.navigation_drawer_contato) {
            Fragment contatoFragment = fragmentManager.findFragmentByTag("contato_fragment");
            if (contatoFragment == null) {
                contatoFragment = new ContatoFragment();
            }
            replaceFragment(contatoFragment, "contato_fragment");
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void mostrarAvaliarResultadosFragment(String titulo, String ano, int indexTipo) {
        AvaliarResultadosFragment avaliarResultadosFragment = (AvaliarResultadosFragment) fragmentManager.findFragmentByTag("avaliar_busca_fragment");
        if (avaliarResultadosFragment == null) {
            avaliarResultadosFragment = new AvaliarResultadosFragment();
        }
        avaliarResultadosFragment.carregarAtributos(titulo, ano, indexTipo);
        replaceFragment(avaliarResultadosFragment, "avaliar_busca_fragment");
    }

    public void mostrarAvaliarFilmeFragment(Filme filme) {
        AvaliarFilmeFragment avaliarFilmeFragment = (AvaliarFilmeFragment) fragmentManager.findFragmentByTag("avaliar_filme_fragment");
        if (avaliarFilmeFragment == null)
            avaliarFilmeFragment = new AvaliarFilmeFragment(filme);
        else
            avaliarFilmeFragment.setFilme(filme);
        replaceFragment(avaliarFilmeFragment, "avaliar_filme_fragment");
    }

    public void enviarContato(String assunto, String mensagem) {
        new GmailSend("contato.poscreditos@gmail.com", assunto, mensagem);
        Toast.makeText(getApplicationContext(), "Contato enviado", Toast.LENGTH_SHORT).show();
        Fragment homeFragment = fragmentManager.findFragmentByTag("home_fragment");
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        replaceFragment(homeFragment, "home_fragment");
    }

    public void goBackToHomeFragment() {
        Fragment homeFragment = fragmentManager.findFragmentByTag("home_fragment");
        if (homeFragment == null)
            homeFragment = new HomeFragment();
        replaceFragment(homeFragment, "home_fragment");
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showKeyboard(View view) {
        hideKeyboard();
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void replaceFragment(Fragment fragment, String tag) {
        hideKeyboard();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }
}
