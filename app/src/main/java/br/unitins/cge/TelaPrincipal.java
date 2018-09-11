package br.unitins.cge;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;
import android.widget.Button;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

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

    private long largura = 0, altura = 0;
    private float red, green, blue;
    private float posX = 0, posY = 0;
    private int direcaoX = -1, direcaoY = -1;
    private float angulo = 0;


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        //SETA A COR DE LIMPEZA DA TELA
        gl.glClearColor(1, 1, 1, 1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {



        /*

            quadrado = new Quadrado(opengl, 4);
            quadrado.setPosXY(200, 400);
            quadrado.setRotacao(45);
            quadrado.setScala(0.5, 0.5);
            quadrado.setCor(1, 0, 0);

         */

        red = (float) Math.random();
        green = (float) Math.random();
        blue = (float) Math.random();

        //configura a area de visualização na tela do dispositivo
        //area da tela
        gl.glViewport(0, 0, width, height);

        //configurando a area de coordenadas do plano cartesiano
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity(); //inicializa a matriz identidade

        //configurando o volume de renderização
        //coordenadas do universo
        gl.glOrthof(0, width, 0, height, -1, 1);

        //configurando a matriz de transf. geometricas
        //translação, rotação e escala
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        largura = width;
        altura = height;

        //habilita o desenho por vértices
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        float tamanho = 300;

        //aloca memoria e define as coordenadas da primitiva
        float[] vetCoords1 = {
                //tri1

                -tamanho/2, -tamanho/2,
                -tamanho/2, tamanho/2,
                tamanho/2, -tamanho/2,
                tamanho/2, tamanho/2



        };

        //cria o vetor de coordenadas
       FloatBuffer buffer1 = generateBuffer(vetCoords1);

       //registrar vetor de coordenadas no openGL
       gl.glVertexPointer(2, GL10.GL_FLOAT, 0, buffer1);


    }

    private FloatBuffer generateBuffer(float[] vetor) {

        //aloca memoria em Bytes
        ByteBuffer prBuffer = ByteBuffer.allocateDirect(vetor.length * 4);

        //ordena os endereços de memoria conforme a arquitetura do processador
        prBuffer.order(ByteOrder.nativeOrder());

        //gera o encapsulador
        FloatBuffer prFloat = prBuffer.asFloatBuffer();
        //limpa
        prFloat.clear();
        //insere o vetor
        prFloat.put(vetor);
        //retira as eventuais sobras de memoria
        prFloat.flip();

        //retorna
        return prFloat;

    }

    @Override
    public void onDrawFrame(GL10 gl) {



        /*
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
            quadrado.desenha();

         */

        float limiteX = largura - (largura/2);
        float limiteY = altura - (altura/2);


        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        gl.glLoadIdentity();

        gl.glTranslatef(400, 600, 0);
        //rotaciona angulo graus em cima de Z
        gl.glRotatef(angulo, 0, 0, 1);
        gl.glScalef(2, 2, 1);
        angulo+=6;


        gl.glColor4f(this.red, this.green, this.blue, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);



        /*
        gl.glTranslatef(this.posX, this.posY, 0);
        gl.glColor4f(this.red, this.green, this.blue, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        float speed = 50;
        if(posX <= 0 || posX >= limiteX){
            direcaoX *= -1;
            mudaCor();
        }
        if(posY <= 0 || posY >= limiteY){
            direcaoY *= -1;
            mudaCor();
        }
        posX += direcaoX * speed;
        posY += direcaoY * speed;
        */
     }

    private void mudaCor() {

        red = (float) Math.random();
        green = (float) Math.random();
        blue = (float) Math.random();

    }


}











