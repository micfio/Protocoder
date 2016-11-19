/*
* Part of Protocoder http://www.protocoder.org
* A prototyping platform for Android devices 
*
* Copyright (C) 2013 Victor Diaz Barrales victormdb@gmail.com
* 
* Protocoder is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Protocoder is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU Lesser General Public License
* along with Protocoder. If not, see <http://www.gnu.org/licenses/>.
*/

package org.protocoderrunner.api.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import org.protocoderrunner.api.common.ReturnInterface;
import org.protocoderrunner.api.common.ReturnObject;
import org.protocoderrunner.apidoc.annotation.ProtoMethod;
import org.protocoderrunner.apidoc.annotation.ProtoMethodParam;
import org.protocoderrunner.apprunner.AppRunner;
import org.protocoderrunner.api.other.WhatIsRunningInterface;

public class PMagnetic extends CustomSensorManager implements WhatIsRunningInterface {

    private final static String TAG = PMagnetic.class.getSimpleName();

    private ReturnInterface mCallbackMagneticChange;

    public PMagnetic(AppRunner appRunner) {
        super(appRunner);

        type = Sensor.TYPE_MAGNETIC_FIELD;
    }

    @ProtoMethod(description = "Start the magnetic sensor", example = "")
    @ProtoMethodParam(params = {"function(value)"})
    public void start() {
        super.start();

        mListener = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent event) {
                ReturnObject r = new ReturnObject();
                r.put("x", event.values[0]);
                r.put("y", event.values[1]);
                r.put("z", event.values[2]);
                mCallbackMagneticChange.event(r);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                switch (accuracy) {
                    case SensorManager.SENSOR_STATUS_UNRELIABLE:
                        break;
                    case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                        break;
                    case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                        break;
                    case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                        break;
                }
            }

        };

        isEnabled = mSensormanager.registerListener(mListener, sensor, speed);
    }

    @Override
    public String units() {
        return "uT";
    }


    @ProtoMethod(description = "Start the magnetic sensor. Returns x, y, z", example = "")
    @ProtoMethodParam(params = {"function(x, y, z)"})
    public PMagnetic onChange(final ReturnInterface callbackfn) {
        mCallbackMagneticChange = callbackfn;

        return this;
    }


}
