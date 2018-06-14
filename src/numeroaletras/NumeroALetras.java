package numeroaletras;

import java.math.BigInteger;
import javax.swing.JOptionPane;

public class NumeroALetras {
    public static boolean isNumber(String s){
        boolean c;
        try {
            new BigInteger(s);
            c = true;
        } catch (NumberFormatException e) {
            c = false;
        }
        return c;
    }
    public static BigInteger pedirNumero(){
        BigInteger n=BigInteger.ZERO;
        boolean esNumero=false;
        do{
            try{
                n=new BigInteger(JOptionPane.showInputDialog("Ingrese un numero: "));
                esNumero=true;
            }catch(NumberFormatException e){
                esNumero=false;
            }
        }while(!esNumero);
        return n;
    }
    
    public static String convertir(BigInteger num){
        int tam=digitos(num);
        BigInteger n[]=new BigInteger [tam];
        int t[]=new int [tam];
        //convertir el numero en vector
        for(int i=0;i<tam;i++){
            n[i]=num.mod(BigInteger.TEN);
            t[i]=n[i].intValue();
            num=num.subtract(n[i]);
            num=num.divide(BigInteger.TEN);
        }
        
        String[] UNIDADES={"", "UN ", "DOS ", "TRES ",
            "CUATRO ", "CINCO ", "SEIS ", "SIETE ", "OCHO ", "NUEVE ", "DIEZ ",
            "ONCE ", "DOCE ", "TRECE ", "CATORCE ", "QUINCE ", "DIECISEIS ",
            "DIECISIETE ", "DIECIOCHO ", "DIECINUEVE ","VEINTE "};
        String[] DECENAS={"","", "VEINTI ", "TREINTA ", "CUARENTA ",
            "CINCUENTA ", "SESENTA ", "SETENTA ", "OCHENTA ", "NOVENTA "};
        String[] CENTENAS = { "CIEN ","CIENTO ","CIENTOS ","","","QUINIENTOS ","","SETECIENTOS ","","NOVECIENTOS "};
        String[] MIL = { "MIL "};
        String[] PUNTO = { "", "MILLONES ","BILLONES ","TRILLONES ","CUATRILLON ", "QUINTILLON ","SEXTILLON ","SEPTILLON ","OCTILLON ","NOVILLON ","DECILLON ","ONCILLON ","DOCILLON ","TRECILLON ","CARTORCILLON ","QUINCILLON "};

        int punto=0;
        int dec=0;
        int cen=0;
        String enLetras="";
        String bloque="";
        for(int i=0;i<tam;i+=3){
            //comprobar decenas y unidades
            if(i+1<tam){
                dec=(t[i+1]*10)+t[i];
            }else{
                dec=t[i];
            }
            //comprobar centenas
            if((i+2)<tam){
                cen=t[i+2];
                if(cen!=1&&cen!=7&&cen!=5&&cen!=9)
                    bloque+=UNIDADES[cen];
                if(cen==1){
                    if(dec==0){
                        bloque+=CENTENAS[0];
                    }else{
                        bloque+=CENTENAS[1];
                    }
                }else{
                    if(cen!=0&&dec!=0&&t[i]!=0&&cen!=7&&cen!=5&&cen!=9)
                        bloque+=CENTENAS[2];//plural cientos
                    if(cen!=0||dec!=0||t[i]!=0||cen==7||cen==5||cen==9)
                        bloque+=CENTENAS[cen];//plural cientos
                }
            }
            //agregar decenas
            if(dec<=20){
                bloque+=UNIDADES[dec];
            }else{
                dec=t[i+1];
                
                bloque+=DECENAS[dec];
                if(dec!=2&&t[i]!=0)
                    bloque+="Y ";
                bloque+=UNIDADES[t[i]];
            }
            //agregar los equivalentes a puntos
            if(cen!=0||dec!=0||punto%2==0)
            if(punto%2==0){
                    bloque+=PUNTO[punto/2];
            }else{
                    bloque+=MIL[0];
            }
            
            enLetras=bloque+enLetras;//acumulamos de derecha a izquierda
            bloque="";//reiniciamos el bloque de 3 digitos
            dec=0;
            cen=0;
            punto++;
        }
        return enLetras;
    }
    private static int digitos(BigInteger a){
        int n=0;
        //
        for(BigInteger i=new BigInteger("1");menor(i,a);i=i.multiply(BigInteger.TEN)){
            n++;
        }
        return n;
    }
    public static boolean menor(BigInteger a, BigInteger b){
        int res=a.compareTo(b);
        boolean r=false;
        switch(res){
        case 0:
            //System.out.println("Iguales");
            r=true;break;//prueba
        case 1:
            //System.out.println(a+" > "+b);
            break;
        case -1:
            //System.out.println(a+" < "+b);
            r=true;break;
        default: break;
        }
        return r;
    }
    
}
