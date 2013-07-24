
package com.wakaguzi.kagua.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.wakaguzi.kagua.R;

public class ProgressDialogFragment extends DialogFragment {
	
	
	@Override
	public Dialog onCreateDialog(final Bundle savedInstanceState) {
	    final ProgressDialog dialog = new ProgressDialog(getActivity());    
		dialog.setProgressStyle(R.style.NewDialog);
		dialog.setIndeterminate(true);
		dialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.progress_dialog_icon_drawable_animation));
		dialog.show();
	    //dialog.setCancelable(false);
	    // etc...
	    return dialog;
	}

}
