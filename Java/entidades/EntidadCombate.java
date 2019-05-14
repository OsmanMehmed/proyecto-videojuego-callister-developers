/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import Combate.Elemento;
import Combate.HealthBar;
import Combate.Magia;
import Combate.ManaBar;
import chaoschild.Punto;
import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author victo
 */
public abstract class EntidadCombate extends Entidad{
    private Entidad combate;
    private boolean lucha;
    private ArrayList<Magia> magias;
    private int EXPN, EXPA, LVL;
    private int[] est;
    private int DMG;
    private int[] estb;
    private int[] multiplicadores;
    private int[] luest;
    private Sound daño;
    private Sound dañar;
    private int animdañar, animbaseco, animest, animmag, SPD;
    private Elemento elemento;
    private int tieneGema=0;
    private int tieneGemaD=0;
    private HealthBar barraV;
    private ManaBar barraM;
    
    
    
    public EntidadCombate(String ruta,String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre) throws SlickException {
        super(ruta, h, w, numAnimaciones, nombre);
        combate=new Entidad(rutaC, 64, 64, numC, nombre+" de Combate") {};
        combate.animaciones(frames, 250, genLimited(numC));
        lucha=false;
        setAnimacionCombate(0);
        magias=new ArrayList();
        est=new int[8];//0:HP,1:MP,2:STR,3:DEF,4:INT,5:HIT,6:AGI,7:SPD
        multiplicadores=new int [8];
        estb=new int [8];
        luest=new int[8];
        daño=new Sound("resources/sonido/HitDamage.ogg");
        dañar=new Sound("resources/sonido/combate/fisica_flecha_o_lanzar.ogg");
        animdañar=2;
        animbaseco=1;
        animest=0;
        animmag=1;
        barraV=new HealthBar(this);
        barraM=new ManaBar(this);
        DMG=0;
        
    }
    public EntidadCombate(String ruta,String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre, int a) throws SlickException {
        super(ruta, h, w, numAnimaciones, nombre);
        combate=new Entidad(rutaC, 65, 65, numC, nombre+" de Combate") {};
        combate.animaciones(frames, 250, genLimited(numC));
        lucha=false;
        magias=new ArrayList();
        setAnimacionCombate(0);
        est=new int[8];
        multiplicadores=new int [8];
        estb=new int [8];
        luest=new int[8];
        daño=new Sound("resources/sonido/HitDamage.ogg");
        dañar=new Sound("resources/sonido/combate/fisica_flecha_o_lanzar.ogg");
        animdañar=2;
        animbaseco=1;
        animest=0;
        animmag=1;
        barraV=new HealthBar(this);
        barraM=new ManaBar(this);
        DMG=0;
        
    }

    public EntidadCombate(String ruta,String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre, int wc, int hc) throws SlickException {
        super(ruta, h, w, numAnimaciones, nombre);
        combate=new Entidad(rutaC, hc, wc, numC, nombre+" de Combate") {};
        combate.animaciones(frames, 250, genLimited(numC));
        lucha=false;
        magias=new ArrayList();
        setAnimacionCombate(0);
         est=new int[8];
        multiplicadores=new int [8];
        estb=new int [8];
        luest=new int[8];
        daño=new Sound("resources/sonido/HitDamage.ogg");
        dañar=new Sound("resources/sonido/combate/fisica_flecha_o_lanzar.ogg");
        animdañar=2;
        animbaseco=1;
        animest=0;
        animmag=1;
        barraV=new HealthBar(this);
        barraM=new ManaBar(this);
        DMG=0;
        
    }
    

    public EntidadCombate(String ruta,String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre, int wc, int hc, boolean[] limited) throws SlickException {
        super(ruta, h, w, numAnimaciones, nombre);
        combate=new Entidad(rutaC, hc, wc, numC, nombre+" de Combate") {};
        combate.animaciones(frames, 250, limited);
        lucha=false;
        magias=new ArrayList();
        setAnimacionCombate(0);
         est=new int[8];
        multiplicadores=new int [8];
        estb=new int [8];
        luest=new int[8];
        daño=new Sound("resources/sonido/HitDamage.ogg");
        dañar=new Sound("resources/sonido/combate/fisica_flecha_o_lanzar.ogg");
        animdañar=2;
        animbaseco=1;
        animest=0;
        animmag=1;
        barraV=new HealthBar(this);
        barraM=new ManaBar(this);
        DMG=0;
    }
    
    
    
    public void draw(){
        if (lucha){
            if(combate.getAnimacion().isStopped()){
                combate.getAnimacion().restart();
                combate.setAnimacion(animest);
                
            }
            combate.drawCombate();
        }
        else super.draw();
    }
    
    public void drawC(GameContainer c,Graphics g){
        
            if(combate.getAnimacion().isStopped()){
                combate.getAnimacion().restart();
                combate.setAnimacion(animest); 
            }
            barraV.render(c, g);
            barraM.render(c, g);
//            g.draw(new Rectangle( combate.getPosicion().getX()+5, combate.getPosicion().getY()+64, 65,20));
//            g.drawString(multiplicadores[0]+"/"+est[0], combate.getPosicion().getX()+5, combate.getPosicion().getY()+64);
            
            combate.drawCombate();
        
       
    }
    

    public void setLucha(boolean lucha) {
        this.lucha = lucha;
    }
    
    public void setPosCombate(Punto p){
        combate.setPosicion(p);
    }
    
    public Punto getPosCombate(){
        return combate.getPosicion();
    }
    
    public void combatir(){
        lucha=true;
    }
    public void caminar(){
        lucha=false;
    }
    
    public void setAnimacionCombate(int i){
        combate.setAnimacion(i);
    }
    
    public void recivirDañoMagico(int dmg){
        multiplicadores[0]=multiplicadores[0]-(int)((double)dmg+0.7*(double)est[3]);
        if(multiplicadores[0]<0){
            multiplicadores[0]=0;
        }
        combate.setAnimacion(animdañar);
        daño.play(1, (float) 0.5);
    }
    
    public int ataqueBasico(){
        combate.setAnimacion(animbaseco);
        dañar.play(1, (float) 0.5 );
        return DMG;
    }
    
    public boolean[] genLimited(int anims){
        boolean[] limited= new boolean[anims];
        limited[0]=true;
        for(int i=1;i<anims;i++){
            limited[i]=false;
        }
        return limited;
    }
    
    public void aprenderMagia(Magia magia){
        magias.add(magia);
    }

    public ArrayList<Magia> getMagias() {
        return magias;
    }
    
    public void estadisticasb(int [] a){
        for(int i=0;i<estb.length;i++){
            estb[i]=a[i];
        }
        LVL=a[8];
        for(int i=9;i<estb.length+9;i++){
            luest[i-9]=a[i];
        }
        
        calcEst();
    }
    
    public void calcEst(){
            for(int i=0;i<est.length-1;i++){
                est[i]=estb[i]+luest[i]*LVL;
                multiplicadores[i]=1;
            }
            est[0]=(int) (est[0]+est[2]*0.7);
            est[5]=(int) (0.7*(est[6]+est[2]));
            est[4]=est[4]+LVL*10;
            est[6]=(int) (est[6]+est[4]*0.7);
            multiplicadores[0]=est[0];
            multiplicadores[1]=est[1];
            est[7]=(int) (estb[7]+0.9*(0.7*est[4]));
            DMG=(int) (0.9*(0.3*(est[2]+est[4])));
            EXPN=(int) (150*1.33*LVL);
        }
    
    public int getAnimId(){
            return combate.getAnimid();
        }

    public int[] getMultiplicadores() {
        return multiplicadores;
    }

    public Sound getDañado() {
        return daño;
    }

    public Sound getDañar() {
        return dañar;
    }

    public void setDañar(Sound dañar) {
        this.dañar = dañar;
    }
        
    public int hacerMagia(int indice, EntidadCombate objetivo){
        setAnimacionCombate(2);
        int da=magias.get(indice).usar(this, objetivo);
        float ml=(int) (magias.get(indice).getElemento().mult(objetivo.getElemento()));
        da=(int) ((int) (da+est[4]*0.9)*ml);
        return da;
    }
    
    public boolean drawMag(int a){
        boolean b;
        magias.get(a).draw();
        if(magias.get(a).getAnim().isStopped()){
            b=true;
            
        }else b=false;
        return b;
    }

    public int[] getEst() {
        return est;
    }
    
    public ArrayList<String> getMagiasStrg(){
        ArrayList<String> a=new ArrayList();
        for(int i=0;i<getMagias().size();i++){
            a.add(getMagias().get(i).toString());
            
        }
        return a;
    }
    
    public void usarObjeto(){
        setAnimacionCombate(1);
    }
    public void curarV(int a){
        System.out.println("Vida Curada");
            multiplicadores[0]=multiplicadores[0]+a;
            if(multiplicadores[0]>est[0]) multiplicadores[0]=est[0];
    }
    public void curarM(int a){
        System.out.println("Mana Recuperado");
        multiplicadores[1]=multiplicadores[1]+a;
        if(multiplicadores[1]>est[1]) multiplicadores[1]=est[1];
    }

    public void setAnimdañar(int animdañar) {
        this.animdañar = animdañar;
    }

    public void setAnimbaseco(int animbaseco) {
        this.animbaseco = animbaseco;
    }

    public void setAnimest(int animest) {
        this.animest = animest;
    }

    public void setAnimmag(int animmag) {
        this.animmag = animmag;
    }

    public Elemento getElemento() {
        return elemento;
    }
    
    public Animation getAnimCom(){
        return combate.getAnimacion();
    }

    public int getAnimest() {
        return animest;
    }

    public int getAnimdañar() {
        return animdañar;
    }

    public int getAnimbaseco() {
        return animbaseco;
    }

    public int getAnimmag() {
        return animmag;
    }
    
    public void playDaño(){
         daño.play(1, (float) 0.5);
    }
    
    public void setMult(int a, int b){
        multiplicadores[a]=b;
    }
    
    
    
    public void recivirDañoFisico(int dmg, EntidadCombate en){
        getMultiplicadores()[0]=(int) (getMultiplicadores()[0]-((int)((double)dmg+0.7*(double)getEst()[3])));
        if(getMultiplicadores()[0]<0){
            setMult(0, 0);
        }
       setAnimacionCombate(getAnimdañar());
       playDaño();
    }

    public int getTieneGema() {
        return tieneGema;
    }

    public void setTieneGema(int tieneGema) {
        this.tieneGema = tieneGema;
    }
    
    
    public Elemento getElementoDef(){
        return elemento;
    }

    public void setTieneGemaD(int tieneGemaD) {
        this.tieneGemaD = tieneGemaD;
    }

    public int getTieneGemaD() {
        return tieneGemaD;
    }

    public int getEXPN() {
        return EXPN;
    }

    public void setEXPN(int EXPN) {
        this.EXPN = EXPN;
    }

    public int getEXPA() {
        return EXPA;
    }

    public void setEXPA(int EXPA) {
        this.EXPA = EXPA;
    }
    
    public ArrayList<String> ganarExperiencia(int i){
        ArrayList<String> a=new ArrayList();
        EXPA=EXPA+i;
        if(EXPA>EXPN){
            a=subirNivel();
        }
        return a;
    }
    
    public ArrayList<String> subirNivel(){
        ArrayList<String> a=new ArrayList();
        ArrayList<String> b=new ArrayList();
        a=toStringEst();
        LVL++;
        calcEst();
        b=toStringEst();
        
        for(int i=0;i<a.size()-1;i++){
            a.set(i, a.get(i)+"  -->  "+b.get(i));
        }
        
        EXPN=(int)(EXPN*1.33);
        
        return a;
    }

    public int getLVL() {
        return LVL;
    }
    
    public ArrayList<String> toStringEst(){
        ArrayList<String> a=new ArrayList();
        a.add("LVL: "+LVL);
        a.add("HP: "+est[0]);
        a.add("MP: "+est[1]);
        a.add("STR: "+est[2]);
        a.add("DEF: "+est[3]);
        a.add("INT: "+est[4]);
        a.add("SPD: "+est[7]);
        a.add(toString());
        return a;
    }
    
   }

