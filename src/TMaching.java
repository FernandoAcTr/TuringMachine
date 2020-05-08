import java.util.Arrays;

public class TMaching {

    private final int F = 6; //conjunto de estados finales
    private final char[] Q = {0,1,2,3,4,5,6};
    private char[] cinta;
    private final int q0 = 0;
    private int estado;
    private int index;

    public TMaching(String cadena){
        cinta = cadena.toCharArray();
        estado = q0;
    }

    private boolean isA(char c){
        return c == 'a';
    }

    private boolean isB(char c){
        return c == 'b';
    }

    private boolean isC(char c){
        return c == 'c';
    }

    private boolean isX(char c){
        return c == 'x';
    }

    private boolean isY(char c){
        return c == 'y';
    }

    private boolean isZ(char c){
        return c == 'z';
    }

    private boolean isWhite(char c){
        return c == 'B';
    }

    private String cintaToString(){
        return Arrays.toString(cinta)
                .replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .replaceAll(",", "")
                .replaceAll(" ", "");
    }

    private String getConfig(int regla){
        return "(q" + estado + "," + cintaToString() + "," + (index+1) + ") ├" + regla + " ";
    }

    private void movimientoD(int newEstado, char impresion, int regla){
        System.out.print(getConfig(regla));
        estado = newEstado;
        if(index < cinta.length) {
            cinta[index] = impresion;
            index++;
        }else{
            String r = getConfig(0);
            System.out.println(r.substring(0, r.length()-3)); // a la ultima configuracion le quitamos el operador de movimiento
        }
    }

    private void movimientoI(int newEstado, char impresion, int regla){
        System.out.print(getConfig(regla));
        cinta[index] = impresion;
        index--;
        estado = newEstado;
    }

    private boolean transicion(char c){
        boolean r = true;

        switch (estado){
            case 0:

                if(isA(c)){
                    movimientoD(1,'x', 1);

                }else if(isY(c)){
                    movimientoD(4,'y', 2);

                }else if(isWhite(c)){
                    movimientoD(6,'B', 3);

                }else
                    r = false;

                break;
            case 1:

                if(isA(c)){
                    movimientoD(1,'a', 4);

                }else if(isY(c)){
                    movimientoD(1,'y', 6);

                }else if(isB(c)){
                    movimientoD(2,'y', 5);
                }else
                    r = false;

                break;
            case 2:

                if(isZ(c)){
                    movimientoD(2,'z', 9);

                }else if(isB(c)){
                    movimientoD(2,'b', 7);

                }else if(isC(c)){
                    movimientoI(3,'z', 8);
                }else
                    r = false;

                break;
            case 3:

                if(isA(c)){
                    movimientoI(3,'a', 12);

                }else if(isB(c)){
                    movimientoI(3,'b', 10);

                }else if(isC(c)){
                    movimientoI(3,'c', 15);

                }else if(isY(c)){
                    movimientoI(3,'y', 11);

                }else if(isZ(c)){
                    movimientoI(3,'z', 14);

                }else if(isX(c)){
                    movimientoD(0,'x', 13);
                }
                else
                    r = false;


                break;
            case 4:

                if(isY(c)){
                    movimientoD(4,'y', 17);
                }else if(isZ(c)){
                    movimientoD(5,'z', 16);
                }else
                    r = false;


                break;
            case 5:

                if(isZ(c)){
                    movimientoD(5,'z', 18);
                }else if(isWhite(c)){
                    movimientoD(6,' ', 19);
                }else
                    r = false;

        }

        return r;
    }

    public boolean reconocer(){
        estado = q0;
        char c;
       do{
           if(cinta.length == 0)  //si la cinta esta vacia
               c = 'B';
           else if(index >= cinta.length){ // si ya se acabo la cinta
               c = 'B';
           }else
               c = cinta[index];

           if (!transicion(c))
               return false;

       }while (index < cinta.length || estado != F);


        return estado == F;

    }

    public void setCinta(String cadena) {
        this.cinta = cadena.toCharArray();
    }
}
