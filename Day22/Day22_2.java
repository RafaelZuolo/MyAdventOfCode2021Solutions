import java.util.*;
public class Day22_2 {
    // estrategia: colisoes ON - ON  -> criamos um cubo OFF igual a colisao
    //             colisoes OFF - ON -> criamos apenas o cubo OFF contido na colisao
    // outras colisoes sao irrelevantes no contexto online
    public static void parseCube(Cuboid cube, boolean state, 
                    List<Cuboid> onList, List<Cuboid> offList) {
        List<Cuboid> onListToAdd = new ArrayList<>();
        List<Cuboid> offListToAdd= new ArrayList<>();
        
        if (state == true) onListToAdd.add(cube); // sempre adicionamos cubos ON
        for (Cuboid onCube : onList) {
            if (!cube.isColliding(onCube)) continue;
            Cuboid collision = new Cuboid(Math.max(cube.xi, onCube.xi),
                                             Math.max(cube.yi, onCube.yi),
                                             Math.max(cube.zi, onCube.zi),
                                             Math.min(cube.xf, onCube.xf),
                                             Math.min(cube.yf, onCube.yf),
                                             Math.min(cube.zf, onCube.zf));
            // colisoes ON - ON e OFF - ON sao analogas
            offListToAdd.add(collision);
        }
        for (Cuboid offCube : offList) {
            if (!cube.isColliding(offCube)) continue;
            Cuboid collision = new Cuboid(Math.max(cube.xi, offCube.xi),
                                             Math.max(cube.yi, offCube.yi),
                                             Math.max(cube.zi, offCube.zi),
                                             Math.min(cube.xf, offCube.xf),
                                             Math.min(cube.yf, offCube.yf),
                                             Math.min(cube.zf, offCube.zf));
            onListToAdd.add(collision);
        }
        onList.addAll(onListToAdd);
        offList.addAll(offListToAdd);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Cuboid> onList = new LinkedList<>();
        List<Cuboid> offList = new LinkedList<>();
        
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split("\\sx=|\\.\\.|\\,y=|\\,z=");
            //System.out.println(Arrays.toString(line));
            assert (line.length == 7);
            boolean state = false;
            if (line[0].equals("on")) state = true;
            long xi = Long.parseLong(line[1]);
            long xf = Long.parseLong(line[2]);
            long yi = Long.parseLong(line[3]);
            long yf = Long.parseLong(line[4]);
            long zi = Long.parseLong(line[5]);
            long zf = Long.parseLong(line[6]);
            
            Cuboid cube = new Cuboid(xi, yi, zi, xf, yf, zf);
            parseCube(cube, state, onList, offList);
            
        }    
        long vol = 0;
        for (Cuboid c : onList) {
            vol += c.volume();
        }
        for (Cuboid c : offList) {
            vol -= c.volume();
        }
        System.out.println(vol);
    }      
}