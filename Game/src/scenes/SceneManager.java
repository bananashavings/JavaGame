package scenes;

import gfx.Renderer;
import game.IRenderable;
import game.IUpdatable;

import java.util.ArrayList;

public class SceneManager implements IUpdatable, IRenderable
{
    private ArrayList<IScene> scenes = new ArrayList<>();
    private int currentScene = 0;

    public void update()
    {
        if(!scenes.isEmpty())
        {
            scenes.get(currentScene).update();
        }
    }

    public void render(Renderer renderer)
    {
        if(!scenes.isEmpty())
        {
            scenes.get(currentScene).render(renderer);
        }
    }

    public void addScene(IScene IScene)
    {
        scenes.add(IScene);
    }

    public void loadScene(int sceneNumber)
    {
        currentScene = sceneNumber;
    }
}
