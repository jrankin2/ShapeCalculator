package controllers;

/**
 * @author Joe
 */
public enum Shapes {
    RECTANGLE(0), CIRCLE(1), TRIANGLE(2);
    
    private final int value;
    Shapes(int value){
        this.value = value;
    }
    
    public int Value(){
        return value;
    }
    
    public static Shapes toShape(int value){
        for(Shapes shape : Shapes.values()){
            if(shape.Value() == value){
                return shape;
            }
        }
        return null;
    }
}
