package f196695_v206681.ft.unicamp.br.aulas;

import android.content.Intent;
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

import f196695_v206681.ft.unicamp.br.aulas.alunos.OnBiografiaRequest;
import f196695_v206681.ft.unicamp.br.aulas.alunos.AlunosFragment;
import f196695_v206681.ft.unicamp.br.aulas.autores.AutoresFragment;
import f196695_v206681.ft.unicamp.br.aulas.biografias.BiografiasFragment;
import f196695_v206681.ft.unicamp.br.aulas.games.frases.FrasesFragment;
import f196695_v206681.ft.unicamp.br.aulas.games.name.NameFragment;
import f196695_v206681.ft.unicamp.br.aulas.internet.InternetFragment;
import f196695_v206681.ft.unicamp.br.aulas.kotlin.KotlinActivity;
import f196695_v206681.ft.unicamp.br.aulas.games.puzzle.PuzzleFragment;
import f196695_v206681.ft.unicamp.br.aulas.database.DatabaseFragment;
import f196695_v206681.ft.unicamp.br.aulas.stats.StatsJogo2Fragment;

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
        navigationView.getMenu().getItem(0).setChecked(true);

        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame, new FirstFragment(), "first_fragment");
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

        if (id == R.id.action_settings) {
            mostraMensagemToast(item.getTitle().toString());
            return true;
        } else if (id == R.id.action_contato) {
            Fragment mailFragment = fragmentManager.findFragmentByTag("mail_fragment");
            if (mailFragment == null) {
                mailFragment = new MailFragment();
            }
            replaceFragment(mailFragment, "mail_fragment");
        } else if (id == R.id.action_stats_jogo2) {
            Fragment statsFragment = fragmentManager.findFragmentByTag("stats_jogo2_fragment");
            if (statsFragment == null) {
                statsFragment = new StatsJogo2Fragment();
            }
            replaceFragment(statsFragment, "stats_jogo2_fragment");
        } else if (id == R.id.action_stats_jogo3) {
            Fragment statsFragment = fragmentManager.findFragmentByTag("stats_jogo3_fragment");
            if (statsFragment == null) {
                statsFragment = new f196695_v206681.ft.unicamp.br.aulas.stats.StatsJogo3Fragment();
            }
            replaceFragment(statsFragment, "stats_jogo3_fragment");
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_autores) {
            Fragment autoresFragment = fragmentManager.findFragmentByTag("autores_fragment");
            if (autoresFragment == null)
                autoresFragment = new AutoresFragment();
            replaceFragment(autoresFragment, "autores_fragment");
        } else if (id == R.id.nav_alunos) {
            AlunosFragment alunosFragment = (AlunosFragment) fragmentManager.findFragmentByTag("alunos_fragment");
            if (alunosFragment == null) {
                alunosFragment = new AlunosFragment();
                alunosFragment.setOnBiografiaRequest(new OnBiografiaRequest() {
                    @Override
                    public void onRequest(int position) {
                        BiografiasFragment biografiasFragment = (BiografiasFragment) fragmentManager.findFragmentByTag("biografias_fragment");
                        if (biografiasFragment == null)
                            biografiasFragment = new BiografiasFragment();
                        biografiasFragment.setPosition(position);
                        replaceFragment(biografiasFragment, "biografias_fragment");

                    }
                });
            }
            replaceFragment(alunosFragment, "alunos_fragment");
        } else if (id == R.id.nav_biografias) {
            Fragment biografiasFragment = fragmentManager.findFragmentByTag("biografias_fragment");
            if (biografiasFragment == null)
                biografiasFragment = new BiografiasFragment();
            replaceFragment(biografiasFragment, "biografias_fragment");
        } else if (id == R.id.nav_jogo1) {
            Fragment puzzleFragment = fragmentManager.findFragmentByTag("puzzle_fragment");
            if (puzzleFragment == null)
                puzzleFragment = new PuzzleFragment();
            replaceFragment(puzzleFragment, "puzzle_fragment");

        } else if (id == R.id.nav_jogo2) {
            NameFragment nameFragment = (NameFragment) fragmentManager.findFragmentByTag("name_fragment");
            if (nameFragment == null) {
                nameFragment = new NameFragment();
                nameFragment.setOnBiografiaRequest(new OnBiografiaRequest() {
                    @Override
                    public void onRequest(int position) {
                        BiografiasFragment biografiasFragment = (BiografiasFragment) fragmentManager.findFragmentByTag("biografias_fragment");
                        if (biografiasFragment == null)
                            biografiasFragment = new BiografiasFragment();
                        biografiasFragment.setPosition(position);
                        replaceFragment(biografiasFragment, "biografias_fragment");

                    }
                });
            }
            replaceFragment(nameFragment, "name_fragment");
        } else if (id == R.id.nav_kolin_activity) {
            Intent intent = new Intent(this, KotlinActivity.class);
            intent.putExtra("chave", "Múltiplas Activities");
            startActivity(intent);
        } else if (id == R.id.nav_database) {
            Fragment dabaseFragment = fragmentManager.findFragmentByTag("database_fragment");
            if (dabaseFragment == null)
                dabaseFragment = new DatabaseFragment();
            replaceFragment(dabaseFragment, "database_fragment");
        } else if (id == R.id.nav_internet) {
            Fragment internetFragment = fragmentManager.findFragmentByTag("internet_fragment");
            if (internetFragment == null)
                internetFragment = new InternetFragment();
            replaceFragment(internetFragment, "internet_fragment");
        } else if (id == R.id.nav_jogo3) {
            Fragment frasesFragment = fragmentManager.findFragmentByTag("frases_fragment");
            if (frasesFragment == null)
                frasesFragment = new FrasesFragment();
            replaceFragment(frasesFragment, "frases_fragment");
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
