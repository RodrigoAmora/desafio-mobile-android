package br.com.rodrigoamora.desafioandroid.util;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class FragmentUtil {

    public static void changeFragment(int id, Fragment fragment, FragmentManager fragmentManager, boolean backstack, Bundle bundle) {
        FragmentTransaction transacao = fragmentManager.beginTransaction();
        transacao.replace(id, fragment);

        if (backstack) {
            transacao.addToBackStack(null);
        }

        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        transacao.commit();
    }

}
