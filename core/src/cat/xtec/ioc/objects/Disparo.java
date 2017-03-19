package cat.xtec.ioc.objects;

import cat.xtec.ioc.helpers.AssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.ArrayList;


public class Disparo extends Actor{

    private Rectangle balaCollision;
    private Vector2 position;
    private int width, height;
    private int direccion;
    private ScrollHandler scrollHandler;

    public Disparo(float x, float y, int width, int height, ScrollHandler scrollHandler){
        position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.scrollHandler = scrollHandler;
        balaCollision = new Rectangle();
        setBounds(position.x, position.y, width, height);

    }

    public TextureRegion getBalaTexture(){ return AssetManager.disparo; }


    @Override
    public void act(float delta) {
        super.act(delta);
        this.position.x += 400*delta;
        balaCollision.set(position.x, position.y, width, height+2);
        impacta(scrollHandler.getAsteroids());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(getBalaTexture(), position.x, position.y, width, height);
    }

    public boolean impacta(ArrayList<Asteroid> asteroides){
        for (Asteroid asteroid: asteroides) {
            if(asteroid.peta(this)){
                Gdx.app.log("SeDestruye", "destroy");
                this.remove();
                asteroid.haPetado = true;
                asteroid.setVisible(false);

                return true;
            }
        }
        return false;
    }

    public Rectangle getBalaCollision() {return balaCollision;}
}