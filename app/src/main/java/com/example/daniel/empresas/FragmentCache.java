package com.example.daniel.empresas;

/**
 * Created by DSSSt on 16/01/2018.
 */

public class FragmentCache {
    static FragmentGeneral portraitFragmentGeneral;
    static FragmentGeneral landscapeFragmentGeneral;
    static FragmentDetails landscapeFragmentDetails;

    public static FragmentGeneral getPortraitFragmentGeneral() {
        if (null == portraitFragmentGeneral) portraitFragmentGeneral = new FragmentGeneral();
        return portraitFragmentGeneral;
    }

    public static FragmentGeneral getLandscapeFragmentGeneral() {
        if (null == landscapeFragmentGeneral) landscapeFragmentGeneral = new FragmentGeneral();
        return landscapeFragmentGeneral;
    }

    public static FragmentDetails getLandscapeFragmentDetails() {
        if (null == landscapeFragmentDetails) landscapeFragmentDetails = new FragmentDetails();
        return landscapeFragmentDetails;
    }
}
