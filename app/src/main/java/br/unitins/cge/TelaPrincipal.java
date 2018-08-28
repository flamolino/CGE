package br.unitins.cge;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class TelaPrincipal extends Activity {

    //Declara uma referencia para a superficie de desenho;
    GLSurfaceView superficieDesenho = null;

    //Declara uma referencia para o Render
    Renderizador render = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //instancia um objeto da superficie de desenho;
        this.superficieDesenho = new GLSurfaceView(this);

        //instancia um objeto Renderizador
        this.render = new Renderizador();

        //Configura o objeto de desenho da superficie
        this.superficieDesenho.setRenderer(this.render);


        //publicar a superficie de desenho na tela
        setContentView(this.superficieDesenho);

    }

}


//Classe que ira implementar a logica do desenho (O que eu quero desenhar na superficie)
class Renderizador implements Renderer{

    private long contaQuadros = 0;
    private long tempoInicial = 0;
    private long tempoAtual = 0;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        tempoInicial = System.currentTimeMillis();
        tempoAtual = System.currentTimeMillis();

        //SETA A COR DE LIMPEZA DA TELA
        gl.glClearColor(1, 0, 0, 1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        //configurando a area de coordenadas do plano cartesiano
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity(); //inicializa a matriz identidade


        //configurando o volume de renderização
        //coordenadas do universo
        gl.glOrthox(0, width, 0, height, 1, -1);

        //configurando a matriz de transf. geometricas
        //translação, rotação e escala
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        //configura a area de visualização na tela do dispositivo
        //area da tela
        gl.glViewport(0, 0, width, height);

    }

    @Override
    public void onDrawFrame(GL10 gl) {

        float red, green, blue;
        red = (float) Math.random();
        green = (float) Math.random();
        blue = (float) Math.random();

        tempoAtual = System.currentTimeMillis();
        contaQuadros ++;

        if(tempoAtual - tempoInicial >= 1000)
        {
            tempoInicial = System.currentTimeMillis();
            Log.i("INFO", "FPS = " + contaQuadros);
            contaQuadros = 0;
            gl.glClearColor(red, green, blue, 1);
        }

        //APLICA A COR DE LIMPEZA DE TELA A TODOS OS BITS DO BUFFER DE COR
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);




     }



}











