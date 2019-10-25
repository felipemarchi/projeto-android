package f196695_v206681.ft.unicamp.br.pos_creditos.application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.controllers.firebase.FirebaseBuscar;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Utils;
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

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    FragmentManager fragmentManager;
    DrawerLayout drawer;

    private int RC_SIGN_IN;
    private FirebaseUser user;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseBuscar.carregarUtils();
        checkAuth();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        drawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.navigation_drawer_home);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                hideKeyboard();
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame, new HomeFragment(), "home_fragment");
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                user = firebaseAuth.getCurrentUser();
            } else {
                if (response == null) {
                    finish();
                } else {
                    // use response.getError().getErrorCode() and handle the error.
                }
            }
        }
    }

    // METODOS DA ACTIVITY

        @Override
        public void onBackPressed() {
            if (drawer.isDrawerOpen(GravityCompat.START))
            {
                drawer.closeDrawer(GravityCompat.START);
            }
            else if (fragmentManager.getBackStackEntryCount() > 0)
            {
                super.onBackPressed();
                setNavigationDrawer();
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.kebab, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            navigationView.getCheckedItem().setChecked(false);

            int id = item.getItemId();
            if (id == R.id.kebab_sobre)
            {
                Fragment sobreFragment = fragmentManager.findFragmentByTag("sobre_fragment");
                if (sobreFragment == null)
                    sobreFragment = new SobreFragment();
                replaceFragment(sobreFragment, "sobre_fragment");
            }
            else if (id == R.id.kebab_autores)
            {
                Fragment autoresFragment = fragmentManager.findFragmentByTag("autores_fragment");
                if (autoresFragment == null)
                    autoresFragment = new AutoresFragment();
                replaceFragment(autoresFragment, "autores_fragment");
            } else if (id == R.id.kebab_logout) {
                firebaseAuth.signOut();
                checkAuth();
            }

            return super.onOptionsItemSelected(item);
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            redirectToFragment(item.getItemId());
            drawer.closeDrawer(GravityCompat.START);
            navigationView.getCheckedItem().setChecked(true);
            return true;
        }

        public void setNavigationDrawer() {
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.frame);

            if (currentFragment instanceof HomeFragment)
                navigationView.setCheckedItem(R.id.navigation_drawer_home);
            else if (currentFragment instanceof AvaliarFragment || currentFragment instanceof AvaliarResultadosFragment || currentFragment instanceof AvaliarFilmeFragment)
                navigationView.setCheckedItem(R.id.navigation_drawer_avaliar);
            else if (currentFragment instanceof ConsultarFragment)
                navigationView.setCheckedItem(R.id.navigation_drawer_consultar);
            else if (currentFragment instanceof ContatoFragment)
                navigationView.setCheckedItem(R.id.navigation_drawer_contato);
            else
                navigationView.getCheckedItem().setChecked(false);
        }

        public void replaceFragment(Fragment fragment, String tag) {
            hideKeyboard();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, tag);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        public void hideKeyboard() {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            View view = getCurrentFocus();
            if (view == null)
                view = new View(this);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    // METODOS UTEIS

        public void showKeyboard() {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
        }

        public void showToast(String mensagem) {
            Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();
        }

        public void redirectToFragment(int id) {
            if (id == R.id.navigation_drawer_home)
            {
                Fragment homeFragment = fragmentManager.findFragmentByTag("home_fragment");
                if (homeFragment == null)
                    homeFragment = new HomeFragment();
                replaceFragment(homeFragment, "home_fragment");
            }
            else if (id == R.id.navigation_drawer_avaliar)
            {
                Fragment avaliarFragment = fragmentManager.findFragmentByTag("avaliar_fragment");
                if (avaliarFragment == null)
                    avaliarFragment = new AvaliarFragment();
                replaceFragment(avaliarFragment, "avaliar_fragment");
            }
            else if (id == R.id.navigation_drawer_consultar)
            {
                Fragment consultarFragment = fragmentManager.findFragmentByTag("consultar_fragment");
                if (consultarFragment == null)
                    consultarFragment = new ConsultarFragment();
                replaceFragment(consultarFragment, "consultar_fragment");
            }
            else if (id == R.id.navigation_drawer_contato)
            {
                Fragment contatoFragment = fragmentManager.findFragmentByTag("contato_fragment");
                if (contatoFragment == null)
                    contatoFragment = new ContatoFragment();
                replaceFragment(contatoFragment, "contato_fragment");
            }
        }

        public void redirectToFragment(int id, Bundle arguments) {
            if (id == R.id.avaliar_resultados_titulo)
            {
                Fragment avaliarResultadosFragment = fragmentManager.findFragmentByTag("avaliar_resultados_fragment");
                if (avaliarResultadosFragment == null)
                    avaliarResultadosFragment = new AvaliarResultadosFragment();
                avaliarResultadosFragment.setArguments(arguments);
                replaceFragment(avaliarResultadosFragment, "avaliar_resultados_fragment");
            }
        }

        public void popAllFragments() {
            for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++)
                fragmentManager.popBackStack();
            navigationView.setCheckedItem(R.id.navigation_drawer_home);
        }

    // METODOS ESPECIFICOS DE FRAGMENTS

        public void redirectToAvaliarFilme(Filme filme) {
            AvaliarFilmeFragment avaliarFilmeFragment = (AvaliarFilmeFragment) fragmentManager.findFragmentByTag("avaliar_filme_fragment");
            if (avaliarFilmeFragment == null)
                avaliarFilmeFragment = new AvaliarFilmeFragment();
            avaliarFilmeFragment.setFilme(filme);
            replaceFragment(avaliarFilmeFragment, "avaliar_filme_fragment");
        }

    private void checkAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        if (user == null) {
            List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.PhoneBuilder().build(),
            new AuthUI.IdpConfig.FacebookBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build(),
            new AuthUI.IdpConfig.EmailBuilder().build());

            // Create and launch sign-in intent
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setLogo(R.drawable.logo_icon)
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);
        }
    }
}
