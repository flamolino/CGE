package br.unitins.cge;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.opengl.GLSurfaceView.Renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class TelaPrincipal extends AppCompatActivity {

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
class Renderizador implements Renderer {

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //Chamado quando a superficie de desenho é criada
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //Chamado quandoa  superficie de desenho sofre alteração
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //Metodo chamado para desenhar na tela
    }
}











