package com.sunland.xsparkmobile.view_controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.sunland.xsparkmobile.R;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class Frg_add_url extends DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.frg_url_add, null, false);
        final EditText et_ip = view.findViewById(R.id.ip);
        final EditText et_port = view.findViewById(R.id.port);
        final EditText et_desc = view.findViewById(R.id.description);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((OnUrlDialogClickedListener) getActivity()).onPositiveClicked(et_ip.getText().toString()
                                , et_port.getText().toString(), et_desc.getText().toString());
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

    public interface OnUrlDialogClickedListener {
        void onPositiveClicked(String ip, String port, String desc);
    }
}
