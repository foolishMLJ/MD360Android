<<<<<<< HEAD
package com.asha.vrlib.strategy.projection;

import android.app.Activity;
import android.util.Log;

import com.asha.vrlib.MDVRLibrary;
import com.asha.vrlib.model.MDMainPluginBuilder;
import com.asha.vrlib.model.MDPosition;
import com.asha.vrlib.objects.MDAbsObject3D;
import com.asha.vrlib.objects.MDCube;
import com.asha.vrlib.objects.MDObject3DHelper;
import com.asha.vrlib.plugins.MDAbsPlugin;
import com.asha.vrlib.plugins.MDCubePlugin;

public class CubeProjection extends AbsProjectionStrategy {
    private MDAbsObject3D object3D;
    private final String TAG="CubeProjection";
    
    public CubeProjection() {
        Log.d(TAG, "CubeProjection");
    }
    
    @Override
    public MDAbsObject3D getObject3D() {
        return object3D;
    }

    @Override
    public MDPosition getModelPosition() {
        return MDPosition.sOriginalPosition;
    }

    @Override
    public void on(Activity activity) {
        Log.d(TAG, "on");
        object3D = new MDCube();
        MDObject3DHelper.loadObj(activity, object3D);
    }

    @Override
    public void off(Activity activity) {
    }

    @Override
    public boolean isSupport(Activity activity) {
        return true;
    }

    @Override
    public MDAbsPlugin buildMainPlugin(MDMainPluginBuilder builder) {
        builder.setContentType(MDVRLibrary.ContentType.CUBE_BITMAP);
        return new MDCubePlugin(builder);
    }

}

=======
package com.asha.vrlib.strategy.projection;

import android.app.Activity;
import android.util.Log;

import com.asha.vrlib.MDVRLibrary;
import com.asha.vrlib.model.MDMainPluginBuilder;
import com.asha.vrlib.model.MDPosition;
import com.asha.vrlib.objects.MDAbsObject3D;
import com.asha.vrlib.objects.MDCube;
import com.asha.vrlib.objects.MDObject3DHelper;
import com.asha.vrlib.plugins.MDAbsPlugin;
import com.asha.vrlib.plugins.MDCubePlugin;

public class CubeProjection extends AbsProjectionStrategy {
    private MDAbsObject3D object3D;
    private final String TAG="CubeProjection";
    
    public CubeProjection() {
        Log.d(TAG, "CubeProjection");
    }
    
    @Override
    public MDAbsObject3D getObject3D() {
        return object3D;
    }

    @Override
    public MDPosition getModelPosition() {
        return MDPosition.sOriginalPosition;
    }

    @Override
    public void on(Activity activity) {
        Log.d(TAG, "on");
        object3D = new MDCube();
        MDObject3DHelper.loadObj(activity, object3D);
    }

    @Override
    public void off(Activity activity) {
    }

    @Override
    public boolean isSupport(Activity activity) {
        return true;
    }

    @Override
    public MDAbsPlugin buildMainPlugin(MDMainPluginBuilder builder) {
        builder.setContentType(MDVRLibrary.ContentType.CUBE_BITMAP);
        return new MDCubePlugin(builder);
    }

}

>>>>>>> message
