package com.tobecontinued.android;

import android.net.Uri;

import com.nuance.speechkit.PcmFormat;

/**
 * All Nuance Developers configuration parameters can be set here.
 *
 * Copyright (c) 2015 Nuance Communications. All rights reserved.
 */
public class Configuration {

    //All fields are required.
    //Your credentials can be found in your Nuance Developers portal, under "Manage My Apps".
    public static final String APP_KEY = "0501164d907a25e86e4597f1349b3551f9d1acb4dd6366308952a8709903359e044beb94c5107c335613248aaf5adba82b885c08ebc2397414e1e5ade41a4ce8";
    public static final String APP_ID = "NMDPTRIAL_yizhangucla_gmail_com20161014233817";
    public static final String SERVER_HOST = "sslsandbox-nmdp.nuancemobility.net";
    public static final String SERVER_PORT = "443";

    public static final Uri SERVER_URI = Uri.parse("nmsps://" + APP_ID + "@" + SERVER_HOST + ":" + SERVER_PORT);

    //Only needed if using NLU
    public static final String CONTEXT_TAG = "!NLU_CONTEXT_TAG!";

    public static final PcmFormat PCM_FORMAT = new PcmFormat(PcmFormat.SampleFormat.SignedLinear16, 16000, 1);
}

