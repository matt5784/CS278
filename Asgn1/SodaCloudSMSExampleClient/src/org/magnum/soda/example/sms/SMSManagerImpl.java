/*
 **
 ** Copyright 2013, Jules White
 **
 **
 */
package org.magnum.soda.example.sms;

import java.util.HashSet;
import java.util.Set;

import org.magnum.soda.proxy.SodaAsync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSManagerImpl implements SMSManager, SMSSender {

	private static final String TAG = SMSManagerImpl.class.getSimpleName();

	private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

	private static final String MESSAGES = "pdus";

	private final Set<SMSListener> mListenerSet = new HashSet<SMSListener>();

	public SMSManagerImpl(final Context ctx){

		final IntentFilter f = new IntentFilter(SMS_RECEIVED);
		f.setPriority(100);

		ctx.registerReceiver(new BroadcastReceiver() {

			@Override
			public void onReceive(final Context context, final Intent intent) {
				// get the data associated with the intent
				final Bundle bundle = intent.getExtras();

				// extract the list of sms messages from the data
				final Object messages[] = (Object[]) bundle.get(MESSAGES);

				// iterate through the sms messages and look for any
				// commands that need to be executed
				for (int i = 0; i < messages.length; i++) {
					final SmsMessage msg = SmsMessage.createFromPdu((byte[]) messages[i]);

					final SMS receivedMsg = new SMS();
					receivedMsg.setContent(msg.getMessageBody());
					receivedMsg.setFrom(msg.getOriginatingAddress());

					received(receivedMsg);
					/**
					 * Asgn Step 1: This code should construct
					 * a new SMS message using the data stored
					 * in "msg" and invoke the the received() method
					 *
					 */
				}
			}
		}, f);
	}


/**
 *
 * Asgn Step 2: Fill in the following three methods using the Observer
 * pattern. This class should store a list of listeners and notify them when
 * a new SMS message is received. You need to call
 * l.smsSenderAdded(this) after adding a listener
 * to your list.
 */

	@Override
	public void addListener(final SMSListener l) {
		mListenerSet.add(l);
		l.smsSenderAdded(this);
	}

	@Override
	public void removeListener(final SMSListener l) {
		if(!mListenerSet.remove(l)) {
			Log.w(TAG, "Tried to remove a listener from the list, but it wasn't there");
		}
	}

	public void received(final SMS sms){
		for (final SMSListener l : mListenerSet) {
			l.smsEvent(new SMSEvent(SMSEvent.EVENT_TYPE.RECEIVE, sms));
		}
	}

	@Override
	@SodaAsync
	public void send(final String to, final String msg) {
		sendSMS(new SMS().setContent(msg).setTo(to));
	}


	public void sendSMS(final SMS sms) {
		final SmsManager mgr = SmsManager.getDefault();
		mgr.sendTextMessage(sms.getTo(), null, sms.getContent(), null, null);
	}

}
