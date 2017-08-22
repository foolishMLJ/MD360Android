package com.asha.vrlib.objects;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

import com.asha.vrlib.MD360Program;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;


public class MDCube extends MDAbsObject3D {


    private static final String TAG = "MDCube";


    @Override
    protected void executeLoad(Context context) {
        Log.d(TAG, "executeLoad");

        generateCube(this);
    }

    private static void generateCube(MDAbsObject3D object3D) {
        Log.d(TAG, "generateCube");
        //todotodo
        generateCube(1, object3D);
    }



    private static void generateCube(float size, MDAbsObject3D object3D) {


        float[] texcoords = new float[]{
                //front
            3.0f/3, 1.0f,
            3.0f/3, 0.5f,
            2.0f/3, 1.0f,
            2.0f/3, 1.0f,
            3.0f/3, 0.5f,
            2.0f/3, 0.5f,

          //back
                1.0f/3, 0.5f,
                1.0f/3, 1.0f,
                2.0f/3, 1.0f,
                2.0f/3, 1.0f,
                2.0f/3, 0.5f,
                1.0f/3, 0.5f,
        //left
                2.0f/3, 0.5f,
                2.0f/3, 0f,
                1.0f/3, 0.5f,
                1.0f/3, 0.5f,
                2.0f/3, 0f,
                1.0f/3, 0f,
            //right
                0, 0,
                0, 0.5f,
                1.0f/3, 0.5f,
                1.0f/3,0.5f,
                1.0f/3,0,
                0, 0,

                //top
                1.0f, 0f,
                2.0f/3, 0f,
                1.0f, 0.5f,
                1.0f, 0.5f,
                2.0f/3, 0f,
                2.0f/3, 0.5f,


//botton
            0, 0.5f,
            0, 1.0f,
            1.0f/3, 1.0f,
            1.0f/3, 1.0f,
            1.0f/3, 0.5f,
            0, 0.5f,


        };
        short[] indices = new short[6* 4 * 6];


        float[] vertexs = new float[] {
            ////////////////////////////////////////////////////////////////////
            // FRONT
            ////////////////////////////////////////////////////////////////////
            // Triangle 1

            -size, -size, size, // bottom-left
            -size, size, size, // top-left
            size, -size, size, // bottom-right
            // Triangle 2
            size, -size, size, // bottom-right
            -size, size, size, // top-left
            size, size, size, // top-right
            ////////////////////////////////////////////////////////////////////
            // BACK
            ////////////////////////////////////////////////////////////////////
            // Triangle 1
            -size, size, -size, // top-left
            -size, -size, -size, // bottom-left
            size, -size, -size, // bottom-right
            // Triangle 2
            size, -size, -size, // bottom-right
            size, size, -size, // top-right
            -size, size, -size, // top-left
            ////////////////////////////////////////////////////////////////////
            // LEFT
            ////////////////////////////////////////////////////////////////////
            // Triangle 1
            -size, -size, -size, // bottom-left
            -size, size, -size, // top-left
            -size, -size, size, // bottom-right
            // Triangle 2
            -size, -size, size, // bottom-right
            -size, size, -size, // top-left
            -size, size, size, // top-right
            ////////////////////////////////////////////////////////////////////
            // RIGHT
            ////////////////////////////////////////////////////////////////////
            // Triangle 1
            size, size, -size, // top-left
            size, -size, -size, // bottom-left
            size, -size, size, // bottom-right
            // Triangle 2
            size, -size, size, // bottom-right
            size, size, size, // top-right
            size, size, -size, // top-left
            ////////////////////////////////////////////////////////////////////
            // TOP
            ////////////////////////////////////////////////////////////////////
            // Triangle 1
            -size, size, size, // bottom-left
            -size, size, -size, // top-left
            size, size, size, // bottom-right
            // Triangle 2
            size, size, size, // bottom-right
            -size, size, -size, // top-left
            size, size, -size, // top-right
            ////////////////////////////////////////////////////////////////////
            // BOTTOM
            ////////////////////////////////////////////////////////////////////
            // Triangle 1
            -size, -size, -size, // top-left
            -size, -size, size, // bottom-left
            size, -size, size, // bottom-right
            // Triangle 2
            size, -size, size, // bottom-right
            size, -size, -size, // top-right
            -size, -size, -size // top-left
        };
            
        


        //texcoords[t++] = ;
        //texcoords[t++] = ;
            
  



        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                vertexs.length * 4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vertexs);
        vertexBuffer.position(0);

        // initialize vertex byte buffer for shape coordinates
        ByteBuffer cc = ByteBuffer.allocateDirect(
                texcoords.length * 4);
        cc.order(ByteOrder.nativeOrder());
        FloatBuffer texBuffer = cc.asFloatBuffer();
        texBuffer.put(texcoords);
        texBuffer.position(0);

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                indices.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        ShortBuffer indexBuffer = dlb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);

        object3D.setIndicesBuffer(indexBuffer);
        object3D.setTexCoordinateBuffer(0,texBuffer);
        //object3D.setTexCoordinateBuffer(1,texBuffer);
        object3D.setVerticesBuffer(0,vertexBuffer);
        //object3D.setVerticesBuffer(1,vertexBuffer);
        object3D.setNumIndices(indices.length);
    }

    public void uploadVerticesBufferIfNeed(MD360Program program, int index){
        // get handle to shape's transformation matrix
        //mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgramObject, "uMVPMatrix");
        //myRenderer.checkGlError("glGetUniformLocation");
        super.uploadVerticesBufferIfNeed( program,  index);
    }


    @Override
    public void uploadTexCoordinateBufferIfNeed(MD360Program program, int index){
		super.uploadTexCoordinateBufferIfNeed(program, index);
    }


    @Override
    public void draw() {
        int startPos =0;
        int verticesPerface = 6;
        float colorcyan[] = myColor.cyan();
        float colorblue[] = myColor.blue();
        float colorred[] = myColor.red();
        float colorgray[] = myColor.gray();
        float colorgreen[] = myColor.green();
        float coloryellow[] = myColor.yellow();
        //if(true) return;
        //draw front face

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,startPos,verticesPerface);
        startPos += verticesPerface;
        //draw back face

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, startPos, verticesPerface);
        startPos += verticesPerface;
        //draw left face

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,startPos,verticesPerface);
        startPos += verticesPerface;
        //draw right face

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,startPos,verticesPerface);
        startPos += verticesPerface;
        //draw top face

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,startPos,verticesPerface);
        startPos += verticesPerface;
        //draw bottom face
 
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,startPos,verticesPerface);
        //last face, so no need to increment.

        
    }

    
}
