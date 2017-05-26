package jp.ac.titech.itpro.sdl.gles10ex;

/**
 * Created by daron on 2017/05/26.
 */
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class myObject implements SimpleRenderer.Obj {

    private FloatBuffer vbuf;
    private float x,y,z;
    public myObject(float s, float x, float y, float z){
        float r_3 = (float) Math.sqrt(3);
        float[] vertices = {
                // bottom
                -s / 2.0f, -s / 2, -s / (2.0f * r_3),
                s / 2.0f, -s / 2, -s / (2.0f * r_3),
                0, -s / 2, s / r_3,
                // top
                -s / 2.0f, s / 2, -s / (2.0f * r_3),
                s / 2.0f, s / 2, -s / (2.0f * r_3),
                0, s / 2, s / r_3,
                // front1
                -s / 2.0f, -s / 2, -s / (2.0f * r_3),
                s / 2.0f, -s / 2, -s / (2.0f * r_3),
                -s / 2.0f, s / 2, -s / (2.0f * r_3),
                s / 2.0f, s / 2, -s / (2.0f * r_3),
                // right
                0, -s / 2, s / r_3,
                0, s / 2, s / r_3,
                s / 2.0f, -s / 2, -s / (2.0f * r_3),
                s / 2.0f, s / 2, -s / (2.0f * r_3),
                // left
                0, -s / 2, s / r_3,
                0, s / 2, s / r_3,
                -s / 2.0f, -s / 2, -s / (2.0f * r_3),
                -s / 2.0f, s / 2, -s / (2.0f * r_3)
        };
        vbuf = ByteBuffer.allocateDirect(vertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vbuf.put(vertices);
        vbuf.position(0);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void draw(GL10 gl) {
        float r_3 = (float) Math.sqrt(3);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vbuf);

        // bottom
        gl.glNormal3f(0, -1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
        // top
        gl.glNormal3f(0, 1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 3, 3);
        // front1
        gl.glNormal3f(0, 0, -1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 6, 4);
        // right
        gl.glNormal3f(r_3, 0, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 10, 4);
        // left
        gl.glNormal3f(-r_3, 0, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 14, 4);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getZ() {
        return z;
    }
}
