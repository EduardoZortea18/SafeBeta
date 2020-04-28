package com.example.safe_v02.Notificacoes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootSetter extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            AlarmManagerUtil alarmManagerUtil = new AlarmManagerUtil(context);
            alarmManagerUtil.recuperarAlarmes(context);
        }
    }
}
