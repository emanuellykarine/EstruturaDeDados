package AVL;

public interface AVLInterface{
    public void rotation(NoAVL a, NoAVL p); 
    public void newFBIntersection(NoAVL n);
    public void newFBRemoval(NoAVL n);
    public void simpleLeftRotation(NoAVL n, NoAVL a);
    public void simpleRightRotation(NoAVL n, NoAVL a);
}