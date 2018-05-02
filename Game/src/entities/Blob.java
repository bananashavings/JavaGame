package entities;

import gfx.SpriteSheet;
import level.Level;

public class Blob extends Enemy
{
    public Blob(String name, double x, double y, double moveSpeed, SpriteSheet sheet, Level level)
    {
        super(name, x, y, moveSpeed, sheet, level);
    }

    @Override
    public void target() {
        if(target == null)
        {
            return;
        }

        double xDir = target.getX() - x;
        double yDir = target.getY() - y;

        if(Math.abs(xDir) > 4)
        {
            xDir = abs(xDir);
        }
        else
        {
            xDir = 0;
        }

        if(Math.abs(yDir) > 4)
        {
            yDir = abs(yDir);
        }
        else
        {
            yDir = 0;
        }

        move(xDir * moveSpeed, yDir * moveSpeed);
    }
}