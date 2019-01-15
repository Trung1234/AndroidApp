package com.betterlife.pronunciationjourney.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.betterlife.pronunciationjourney.R;

public class ScreenManager {

    private static ScreenManager screenManager;

    public static ScreenManager getInst() {
        if (screenManager == null) {
            screenManager = new ScreenManager();
        }
        return screenManager;
    }

    public void openFragment(FragmentManager fragmentManager, int layoutContainerId,
                             Fragment fragment, boolean addToBackStack) {
        if (fragmentManager == null) {
            return;
        }
        if (fragment != null && !fragment.isAdded()) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(layoutContainerId, fragment);
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(fragment.getClass().toString());
            }
            fragmentTransaction.commit();
        }
    }

    public void openFragmentWithAnimation(FragmentManager fragmentManager, int layoutContainerId,
                                          Fragment fragment, boolean addToBackStack, String tag) {
        if (fragmentManager == null) {
            return;
        }
        if (fragment != null && !fragment.isAdded()) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.trans_in, R.anim.trans_out,
                    R.anim.trans_back_in, R.anim.trans_back_out);
            fragmentTransaction.replace(layoutContainerId, fragment, tag);
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(fragment.getClass().toString());
            }
            fragmentTransaction.commit();
        }
    }

    public void showDialog(Context context, String title, String mess, final OnClickDialogInterface callback) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(mess)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        callback.onClickOK();
                    }
                })
                .setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(callback!=null) {
                            callback.onClickCancel();
                        }
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }
}
