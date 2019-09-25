package f196695_v206681.ft.unicamp.br.assista_me;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import f196695_v206681.ft.unicamp.br.assista_me.menu.autores.AutoresFragment;
import f196695_v206681.ft.unicamp.br.assista_me.menu.contato.MailFragment;
import f196695_v206681.ft.unicamp.br.assista_me.navigation.cadastro.BuscaFilme;
import f196695_v206681.ft.unicamp.br.assista_me.navigation.consulta.ConsultaFragment;
import f196695_v206681.ft.unicamp.br.assista_me.navigation.home.HomeFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame, new HomeFragment(), "home_fragment");
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        if (id == R.id.action_autores) {
            Fragment autoresFragment = fragmentManager.findFragmentByTag("autores_fragment");
            if (autoresFragment == null)
                autoresFragment = new AutoresFragment();
            replaceFragment(autoresFragment, "autores_fragment");
            return true;
        } else if (id == R.id.action_contato) {
            Fragment mailFragment = fragmentManager.findFragmentByTag("mail_fragment");
            if (mailFragment == null) {
                mailFragment = new MailFragment();
            }
            replaceFragment(mailFragment, "mail_fragment");
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Fragment homeFragment = fragmentManager.findFragmentByTag("home_fragment");
            if (homeFragment == null) {
                homeFragment = new MailFragment();
            }
            replaceFragment(homeFragment, "home_fragment");
        } else if (id == R.id.nav_cadastro) {
            Fragment cadastroFragment = fragmentManager.findFragmentByTag("busca_filme_fragment");
            if (cadastroFragment == null) {
                cadastroFragment = new BuscaFilme();
            }
            replaceFragment(cadastroFragment, "busca_filme_fragment");
        } else if (id == R.id.consulta) {
            Fragment consultaFragment = fragmentManager.findFragmentByTag("consulta_filmes_fragment");
            if (consultaFragment == null) {
                consultaFragment = new ConsultaFragment();
            }
            replaceFragment(consultaFragment, "consulta_filmes_fragment");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void mostraMensagemToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    public void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void abrirAutoresFragment(String mensagem) {
        AutoresFragment autoresFragment = (AutoresFragment) fragmentManager.findFragmentByTag("autores_fragment");
        if (autoresFragment == null)
            autoresFragment = new AutoresFragment();
        autoresFragment.setMailContent(mensagem);
        replaceFragment(autoresFragment, "autores_fragment");
    }
}
