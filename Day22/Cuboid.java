public class Cuboid {
    long xi, yi, zi, xf, yf,zf;

    public Cuboid(long xi, long yi, long zi, long xf, long yf, long zf) {
        this.xi = xi;
        this.yi = yi;
        this.zi = zi;
        this.xf = xf;
        this.yf = yf;
        this.zf = zf;
    }
    
    public long volume() {
        return (xf-xi+1)*(yf-yi+1)*(zf-zi+1);
    }
    
    public boolean isColliding(Cuboid that) {
        if (Math.max(this.xi, that.xi) > Math.min(this.xf, that.xf))
            return false;
        if (Math.max(this.yi, that.yi) > Math.min(this.yf, that.yf)) 
            return false;
        if (Math.max(this.zi, that.zi) > Math.min(this.zf, that.zf))
            return false;
        return true;
    }
}