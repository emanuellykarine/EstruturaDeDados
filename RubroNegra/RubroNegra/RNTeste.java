public class RNTeste {
    public static void main(String[] args){

        System.out.println("-------------1 arvore -------------");
        

        RNClasse rn = new RNClasse(10);
        rn.addChild(5);
        rn.addChild(15);
        rn.addChild(2);
        rn.addChild(8);
        rn.addChild(22);

        rn.printArvore();
        System.out.println();

        rn.addChild(25);
        rn.printArvore();
        System.out.println();

        rn.remove(5);
        rn.printArvore();
        System.out.println();


        System.out.println("-----------2--------------");
        RNClasse rn2 = new RNClasse(50);
        rn2.addChild(20);
        rn2.printArvore();
        System.out.println();
        rn2.addChild(90);
        rn2.printArvore();
        System.out.println();
        rn2.addChild(10);
        rn2.printArvore();
        System.out.println();
        rn2.addChild(40);

        rn2.printArvore();
        System.out.println();
        rn2.addChild(30);
    
        rn2.printArvore();
        System.out.println();

        rn2.remove(40);
        rn2.printArvore();
        System.out.println();

        System.out.println("----------3--------------");
        RNClasse rn3 = new RNClasse(50);
        rn3.addChild(20);
        rn3.addChild(80);
        rn3.addChild(70);
        rn3.addChild(90);

        rn3.printArvore();
        System.out.println();
        rn3.addChild(60);
    
        rn3.printArvore();

        System.out.println("-------------4--------------");
        RNClasse rn4 = new RNClasse(10);
        rn4.addChild(20);
        rn4.printArvore();
        rn4.addChild(30);
        rn4.printArvore();
        System.out.println();

        rn4.addChild(80);
        rn4.printArvore();
        System.out.println();
        
        rn4.addChild(70);
        rn4.printArvore();
        System.out.println();

        rn4.addChild(40);
        rn4.printArvore();
        System.out.println();

        rn4.remove(40);
        rn4.printArvore();
        System.out.println();

        rn4.remove(70);
        rn4.printArvore();
        System.out.println();

        rn4.remove(80);
        rn4.printArvore();
        System.out.println();

        System.out.println("--------------5-------------");
        RNClasse rn5 = new RNClasse(10);
        rn5.addChild(20);
        rn5.addChild(30);
        rn5.printArvore();
        System.out.println();

        rn5.addChild(80);
        rn5.printArvore();
        System.out.println();
        
        rn5.addChild(70);
        rn5.printArvore();
        System.out.println();

        rn5.addChild(40);
        rn5.printArvore();
        System.out.println();

        rn5.addChild(25);
        rn5.printArvore();
        System.out.println();

        rn5.remove(10);
        rn5.printArvore();
        System.out.println();

        rn5.remove(25);
        rn5.printArvore();
        System.out.println();
        
        System.out.println("--------------6-------------");
        RNClasse rn6 = new RNClasse(1);
        rn6.addChild(2);
        rn6.addChild(3);
        rn6.printArvore();
        System.out.println();

        rn6.addChild(4);
        rn6.printArvore();
        System.out.println();
        
        rn6.addChild(5);
        rn6.printArvore();
        System.out.println();

        rn6.addChild(6);
        rn6.printArvore();
        System.out.println();

        rn6.remove(3);
        rn6.printArvore();
        System.out.println();

        rn6.addChild(7);
        rn6.printArvore();
        System.out.println();

        rn6.addChild(8);
        rn6.printArvore();
        System.out.println();

        rn6.addChild(9);
        rn6.printArvore();
        System.out.println();

        System.out.println("--------------7-------------");
        RNClasse rn7 = new RNClasse(9);
        rn7.addChild(8);
        rn7.addChild(7);
        rn7.printArvore();
        System.out.println();

        rn7.addChild(6);
        rn7.printArvore();
        System.out.println();
        
        rn7.addChild(5);
        rn7.printArvore();
        System.out.println();

        rn7.addChild(4);
        rn7.printArvore();
        System.out.println();

        rn7.addChild(3);
        rn7.printArvore();
        System.out.println();

        rn7.addChild(2);
        rn7.printArvore();
        System.out.println();

        rn7.addChild(1);
        rn7.printArvore();
        System.out.println();
    
        System.out.println("--------------8-------------");
        RNClasse rn8 = new RNClasse(10);
        rn8.addChild(12);
        rn8.addChild(8);
        rn8.printArvore();
        System.out.println();

        rn8.addChild(7);
        rn8.printArvore();
        System.out.println();
        
        rn8.remove(12);
        rn8.printArvore();
        System.out.println();

        System.out.println("--------------9-------------");
        RNClasse rn9 = new RNClasse(10);
        rn9.addChild(12);
        rn9.addChild(8);
        rn9.printArvore();
        System.out.println();

        rn9.addChild(9);
        rn9.printArvore();
        System.out.println();
        
        rn9.remove(12);
        rn9.printArvore();
        System.out.println();

        System.out.println("--------------10-------------");
        RNClasse rn10 = new RNClasse(10);
        rn10.addChild(12);
        rn10.addChild(8);
        rn10.printArvore();
        System.out.println();

        rn10.addChild(13);
        rn10.printArvore();
        System.out.println();
        
        rn10.remove(8);
        rn10.printArvore();
        System.out.println();

        System.out.println("--------------11-------------");
        RNClasse rn11 = new RNClasse(10);
        rn11.addChild(12);
        rn11.addChild(8);
        rn11.printArvore();
        System.out.println();

        rn11.addChild(11);
        rn11.printArvore();
        System.out.println();
        
        rn11.remove(8);
        rn11.printArvore();
        System.out.println();

        System.out.println("--------------12-------------");
        RNClasse rn12 = new RNClasse(7);
        rn12.addChild(5);
        rn12.addChild(9);
        rn12.printArvore();
        System.out.println();

        rn12.addChild(8);
        rn12.printArvore();
        System.out.println();

        rn12.addChild(10);
        rn12.printArvore();
        System.out.println();

        rn12.addChild(15);
        rn12.printArvore();
        System.out.println();
        
        rn12.remove(5);
        rn12.printArvore();
        System.out.println();

        System.out.println("--------------13-------------");
        RNClasse rn13 = new RNClasse(20);
        rn13.addChild(25);
        rn13.addChild(15);
        rn13.printArvore();
        System.out.println();

        rn13.addChild(14);
        rn13.printArvore();
        System.out.println();

        rn13.addChild(17);
        rn13.printArvore();
        System.out.println();

        rn13.addChild(13);
        rn13.printArvore();
        System.out.println();
        
        rn13.remove(25);
        rn13.printArvore();
        System.out.println();

        System.out.println("--------------14-------------");
        RNClasse rn14 = new RNClasse(10);
        rn14.addChild(5);
        rn14.addChild(12);
        rn14.printArvore();
        System.out.println();

        rn14.addChild(1);
        rn14.printArvore();
        System.out.println();

        rn14.addChild(6);
        rn14.printArvore();
        System.out.println();

        rn14.addChild(7);
        rn14.printArvore();
        System.out.println();
        
        rn14.remove(12);
        rn14.printArvore();
        System.out.println();
    }
}
