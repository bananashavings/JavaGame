package gfx;

import game.Camera;
import game.GameManager;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Renderer extends Canvas {
    private Camera camera;

    private int[] pixels;
    private BufferedImage view;

    public Renderer(Camera player)
    {
        this.camera = player;

        this.view = new BufferedImage(camera.getViewportWidth(), camera.getViewportHeight(), BufferedImage.TYPE_INT_RGB);
        this.pixels = ((DataBufferInt) view.getRaster().getDataBuffer()).getData();
    }

    public void clearScreen(int color)
    {
        for (int pixelIndex = 0; pixelIndex < pixels.length; pixelIndex++) {
            pixels[pixelIndex] = color;
        }
    }

    public void render()
    {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        AffineTransform t = new AffineTransform();

        t.scale(GameManager.getConfiguration().getRenderScale(), GameManager.getConfiguration().getRenderScale());
        g.setTransform(t);

        g.drawImage(view, 0, 0,null);

        g.dispose();
        bs.show();

        clearScreen(0xffffffff);
    }

    public void renderPixel(int x, int y, int color)
    {
        if (color != 0xffff00ff && x >= 0 && x < camera.getViewportWidth() && y >= 0 && y < camera.getViewportHeight()) {
            double pixelIndex = x + y * camera.getViewportWidth();

            if (pixelIndex < pixels.length && pixelIndex >= 0) {
                pixels[(int) pixelIndex] = color;
            }
        }
    }

    public void renderSprite(Sprite sprite, int xPosition, int yPosition)
    {
        int spriteWidth = sprite.getWidth();
        int spriteHeight = sprite.getHeight();
        int[] spritePixels = sprite.getPixels();

        for (int y = 0; y < spriteHeight; y++) {
            for (int x = 0; x < spriteWidth; x++) {

                renderPixel(x + xPosition - camera.getX(),
                        y + yPosition - camera.getY(),
                        spritePixels[x + y * spriteWidth]);
            }
        }
    }
}