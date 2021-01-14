package agh.cs.miner.basics;
import java.util.Objects;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Vector2d{
    public int x;
    public int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return ("(" + this.x + "," + this.y + ")");
    }

    public int distance(Vector2d other){
        Vector2d vector2d = this.subtract(other);
        return (int) sqrt(pow(vector2d.x,2) + pow(vector2d.y,2));
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(other.x + this.x, other.y + this.y);
    }
    public Vector2d subtract(Vector2d other){
        return new Vector2d(other.x - this.x, other.y - this.y);
    }

    public boolean precedes(Vector2d other){
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2d other){
        return this.x >= other.x && this.y >= other.y;
    }

    public boolean equals(Object other){
        if (other == this) return true;
        if (other == null) return false;
        if (getClass()!=other.getClass()) return false;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}